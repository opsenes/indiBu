package com.internship.mts.internproject.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.base.BaseFragment;
import com.internship.mts.internproject.databinding.ReferenceFragmentBinding;
import com.internship.mts.internproject.network.model.Reference;

import java.util.ArrayList;
import java.util.List;

public class ReferenceFragment extends BaseFragment {

    private ReferenceFragmentBinding binding;
    private ReferenceAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.reference_fragment, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        binding.referenceFragmentRecyclerView.setLayoutManager(layoutManager);

        binding.referenceFragmentRecyclerView.setAdapter(new ReferenceAdapter(new ArrayList<Reference>()));
        adapter = (ReferenceAdapter) binding.referenceFragmentRecyclerView.getAdapter();
        return binding.getRoot();
    }

    public void addReference(Reference reference) {
        adapter.addItemToTail(reference);
    }

    public void fillReferenceContainer(List<Reference> references) {
        for (Reference reference : references) {
            addReference(reference);
        }
    }
}
