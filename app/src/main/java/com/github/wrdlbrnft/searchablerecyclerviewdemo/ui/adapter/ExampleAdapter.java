package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.wrdlbrnft.searchablerecyclerviewdemo.databinding.ItemExampleBinding;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.viewholder.ExampleViewHolder;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.models.ExampleModel;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Comparator;

/**
 * Created with Android Studio
 * User: Xaver
 * Date: 24/05/15
 */
public class ExampleAdapter extends SortedListAdapter<ExampleModel> {

    @Override
    protected ViewHolder<? extends ExampleModel> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemExampleBinding binding = ItemExampleBinding.inflate(inflater, parent, false);
        return new ExampleViewHolder(binding, mListener);
    }

    public interface Listener {
        void onExampleModelClicked(ExampleModel model);
    }

    private final Listener mListener;

    public ExampleAdapter(Context context, Comparator<ExampleModel> comparator, Listener listener) {
        super(context, ExampleModel.class, comparator);
        mListener = listener;
    }
}
