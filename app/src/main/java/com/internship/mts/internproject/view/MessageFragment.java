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
import com.internship.mts.internproject.databinding.MessageFragmentBinding;
import com.internship.mts.internproject.network.model.Message;

import java.util.ArrayList;

public class MessageFragment extends BaseFragment {

    private MessageFragmentBinding binding;
    private MessageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.message_fragment, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        binding.messageFragmentRecyclerView.setLayoutManager(layoutManager);

        binding.messageFragmentRecyclerView.setAdapter(new MessageAdapter(
                createMockDiscountList(new ArrayList<Message>())));
        adapter = (MessageAdapter) binding.messageFragmentRecyclerView.getAdapter();
        return binding.getRoot();
    }

    public ArrayList<Message> createMockDiscountList(ArrayList<Message> messages) {
        messages.add(new Message(
                "sena",
                "simsare",
                4655656545l,
                "naber"
        ));

        return messages;
    }

    public void addComment(Message message) {
        adapter.addItemToHead(message);
        binding.messageFragmentRecyclerView.smoothScrollToPosition(0);
    }
}
