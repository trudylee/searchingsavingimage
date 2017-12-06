package com.trudytyped.searchingsavingimage.presentation.search;

import com.trudytyped.searchingsavingimage.data.api.SearchService;
import com.trudytyped.searchingsavingimage.model.SearchResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import retrofit2.Retrofit;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    @Mock
    SearchContract.View view;

    @Mock
    SearchService searchService;

    @Mock
    SearchAdapter searchAdapter;

    @Captor
    ArgumentCaptor<retrofit2.Callback<SearchResult>> argumentCaptor;

    private SearchContract.Presenter searchPresenter;

    /**
     * 과제 수행 시간 내에 SearchPresenter 테스트를 완성하지 못했지만,
     * 면접관분들께 Mock 을 이용한 Presenter Unit Test 작성 경험을 보여드리고 싶어서 코드를 남겨둡니다.
     */

    @Test
    public void 검색에_성공하면_어댑터를_갱신한다() {
        searchPresenter = new SearchPresenter(view, searchService, searchAdapter);
        searchPresenter.search("");

        verify(searchService).image("").enqueue(argumentCaptor.capture());
        SearchResult searchResult = new SearchResult();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .build();

        SearchService searchService = retrofit.create(SearchService.class);
        retrofit2.Call<SearchResult> call = searchService.image("");
        retrofit2.Response<SearchResult> response = retrofit2.Response.success(searchResult);
        argumentCaptor.getValue().onResponse(call, response);

        verify(searchAdapter).swap(response.body().getDocuments());
    }

    @Test
    public void 검색에_실패하면_스낵바를_표시한다() {
        searchPresenter = new SearchPresenter(view, searchService, searchAdapter);
        searchPresenter.search("");

        verify(searchService).image("").enqueue(argumentCaptor.capture());
        SearchResult searchResult = new SearchResult();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .build();

        SearchService searchService = retrofit.create(SearchService.class);
        retrofit2.Call<SearchResult> call = searchService.image("");
        argumentCaptor.getValue().onFailure(call, new Throwable());

        verify(view).showSnackBar(anyInt());
    }
}