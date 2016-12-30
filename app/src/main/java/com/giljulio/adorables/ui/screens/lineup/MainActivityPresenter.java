package com.giljulio.adorables.ui.screens.lineup;

import com.giljulio.adorables.net.JsonPlaceholderService;
import com.giljulio.adorables.ui.model.Adorable;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivityPresenter {

    private final View view;
    private CompositeSubscription compositeSubscription;
    @Inject JsonPlaceholderService apiService;


    MainActivityPresenter(View view) {
        this.view = view;
    }

    void bind() {
        compositeSubscription = new CompositeSubscription();
        view.setupList();
        fetchAdorables();
    }

    void unbind() {
        compositeSubscription.unsubscribe();
    }

    void fetchAdorables() {
        view.showLoading();
        compositeSubscription.add(apiService.getUsers()
                .flatMap(Adorable::from)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adorables -> {
                    view.hideLoading();
                    view.showAdorables(adorables);
                }, throwable -> {
                    throwable.printStackTrace();
                    view.hideLoading();
                    view.showError();
                }));
    }

    interface View {

        void setupList();
        void showAdorables(List<Adorable> adorable);
        void showLoading();
        void hideLoading();
        void showError();
    }
}
