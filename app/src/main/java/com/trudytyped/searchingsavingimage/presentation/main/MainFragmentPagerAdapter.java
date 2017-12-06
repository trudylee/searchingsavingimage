package com.trudytyped.searchingsavingimage.presentation.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.trudytyped.searchingsavingimage.R;
import com.trudytyped.searchingsavingimage.presentation.search.SearchFragment;
import com.trudytyped.searchingsavingimage.presentation.storage.DisplayFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGER_COUNT = 2;

    private Context context;

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SearchFragment.newInstance();
            case 1:
                return DisplayFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tabTitle;

        if (position == 0) {
            tabTitle = context.getString(R.string.search);
        } else {
            tabTitle = context.getString(R.string.display);
        }
        return tabTitle;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

}
