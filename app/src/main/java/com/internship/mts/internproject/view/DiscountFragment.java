package com.internship.mts.internproject.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.databinding.FeedFragmentBinding;
import com.internship.mts.internproject.viewmodel.DiscountFragmentViewModel;

public class DiscountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FeedFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.feed_fragment, container, false);

        binding.feedFragmentRecyclerView
                .setLayoutManager(new LinearLayoutManager(this.getContext()));

        final DiscountFragmentViewModel viewModel = new DiscountFragmentViewModel();
        binding.setBaseFeedViewModel(viewModel);
        binding.feedFragmentRecyclerView.setAdapter(new DiscountAdapter(viewModel.getFeedList()));

        TabLayout tabLayout = binding.feedFragmentTabLayout;
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_layout_special)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_layout_popular)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_layout_recent)));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabLayout.getSelectedTabPosition() == 0) {
                    viewModel.setMode(0);
                }
                else if(tabLayout.getSelectedTabPosition() == 1) {
                    viewModel.setMode(1);
                }
                else {
                    viewModel.setMode(2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewModel.getFeedList().clear();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return binding.getRoot();
    }
}

