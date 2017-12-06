package com.trudytyped.searchingsavingimage.data.api;

public class SearchUtils {

    public static final String BASE_URL = "https://dapi.kakao.com";

    public static final String HEADER_PREFIX = "KakaoAK ";

    public static final String APP_KEY = "3ae0ab83407eb6711acffd89fb46d4a0";

    public static SearchService getSearchService() {
        return RetrofitClient.getClient().create(SearchService.class);
    }

}
