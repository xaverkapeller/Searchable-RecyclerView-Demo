package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.models;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

/**
 * Created with Android Studio
 * User: Xaver
 * Date: 24/05/15
 */
public class WordModel implements SortedListAdapter.ViewModel {

    private final long mId;
    private final int mRank;
    private final String mWord;

    public WordModel(long id, int rank, String word) {
        mId = id;
        mRank = rank;
        mWord = word;
    }

    public long getId() {
        return mId;
    }

    public int getRank() {
        return mRank;
    }

    public String getWord() {
        return mWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordModel wordModel = (WordModel) o;

        if (mRank != wordModel.mRank) return false;
        return mWord != null ? mWord.equals(wordModel.mWord) : wordModel.mWord == null;

    }

    @Override
    public int hashCode() {
        int result = mRank;
        result = 31 * result + (mWord != null ? mWord.hashCode() : 0);
        return result;
    }

    @Override
    public <T> boolean isSameModelAs(T item) {
        if (item instanceof WordModel) {
            final WordModel wordModel = (WordModel) item;
            return wordModel.mId == mId;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(T item) {
        if (item instanceof WordModel) {
            return equals(item);
        }
        return false;
    }
}
