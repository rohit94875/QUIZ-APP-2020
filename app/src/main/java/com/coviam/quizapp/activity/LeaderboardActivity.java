package com.coviam.quizapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.coviam.quizapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LeaderboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

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
                    case R.id.leaderboard:
                        startActivity(new Intent(LeaderboardActivity.this,LeaderboardActivity.class));
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

}
