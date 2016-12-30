package com.giljulio.adorables.net;

import android.widget.ImageView;

import com.squareup.picasso.Callback;

public interface ImageLoader {

    void loadImage(String url, Config config, ImageView imageView, Callback callback);

    class Config {
        boolean circular;
        int size;

        public Config(boolean circular, int size) {
            this.circular = circular;
            this.size = size;
        }
    }
}
