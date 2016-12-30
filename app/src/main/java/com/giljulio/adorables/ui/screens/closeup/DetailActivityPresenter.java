package com.giljulio.adorables.ui.screens.closeup;

import com.giljulio.adorables.net.JsonPlaceholderService;
import com.giljulio.adorables.ui.model.Adorable;
import com.giljulio.adorables.ui.model.Chat;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DetailActivityPresenter {

    @Inject JsonPlaceholderService apiService;
    private final View view;
    private CompositeSubscription compositeSubscription;

    DetailActivityPresenter(View view) {
        this.view = view;
    }

    void bind() {
        compositeSubscription = new CompositeSubscription();
        view.setupList();
    }

    void unbind() {
        compositeSubscription.unsubscribe();
    }

    public void fetchChats(Adorable adorable) {

        compositeSubscription.add(apiService.getPosts(adorable.getId())
                .flatMap(Observable::from)
                .flatMap(post -> Observable.merge(
                        Observable.just(post), apiService.getComments(post.getId())
                                .flatMap(Observable::from)))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chats -> {
                    view.hideLoading();
                    view.showChats(chats);
                }, throwable -> {
                    throwable.printStackTrace();
                    view.hideLoading();
                }));
    }

    interface View {
        void setupList();

        void hideLoading();

        void showChats(List<Chat> chats);
    }
}
