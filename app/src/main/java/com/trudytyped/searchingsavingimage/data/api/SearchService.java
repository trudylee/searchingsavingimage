package com.trudytyped.searchingsavingimage.data.api;

import com.trudytyped.searchingsavingimage.model.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("v2/search/image")
    Call<SearchResult> image(@Query("query") String query);

}
