package com.trudytyped.searchingsavingimage.presentation.storage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trudytyped.searchingsavingimage.R;
import com.trudytyped.searchingsavingimage.data.local.DatabaseManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayFragment extends Fragment implements DisplayContract.View, SwipeRefreshLayout.OnRefreshListener {

    protected static final int GRID_COUNT = 2;

    @BindView(R.id.swipe_layout) SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private DisplayPresenter presenter;

    private DisplayAdapter displayAdapter;

    private DatabaseManager databaseManager;

    public static DisplayFragment newInstance() {
        return new DisplayFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        displayAdapter = new DisplayAdapter();
        databaseManager = DatabaseManager.getInstance(getActivity());

        presenter = new DisplayPresenter(this, displayAdapter, databaseManager);
        presenter.initImage();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), GRID_COUNT));
        recyclerView.setAdapter(displayAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onRefresh() {
        presenter.initImage();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshImage() {
        presenter.initImage();
    }

}
