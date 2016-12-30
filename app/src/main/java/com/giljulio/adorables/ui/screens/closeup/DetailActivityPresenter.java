package com.giljulio.adorables.ui.screens.closeup;

import com.giljulio.adorables.net.JsonPlaceholderService;
import com.giljulio.adorables.ui.model.Adorable;
import com.giljulio.adorables.ui.model.Natter;
import com.giljulio.adorables.ui.model.Reply;
import com.giljulio.adorables.ui.model.diff.Identifiable;
import com.giljulio.adorables.utils.RxMapper;

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
                .flatMap(posts -> RxMapper.from(posts, Natter::create))
                .flatMap(Observable::from)
                .flatMap(natter -> Observable.merge(
                        Observable.just(natter), apiService.getComments(natter.getId())
                                .flatMap(posts -> RxMapper.from(posts, Reply::create))
                                .flatMap(Observable::from)))
                .cast(Identifiable.class)
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

        void showChats(List<Identifiable> chats);
    }
}
