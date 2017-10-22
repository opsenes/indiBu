package com.internship.mts.internproject.viewmodel;

import android.util.Log;

import com.internship.mts.internproject.network.ApiService;
import com.internship.mts.internproject.network.IndiBuServiceApi;
import com.internship.mts.internproject.network.model.CouponFeedResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CouponFragmentViewModel extends BaseFeedViewModel{
    @Override
    public void loadFeedList() {
        ApiService.getInstance().getApi(IndiBuServiceApi.class).getCoupons()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleCoupons, this::handleError);

        Log.e("load feeds", "load feeds fonksiyonu çalıştı");
    }

    private void handleCoupons(CouponFeedResponse response) {
        Log.d("size", response.getCouponList().size()+"");
        for (int i=0; i<response.getCouponList().size(); i++){
            feedList.add(response.getCouponList().get(i));
        }
    }
}
