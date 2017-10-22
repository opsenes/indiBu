package com.internship.mts.internproject.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.internship.mts.internproject.utils.DateFormatUtil;
import com.internship.mts.internproject.network.model.Comment;
import com.internship.mts.internproject.utils.UserUtil;

public class CommentViewModel extends BaseObservable {

    private Comment comment;

    public CommentViewModel(Comment comment) {
        this.comment = comment;
    }

    @Bindable
    public String getPhotoUrl() {
        return UserUtil.getUserPhotoUrl(comment.getNickname());
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(SimpleDraweeView view, String imageUrl) {
        view.setImageURI(imageUrl);
    }

    public String getNickname(){
        return comment.getNickname();
    }

    public String getCommentText(){
        return comment.getCommentText();
    }

    public String getCommentDate(){
        return DateFormatUtil.formatDate(comment.getCommentDate());
    }

}
