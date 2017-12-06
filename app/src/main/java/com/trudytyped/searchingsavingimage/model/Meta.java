package com.trudytyped.searchingsavingimage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("is_end")
    @Expose
    private Boolean isEnd;

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    @SerializedName("pageable_count")
    @Expose
    private Integer pageableCount;

    public Boolean getIsEnd() {
        return isEnd;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getPageableCount() {
        return pageableCount;
    }

}
