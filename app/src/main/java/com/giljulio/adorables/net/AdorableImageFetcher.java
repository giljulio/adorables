package com.giljulio.adorables.net;

import android.widget.ImageView;

import com.squareup.picasso.Callback;

import rx.Observable;
import rx.Subscriber;

public class AdorableImageFetcher {

    private static final String ADORABLE_API_BASE_URL = "https://api.adorable.io/avatars/200/";

    private final ImageLoader imageLoader;

    public AdorableImageFetcher(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public Observable<ImageView> fetch(String email, ImageView target) {
        return Observable.create(new Observable.OnSubscribe<ImageView>() {

            @Override
            public void call(Subscriber<? super ImageView> subscriber) {
                imageLoader.loadImage(ADORABLE_API_BASE_URL + email, target, new Callback() {
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
