package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.models;

import android.support.annotation.NonNull;

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
    public <T> boolean isSameModelAs(@NonNull T item) {
        if (item instanceof WordModel) {
            final WordModel wordModel = (WordModel) item;
            return wordModel.mId == mId;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T item) {
        if (item instanceof WordModel) {
            final WordModel other = (WordModel) item;
            if (mRank != other.mRank) {
                return false;
            }
            return mWord != null ? mWord.equals(other.mWord) : other.mWord == null;
        }
        return false;
    }
}
