package com.trudytyped.searchingsavingimage.presentation.storage;

import com.trudytyped.searchingsavingimage.presentation.base.BasePresenter;
import com.trudytyped.searchingsavingimage.presentation.base.BaseView;

public interface DisplayContract {

    interface View extends BaseView {

        void refreshImage();
    }


    interface Presenter extends BasePresenter<View> {

        void initImage();
    }
}
