package com.internship.mts.internproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.network.model.Comment;
import com.internship.mts.internproject.network.model.CommentRequestModel;
import com.internship.mts.internproject.network.model.CommentResponseModel;
import com.internship.mts.internproject.network.model.Discount;
import com.internship.mts.internproject.network.model.User;
import com.internship.mts.internproject.network.model.UserLocalStore;
import com.internship.mts.internproject.utils.DateFormatUtil;
import com.internship.mts.internproject.utils.PhotoUtil;
import com.internship.mts.internproject.utils.UserUtil;
import com.internship.mts.internproject.view.CommentFragment;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountDetailActivity extends BaseActivity {

    private Discount discount;

    private static final String TAG = "commentFragment";
    private static final String DISCOUNT_ID_KEY = "discountId";

    private double locationLat;
    private double locationLong;

    @BindView(R.id.discount_detail_activity_edit_text_comment)
    EditText commentEditText;

    @BindView(R.id.discount_details_imageview_photo)
    SimpleDraweeView discountPhotoImageView;

    @BindView(R.id.discount_details_textview_title)
    TextView titleTextView;

    @BindView(R.id.discount_details_textview_decription)
    TextView descriptionTextView;

    @BindView(R.id.discount_details_textview_postdate)
    TextView postDateTextView;

    @BindView(R.id.discount_details_textview_postby)
    TextView authorTextView;

    @BindView(R.id.discount_details_textview_comments)
    TextView commentsCountTextView;

    @BindView(R.id.discount_details_textview_fire)
    TextView likeCountTextView;

    @BindView(R.id.discount_details_textview_ice)
    TextView dislikeCountTextView;

    @BindView(R.id.discount_detail_activity_text_view_author_reference)
    TextView referencePointCountTextView;

    @BindView(R.id.discount_detail_activity_toolbar)
    Toolbar discountDetailToolbar;

    @BindView(R.id.activity_discount_detail_textview_location)
    TextView locationFinder;

    public static Intent newIntent(Context context, int discountId) {
        return new Intent(context, DiscountDetailActivity.class).putExtra(DISCOUNT_ID_KEY, discountId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(discountDetailToolbar, R.string.dicount_detail_title);
        fetchDiscountDetails();
    }

    private void fetchDiscountDetails() {
        addRequest(getService().getDiscountDetail(getIntent().getIntExtra(DISCOUNT_ID_KEY, 0)), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Discount discount = (Discount) response.body();
                setDiscount(discount);
                setDiscountDetails(discount);
                fetchDiscountComments();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {

            }
        });
    }

    private void fetchDiscountComments() {
        addRequest(getService().getComments(discount.getId()), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                CommentResponseModel comments = (CommentResponseModel) response.body();
                for (Comment comment : comments.getComments()) {
                    ((CommentFragment) getContainerFragment()).addComment(comment);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
            }
        }, false);
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    private void setDiscountDetails(Discount discount) {
        discountPhotoImageView.setImageURI(PhotoUtil.getDealPhotoUrl(String.valueOf(discount.getId())));
        titleTextView.setText(discount.getTitle());
        descriptionTextView.setText(discount.getDescription());
        postDateTextView.setText(DateFormatUtil.formatDate(discount.getPublishDate()));
        authorTextView.setText(discount.getAuthor());
        commentsCountTextView.setText(Integer.toString(discount.getCommentsNumber()));
        likeCountTextView.setText(Integer.toString(discount.getLikesNumber()));
        dislikeCountTextView.setText(Integer.toString(discount.getDislikesNumber()));
        referencePointCountTextView.setText(getResources().getString(
                R.string.discount_detail_activity_reference_bar,
                discount.getReferencePoint(), discount.getReferenceCount()
        ));
        if (discount.getLocationX() != 0.0 && discount.getLocationY() != 0.0) {
            locationLat = discount.getLocationX();
            locationLong = discount.getLocationY();
        } else {
            locationFinder.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.discount_detail_activity;
    }

    @OnClick(R.id.discount_detail_activity_image_send_comment)
    public void sendComment() {
        final String commentText = commentEditText.getText().toString();
        addRequest(getService().postComment(new CommentRequestModel(
                commentText,
                discount.getId()
        )), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                User user = UserLocalStore.getInstance().getLoggedInUser();
                ((CommentFragment) getContainerFragment()).sendComment(
                        new Comment(
                                UserUtil.getUserPhotoUrl(user.getNickname()),
                                user.getNickname(),
                                commentText,
                                new Date().getTime()
                        )
                );
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {

            }
        });
        commentEditText.getText().clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContainerFragmentLayoutId() {
        return R.id.discount_detail_activity_fragment_container;
    }

    @Override
    protected Fragment getFragment(){
        return new CommentFragment();
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }

    @OnClick(R.id.activity_discount_detail_textview_location)
    public void findLocation(){
        Intent intent = MapsActivity.newIntent(this);
        intent.putExtra("locationLat", locationLat );
        intent.putExtra("locationLong", locationLong );
        startActivity(intent);
    }

}