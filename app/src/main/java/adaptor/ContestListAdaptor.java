package adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.coviam.quizapp.R;

import java.util.List;

import pojo.ContestList;

public class ContestListAdaptor extends RecyclerView.Adapter<ContestListAdaptor.ViewHolder>{
    List<ContestList> myContestList;
    ContestDetails contestDetails;

    public ContestListAdaptor(List<ContestList> contestList,ContestDetails contestDetails1)
    {
        this.myContestList=contestList;
        this.contestDetails=contestDetails1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_contest_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView1.setText(myContestList.get(position).getContestName());
        holder.textView2.setText("Total questions :" + myContestList.get(position).getNoOfQuestions());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contestDetails.onClick(myContestList.get(position).getContestName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return myContestList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = itemView.findViewById(R.id.contestName);
            this.textView2 = itemView.findViewById(R.id.questions);
            constraintLayout = itemView.findViewById(R.id.constraintContest);
        }
    }

    public interface ContestDetails
    {
        void onClick(String contestDetails);
    }
}
