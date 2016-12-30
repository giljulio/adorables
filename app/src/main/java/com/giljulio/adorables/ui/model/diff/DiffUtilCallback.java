package com.giljulio.adorables.ui.model.diff;

import android.support.v7.util.DiffUtil;

import java.util.List;
import java.util.Objects;

public class DiffUtilCallback<T extends Identifiable> extends DiffUtil.Callback {

    private final List<T> oldList;
    private final List<T> newList;

    public DiffUtilCallback(List<T> oldList, List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldList.get(oldItemPosition).getKey(), newList.get(newItemPosition).getKey());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

}
