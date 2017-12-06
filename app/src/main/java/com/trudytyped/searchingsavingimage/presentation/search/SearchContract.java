package com.trudytyped.searchingsavingimage.presentation.search;

import android.support.annotation.StringRes;

import com.trudytyped.searchingsavingimage.presentation.base.BasePresenter;
import com.trudytyped.searchingsavingimage.presentation.base.BaseView;

public interface SearchContract {

    interface View extends BaseView {

        void showSnackBar(@StringRes int resID);
    }


    interface Presenter extends BasePresenter<View> {

        void search(String string);
    }

}
