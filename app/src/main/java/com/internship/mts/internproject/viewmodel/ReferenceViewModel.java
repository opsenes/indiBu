package com.internship.mts.internproject.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.internship.mts.internproject.utils.DateFormatUtil;
import com.internship.mts.internproject.network.model.Reference;
import com.internship.mts.internproject.utils.UserUtil;

public class ReferenceViewModel extends BaseObservable {

    private Reference reference;

    public ReferenceViewModel(Reference reference) {
        this.reference = reference;
    }

    public String getNickname() {
        return reference.getNickname();
    }

    public int getStarRating() {
        return reference.getStarRating();
    }

    public String getComment() {
        return reference.getComment();
    }

    @Bindable
    public String getPhotoUrl() {
        return UserUtil.getUserPhotoUrl(reference.getNickname());
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(SimpleDraweeView view, String imageUrl) {
        view.setImageURI(imageUrl);
    }

    public String getReferenceDate() {
        return DateFormatUtil.formatDate(reference.getReferenceDate());
    }

}