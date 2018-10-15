package com.cssc.eos.api;

import com.cssc.eos.Eos;
import com.cssc.eos.EosHelpRpc;
import com.cssc.eos.crypto.ec.EosPrivateKey;
import com.cssc.eos.crypto.model.api.EosChainInfo;
import com.cssc.eos.crypto.model.chain.Action;
import com.cssc.eos.crypto.model.chain.SignedTransaction;
import com.cssc.eos.crypto.model.types.TypeChainId;
import com.cssc.eos.entity.AbiJsonToBin;
import com.cssc.eos.entity.ChainInfo;
import com.cssc.eos.entity.RequiredKeys;
import com.cssc.eos.entity.TableRow;
import com.cssc.eos.entity.transaction.Account;
import com.cssc.eos.util.LLogger;
import com.cssc.eos.util.LLoggerFactory;
import com.cssc.eos.util.Utils;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EosHelpRpcImpl implements EosHelpRpc {
    private LLogger lLogger = LLoggerFactory.getLogger(EosHelpRpcImpl.class);
    private Eos eos =null;
    public EosHelpRpcImpl(Eos eos){
        this.eos=eos;
    }
    @Override
    public String transfer(Map<String,String> keys, String from, String to, String amount, String contract,String memo) {
        String action = "transfer";
        Map<String, Object> params = new HashMap<>(4);
        params.put("from", from);
        params.put("to", to);
        params.put("quantity", amount);
        params.put("memo", memo);
        return packedTransaction(keys, params, from, action, contract,true );
    }

    @Override
    public String buyRam(Map<String,String> keys, String payer, String receiver, String amount) {
        String action = "buyram";
        Map<String, Object> params = new HashMap<>(4);
        params.put("payer", payer);
        params.put("receiver", receiver);
        params.put("quant", amount);
        return packedTransaction(keys, params, payer, action, "eosio",true );
    }

    @Override
    public String sellRam(Map<String,String> keys, String to, String amount) {
        String action = "sellram";
        Map<String, Object> params = new HashMap<>(4);
        params.put("account", to);
        params.put("bytes", Integer.valueOf(amount));
        return packedTransaction(keys, params, to, action, "eosio",true );
    }

    @Override
    public String ramMarket() {
        TableRow tableRow= eos.getTableRows("eosio","eosio","rammarket");
        return Utils.toJson(tableRow);
    }

    @Override
    public String createAsset(Map<String,String> keys,String account, String asset, String eos, String fee, String transferbool) {
        String action = "create";
        Map<String, Object> params = new HashMap<>(4);
        params.put("issuer", account);
        params.put("maximum_supply", asset);
        params.put("exchange_base",eos);
        params.put("fee_amount",Integer.valueOf(fee));
        params.put("open_transfer",Integer.valueOf(transferbool));
        return packedTransaction(keys, params, account, action, "bancor.token",true );
    }

    @Override
    public String exchangeBuy(Map<String,String> keys,String amount, String eos, String asset, String feeAmont) {
        String action = "buy";
        Map<String, Object> params = new HashMap<>(4);
        params.put("from", amount);
        params.put("quant", eos);
        params.put("symbol",asset);
        params.put("feeto",feeAmont);
        return packedTransaction(keys, params, amount, action, "bancor.token",true );
    }

    @Override
    public String exchangeSell(Map<String,String> keys,String amount, String asset, String eos, String feeAmont) {
        String action = "sell";
        Map<String, Object> params = new HashMap<>(4);
        params.put("from", amount);
        params.put("quant", asset);
        params.put("symbol",eos);
        params.put("feeto",feeAmont);
        return packedTransaction(keys, params, amount, action, "bancor.token",true );
    }
    @Override
    public String accountAuthority(Map<String,String> keys, String accoun, String publicKey, String code) {
        String action = "updateauth";
        Map<String,Object> params=new HashMap<>();
        Map<String, Object> auth = new HashMap<>(4);
        com.cssc.eos.entity.transaction.Permission permissions=new com.cssc.eos.entity.transaction.Permission();
        permissions.setActor(code);
        permissions.setPermission("eosio.code");
        Account account=new Account();
        account.setPermission(permissions);
        account.setWeight(1);
        List<Account> accounts=new ArrayList<>();
        accounts.add(account);
        Map<String, Object> pubKey = new HashMap<>(4);
        pubKey.put("key",publicKey);
        pubKey.put("weight",1);
        List<Map> pubKeys=new ArrayList<>();
        pubKeys.add(pubKey);
        auth.put("threshold", 1);
        auth.put("keys", pubKeys);
        auth.put("accounts",accounts);
        auth.put("waits",new ArrayList<>());
        params.put("account",accoun);
        params.put("permission","active");
        params.put("parent","owner");
        params.put("auth",auth);
        return packedTransaction(keys, params, accoun, action, "eosio",false );
    }

    @Override
    public String selectExchange(String pair) {
        TableRow tableRow= eos.getTableRows( pair,"bancor.token","tokenmarket");
        return Utils.toJson(tableRow);
    }
    @Override
    public String selectAsset(String amount) {
        TableRow tableRow= eos.getTableRows( amount,"bancor.token","accounts");
        return Utils.toJson(tableRow);
    }

    @Override
    public String selectExBuyStat(String asset) {
        TableRow tableRow= eos.getTableRows( asset,"bancor.token","stat");
        return Utils.toJson(tableRow);
    }

    @Override
    public String delegatebw(Map<String, String> keys, String form, String receiver, String assetNet, String assetCpu) {
        String action = "delegatebw";
        Map<String,Object> params=new HashMap<>();
        params.put("from",form);
        params.put("receiver",receiver);
        params.put("stake_net_quantity",assetNet);
        params.put("stake_cpu_quantity",assetCpu);
        params.put("transfer",1);
        return packedTransaction(keys, params, form, action, "eosio",true );
    }

    @Override
    public String undelegatebw(Map<String, String> keys, String form, String receiver, String assetNet, String assetCpu) {
        String action="undelegatebw";
        Map<String,Object> params=new HashMap<>();
        params.put("from",form);
        params.put("receiver",receiver);
        params.put("unstake_net_quantity",assetNet);
        params.put("unstake_cpu_quantity",assetCpu);
        return packedTransaction(keys, params, form, action, "eosio",true );
    }

    private String packedTransaction(Map<String,String> keys, Map<String, Object> params, String activer, String action, String contract,Boolean activeOrOwner) {
        ChainInfo chainInfo = eos.getChainInfo();

        TypeChainId chainId = new TypeChainId(chainInfo.getChainId());
        AbiJsonToBin data = eos.abiJsonToBin(contract, action, params);

        lLogger.info("data: {}", Utils.toJson(data));
        EosChainInfo eosChainInfo = new EosChainInfo();
        eosChainInfo.setHeadBlockTime(chainInfo.getHeadBlockTime());
        eosChainInfo.setHeadBlockId(chainInfo.getHeadBlockId());

        SignedTransaction transaction =null;
        if (activeOrOwner){
            transaction= createTransaction(contract, action, data.getBinargs(), getActivePermission(activer), eosChainInfo);
        }else{
            transaction= createTransaction(contract, action, data.getBinargs(), getOwnerPermission(activer), eosChainInfo);
        }
        RequiredKeys requiredKeys= eos.getRequiredKeys(transaction, new ArrayList(keys.keySet()));
        lLogger.info("trx requiredKeys: {}", requiredKeys.getRequiredKeys().get(0));
        System.out.println("trx requiredKeys: "+keys.get(requiredKeys.getRequiredKeys().get(0)));;
        EosPrivateKey eosPrivateKey = new EosPrivateKey(keys.get(requiredKeys.getRequiredKeys().get(0)));
        transaction = signTransaction(transaction, eosPrivateKey, chainId);
        System.out.println("trx request: "+ Utils.toJson(transaction));
        com.cssc.eos.crypto.model.chain.PackedTransaction packedTransaction = buildTransaction(transaction);
        lLogger.info("trx request: {}", Utils.toJson(packedTransaction));
        System.out.println("trx request: "+ Utils.toJson(packedTransaction));
        JsonNode pushTxnResponse = eos.pushTransaction(packedTransaction);
        lLogger.info("packed: {}", Utils.toJsonByGson(pushTxnResponse));
        if (pushTxnResponse != null) {
            JsonNode transaction_id = pushTxnResponse.findValue("transaction_id");
            JsonNode result = transaction_id.findValue("_value");
            if (result != null) {
                lLogger.info("txId: {}", result.asText());
                return result.asText();
            } else {
                lLogger.info("txId: {}", transaction_id.asText());
                return transaction_id.asText();
            }
        }
        return null;
    }


    public com.cssc.eos.crypto.model.chain.PackedTransaction buildTransaction(SignedTransaction stxn) {
        return new com.cssc.eos.crypto.model.chain.PackedTransaction(stxn);
    }

    public SignedTransaction signTransaction(final SignedTransaction txn,
                                             EosPrivateKey privateKey, final TypeChainId id) throws IllegalStateException {
        SignedTransaction stxn = new SignedTransaction(txn);
        stxn.sign(privateKey, id);
        return stxn;
    }

    private SignedTransaction createTransaction(String contract, String actionName, String dataAsHex,
                                                String[] permissions, EosChainInfo chainInfo) {
        Action action = new Action(contract, actionName);
        action.setAuthorization(permissions);
        action.setData(dataAsHex);


        SignedTransaction txn = new SignedTransaction();
        txn.addAction(action);
        txn.setSignatures(new ArrayList<String>());


        if (null != chainInfo) {
            txn.setReferenceBlock(chainInfo.getHeadBlockId());
            txn.setExpiration(chainInfo.getTimeAfterHeadBlockTime(30000));
        }
        lLogger.info("rawTransaction: {}", txn);
        return txn;
    }

    private String[] getActivePermission(String accountName) {
        return new String[]{accountName + "@active"};
    }

    private String[] getOwnerPermission(String accountName) {
        return new String[]{accountName + "@owner"};
    }
}
