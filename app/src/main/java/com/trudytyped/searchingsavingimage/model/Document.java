package com.trudytyped.searchingsavingimage.model;

import com.google.gson.annotations.SerializedName;

public class Document {

    @SerializedName("collection")
    private String collection;

    @SerializedName("datetime")
    private String datetime;

    @SerializedName("height")
    private Integer height;

    @SerializedName("width")
    private Integer width;

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("display_sitename")
    private String displaySitename;

    @SerializedName("doc_url")
    private String docUrl;

    public String getCollection() {
        return collection;
    }

    public String getDatetime() {
        return datetime;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDisplaySitename() {
        return displaySitename;
    }

    public String getDocUrl() {
        return docUrl;
    }

}
