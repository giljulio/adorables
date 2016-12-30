package com.giljulio.adorables.net;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class PicassoImageLoader implements ImageLoader {

    private final Context context;

    public PicassoImageLoader(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, Config config, ImageView imageView, Callback callback) {
        RequestCreator creator = Picasso.with(context)
                .load(url);

        if (config.circular)
            creator.transform(new CircleTransform());

        creator.into(imageView, callback);
    }

}
