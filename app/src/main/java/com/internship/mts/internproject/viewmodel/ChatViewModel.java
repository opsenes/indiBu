package com.internship.mts.internproject.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.internship.mts.internproject.network.model.Chat;
import com.internship.mts.internproject.utils.DateFormatUtil;
import com.internship.mts.internproject.utils.UserUtil;

public class ChatViewModel extends BaseObservable {
    private Chat chat;

    public ChatViewModel(Chat chat) {
        this.chat = chat;
    }

    public String getNickname(){
        if(UserUtil.isLoggedUser(chat.getBuyerNickname())) {
            return chat.getSellerNickname();
        }
        return chat.getBuyerNickname();
    }

    @Bindable
    public String getPhotoUrl() {
        if(UserUtil.isLoggedUser(chat.getBuyerNickname())) {
            return UserUtil.getUserPhotoUrl(chat.getSellerNickname());
        }
        return UserUtil.getUserPhotoUrl(chat.getBuyerNickname());
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(SimpleDraweeView view, String imageUrl) {
        view.setImageURI(imageUrl);
    }

    public String getTitle() {
        return chat.getCouponTitle();
    }

    public String getChatDate() {
        return DateFormatUtil.formatDate(chat.getDateOfUpdate());
    }

    public String getMessageBody() {
        return chat.getLastMessageBody();
    }

}
