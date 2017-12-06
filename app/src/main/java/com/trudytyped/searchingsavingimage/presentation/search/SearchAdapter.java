package com.trudytyped.searchingsavingimage.presentation.search;

import android.content.Context;
import android.database.SQLException;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.trudytyped.searchingsavingimage.R;
import com.trudytyped.searchingsavingimage.data.local.DatabaseManager;
import com.trudytyped.searchingsavingimage.model.Document;
import com.trudytyped.searchingsavingimage.presentation.MeasureUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchImageHolder> {

    AdapterListener adapterListener;

    public interface AdapterListener {

        void onSaved();

        void onError();

        void onDataError();
    }


    private List<Document> searchImageList = new ArrayList<>();

    private Context context;

    public SearchAdapter(Context context, AdapterListener adapterListener) {
        this.context = context;
        this.adapterListener = adapterListener;
    }

    @Override
    public SearchImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_image, parent, false);
        return new SearchImageHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchImageHolder searchImageHolder, final int position) {
        SearchImageHolder vh = searchImageHolder;

        int gridWidth = vh.getGridWidth();
        vh.imageView.getLayoutParams().width = gridWidth;
        vh.imageView.getLayoutParams().height = gridWidth;

        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .override(gridWidth, gridWidth)
                .error(R.drawable.ic_error);

        Glide.with(vh.imageView.getContext())
                .load(searchImageList.get(position).getThumbnailUrl())
                .apply(requestOptions)
                .listener(requestListener)
                .into(vh.imageView);

        vh.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatabaseManager databaseManager = DatabaseManager.getInstance(context);
                try {
                    databaseManager.insertImageUrl(searchImageList.get(position).getImageUrl());
                    adapterListener.onSaved();
                } catch (SQLException e) {
                    adapterListener.onDataError();
                }
            }
        });
    }

    private RequestListener<Drawable> requestListener = new RequestListener<Drawable>() {

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            adapterListener.onError();
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            return false;
        }
    };

    @Override
    public int getItemCount() {
        return searchImageList.size();
    }

    public void swap(List<Document> searchImages) {
        if (searchImages != null) {
            searchImageList = searchImages;
            notifyDataSetChanged();
        }
    }

    public class SearchImageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_search_image)
        ImageView imageView;

        public SearchImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public int getGridWidth() {
            return MeasureUtil.getGridWidth(this.itemView, SearchFragment.GRID_COUNT);
        }
    }
}
