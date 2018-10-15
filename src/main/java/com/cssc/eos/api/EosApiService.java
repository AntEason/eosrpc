package com.cssc.eos.api;

import com.cssc.eos.entity.*;
import com.cssc.eos.entity.account.Account;
import com.cssc.eos.entity.code.Code;
import com.cssc.eos.entity.req.AbiJsonToBinRequest;
import com.cssc.eos.entity.sign.PushedTransaction;
import com.cssc.eos.entity.transaction.PushBlockRequest;
import com.cssc.eos.entity.transaction.PushTransactionRequest;
import com.cssc.eos.entity.transaction.RequiredKeysRequest;
import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;
import java.util.Map;

public interface EosApiService {

    @GET("/v1/chain/get_info")
    Call<ChainInfo> getChainInfo();

    @POST("/v1/chain/get_abi")
    Call<JsonNode> getAbi(@Body Map<String, String> requestField);

    @POST("/v1/chain/get_block")
    Call<Block> getBlock(@Body Map<String, String> requestFields);

    @POST("/v1/chain/get_account")
    Call<Map> getAccount(@Body Map<String, String> requestFields);

    @POST("/v1/chain/get_code")
    Call<Code> getCode(@Body Map<String, String> requestFields);

    @POST("/v1/chain/get_table_rows")
    Call<TableRow> getTableRows(@Body Map<String, String> requestFields);

    @POST("/v1/chain/abi_json_to_bin")
    Call<AbiJsonToBin> abiJsonToBin(@Body AbiJsonToBinRequest abiJsonToBinRequest);

    @POST("/v1/chain/abi_bin_to_json")
    Call<AbiBinToJson> abiBinToJson(@Body Map<String, String> requestFields);

    @POST("/v1/chain/push_transaction")
    Call<PushedTransaction> pushTransaction(@Body PushTransactionRequest pushTransactionRequest);

    @POST("/v1/chain/push_transaction")
    Call<JsonNode> pushTransaction(@Body com.cssc.eos.crypto.model.chain.PackedTransaction body);

    @POST("/v1/chain/push_transactions")
    Call<List<PushedTransaction>> pushTransactions(@Body List<PushTransactionRequest> pushTransactionRequests);

    @POST("/v1/chain/get_required_keys")
    Call<RequiredKeys> getRequiredKeys(@Body RequiredKeysRequest requiredKeysRequest);

    @POST("/v1/chain/push_test_block")
    Call<Void> pushBlock(@Body PushBlockRequest blockRequest);
}
