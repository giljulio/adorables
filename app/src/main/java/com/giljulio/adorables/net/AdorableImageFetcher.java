package com.giljulio.adorables.net;

import android.widget.ImageView;

import com.squareup.picasso.Callback;

import java.util.Locale;

import rx.Observable;
import rx.Subscriber;

public class AdorableImageFetcher {

    private static final String ADORABLE_API_URL = "https://api.adorable.io/avatars/%d/%s";

    private final ImageLoader imageLoader;

    public AdorableImageFetcher(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public Observable<ImageView> fetch(String email, int size, ImageView target) {
        return Observable.create(new Observable.OnSubscribe<ImageView>() {

            @Override
            public void call(Subscriber<? super ImageView> subscriber) {
                String url = String.format(Locale.getDefault(), ADORABLE_API_URL, size, email);
                imageLoader.loadImage(url, target, new Callback() {
                    @Override
                    public void onSuccess() {
                        subscriber.onNext(target);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError() {
                        subscriber.onError(new Exception("Picasso onError callback. Please check upstream stacktrace."));

                    }
                });
            }
        });

    }
}
