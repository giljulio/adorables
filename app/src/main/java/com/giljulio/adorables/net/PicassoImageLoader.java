package com.giljulio.adorables.net;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PicassoImageLoader implements ImageLoader {

    private final Context context;

    public PicassoImageLoader(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView imageView, Callback callback) {
        Picasso.with(context)
                .load(url)
                .into(imageView, callback);
    }
}
