package com.giljulio.adorables.net.model;

import com.giljulio.adorables.ui.model.Chat;

public class User implements Chat {

    private int id;
    private String name;
    private String email;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getKey() {
        return User.class.getSimpleName() + String.valueOf(getId());
    }
}