package com.giljulio.adorables.ui.screens.lineup;

import com.giljulio.adorables.net.model.User;
import com.giljulio.adorables.net.ImageLoader;
import com.giljulio.adorables.ui.model.Adorable;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

class LineUpAdapter extends ListDelegationAdapter<List<Adorable>> {

    LineUpAdapter(ImageLoader imageLoader) {

        delegatesManager.addDelegate(new AdorableAdapterDelegate(imageLoader));

        setItems(new ArrayList<>());
    }
}
