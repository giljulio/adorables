package com.giljulio.adorables.ui;

import com.giljulio.adorables.model.Adorable;
import com.giljulio.adorables.net.FakeApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.subscriptions.CompositeSubscription;

public class MainActivityPresenter {

    private final View view;
    private CompositeSubscription compositeSubscription;
    private FakeApiService apiService;

    @Inject Retrofit retrofit;

    MainActivityPresenter(View view) {
        this.view = view;
    }

    void bind() {
        apiService = retrofit.create(FakeApiService.class);
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
