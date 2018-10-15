package com.cssc.eos.entity.transaction;

import com.cssc.eos.crypto.model.chain.SignedTransaction;

import java.util.List;

public class RequiredKeysRequest {

    private SignedTransaction transaction;

    private List<String> available_keys;

    public RequiredKeysRequest(SignedTransaction transaction, List<String> availableKeys){
        this.transaction = transaction;
        this.available_keys = availableKeys;
    }

    public SignedTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(SignedTransaction transaction) {
        this.transaction = transaction;
    }

    public List<String> getAvailable_keys() {
        return available_keys;
    }

    public void setAvailable_keys(List<String> available_keys) {
        this.available_keys = available_keys;
    }
}
