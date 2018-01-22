package com.snappenio.mitosis.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SnapRequest {

    @JsonProperty("codepen_url")
    private String codepenUrl;

    public SnapRequest() {}

    public String getCodepenUrl() {
        return codepenUrl;
    }
    public void setCodepenUrl(String codepenUrl) {
        this.codepenUrl = codepenUrl;
    }

}
