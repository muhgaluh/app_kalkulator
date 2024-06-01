package com.example.aplikasikalkulator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holder> {

    ArrayList<HistoryModel> listHistory;

    public HistoryAdapter(ArrayList<HistoryModel> listHistory) {
        this.listHistory = listHistory;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        HistoryModel historyModel = listHistory.get(position);
        holder.txtnilai1.setText(String.valueOf(historyModel.getNilai1()));
        holder.txtOperator.setText(String.valueOf(historyModel.getOperator()));
        holder.txtnilai2.setText(String.valueOf(historyModel.getNilai2()));
        holder.txtHasil.setText(String.valueOf(historyModel.getHasil()));
    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txtnilai1, txtOperator, txtnilai2, txtHasil;
        LinearLayout linierLayout;

        public Holder(@NonNull View itemView) {
            super(itemView);

            txtnilai1 = itemView.findViewById(R.id.txtnilai1);
            txtOperator = itemView.findViewById(R.id.txtOperator);
            txtnilai2 = itemView.findViewById(R.id.txtnilai2);
            txtHasil = itemView.findViewById(R.id.txtHasil);
            linierLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
