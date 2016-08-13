package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.sortedlistadapter;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with Android Studio
 * User: Xaver
 * Date: 13/08/16
 */

public abstract class SortedListAdapter<T extends ViewModel> extends RecyclerView.Adapter<BaseViewHolder<? extends T>> {

    public interface Editor<T extends ViewModel> {
        Editor<T> add(T item);
        Editor<T> add(T... items);
        Editor<T> add(List<T> items);
        Editor<T> remove(T item);
        Editor<T> remove(T... items);
        Editor<T> remove(List<T> items);
        Editor<T> update(List<T> items);
        void commit();
    }

    public interface Filter<T> {
        boolean test(T item);
    }

    private final SortedList.Callback<T> mCallback = new SortedList.Callback<T>() {
        @Override
        public int compare(T a, T b) {
            return mComparator.compare(a, b);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(T oldItem, T newItem) {
            return SortedListAdapter.this.areContentsTheSame(oldItem, newItem);
        }

        @Override
        public boolean areItemsTheSame(T item1, T item2) {
            return SortedListAdapter.this.areItemsTheSame(item1, item2);
        }
    };

    private final LayoutInflater mInflater;
    private final SortedList<T> mSortedList;
    private final Comparator<T> mComparator;

    public SortedListAdapter(Context context, Class<T> itemClass, Comparator<T> comparator) {
        mInflater = LayoutInflater.from(context);
        mSortedList = new SortedList<>(itemClass, mCallback);
        mComparator = comparator;
    }

    @Override
    public final BaseViewHolder<? extends T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(mInflater, parent, viewType);
    }

    protected abstract BaseViewHolder<? extends T> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    protected boolean areContentsTheSame(T oldItem, T newItem) {
        return oldItem.equals(newItem);
    }

    protected boolean areItemsTheSame(T item1, T item2) {
        return item1 == item2;
    }

    @Override
    public final void onBindViewHolder(BaseViewHolder<? extends T> holder, int position) {
        final T item = mSortedList.get(position);
        ((BaseViewHolder<T>) holder).bind(item);
    }

    public final Editor<T> edit() {
        return new EditorImpl();
    }

    public final T getItem(int position) {
        return mSortedList.get(position);
    }

    @Override
    public final int getItemCount() {
        return mSortedList.size();
    }

    public final List<T> filter(Filter<T> filter) {
        final List<T> list = new ArrayList<>();
        for (int i = 0, count = mSortedList.size(); i < count; i++) {
            final T item = mSortedList.get(i);
            if (filter.test(item)) {
                list.add(item);
            }
        }
        return list;
    }

    public final T filterOne(Filter<T> filter) {
        for (int i = 0, count = mSortedList.size(); i < count; i++) {
            final T item = mSortedList.get(i);
            if (filter.test(item)) {
                return item;
            }
        }
        return null;
    }

    private interface Action<T extends ViewModel> {
        void perform(SortedList<T> list);
    }

    private class EditorImpl implements Editor<T> {

        private final List<Action<T>> mActions = new ArrayList<>();

        @Override
        public Editor<T> add(final T item) {
            mActions.add(new Action<T>() {
                @Override
                public void perform(SortedList<T> list) {
                    mSortedList.add(item);
                }
            });
            return this;
        }

        @SafeVarargs
        @Override
        public final Editor<T> add(T... items) {
            return add(Arrays.asList(items));
        }

        @Override
        public Editor<T> add(final List<T> items) {
            mActions.add(new Action<T>() {
                @Override
                public void perform(SortedList<T> list) {
                    Collections.sort(items, mComparator);
                    mSortedList.addAll(items);
                }
            });
            return this;
        }

        @Override
        public Editor<T> remove(final T item) {
            mActions.add(new Action<T>() {
                @Override
                public void perform(SortedList<T> list) {
                    mSortedList.remove(item);
                }
            });
            return this;
        }

        @SafeVarargs
        @Override
        public final Editor<T> remove(final T... items) {
            mActions.add(new Action<T>() {
                @Override
                public void perform(SortedList<T> list) {
                    for (T item : items) {
                        mSortedList.remove(item);
                    }
                }
            });
            return this;
        }

        @Override
        public Editor<T> remove(final List<T> items) {
            mActions.add(new Action<T>() {
                @Override
                public void perform(SortedList<T> list) {
                    for (T item : items) {
                        mSortedList.remove(item);
                    }
                }
            });
            return this;
        }

        @Override
        public Editor<T> update(final List<T> items) {
            mActions.add(new Action<T>() {
                @Override
                public void perform(SortedList<T> list) {
                    final List<T> itemsToRemove = filter(new Filter<T>() {
                        @Override
                        public boolean test(T item) {
                            return !items.contains(item);
                        }
                    });
                    for (int i = itemsToRemove.size() - 1; i >= 0; i--) {
                        final T item = itemsToRemove.get(i);
                        mSortedList.remove(item);
                    }
                    Collections.sort(items, mComparator);
                    mSortedList.addAll(items);
                }
            });
            return this;
        }

        @Override
        public void commit() {
            mSortedList.beginBatchedUpdates();
            for (Action<T> action : mActions) {
                action.perform(mSortedList);
            }
            mSortedList.endBatchedUpdates();
        }
    }
}
