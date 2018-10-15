package com.cssc.eos.crypto.model.api;

import com.cssc.eos.crypto.digest.Sha256;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by swapnibble on 2018-01-10.
 */

public class GetCodeResponse {
    @Expose
    private String account_name;

    @Expose
    private String wast;

    @Expose
    private String code_hash;

    @Expose
    private JsonObject abi;

    public JsonObject getAbi() { return abi; }

    public boolean isValidCode() {
        return ! ( StringUtils.isEmpty(code_hash) || Sha256.ZERO_HASH.toString().equals( code_hash ));
    }
}
