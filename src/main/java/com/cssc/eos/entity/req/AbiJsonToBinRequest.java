package com.cssc.eos.entity.req;

import java.util.Map;

public class AbiJsonToBinRequest {

    private String code;

    private String action;

    private Map<String, Object> args;

    public AbiJsonToBinRequest(String code, String action, Map<String, Object> args) {
        this.code = code;
        this.action = action;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }
}
