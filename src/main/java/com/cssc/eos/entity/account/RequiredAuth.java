package com.cssc.eos.entity.account;

import java.util.List;

public class RequiredAuth {

    private List<String> accounts;

    private List<Key> keys;

    private String threshold;

    private List waits;

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public List getWaits() {
        return waits;
    }

    public void setWaits(List waits) {
        this.waits = waits;
    }
}
