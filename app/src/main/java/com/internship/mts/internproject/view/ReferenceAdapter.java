package com.internship.mts.internproject.view;

import com.internship.mts.internproject.R;
import com.internship.mts.internproject.base.BaseAdapter;
import com.internship.mts.internproject.databinding.ReferenceItemBinding;
import com.internship.mts.internproject.network.model.Reference;
import com.internship.mts.internproject.viewmodel.ReferenceViewModel;

import java.util.List;

public class ReferenceAdapter extends BaseAdapter<Reference, ReferenceItemBinding> {

    public ReferenceAdapter(List<Reference> referenceList) {
        super(referenceList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.reference_item;
    }

    @Override
    protected void onBindViewHolder(ReferenceItemBinding binding, Reference item, int position) {
        binding.setReferenceVM(new ReferenceViewModel(item));
    }
}
