package com.giljulio.adorables.ui.screens.lineup.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giljulio.adorables.R;
import com.giljulio.adorables.net.AdorableImageFetcher;
import com.giljulio.adorables.net.ImageLoader;
import com.giljulio.adorables.ui.model.Adorable;
import com.giljulio.adorables.ui.screens.closeup.DetailActivity;
import com.giljulio.adorables.utils.ColorUtils;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

class AdorableAdapterDelegate extends AbsListItemAdapterDelegate<Adorable, Adorable, AdorableAdapterDelegate.AdorableViewHolder> {

    private final Activity activity;
    private final AdorableImageFetcher adorableImageFetcher;

    AdorableAdapterDelegate(Activity activity, AdorableImageFetcher adorableImageFetcher) {
        this.activity = activity;
        this.adorableImageFetcher = adorableImageFetcher;
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

        adorableImageFetcher.fetch(item.getEmail(), new ImageLoader.Config(false, 200), viewHolder.thumbnailView)
                .map(ColorUtils::extractColor)
                .subscribe(color -> {
                    viewHolder.cardView.setCardBackgroundColor(color);
                });

        //noinspection unchecked
        Pair<View, String>[] pairs = new Pair[]{
                new Pair<>(viewHolder.thumbnailView, activity.getString(R.string.transition_thumbnail, item.getId())),
                new Pair<>(viewHolder.cardView, activity.getString(R.string.transition_background, item.getId()))
        };

        for (Pair<View, String> pair : pairs) {
            pair.first.setTransitionName(pair.second);
        }

        viewHolder.cardView.setOnClickListener(v -> {
            Intent intent = DetailActivity.createIntent(activity, item);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
            viewHolder.cardView.getContext().startActivity(intent, activityOptionsCompat.toBundle());
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
