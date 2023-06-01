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
    private String type, str, ID;
    DatabaseReference reference, db;

    public RequestsAdapter(List<Requests> requestsList, String type, String str, String ID) {
        this.requestsList = requestsList;
        this.type= type;
        this.str= str;
        this.ID= ID;

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

        holder.idTextView.setText(str+request.getSender());
        holder.typeTextView.setText("Type: "+type);
        holder.reasonTextView.setText(request.getReason());
        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Approved! :D", Toast.LENGTH_SHORT).show();
                reference= FirebaseDatabase.getInstance().getReference("Data").child("Student").child("users").child(request.getSender()).child("requestHistory");
                reference.child(request.getUID()).child("status").setValue("Approved! :D");
                //Remove node from firebase
                db= FirebaseDatabase.getInstance().getReference("Data").child("Faculty").child("users").child(ID).child(type);
                db.child(request.getSender()).removeValue();
                //reload the activity
                f_requests activity= (f_requests) v.getContext();
                activity.recreate();
            }
        });
        holder.deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Denied! :C", Toast.LENGTH_SHORT).show();
                reference= FirebaseDatabase.getInstance().getReference("Data").child("Student").child("users").child(request.getSender()).child("requestHistory");
                reference.child(request.getUID()).child("status").setValue("Denied! :C");
                //remove node from firebase
                db= FirebaseDatabase.getInstance().getReference("Data").child("Faculty").child("users").child(ID).child(type);
                db.child(request.getSender()).removeValue();
                //reload the activity
                f_requests activity= (f_requests) v.getContext();
                activity.recreate();
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
