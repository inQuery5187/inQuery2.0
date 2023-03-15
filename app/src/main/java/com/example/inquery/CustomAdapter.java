package com.example.inquery;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Requests[] localDataSet;
    private TextView textType;
    private TextView textSender;
    private TextView textReason;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textType;
        private final TextView textSender;
        private final TextView textReason;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textType = (TextView) view.findViewById(R.id.textType);
            textSender = (TextView) view.findViewById(R.id.textSender);
            textReason = (TextView) view.findViewById(R.id.textReason);
        }

        public TextView getTextView() {
            return textType;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(Requests[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_query, viewGroup, false);
        textType= view.findViewById(R.id.textType);
        textSender= view.findViewById(R.id.textSender);
        textReason= view.findViewById(R.id.textReason);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        textType.setText("Type: "+ localDataSet[position].type);
        textSender.setText("Sender: "+localDataSet[position].sender);
        textReason.setText(localDataSet[position].reason);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
