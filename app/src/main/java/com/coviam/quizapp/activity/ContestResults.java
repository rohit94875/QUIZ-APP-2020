package com.coviam.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.coviam.quizapp.R;
import com.coviam.quizapp.adaptor.ContestResultsAdaptor;
import com.coviam.quizapp.api.APIInterface;
import com.coviam.quizapp.api.App2;
import com.coviam.quizapp.api.App5;
import com.coviam.quizapp.pojo.LeaderBoardDetails;
import com.coviam.quizapp.pojo.ReportCreate;
import com.coviam.quizapp.pojo.UserPositions;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestResults extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    TextView nameOfContent;
    TextView value;
    SharedPreferences sharedPreferences;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_results);

        recyclerView=findViewById(R.id.resultsRecycler);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        progressBar=findViewById(R.id.progressBar);
        nameOfContent=findViewById(R.id.contestName);
        value=findViewById(R.id.value);
        sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE);

        Intent intent=getIntent();
        String contestName=intent.getStringExtra("contestName");
        String contestId=intent.getStringExtra("contestId");

        nameOfContent.setText(contestName);


        apiInterface= App5.getClient().create(APIInterface.class);
        final LeaderBoardDetails leaderBoardDetails=new LeaderBoardDetails();
        leaderBoardDetails.setContestId(contestId);
        leaderBoardDetails.setUserId(String.valueOf(sharedPreferences.getInt("userId",0)));
        ReportCreate reportCreate=new ReportCreate();
        reportCreate.setContestId(contestId);
        apiInterface.reportCreate(reportCreate).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                apiInterface.getLeaderboardPosition(leaderBoardDetails).enqueue(new Callback<List<UserPositions>>() {
                    @Override
                    public void onResponse(Call<List<UserPositions>> call, Response<List<UserPositions>> response) {
                        progressBar.setVisibility(View.GONE);
                        List<UserPositions> list=response.body();
                        value.setText(String.valueOf(list.get(0).getRank()));
                        list.remove(0);
                        adapter=new ContestResultsAdaptor(list);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<UserPositions>> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);

                    }
                });

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(ContestResults.this,"backend do better job",Toast.LENGTH_SHORT).show();

            }
        });








    }
}
