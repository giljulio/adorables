package com.giljulio.adorables.ui;

import android.support.v7.util.DiffUtil;

import com.giljulio.adorables.model.Adorable;

import java.util.List;

public class AdorableDiffUtilCallback extends DiffUtil.Callback {

    private final List<Adorable> oldList;
    private final List<Adorable> newList;

    public AdorableDiffUtilCallback(List<Adorable> oldList, List<Adorable> newList) {
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
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
