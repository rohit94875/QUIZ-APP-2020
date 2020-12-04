package com.coviam.quizapp.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coviam.quizapp.R;
import com.coviam.quizapp.pojo.UserPositions;

import java.util.List;

public class ContestResultsAdaptor extends RecyclerView.Adapter<ContestResultsAdaptor.ViewHolder> {

    List<UserPositions> list;

    public ContestResultsAdaptor(List<UserPositions> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ContestResultsAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.results_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContestResultsAdaptor.ViewHolder holder, int position) {
        holder.counter.setText(String.valueOf(position+1));
        holder.userName.setText(list.get(position).getUserName());
        holder.userPoints.setText(String.valueOf(list.get(position).getPoints()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView counter;
        TextView userName;
        TextView userPoints;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            counter=itemView.findViewById(R.id.counter);
            userName=itemView.findViewById(R.id.idName);
            userPoints=itemView.findViewById(R.id.idScore);

        }
    }
}
