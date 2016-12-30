package com.giljulio.adorables.net.model;

import com.giljulio.adorables.ui.model.Chat;

public class Post implements Chat {

    private int userId;
    private int id;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String getKey() {
        return Post.class.getSimpleName() + String.valueOf(getId());
    }
}
