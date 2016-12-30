package com.giljulio.adorables.ui.screens.closeup.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giljulio.adorables.R;
import com.giljulio.adorables.net.AdorableImageFetcher;
import com.giljulio.adorables.net.ImageLoader;
import com.giljulio.adorables.ui.model.Reply;
import com.giljulio.adorables.ui.model.diff.Identifiable;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

class ReplyAdapterDelegate extends AbsListItemAdapterDelegate<Reply, Identifiable, ReplyAdapterDelegate.CommentViewHolder> {

    AdorableImageFetcher adorableImageFetcher;

    public ReplyAdapterDelegate(AdorableImageFetcher adorableImageFetcher) {
        this.adorableImageFetcher = adorableImageFetcher;
    }

    @Override
    protected boolean isForViewType(@NonNull Identifiable item, @NonNull List<Identifiable> items, int position) {
        return item instanceof Reply;
    }

    @NonNull
    @Override
    protected CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reply, parent, false);
        return new ReplyAdapterDelegate.CommentViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull Reply item, @NonNull CommentViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.bodyView.setText(item.getBody());
        adorableImageFetcher.fetch(item.getEmail(), new ImageLoader.Config(true, 48), viewHolder.avatarView).subscribe();
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
