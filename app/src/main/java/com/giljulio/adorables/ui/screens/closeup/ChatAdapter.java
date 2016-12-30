package com.giljulio.adorables.ui.screens.closeup;

import com.giljulio.adorables.ui.model.Chat;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends ListDelegationAdapter<List<Chat>> {

    public ChatAdapter() {

        delegatesManager.addDelegate(new PostAdapterDelegate());
        delegatesManager.addDelegate(new CommentAdapterDelegate());

        setItems(new ArrayList<>());
    }
}
