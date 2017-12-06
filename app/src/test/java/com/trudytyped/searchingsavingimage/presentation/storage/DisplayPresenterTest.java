package com.trudytyped.searchingsavingimage.presentation.storage;

import com.trudytyped.searchingsavingimage.data.local.DatabaseManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DisplayPresenterTest {

    @Mock DisplayContract.View view;

    @Mock DisplayAdapter displayAdapter;

    @Mock DatabaseManager databaseManager;

    @Test
    public void 저장된_이미지를_불러오기위해_어댑터를_갱신한다() {
        DisplayContract.Presenter presenter = new DisplayPresenter(view, displayAdapter, databaseManager);
        presenter.initImage();

        verify(displayAdapter).swap(databaseManager.selectImageUrl());
    }
}