package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.sortedlistadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created with Android Studio
 * User: Xaver
 * Date: 13/08/16
 */

public abstract class BaseViewHolder<T extends ViewModel> extends RecyclerView.ViewHolder {

    private T mCurrentItem;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public final void bind(T item) {
        mCurrentItem = item;
        performBind(item);
    }

    protected abstract void performBind(T item);

    public final T getCurrentItem() {
        return mCurrentItem;
    }
}
