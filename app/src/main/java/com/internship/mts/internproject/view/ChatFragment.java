package com.internship.mts.internproject.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.base.BaseFragment;
import com.internship.mts.internproject.databinding.ChatFragmentBinding;
import com.internship.mts.internproject.network.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends BaseFragment {

    private ChatFragmentBinding binding;
    private ChatAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.chat_fragment, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        binding.chatFragmentRecyclerView.setLayoutManager(layoutManager);


        binding.chatFragmentRecyclerView.setAdapter(new ChatAdapter(new ArrayList<Chat>()));
        adapter = (ChatAdapter) binding.chatFragmentRecyclerView.getAdapter();
        return binding.getRoot();
    }

    public void addChat(Chat chat) {
        adapter.addItemToHead(chat);
    }

    public void fillChatContainer(List<Chat> chats) {
        for (Chat chat : chats) {
            addChat(chat);
        }
    }
}
