package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.wrdlbrnft.searchablerecyclerviewdemo.databinding.ItemWordBinding;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.viewholder.WordViewHolder;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.models.WordModel;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Comparator;

/**
 * Created with Android Studio
 * User: Xaver
 * Date: 24/05/15
 */
public class ExampleAdapter extends SortedListAdapter<WordModel> {

    public interface Listener {
        void onExampleModelClicked(WordModel model);
    }

    private final Listener mListener;

    public ExampleAdapter(Context context, Comparator<WordModel> comparator, Listener listener) {
        super(context, WordModel.class, comparator);
        mListener = listener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends WordModel> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final ItemWordBinding binding = ItemWordBinding.inflate(inflater, parent, false);
        return new WordViewHolder(binding, mListener);
    }
}
