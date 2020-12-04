package com.coviam.quizapp.adaptor;

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

public class LeaderboardAdaptor extends RecyclerView.Adapter<LeaderboardAdaptor.ViewHolder> {

    List<SubscribedResponse> list;
    LeaderboardCommunication leaderboardCommunication;

    public LeaderboardAdaptor(List<SubscribedResponse> list,LeaderboardCommunication leaderboardCommunication) {
        this.list = list;
        this.leaderboardCommunication=leaderboardCommunication;
    }

    @NonNull
    @Override
    public LeaderboardAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.sub_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardAdaptor.ViewHolder holder, final int position) {

        holder.contestName.setText(list.get(position).getContestName());
        holder.showLeaderboard.setText("show");
        holder.showLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaderboardCommunication.onClick(list.get(position).getContestId(),list.get(position).getContestName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contestName;
        Button showLeaderboard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contestName=itemView.findViewById(R.id.contestName);
            showLeaderboard=itemView.findViewById(R.id.startContent);
        }
    }
    public interface LeaderboardCommunication{
        void onClick(String contestId,String contestName);
    }
}
