package com.internship.mts.internproject.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.internship.mts.internproject.BR;
import com.internship.mts.internproject.DiscountDetailActivity;
import com.internship.mts.internproject.network.ApiService;
import com.internship.mts.internproject.network.IndiBuServiceApi;
import com.internship.mts.internproject.network.model.Discount;
import com.internship.mts.internproject.network.model.Vote;
import com.internship.mts.internproject.utils.DateFormatUtil;
import com.internship.mts.internproject.utils.PhotoUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DiscountViewModel extends BaseObservable {

    private Discount discount;

    public DiscountViewModel(Discount discount) {
        this.discount = discount;
    }

    public String getTitle() {
        return discount.getTitle();
    }

    public String getDescription() {
        return discount.getDescription();
    }

    public String getPhotoUrl() {
        return PhotoUtil.getDealPhotoUrl(String.valueOf(discount.getId()));
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).centerCrop().into(view);
    }

    @Bindable
    public String getDislikeCount() {
        return String.valueOf(discount.getDislikesNumber());
    }

    @Bindable
    public String getLikeCount() {
        return String.valueOf(discount.getLikesNumber());
    }

    public String getCommentCount() {
        return String.valueOf(discount.getCommentsNumber());
    }

    public String getPublishDate() {
        return DateFormatUtil.formatDate(discount.getPublishDate());
    }

    public String getAuthor() {
        return discount.getAuthor();
    }

    public String getAuthorReference() {
        return String.valueOf(discount.getReferencePoint());
    }

    public void onLikeClicked(View view) {
        discount.userLiked();
        voteDiscount(new Vote(discount.getId(), true));
    }

    public void onDislikeClicked(View view) {
        discount.userDisliked();
        voteDiscount(new Vote(discount.getId(), false));
    }

    public void onDiscountCardClicked(View view) {
        view.getContext().startActivity(DiscountDetailActivity.newIntent(view.getContext(), discount.getId()));
    }

    private void voteDiscount(Vote vote){
        ApiService.getInstance().getApi(IndiBuServiceApi.class).voteDiscount(vote)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::success, this::handleError);
    }

    private void success(ResponseBody response) {
        Log.e("success", "voted");
        notifyPropertyChanged(BR.dislikeCount);
        notifyPropertyChanged(BR.likeCount);
    }

    private void handleError(Throwable error) {
        Log.e("error", "Already voted");
        error.printStackTrace();
    }
}
