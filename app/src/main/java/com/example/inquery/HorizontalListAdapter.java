package com.example.inquery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HorizontalListAdapter extends RecyclerView.Adapter<HorizontalListAdapter.ViewHolder> {
    private ArrayList<Integer> images;
    private ArrayList<String> requests;
    private Context context;

    public HorizontalListAdapter(Context context, ArrayList<Integer> images, ArrayList<String> requests) {
        this.images = images;
        this.requests= requests;
        this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_querytype, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String request = requests.get(position);
        int image= images.get(position);
        holder.imageView.setImageResource(image);
        holder.textView.setText(request);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.getAdapterPosition()== 0){
                    Intent intent = new Intent(context, qleave.class);
                    context.startActivity(intent);
                }
                else if(holder.getAdapterPosition()== 1) {
                    Intent intent = new Intent(context, qcomplaint.class);
                    context.startActivity(intent);
                }
                else if(holder.getAdapterPosition()== 2) {
                    Intent intent = new Intent(context, qmisconduct.class);
                    context.startActivity(intent);
                }
                else if(holder.getAdapterPosition()== 3) {
                    Intent intent = new Intent(context, qcustom.class);
                    context.startActivity(intent);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.request);
            imageView= itemView.findViewById(R.id.requestLogo);
        }


    }
}
