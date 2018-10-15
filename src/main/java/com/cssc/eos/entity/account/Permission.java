package com.cssc.eos.entity.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Permission {

    private String name;

    private String parent;

    private List<RequiredAuth> required_auth;

    public Permission(){

    }
    @JsonProperty("perm_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("parent")
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<RequiredAuth> getRequired_auth() {
        return required_auth;
    }

    public void setRequired_auth(List<RequiredAuth> required_auth) {
        this.required_auth = required_auth;
    }
}
