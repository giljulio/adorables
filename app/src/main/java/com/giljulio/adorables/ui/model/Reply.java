package com.giljulio.adorables.ui.model;

import android.os.Parcelable;

import com.giljulio.adorables.net.model.Comment;
import com.giljulio.adorables.ui.model.diff.Identifiable;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Reply implements Parcelable, Identifiable {

    public abstract int getId();
    public abstract int getPostId();
    public abstract String getName();
    public abstract String getEmail();
    public abstract String getBody();

    public static Reply create(Comment comment) {
        return new AutoValue_Reply(comment.getId(), comment.getPostId(), comment.getName(), comment.getEmail(), comment.getBody());
    }

    @Override
    public String getKey() {
        return Reply.class.getSimpleName() + String.valueOf(getId());
    }
}
