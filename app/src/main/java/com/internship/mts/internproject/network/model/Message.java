package com.internship.mts.internproject.network.model;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("receiverNickname")
    private String receiverNickname;

    @SerializedName("senderNickname")
    private String senderNickname;

    @SerializedName("date")
    private long dateOfMessage;

    @SerializedName("body")
    private String messageBody;

    public Message(String receiverNickname, String senderNickname, long dateOfMessage, String messageBody) {
        this.receiverNickname = receiverNickname;
        this.senderNickname = senderNickname;
        this.dateOfMessage = dateOfMessage;
        this.messageBody = messageBody;
    }

    public String getReceiverNickname() {
        return receiverNickname;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public long getDateOfMessage() {
        return dateOfMessage;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
