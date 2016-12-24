package com.giljulio.adorables.net;

import android.widget.ImageView;

import com.squareup.picasso.Callback;

public interface ImageLoader {

    void loadImage(String url, ImageView imageView, Callback callback);
}
