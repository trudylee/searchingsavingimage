package com.trudytyped.searchingsavingimage.presentation.base;

public abstract class AbsPresenter<V extends BaseView> implements BasePresenter<V> {

    private V view;

    public AbsPresenter(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
}

