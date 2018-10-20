package com.cssc.eos.api;


import com.cssc.eos.Eos;
import com.cssc.eos.crypto.model.chain.SignedTransaction;
import com.cssc.eos.entity.*;
import com.cssc.eos.entity.account.Account;
import com.cssc.eos.entity.code.Code;
import com.cssc.eos.entity.req.AbiJsonToBinRequest;
import com.cssc.eos.entity.sign.PushedTransaction;
import com.cssc.eos.entity.transaction.PushBlockRequest;
import com.cssc.eos.entity.transaction.PushTransactionRequest;
import com.cssc.eos.entity.transaction.RequiredKeysRequest;
import com.cssc.eos.entity.transaction.SignedPackedTransaction;
import com.cssc.eos.util.Utils;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

public class EosApiRestClientImpl implements Eos {

    private final EosApiService eosApiService;

    public EosApiRestClientImpl(String baseUrl) {
        eosApiService = EosApiServiceGenerator.createService(EosApiService.class, baseUrl);
    }

    @Override
    public JsonNode getAbi(Map<String, String> requestField) {
        return EosApiServiceGenerator.executeSync(eosApiService.getAbi(requestField));
    }

    @Override
    public ChainInfo getChainInfo() {
        return EosApiServiceGenerator.executeSync(eosApiService.getChainInfo());
    }

    @Override
    public Block getBlock(String blockNumberOrId) {
        return EosApiServiceGenerator.executeSync(eosApiService.getBlock(Collections.singletonMap("block_num_or_id", blockNumberOrId)));
    }

    @Override
    public Map getAccount(String accountName) {
        return EosApiServiceGenerator.executeSync(eosApiService.getAccount(Collections.singletonMap("account_name", accountName)));
    }

    @Override
    public Code getCode(String accountName) {
        return EosApiServiceGenerator.executeSync(eosApiService.getCode(Collections.singletonMap("account_name", accountName)));
    }

    @Override
    public TableRow getTableRows(String scope, String code, String table) {
        Map<String, String> requestParameters = new HashMap<>(7);

        requestParameters.put("scope", scope);
        requestParameters.put("code", code);
        requestParameters.put("table", table);
        requestParameters.put("json", "true");
        requestParameters.put("lower_bound", "0");
        requestParameters.put("upper_bound", "-1");
        requestParameters.put("limit", "10");

        return EosApiServiceGenerator.executeSync(eosApiService.getTableRows(requestParameters));
    }

    @Override
    public AbiBinToJson abiBinToJson(String code, String action, String binargs) {
        LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(3);

        requestParameters.put("code", code);
        requestParameters.put("action", action);
        requestParameters.put("binargs", binargs);

        return EosApiServiceGenerator.executeSync(eosApiService.abiBinToJson(requestParameters));
    }

    @Override
    public AbiJsonToBin abiJsonToBin(String code, String action, Map<String, Object> args) {
        AbiJsonToBinRequest abiJsonToBinRequest= new AbiJsonToBinRequest(code, action, args);
        System.out.println("abiJsonToBinRequest :"+ Utils.toJson(abiJsonToBinRequest));
        return EosApiServiceGenerator.executeSync(eosApiService.abiJsonToBin(abiJsonToBinRequest));
    }

    @Override
    public PushedTransaction pushTransaction(String compression, SignedPackedTransaction packedTransaction) {
        PushTransactionRequest request = new PushTransactionRequest(compression, packedTransaction, packedTransaction.getSignatures());
        return EosApiServiceGenerator.executeSync(eosApiService.pushTransaction(request));
    }

    @Override
    public JsonNode pushTransaction(com.cssc.eos.crypto.model.chain.PackedTransaction body) {
        return EosApiServiceGenerator.executeSync(eosApiService.pushTransaction(body));
    }

    @Override
    public List<PushedTransaction> pushTransactions(List<PushTransactionRequest> pushTransactionRequests) {
        return EosApiServiceGenerator.executeSync(eosApiService.pushTransactions(pushTransactionRequests));
    }

    @Override
    public RequiredKeys getRequiredKeys(SignedTransaction transaction, List<String> keys) {
        RequiredKeysRequest requiredKeysRequest= new RequiredKeysRequest(transaction, keys);
        System.out.println("transaction = [" + Utils.toJson(requiredKeysRequest)+"]");
        return EosApiServiceGenerator.executeSync(eosApiService.getRequiredKeys(requiredKeysRequest));
    }


    @Override
    public void pushBlock(PushBlockRequest pushBlockRequest) {
        EosApiServiceGenerator.executeSync(eosApiService.pushBlock(pushBlockRequest));
    }

}
