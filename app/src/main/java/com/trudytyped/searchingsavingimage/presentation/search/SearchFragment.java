package com.trudytyped.searchingsavingimage.presentation.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.trudytyped.searchingsavingimage.R;
import com.trudytyped.searchingsavingimage.data.api.SearchService;
import com.trudytyped.searchingsavingimage.data.api.SearchUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment implements SearchContract.View {

    StatusListener statusListener;

    public interface StatusListener {
        void onRefreshNeed();
    }

    protected static final int GRID_COUNT = 3;

    @BindView(R.id.search_view) SearchView searchView;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private SearchPresenter presenter;

    private SearchAdapter searchAdapter;

    private SearchService searchService;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        statusListener = (StatusListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchAdapter = new SearchAdapter(getContext(), getAdapterListener());
        searchService = SearchUtils.getSearchService();

        presenter = new SearchPresenter(this, searchService, searchAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        initSearchView();

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), GRID_COUNT));
        recyclerView.setAdapter(searchAdapter);

        return view;
    }

    private void initSearchView() {
        searchView.requestFocus();
        searchView.setQueryHint(getString(R.string.search_view_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private SearchAdapter.AdapterListener getAdapterListener() {
        return new SearchAdapter.AdapterListener() {

            @Override
            public void onSaved() {
                showSnackBar(R.string.saved);
                statusListener.onRefreshNeed();
            }

            @Override
            public void onError() {
                showSnackBar(R.string.general_error);
            }

            @Override
            public void onDataError() {
                showSnackBar(R.string.data_error);
            }
        };
    }

    @Override
    public void showSnackBar(int resID) {
        if (!isAdded()) {
            return;
        }
        Snackbar.make(getActivity().findViewById(android.R.id.content),
                resID, BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}

