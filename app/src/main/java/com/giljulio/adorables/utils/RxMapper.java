package com.giljulio.adorables.utils;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class RxMapper {

    /**
     * Helper method to map to another List
     */
    public static <T, R> Observable<List<T>> from(List<R> list, Func1<R, T> mapper) {
        return Observable.just(list)
                .flatMapIterable(posts -> posts)
                .map(mapper)
                .toList();
    }
}
