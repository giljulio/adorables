package com.giljulio.adorables.ui.screens.closeup.adapter;

import com.giljulio.adorables.net.AdorableImageFetcher;
import com.giljulio.adorables.ui.model.diff.Identifiable;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends ListDelegationAdapter<List<Identifiable>> {

    public ChatAdapter(AdorableImageFetcher adorableImageFetcher) {

        delegatesManager.addDelegate(new NatterAdapterDelegate());
        delegatesManager.addDelegate(new ReplyAdapterDelegate(adorableImageFetcher));

        setItems(new ArrayList<>());
    }
}
