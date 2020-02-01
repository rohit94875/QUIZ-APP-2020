package com.coviam.quizapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coviam.quizapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import adaptor.CategoryAdaptor;
import api.API;
import api.APIInterface;

import com.coviam.quizapp.pojo.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CategoryAdaptor.CategoryDetails {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter categoryAdaptor;
    APIInterface apiInterface;
    ProgressBar loadbar;
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutManager=new GridLayoutManager(this,2);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        loadbar=findViewById(R.id.progressBar);

        apiInterface= API.getClient().create(APIInterface.class);
        Call<List<Category>> call= apiInterface.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                loadbar.setVisibility(View.GONE);
                List<Category> list=response.body();
                categoryAdaptor=new CategoryAdaptor(list,MainActivity.this);
                recyclerView.setAdapter(categoryAdaptor);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                loadbar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,"try harder",Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.subs:
                        startActivity(new Intent(MainActivity.this,SubscribedActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.leaderboard:
                        startActivity(new Intent(MainActivity.this,LeaderboardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    default:return false;
                }
            }
        });
    }

    @Override
    public void onClick(final String categoryId) {
        Intent intent=new Intent(getBaseContext(),ContestActivity.class);
        intent.putExtra("category_id",categoryId);
        startActivity(intent);
    }
}
