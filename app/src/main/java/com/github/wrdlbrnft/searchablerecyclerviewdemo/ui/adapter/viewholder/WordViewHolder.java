package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.viewholder;

import android.support.annotation.NonNull;

import com.github.wrdlbrnft.searchablerecyclerviewdemo.databinding.ItemWordBinding;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.ExampleAdapter;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.models.WordModel;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class WordViewHolder extends SortedListAdapter.ViewHolder<WordModel> {

    private final ItemWordBinding mBinding;

    public WordViewHolder(ItemWordBinding binding, ExampleAdapter.Listener listener) {
        super(binding.getRoot());
        binding.setListener(listener);

        mBinding = binding;
    }

    @Override
    protected void performBind(@NonNull WordModel item) {
        mBinding.setModel(item);
    }
}
