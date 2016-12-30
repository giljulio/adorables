package com.giljulio.adorables.ui.model;

import android.os.Parcelable;

import com.giljulio.adorables.net.model.Post;
import com.giljulio.adorables.ui.model.diff.Identifiable;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Natter implements Parcelable, Identifiable {

    public abstract int getUserId();
    public abstract int getId();
    public abstract String getTitle();
    public abstract String getBody();

    public static Natter create(Post post) {
        return new AutoValue_Natter(post.getUserId(), post.getId(), post.getTitle(), post.getBody());
    }

    @Override
    public String getKey() {
        return Natter.class.getSimpleName() + String.valueOf(getId());
    }
}
