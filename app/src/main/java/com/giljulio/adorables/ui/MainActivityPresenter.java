package com.giljulio.adorables.ui;

import com.giljulio.adorables.model.Adorable;
import com.giljulio.adorables.net.FakeApiService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.subscriptions.CompositeSubscription;

class MainActivityPresenter {

    private final View view;
    private CompositeSubscription compositeSubscription;
    private final FakeApiService apiService;

    MainActivityPresenter(View view) {
        this.view = view;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FakeApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(FakeApiService.class);
    }

    void bind() {
        compositeSubscription = new CompositeSubscription();
        fetchAdorables();
    }

    void unbind() {
        compositeSubscription.unsubscribe();
    }

    void fetchAdorables() {
        view.showLoading();
        compositeSubscription.add(apiService.getAdorables()
                .subscribe(adorables -> {
                    view.hideLoading();
                    view.showAdorables(adorables);
                }, throwable -> {
                    view.hideLoading();
                    view.showError();
                }));
    }

    interface View {

        void showAdorables(List<Adorable> adorable);
        void showLoading();
        void hideLoading();
        void showError();
    }
}
