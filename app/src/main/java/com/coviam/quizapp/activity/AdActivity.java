package com.coviam.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coviam.quizapp.R;
import com.coviam.quizapp.api.App3;
import com.coviam.quizapp.pojo.AdResponse;

import java.util.List;

import api.API;
import api.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdActivity extends AppCompatActivity {

    Button ad;
    ImageView imageView;
    TextView textView;
    int counter;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String userId=String.valueOf(sharedPreferences.getInt("userId",0));

        setContentView(R.layout.activity_ad);
        ad=findViewById(R.id.back);
        imageView=findViewById(R.id.image);
        textView=findViewById(R.id.adText);
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent=getIntent();
        counter=intent.getIntExtra("counter",0);
        counter=counter/3;

        APIInterface apiInterface= App3.getClient().create(APIInterface.class);
        apiInterface.getAds("Bearer " +sharedPreferences.getString("token",null))
                .enqueue(new Callback<List<AdResponse>>() {
                    @Override
                    public void onResponse(Call<List<AdResponse>> call, Response<List<AdResponse>> response) {
                        List<AdResponse> list=response.body();
                        url=response.body().get(counter).getTargetUrl();
                        Glide.with(AdActivity.this).load(list.get(counter).getImageUrl()).into(imageView);
                        textView.setText(list.get(counter).getDescription());
                    }

                    @Override
                    public void onFailure(Call<List<AdResponse>> call, Throwable t) {
                        Toast.makeText(AdActivity.this,"server error",Toast.LENGTH_SHORT).show();

                    }
                });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri=Uri.parse(url);
                Intent intent1=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent1);

            }
        });



    }
}
