package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.viewholder;

import com.github.wrdlbrnft.searchablerecyclerviewdemo.databinding.ItemExampleBinding;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.models.ExampleModel;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.sortedlistadapter.BaseViewHolder;

public class ExampleViewHolder extends BaseViewHolder<ExampleModel> {

    private final ItemExampleBinding mBinding;

    public ExampleViewHolder(ItemExampleBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    @Override
    protected void performBind(ExampleModel item) {
        mBinding.setModel(item);
    }
}
