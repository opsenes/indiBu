package com.internship.mts.internproject.view;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.base.BaseAdapter;
import com.internship.mts.internproject.databinding.ChatItemBinding;
import com.internship.mts.internproject.network.model.Chat;
import com.internship.mts.internproject.viewmodel.ChatViewModel;

import java.util.List;

public class ChatAdapter extends BaseAdapter<Chat, ChatItemBinding> {

    public ChatAdapter(List<Chat> chatList) {
        super(chatList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.chat_item;
    }

    @Override
    protected void onBindViewHolder(ChatItemBinding binding, Chat item, int position) {
        binding.setChatVM(new ChatViewModel(item));
    }
}
