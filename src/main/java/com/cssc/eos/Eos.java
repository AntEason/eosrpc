package com.cssc.eos;

import com.cssc.eos.crypto.model.chain.SignedTransaction;
import com.cssc.eos.entity.*;
import com.cssc.eos.entity.account.Account;
import com.cssc.eos.entity.code.Code;
import com.cssc.eos.entity.sign.PushedTransaction;
import com.cssc.eos.entity.transaction.PushBlockRequest;
import com.cssc.eos.entity.transaction.PushTransactionRequest;
import com.cssc.eos.entity.transaction.SignedPackedTransaction;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

public interface Eos {
    JsonNode getAbi(Map<String, String> requestField);

    ChainInfo getChainInfo();

    Block getBlock(String blockNumberOrId);

    Map getAccount(String accountName);

    Code getCode(String accountName);

    TableRow getTableRows(String scope, String code, String table);

    AbiBinToJson abiBinToJson(String code, String action, String binargs);

    AbiJsonToBin abiJsonToBin(String code, String action, Map<String, Object> args);

    PushedTransaction pushTransaction(String compression, SignedPackedTransaction packedTransaction);

    public JsonNode pushTransaction(com.cssc.eos.crypto.model.chain.PackedTransaction body);

    List<PushedTransaction> pushTransactions(List<PushTransactionRequest> pushTransactionRequests);

    RequiredKeys getRequiredKeys(SignedTransaction transaction, List<String> keys);

    void pushBlock(PushBlockRequest pushBlockRequest);

}
