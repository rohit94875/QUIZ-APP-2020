package com.coviam.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.coviam.quizapp.R;
import com.coviam.quizapp.adaptor.ContestResultsAdaptor;
import com.coviam.quizapp.api.APIInterface;
import com.coviam.quizapp.api.App2;
import com.coviam.quizapp.pojo.UserPositions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestResults extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    TextView nameOfContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_results);

        recyclerView=findViewById(R.id.resultsRecycler);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        progressBar=findViewById(R.id.progressBar);
        nameOfContent=findViewById(R.id.contentName);

        Intent intent=getIntent();
        nameOfContent.setText(intent.getStringExtra("contestName"));


        APIInterface apiInterface= App2.getClient().create(APIInterface.class);
        apiInterface.getLeaderboardPosition(intent.getStringExtra("contestId")).enqueue(new Callback<List<UserPositions>>() {
            @Override
            public void onResponse(Call<List<UserPositions>> call, Response<List<UserPositions>> response) {
                progressBar.setVisibility(View.GONE);
                List<UserPositions> list=response.body();
                adapter=new ContestResultsAdaptor(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<UserPositions>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}
