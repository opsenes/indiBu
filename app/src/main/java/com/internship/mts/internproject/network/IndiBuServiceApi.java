package com.internship.mts.internproject.network;

import com.internship.mts.internproject.network.model.ChatsResponseModel;
import com.internship.mts.internproject.network.model.Coupon;
import com.internship.mts.internproject.network.model.CommentRequestModel;
import com.internship.mts.internproject.network.model.CommentResponseModel;
import com.internship.mts.internproject.network.model.CreateCouponModel;
import com.internship.mts.internproject.network.model.CreateDiscountRequest;
import com.internship.mts.internproject.network.model.CouponFeedResponse;
import com.internship.mts.internproject.network.model.CreateDiscountResponseModel;
import com.internship.mts.internproject.network.model.DiscountFeedResponse;
import com.internship.mts.internproject.network.model.Discount;
import com.internship.mts.internproject.network.model.LoginRequest;
import com.internship.mts.internproject.network.model.ReferencesResponseModel;
import com.internship.mts.internproject.network.model.SignupRequest;
import com.internship.mts.internproject.network.model.User;
import com.internship.mts.internproject.network.model.UserUpdateRequest;
import com.internship.mts.internproject.network.model.Vote;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IndiBuServiceApi {

    @POST("user/login")
    Call<Void> login(@Body LoginRequest loginRequest);

    @GET("user/info")
    Call<User> getUser();

    @PUT("user/info")
    Call<Void> updateUser(@Body UserUpdateRequest userUpdateRequest);

    @GET("user/references")
    Call<ReferencesResponseModel> getReferences(@Query("page") int page, @Query("nickname") String nickname);

    @GET("message/chats")
    Call<ChatsResponseModel> getChats();

    @GET("deal/comment")
    Call<CommentResponseModel> getComments(@Query("dealId") int id);

    @POST("deal/comment")
    Call<Void> postComment(@Body CommentRequestModel commentRequestModel);

    @GET("deal/details")
    Call<Discount> getDiscountDetail(@Query("dealId") int id);

    @POST("user/register")
    Call<Void> createAccount(@Body SignupRequest signupRequest);

    @POST("deal/create")
    Call<CreateDiscountResponseModel> createDiscount(@Body CreateDiscountRequest createDiscountRequest);

    @POST("coupon/create")
    Call<Void> createCoupon(@Body CreateCouponModel createCouponModel);

    @GET("coupon/details")
    Call<Coupon> getCouponDetail(@Query("couponId") int id);

    @Multipart
    @POST("photo/user")
    Call<Void> uploadUserPhoto(@Part MultipartBody.Part image);

    @Multipart
    @POST("photo/deal")
    Call<Void> uploadDealPhoto(@Part MultipartBody.Part image, @Query("dealId") long dealId);

    @GET("/coupon/feed")
    Observable<CouponFeedResponse> getCoupons();

    @POST("/user/vote")
    Observable<ResponseBody> voteDiscount(@Body Vote vote);

    @GET("/deal/feed")
    Observable<DiscountFeedResponse> getOrderedDiscounts(@Query("sort") String sort);
}