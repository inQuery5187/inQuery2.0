package com.example.inquery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder> {

    private List<Requests> requestsList;
    private String type;
    DatabaseReference reference;

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
        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Approved! :D", Toast.LENGTH_SHORT).show();
                String sender= request.getSender();
                reference= FirebaseDatabase.getInstance().getReference("Data").child("Student").child("users");

            }
        });
        holder.deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Denied! :C", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView reasonTextView;
        TextView typeTextView;
        TextView approve;
        TextView deny;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.textSender);
            typeTextView = itemView.findViewById(R.id.textType);
            reasonTextView = itemView.findViewById(R.id.textReason);
            approve= itemView.findViewById(R.id.approve);
            deny = itemView.findViewById(R.id.deny);
        }
    }
}
