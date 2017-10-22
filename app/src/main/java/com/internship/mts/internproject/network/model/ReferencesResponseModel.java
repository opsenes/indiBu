package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class ReferencesResponseModel {

    @SerializedName("content")
    private List<Reference> references;

    @SerializedName("number")
    private int page;

    public List<Reference> getReferences() {
        return references;
    }
}
