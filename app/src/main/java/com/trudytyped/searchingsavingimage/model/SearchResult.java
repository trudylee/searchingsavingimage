package com.trudytyped.searchingsavingimage.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("documents")
    private List<Document> documents = null;

    public Meta getMeta() {
        return meta;
    }

    public List<Document> getDocuments() {
        return documents;
    }

}
