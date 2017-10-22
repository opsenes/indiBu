package com.internship.mts.internproject.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.base.BaseFragment;
import com.internship.mts.internproject.databinding.FeedFragmentBinding;
import com.internship.mts.internproject.viewmodel.CouponFragmentViewModel;

public class CouponFragment extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FeedFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.feed_fragment, container, false);

        binding.feedFragmentTabLayout.setVisibility(View.GONE);

        binding.feedFragmentRecyclerView
                .setLayoutManager(new LinearLayoutManager(this.getContext()));
        final CouponFragmentViewModel viewModel = new CouponFragmentViewModel();
        binding.setBaseFeedViewModel(viewModel);
        binding.feedFragmentRecyclerView.setAdapter(new CouponAdapter(viewModel.getFeedList()));

        return binding.getRoot();
    }
}
