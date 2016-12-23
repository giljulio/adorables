package com.giljulio.adorables.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giljulio.adorables.R;
import com.giljulio.adorables.model.Adorable;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

class AdorableAdapterDelegate extends AbsListItemAdapterDelegate<Adorable, Adorable, AdorableAdapterDelegate.AdorableViewHolder> {

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
    }

    static class AdorableViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_adorable_name) TextView nameView;
        @Bind(R.id.item_adorable_thumbnail) ImageView thumbnailView;

        AdorableViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
