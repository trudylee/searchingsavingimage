package com.trudytyped.searchingsavingimage.presentation.storage;

import com.trudytyped.searchingsavingimage.data.local.DatabaseManager;
import com.trudytyped.searchingsavingimage.presentation.base.AbsPresenter;

public class DisplayPresenter extends AbsPresenter<DisplayContract.View> implements DisplayContract.Presenter {

    private DisplayAdapter displayAdapter;

    private DatabaseManager databaseManager;

    public DisplayPresenter(DisplayContract.View view,
                            DisplayAdapter displayAdapter,
                            DatabaseManager databaseManager) {
        super(view);

        this.displayAdapter = displayAdapter;
        this.databaseManager = databaseManager;
    }

    @Override
    public void initImage() {
        displayAdapter.swap(databaseManager.selectImageUrl());
    }
}
