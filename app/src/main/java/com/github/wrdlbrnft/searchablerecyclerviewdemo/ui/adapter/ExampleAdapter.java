package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.wrdlbrnft.searchablerecyclerviewdemo.databinding.ItemExampleBinding;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.sortedlistadapter.BaseViewHolder;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.sortedlistadapter.SortedListAdapter;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.viewholder.ExampleViewHolder;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.models.ExampleModel;

import java.util.Comparator;

/**
 * Created with Android Studio
 * User: Xaver
 * Date: 24/05/15
 */
public class ExampleAdapter extends SortedListAdapter<ExampleModel> {

    public ExampleAdapter(Context context, Comparator<ExampleModel> comparator) {
        super(context, ExampleModel.class, comparator);
    }

    @Override
    protected BaseViewHolder<? extends ExampleModel> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemExampleBinding binding = ItemExampleBinding.inflate(inflater, parent, false);
        return new ExampleViewHolder(binding);
    }
}
