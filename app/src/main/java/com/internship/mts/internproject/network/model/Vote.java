package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class Vote {
    @SerializedName("dealId")
    private int dealId;

    @SerializedName("hot")
    private boolean hot;

    public Vote(int dealId, boolean hot) {
        this.dealId = dealId;
        this.hot = hot;
    }
}
