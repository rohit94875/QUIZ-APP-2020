package com.coviam.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coviam.quizapp.R;

import api.API;
import api.APIInterface;

public class AdActivity extends AppCompatActivity {

    Button ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String userId=sharedPreferences.getString("userId",null);

        setContentView(R.layout.activity_ad);
        ad=findViewById(R.id.back);
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        APIInterface apiInterface= API.getClient().create(APIInterface.class);
        //apiInterface.getAd()


    }
}
