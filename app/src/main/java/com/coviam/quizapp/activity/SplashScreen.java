package com.coviam.quizapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.coviam.quizapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful())
                        {
                            String token=task.getResult().getToken();
                            Log.d("my app", "onComplete: Token: "+token);
                            Toast.makeText(SplashScreen.this,"Token generated",Toast.LENGTH_SHORT).show();
                        }
                        else
                            {
                            Toast.makeText(SplashScreen.this,"Token NOT generated",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("flag",false)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this,
                            MainActivity.class);

                    startActivity(i);

                    finish();

                }
            }, SPLASH_SCREEN_TIME_OUT);
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this,
                            LoginActivity.class);

                    startActivity(i);

                    finish();

                }
            }, SPLASH_SCREEN_TIME_OUT);

        }
    }
}
