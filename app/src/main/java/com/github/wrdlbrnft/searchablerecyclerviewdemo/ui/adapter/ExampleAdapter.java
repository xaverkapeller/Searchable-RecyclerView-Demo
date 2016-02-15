package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.wrdlbrnft.searchablerecyclerviewdemo.R;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.models.ExampleModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio
 * User: Xaver
 * Date: 24/05/15
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private final LayoutInflater mInflater;
    private final List<ExampleModel> mModels;
    ItemClickListener onItemClickListener;

    /**
     * Declare the ItemClickListener
     */
    public interface ItemClickListener{
        public void onItemClick(View view,int position);
    }

    public ExampleAdapter(Context context, List<ExampleModel> models) {
        mInflater = LayoutInflater.from(context);
        mModels = new ArrayList<>(models);
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = mInflater.inflate(R.layout.item_example, parent, false);
        return new ExampleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        final ExampleModel model = mModels.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public void animateTo(List<ExampleModel> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<ExampleModel> newModels) {
        for (int i = mModels.size() - 1; i >= 0; i--) {
            final ExampleModel model = mModels.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ExampleModel> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ExampleModel model = newModels.get(i);
            if (!mModels.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ExampleModel> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ExampleModel model = newModels.get(toPosition);
            final int fromPosition = mModels.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public ExampleModel removeItem(int position) {
        final ExampleModel model = mModels.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, ExampleModel model) {
        mModels.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final ExampleModel model = mModels.remove(fromPosition);
        mModels.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * Method for initializing the adapter's onitemClickListener to the one which is
     * defined in the Main thread. The user can define the work to be done in the listener
     * at main thread and assign it to the adapter's Listener.
     * @param listener : NotNull. Custom ItemClickListener defined by the user
     */

    public void setItemClickListener(final ItemClickListener listener){
        this.onItemClickListener=listener;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvText;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            tvText = (TextView) itemView.findViewById(R.id.tvText);
            itemView.setOnClickListener(this);
        }

        public void bind(ExampleModel model) {
            tvText.setText(model.getText());
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener!=null)
                onItemClickListener.onItemClick(v,getAdapterPosition());
        }

    }
}
