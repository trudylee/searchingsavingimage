package com.trudytyped.searchingsavingimage.presentation.storage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trudytyped.searchingsavingimage.R;
import com.trudytyped.searchingsavingimage.presentation.MeasureUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.DisplayImageHolder> {

    private List<String> urls = new ArrayList<>();

    public DisplayAdapter() {

    }

    @Override
    public DisplayImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_display_image, parent, false);
        return new DisplayImageHolder(view);
    }

    @Override
    public void onBindViewHolder(DisplayImageHolder holder, int position) {
        DisplayImageHolder vh = holder;

        int gridWidth = vh.getGridWidth();
        vh.imageView.getLayoutParams().width = gridWidth;
        vh.imageView.getLayoutParams().height = gridWidth;

        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .override(gridWidth, gridWidth)
                .error(R.drawable.ic_error);

        Glide.with(vh.imageView.getContext())
                .load(urls.get(position))
                .apply(requestOptions)
                .into(vh.imageView);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public void swap(List<String> urls) {
        if (urls != null) {
            this.urls = urls;
            notifyDataSetChanged();
        }
    }

    public class DisplayImageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_display_image)
        ImageView imageView;

        public DisplayImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public int getGridWidth() {
            return MeasureUtil.getGridWidth(this.itemView, DisplayFragment.GRID_COUNT);
        }
    }

}
