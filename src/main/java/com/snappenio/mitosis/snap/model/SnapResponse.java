package com.snappenio.mitosis.snap.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SnapResponse {

    @JsonProperty("snap_id")
    private String snapId;

    public SnapResponse(String snapId) {
        this.snapId = snapId;
    }

    public String getSnapId() {
        return snapId;
    }
    public void setSnapId(String snapId) {
        this.snapId = snapId;
    }

}
