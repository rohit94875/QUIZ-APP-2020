package com.coviam.quizapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coviam.quizapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import api.API;
import api.APIInterface;
import com.coviam.quizapp.pojo.Profile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    APIInterface apiInterface;
    ProgressBar loadbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView userName=findViewById(R.id.user);
        final TextView easyAnswered=findViewById(R.id.easyAns);
        final TextView medAnswered=findViewById(R.id.medAns);
        final TextView diffAnswered=findViewById(R.id.diffAns);
        final TextView points=findViewById(R.id.points);
        final ImageView imageView=findViewById(R.id.img);
        final TextView nextLevel=findViewById(R.id.nextLevel);
        final TextView nextDes=findViewById(R.id.nextDes);
        loadbar=findViewById(R.id.progressBar);

        apiInterface= API.getClient().create(APIInterface.class);
        Call<Profile> call=apiInterface.getPoints();
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                loadbar.setVisibility(View.GONE);
                Profile list=response.body();
                userName.setText(list.getUserName());
                easyAnswered.setText(list.getEasyAns());
                medAnswered.setText(list.getMedAns());
                diffAnswered.setText(list.getDiffAns());
                points.setText(list.getPoints());

                int score=Integer.parseInt(points.getText().toString());
                if(score<1000 || score==1000)
                {
                    Glide.with(ProfileActivity.this).load("https://pubglookup.com/assets/img/ranks/Rank_Icon_01_bronze.png").into(imageView);
                    String text=String.valueOf(1000-score);
                    nextLevel.setText(text);
                    nextDes.setVisibility(View.VISIBLE);
                }
                else if(score>1000 && score<2500)
                {
                    Glide.with(ProfileActivity.this).load("https://lh5.googleusercontent.com/proxy/h7_nhcOTSM-hi1KAKtY9z5xVRMNdHGYxVVpgWv-MzQNwA2usX0BXlCIW46aJU9WLqpAgs3tXS-hJeewAIPxXJOr3ylQ7ElmWA0veA2hxyGlFtG_Q_nYw5z60EQ").into(imageView);
                    String text=String.valueOf(2500-score);
                    nextLevel.setText(text);
                    nextDes.setVisibility(View.VISIBLE);
                }
                else if(score>2500 && score<6000)
                {
                    Glide.with(ProfileActivity.this).load("https://www.stickpng.com/assets/images/580b585b2edbce24c47b2af3.png").into(imageView);
                    String text=String.valueOf(6000-score);
                    nextLevel.setText(text);
                    nextDes.setVisibility(View.VISIBLE);
                }
                else if(score>6000)
                {
                    Glide.with(ProfileActivity.this).load("https://thumbnail.imgbin.com/2/14/9/imgbin-silver-medal-gold-medal-medal-TXjPFnAHnfRAJhdd27xLtbTSs_t.jpg").into(imageView);
                    nextLevel.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                loadbar.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this,"try harder",Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.subs:
                        startActivity(new Intent(ProfileActivity.this,SubscribedActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.leaderboard:
                        startActivity(new Intent(ProfileActivity.this,LeaderboardActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(ProfileActivity.this,ProfileActivity.class));
                        bottomNavigationView.hasFocus();
                        overridePendingTransition(0,0);
                        return true;
                        default:return false;
                }
            }
        });
    }
}
