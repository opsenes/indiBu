package com.internship.mts.internproject.view;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.base.BaseAdapter;
import com.internship.mts.internproject.databinding.MessageItemBinding;
import com.internship.mts.internproject.network.model.Message;
import com.internship.mts.internproject.viewmodel.MessageViewModel;

import java.util.List;

public class MessageAdapter extends BaseAdapter<Message, MessageItemBinding> {

    public MessageAdapter(List<Message> messageList) {
        super(messageList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.message_item;
    }

    @Override
    protected void onBindViewHolder(MessageItemBinding binding, Message item, int position) {
        binding.setMessageVM(new MessageViewModel(item));
    }
}

