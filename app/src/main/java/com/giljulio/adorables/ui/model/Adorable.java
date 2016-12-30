package com.giljulio.adorables.ui.model;

import android.os.Parcelable;

import com.giljulio.adorables.net.model.User;
import com.giljulio.adorables.ui.model.diff.Identifiable;
import com.google.auto.value.AutoValue;

import java.util.List;

import rx.Observable;

@AutoValue
public abstract class Adorable implements Parcelable, Identifiable {

    public abstract int getId();
    public abstract String getName();
    public abstract String getEmail();

    @Override
    public String getKey() {
        return Adorable.class.getSimpleName() + String.valueOf(getId());
    }

    public static Adorable create(User user) {
        return new AutoValue_Adorable(user.getId(), user.getName(), user.getEmail());
    }

    /**
     * Helper method to convert List of {@link User} to List of {@link Adorable}
     */
    public static Observable<List<Adorable>> from(List<User> user) {
        return Observable.just(user)
                .flatMapIterable(users -> users)
                .map(Adorable::create)
                .toList();
    }
}
