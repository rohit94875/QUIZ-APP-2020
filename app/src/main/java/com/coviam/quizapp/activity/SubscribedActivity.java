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
import com.coviam.quizapp.adaptor.SubscribedAdaptor;
import com.coviam.quizapp.api.APIInterface;
import com.coviam.quizapp.api.App2;
import com.coviam.quizapp.pojo.SubscribedResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribedActivity extends AppCompatActivity implements SubscribedAdaptor.SubscribedCommunication {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdaptor;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar loadbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribed);

        recyclerView=findViewById(R.id.subscribedRecycler);
        loadbar=findViewById(R.id.progressBar);
        layoutManager= new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        //getting userId from shared Preferences
        SharedPreferences sharedPreferences=getSharedPreferences("userAccess", Context.MODE_PRIVATE);

        //api call
        APIInterface apiInterface=App2.getClient().create(APIInterface.class);
        apiInterface.getSubscribedContent(sharedPreferences.getString("userId",null)).enqueue(new Callback<List<SubscribedResponse>>() {
            @Override
            public void onResponse(Call<List<SubscribedResponse>> call, Response<List<SubscribedResponse>> response) {
                loadbar.setVisibility(View.GONE);
                List<SubscribedResponse> list=response.body();
                mAdaptor=new SubscribedAdaptor(list, SubscribedActivity.this);
                recyclerView.setAdapter(mAdaptor);
            }

            @Override
            public void onFailure(Call<List<SubscribedResponse>> call, Throwable t) {

                Toast.makeText(SubscribedActivity.this,"server Error!!",Toast.LENGTH_SHORT).show();

            }
        });

        //bottomNavigation
        bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(SubscribedActivity.this,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.leaderboard:
                        startActivity(new Intent(SubscribedActivity.this,LeaderboardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(SubscribedActivity.this,ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    default:return false;
                }
            }
        });



    }

    @Override
    public void onClick(String contestId,String contestName) {

        Intent intent=new Intent(SubscribedActivity.this, StaticQuizActivity.class);
        intent.putExtra("contestId",contestId);
        intent.putExtra("contestName",contestName);
        startActivity(intent);
    }
}
