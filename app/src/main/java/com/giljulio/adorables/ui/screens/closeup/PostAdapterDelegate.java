package com.giljulio.adorables.ui.screens.closeup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giljulio.adorables.R;
import com.giljulio.adorables.net.model.Post;
import com.giljulio.adorables.ui.model.Chat;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

class PostAdapterDelegate extends AbsListItemAdapterDelegate<Post, Chat, PostAdapterDelegate.PostViewHolder> {

    @Override
    protected boolean isForViewType(@NonNull Chat item, @NonNull List<Chat> items, int position) {
        return item instanceof Post;
    }

    @NonNull
    @Override
    protected PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull Post item, @NonNull PostViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.titleView.setText(item.getTitle());
        viewHolder.bodyView.setText(item.getBody());
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_post_title) TextView titleView;
        @Bind(R.id.item_post_body) TextView bodyView;


        PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
