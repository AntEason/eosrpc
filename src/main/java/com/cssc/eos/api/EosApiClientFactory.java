package com.cssc.eos.api;

import com.cssc.eos.Eos;

public class EosApiClientFactory {

    private String baseUrl;

    private EosApiClientFactory(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public static EosApiClientFactory newInstance(String baseUrl) {
        return new EosApiClientFactory(baseUrl);
    }


    public Eos newRestClient() {
        return new EosApiRestClientImpl(baseUrl);
    }

}
