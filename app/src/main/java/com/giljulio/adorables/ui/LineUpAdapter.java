package com.giljulio.adorables.ui;

import com.giljulio.adorables.model.Adorable;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class LineUpAdapter extends ListDelegationAdapter<List<Adorable>> {

    public LineUpAdapter() {

        delegatesManager.addDelegate(new AdorableAdapterDelegate());

        setItems(new ArrayList<>());
    }
}
