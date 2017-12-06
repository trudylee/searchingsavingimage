package com.trudytyped.searchingsavingimage.presentation.search;

import com.trudytyped.searchingsavingimage.R;
import com.trudytyped.searchingsavingimage.data.api.SearchService;
import com.trudytyped.searchingsavingimage.model.SearchResult;
import com.trudytyped.searchingsavingimage.presentation.base.AbsPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter extends AbsPresenter<SearchContract.View> implements
        SearchContract.Presenter {

    private SearchService searchService;

    private SearchAdapter searchAdapter;

    public SearchPresenter(SearchContract.View view,
                           SearchService searchService,
                           SearchAdapter searchAdapter) {
        super(view);

        this.searchService = searchService;
        this.searchAdapter = searchAdapter;
    }

    @Override
    public void search(String query) {
        searchService.image(query).enqueue(new Callback<SearchResult>() {

            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.isSuccessful() &&
                        !(response.body() == null) &&
                        !(response.body().getDocuments() == null)) {
                    searchAdapter.swap(response.body().getDocuments());
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                getView().showSnackBar(R.string.network_error);
            }
        });
    }
}
