package com.coviam.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coviam.quizapp.R;

import api.API;
import api.APIInterface;
import okhttp3.ResponseBody;
import pojo.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView userName=findViewById(R.id.userName);
    TextView password=findViewById(R.id.password);
    TextView newUser=findViewById(R.id.newUser);
    Button signIn=findViewById(R.id.signin);
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String user=userName.getText().toString();
        String pass=password.getText().toString();

        final Login login=new Login(user,pass);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface= API.getClient().create(APIInterface.class);
                Call<ResponseBody> call=apiInterface.login(login);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(LoginActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
