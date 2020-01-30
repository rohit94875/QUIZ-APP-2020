package com.coviam.quizapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coviam.quizapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import adaptor.ContestListAdaptor;
import api.API;
import api.APIInterface;
import okhttp3.ResponseBody;
import pojo.ContestList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestActivity extends AppCompatActivity implements ContestListAdaptor.ContestDetails {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter contestListAdaptor;
    APIInterface apiInterface;
    ProgressBar loadbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);

        layoutManager=new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerContest);
        recyclerView.setLayoutManager(layoutManager);
        loadbar=findViewById(R.id.progressBar);

        apiInterface= API.getClient().create(APIInterface.class);
        Intent intent=getIntent();

        Call<List<ContestList>> call=apiInterface.getContests(intent.getStringExtra("category_id"));
        call.enqueue(new Callback<List<ContestList>>() {
            @Override
            public void onResponse(Call<List<ContestList>> call, Response<List<ContestList>> response) {
                loadbar.setVisibility(View.GONE);
                List<ContestList> list=response.body();
                contestListAdaptor=new ContestListAdaptor(list,ContestActivity.this);
                recyclerView.setAdapter(contestListAdaptor);
            }

            @Override
            public void onFailure(Call<List<ContestList>> call, Throwable t) {
                loadbar.setVisibility(View.GONE);
                Toast.makeText(ContestActivity.this,"try harder",Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public void onClick(String contestDetails) {
        Intent intent=new Intent(getBaseContext(),LeaderboardActivity.class);
        intent.putExtra("contest_details",contestDetails);
        startActivity(intent);
    }
}
