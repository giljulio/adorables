package com.giljulio.adorables.ui.screens.closeup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giljulio.adorables.R;
import com.giljulio.adorables.net.model.Comment;
import com.giljulio.adorables.ui.model.Chat;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

class CommentAdapterDelegate extends AbsListItemAdapterDelegate<Comment, Chat, CommentAdapterDelegate.CommentViewHolder> {


    @Override
    protected boolean isForViewType(@NonNull Chat item, @NonNull List<Chat> items, int position) {
        return item instanceof Comment;
    }

    @NonNull
    @Override
    protected CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentAdapterDelegate.CommentViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull Comment item, @NonNull CommentViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.bodyView.setText(item.getBody());
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_comment_avatar) ImageView avatarView;
        @Bind(R.id.item_comment_body) TextView bodyView;

        CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
