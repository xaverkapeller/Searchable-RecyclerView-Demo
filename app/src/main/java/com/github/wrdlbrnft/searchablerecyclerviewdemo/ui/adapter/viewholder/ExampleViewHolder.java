package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.viewholder;

import com.github.wrdlbrnft.searchablerecyclerviewdemo.databinding.ItemExampleBinding;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.ExampleAdapter;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.models.ExampleModel;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class ExampleViewHolder extends SortedListAdapter.ViewHolder<ExampleModel> {

    private final ItemExampleBinding mBinding;

    public ExampleViewHolder(ItemExampleBinding binding, ExampleAdapter.Listener listener) {
        super(binding.getRoot());
        binding.setListener(listener);

        mBinding = binding;
    }

    @Override
    protected void performBind(ExampleModel item) {
        mBinding.setModel(item);
    }
}
