package com.trudytyped.searchingsavingimage.presentation.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.trudytyped.searchingsavingimage.R;
import com.trudytyped.searchingsavingimage.presentation.search.SearchFragment;
import com.trudytyped.searchingsavingimage.presentation.storage.DisplayFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchFragment.StatusListener {

    @BindView(R.id.tab_layout) TabLayout tabLayout;

    @BindView(R.id.view_pager) ViewPager viewPager;

    private MainFragmentPagerAdapter mainFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        viewPager.setAdapter(mainFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private Fragment findFragmentByPosition(int position) {
        return getSupportFragmentManager().findFragmentByTag(
                "android:switcher:" + viewPager.getId() + ":" + mainFragmentPagerAdapter.getItemId(position));
    }

    @Override
    public void onRefreshNeed() {
        DisplayFragment fragment = (DisplayFragment) findFragmentByPosition(1);
        fragment.refreshImage();
    }
}
