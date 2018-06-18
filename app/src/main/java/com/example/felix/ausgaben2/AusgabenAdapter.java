package com.example.felix.ausgaben2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AusgabenAdapter extends RecyclerView.Adapter<AusgabenAdapter.MyViewHolder> {

    private List<Ausgaben> ausgabenList;

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView activityString, price, date;

        public MyViewHolder(View view) {
            super(view);
            activityString = (TextView) view.findViewById(R.id.activityString);
            price = (TextView) view.findViewById(R.id.price);
            date = (TextView) view.findViewById(R.id.date);
        }
    }


    public AusgabenAdapter(List<Ausgaben> ausgabenList) {
        this.ausgabenList = ausgabenList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ausgaben ausgaben = ausgabenList.get(position);
        holder.activityString.setText(ausgaben.getActivtiyString());
        //TODO parameter cast to int (have to be a double value)
        holder.price.setText(String.valueOf(ausgaben.getPrice()));
        String reportDate = df.format(ausgaben.getDate());
        holder.date.setText(reportDate);

    }

    @Override
    public int getItemCount() {
        return ausgabenList.size();
    }
}
