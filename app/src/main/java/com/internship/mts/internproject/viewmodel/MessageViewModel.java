package com.internship.mts.internproject.viewmodel;


import android.databinding.BaseObservable;

import com.internship.mts.internproject.network.model.Message;
import com.internship.mts.internproject.utils.DateFormatUtil;
import com.internship.mts.internproject.network.model.Comment;

public class MessageViewModel extends BaseObservable {

    private Message message;

    public MessageViewModel(Message message) {
        this.message = message;
    }

    public String getCardColor(){
        return "";
    }

    public String getNickname(){
        return message.getReceiverNickname();
    }

    public String getMessageBody(){
        return message.getMessageBody();
    }

    public String getMessageDate(){
        return DateFormatUtil.formatDate(message.getDateOfMessage());
    }

}