package com.example.inquery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder> {

    private List<Requests> requestsList;
    private String type;

    public RequestsAdapter(List<Requests> requestsList, String type) {
        this.requestsList = requestsList;
        this.type= type;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_query, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        Requests request = requestsList.get(position);

        holder.idTextView.setText("Sender: "+request.getSender());
        holder.typeTextView.setText("Type: "+type);
        holder.reasonTextView.setText(request.getReason());
    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView reasonTextView;
        TextView typeTextView;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.textSender);
            typeTextView = itemView.findViewById(R.id.textType);
            reasonTextView = itemView.findViewById(R.id.textReason);
        }
    }
}
