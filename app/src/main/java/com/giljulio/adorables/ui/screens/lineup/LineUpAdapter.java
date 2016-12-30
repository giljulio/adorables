package com.giljulio.adorables.ui.screens.lineup;

import android.app.Activity;

import com.giljulio.adorables.net.AdorableImageFetcher;
import com.giljulio.adorables.ui.model.Adorable;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

class LineUpAdapter extends ListDelegationAdapter<List<Adorable>> {

    LineUpAdapter(Activity activity, AdorableImageFetcher adorableImageFetcher) {

        delegatesManager.addDelegate(new AdorableAdapterDelegate(activity, adorableImageFetcher));

        setItems(new ArrayList<>());
    }
}
