package com.internship.mts.internproject.viewmodel;

import android.databinding.ObservableArrayList;
import android.util.Log;

import com.internship.mts.internproject.network.ApiService;
import com.internship.mts.internproject.network.IndiBuServiceApi;
import com.internship.mts.internproject.network.model.DiscountFeedResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DiscountFragmentViewModel extends BaseFeedViewModel{
    private int LOAD_MODE_SPECIAL = 0;
    private String LOAD_SPECIAL_REQUEST = "";

    private int LOAD_MODE_POPULAR = 1;
    private String LOAD_POPULAR_REQUEST = "hotVoteCounter,desc";

    private int LOAD_MODE_RECENT = 2;
    private String LOAD_RECENT_REQUEST = "dateOfCreation,desc";

    private String query;
    @Override
    protected void loadFeedList() {
        if(mode == LOAD_MODE_SPECIAL){
            query = LOAD_SPECIAL_REQUEST;
        }
        else if (mode == LOAD_MODE_POPULAR){
            query = LOAD_POPULAR_REQUEST;
        }
        else if (mode == LOAD_MODE_RECENT) {
            query = LOAD_RECENT_REQUEST;
        }
        ApiService.getInstance().getApi(IndiBuServiceApi.class).getOrderedDiscounts(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleDiscounts, this::handleError);

        Log.e("load feeds", "load feeds fonksiyonu çalıştı");
    }

    private void handleDiscounts(DiscountFeedResponse response) {
        Log.d("response", response.getDiscountList().toString());
        //feedList = (ObservableArrayList) response.getDiscountList();
        for (int i=0; i<response.getDiscountList().size(); i++){
            feedList.add(response.getDiscountList().get(i));
        }
    }
}
