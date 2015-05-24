package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.wrdlbrnft.searchablerecyclerviewdemo.R;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.models.ExampleModel;

public class ExampleViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvText;

    public ExampleViewHolder(View itemView) {
        super(itemView);

        tvText = (TextView) itemView.findViewById(R.id.tvText);
    }

    public void bind(ExampleModel model) {
        tvText.setText(model.getText());
    }
}
