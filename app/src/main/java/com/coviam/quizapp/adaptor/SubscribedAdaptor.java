package com.coviam.quizapp.adaptor;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coviam.quizapp.R;
import com.coviam.quizapp.pojo.SubscribedResponse;

import java.util.List;

public class SubscribedAdaptor extends RecyclerView.Adapter<SubscribedAdaptor.ViewHolder> {

    List<SubscribedResponse> list;
    SubscribedCommunication subscribedCommunication;

    public SubscribedAdaptor(List<SubscribedResponse> list, SubscribedCommunication subscribedCommunication) {
        this.list = list;
        this.subscribedCommunication=subscribedCommunication;
    }

    @NonNull
    @Override
    public SubscribedAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.sub_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubscribedAdaptor.ViewHolder holder, final int position) {
        holder.contextName.setText(list.get(position).getContestName());
        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribedCommunication.onClick(list.get(position).getContestId(),list.get(position).getContestName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contextName;
        Button start;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contextName=itemView.findViewById(R.id.contentName);
            start=itemView.findViewById(R.id.startContent);
        }
    }
    public interface SubscribedCommunication{
        void onClick(String contestId,String contestName);
    }
}
