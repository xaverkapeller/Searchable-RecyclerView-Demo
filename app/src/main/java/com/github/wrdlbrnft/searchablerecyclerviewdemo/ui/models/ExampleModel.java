package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.models;

import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.sortedlistadapter.ViewModel;

/**
 * Created with Android Studio
 * User: Xaver
 * Date: 24/05/15
 */
public class ExampleModel implements ViewModel {

    private final String mText;

    public ExampleModel(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExampleModel that = (ExampleModel) o;

        return mText != null ? mText.equals(that.mText) : that.mText == null;

    }

    @Override
    public int hashCode() {
        return mText != null ? mText.hashCode() : 0;
    }
}
