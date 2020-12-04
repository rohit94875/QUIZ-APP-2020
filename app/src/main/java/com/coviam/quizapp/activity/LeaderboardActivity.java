package com.coviam.quizapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coviam.quizapp.R;
import com.coviam.quizapp.adaptor.LeaderboardAdaptor;
import com.coviam.quizapp.api.APIInterface;
import com.coviam.quizapp.api.App2;
import com.coviam.quizapp.pojo.SubscribedResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardActivity extends AppCompatActivity implements LeaderboardAdaptor.LeaderboardCommunication {

    ProgressBar loadbar;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        recyclerView=findViewById(R.id.leaderboardRecyclerView);
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        loadbar=findViewById(R.id.progressBar);

        //getting userId
        SharedPreferences sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String uid=String.valueOf(sharedPreferences.getInt("userId",0));



        APIInterface apiInterface= App2.getClient().create(APIInterface.class);
        apiInterface.getleaderboard(uid).enqueue(new Callback<List<SubscribedResponse>>() {
            @Override
            public void onResponse(Call<List<SubscribedResponse>> call, Response<List<SubscribedResponse>> response) {
                loadbar.setVisibility(View.GONE);
                List<SubscribedResponse> list=response.body();
                adapter=new LeaderboardAdaptor(list,LeaderboardActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SubscribedResponse>> call, Throwable t) {
                loadbar.setVisibility(View.GONE);
                Toast.makeText(LeaderboardActivity.this,"server error",Toast.LENGTH_SHORT).show();

            }
        });

        //bottomNavigation
        bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(LeaderboardActivity.this,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.subs:
                        startActivity(new Intent(LeaderboardActivity.this,SubscribedActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(LeaderboardActivity.this,ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    default:return false;
                }
            }
        });

    }

    @Override
    public void onClick(String contestId,String contestName) {

        Intent intent=new Intent(LeaderboardActivity.this,ContestResults.class);
        intent.putExtra("contestId",contestId);
        intent.putExtra("contestName",contestName);
        startActivity(intent);
    }
}
