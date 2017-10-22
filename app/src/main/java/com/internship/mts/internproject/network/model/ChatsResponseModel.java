package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatsResponseModel {

    @SerializedName("chatResponseList")
    private List<Chat> chats;

    public List<Chat> getChats() {
        return chats;
    }
}
