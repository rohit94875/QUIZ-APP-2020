package com.coviam.quizapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.coviam.quizapp.R;
import com.coviam.quizapp.api.App7;
import com.coviam.quizapp.pojo.Analytics;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import adaptor.ContestListAdaptor;
import api.API;
import api.APIInterface;
import okhttp3.ResponseBody;
import com.coviam.quizapp.pojo.ContestList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestActivity extends AppCompatActivity implements ContestListAdaptor.ContestDetails {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter contestListAdaptor;
    APIInterface apiInterface;
    BottomNavigationView bottomNavigationView;
    ProgressBar loadbar;
    TextView dynamic;
    Button subscribe;
    List<ContestList> list;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);

        layoutManager=new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerContest);
        recyclerView.setLayoutManager(layoutManager);
        loadbar=findViewById(R.id.progressBar);

        apiInterface= API.getClient().create(APIInterface.class);
        intent=getIntent();

        Call<List<ContestList>> call=apiInterface.getContests(intent.getStringExtra("category_id"));
        call.enqueue(new Callback<List<ContestList>>() {
            @Override
            public void onResponse(Call<List<ContestList>> call, Response<List<ContestList>> response) {
                loadbar.setVisibility(View.GONE);
                list = response.body();
//                for(int i=0;i<list.size();i++){
//                    if(list.get(i).getComlpeted()){
//                        list.remove(i);
//                    }
//                }
                contestListAdaptor=new ContestListAdaptor(list,ContestActivity.this);
                recyclerView.setAdapter(contestListAdaptor);
            }

            @Override
            public void onFailure(Call<List<ContestList>> call, Throwable t) {
                loadbar.setVisibility(View.GONE);
                Toast.makeText(ContestActivity.this,"try harder",Toast.LENGTH_SHORT).show();
            }
        });

//        dynamic=findViewById(R.id.dynamicContest);
//        dynamic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Call<List<ContestList>> callDynamic=apiInterface.getDynamicContests(intent.getStringExtra("category_id"));
//                callDynamic.enqueue(new Callback<List<ContestList>>() {
//                    @Override
//                    public void onResponse(Call<List<ContestList>> call, Response<List<ContestList>> response) {
//                        loadbar.setVisibility(View.GONE);
//                        list = response.body();
//
//                        if(response.body()==null)
//                            Toast.makeText(ContestActivity.this,"No dynamic contests available",Toast.LENGTH_SHORT).show();
//                        else
//                            {
//                            contestListAdaptor = new ContestListAdaptor(list, ContestActivity.this);
//                            recyclerView.setAdapter(contestListAdaptor);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<ContestList>> call, Throwable t) {
//                        loadbar.setVisibility(View.GONE);
//                        Toast.makeText(ContestActivity.this,"try harder",Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(ContestActivity.this,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.subs:
                        startActivity(new Intent(ContestActivity.this,SubscribedActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.leaderboard:
                        startActivity(new Intent(ContestActivity.this,LeaderboardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(ContestActivity.this,ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    default:return false;
                }
            }
        });

    }



    @Override
    public void onClick(final ContestList pos,String contestId) {

        final SharedPreferences sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String userId=String.valueOf(sharedPreferences.getInt("userId",0));

        String contestType="static";
        Call<ResponseBody> call=apiInterface.postSubscribed(contestId,userId,contestType);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(ContestActivity.this,"Subscription successful",Toast.LENGTH_SHORT).show();
                apiInterface= App7.getClient().create(APIInterface.class);
                Analytics analytics=new Analytics();
                analytics.setAction("subscribe");
                analytics.setUserId(String.valueOf(sharedPreferences.getInt("userId",0)));
                analytics.setTagName(intent.getStringExtra("category_name"));
                Call<ResponseBody> callAnalytics=apiInterface.analytics(analytics);
                callAnalytics.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(ContestActivity.this,"Analytics success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ContestActivity.this,"Analytics failed",Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ContestActivity.this,"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
