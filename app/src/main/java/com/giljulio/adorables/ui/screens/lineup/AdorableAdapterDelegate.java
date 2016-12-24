package com.giljulio.adorables.ui.screens.lineup;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giljulio.adorables.R;
import com.giljulio.adorables.net.ImageLoader;
import com.giljulio.adorables.ui.model.Adorable;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;
import com.squareup.picasso.Callback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

class AdorableAdapterDelegate extends AbsListItemAdapterDelegate<Adorable, Adorable, AdorableAdapterDelegate.AdorableViewHolder> {

    private static final String ADORABLE_API_BASE_URL = "https://api.adorable.io/avatars/200/";
    private final ImageLoader imageLoader;

    AdorableAdapterDelegate(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    protected boolean isForViewType(@NonNull Adorable item, @NonNull List<Adorable> items, int position) {
        return true; // we currently only have adorables in the line up
    }

    @NonNull
    @Override
    protected AdorableViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adorable, parent, false);
        return new AdorableViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull Adorable item, @NonNull AdorableViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.nameView.setText(item.getName());
        imageLoader.loadImage(ADORABLE_API_BASE_URL + item.getEmail(), viewHolder.thumbnailView, new Callback() {
            @Override
            public void onSuccess() {
                // experimented using palette... but it was unsuccessful as it was returning
                // colors near to the thumbnail background but not the exact color.
                // This is also much faster O(1)!
                Bitmap bitmap = ((BitmapDrawable)viewHolder.thumbnailView.getDrawable()).getBitmap();
                viewHolder.cardView.setCardBackgroundColor(bitmap.getPixel(0, 0));
            }

            @Override
            public void onError() {

            }
        });

    }

    static class AdorableViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_adorable_name) TextView nameView;
        @Bind(R.id.item_adorable_thumbnail) ImageView thumbnailView;
        @Bind(R.id.item_adorable_card) CardView cardView;

        AdorableViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
