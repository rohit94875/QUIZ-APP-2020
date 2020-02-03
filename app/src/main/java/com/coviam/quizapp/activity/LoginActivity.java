package com.coviam.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coviam.quizapp.R;

import api.API;
import api.APIInterface;
import okhttp3.ResponseBody;

import com.coviam.quizapp.api.App6;
import com.coviam.quizapp.api.App7;
import com.coviam.quizapp.pojo.Analytics;
import com.coviam.quizapp.pojo.Login;
import com.coviam.quizapp.pojo.LoginResponse;
import com.coviam.quizapp.pojo.LoginToken;
import com.coviam.quizapp.pojo.Provider;
import com.coviam.quizapp.pojo.Role;
import com.coviam.quizapp.pojo.RoleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    APIInterface apiInterface;
    String tokenId;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView userName=findViewById(R.id.userName);
        final TextView password=findViewById(R.id.password);
        TextView newUser=findViewById(R.id.newUser);
        Button signIn=findViewById(R.id.signin);

        sharedPreferences=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String user=userName.getText().toString();
                final String pass=password.getText().toString();
                final Login login=new Login(user,pass);
                apiInterface= App6.getClient().create(APIInterface.class);
                Call<LoginToken> call=apiInterface.login(login);
                call.enqueue(new Callback<LoginToken>() {
                    @Override
                    public void onResponse(Call<LoginToken> call, Response<LoginToken> response) {
                        Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                        LoginToken token=response.body();
                        editor.putString("userName",user);
                        editor.putString("password",pass);
                        editor.putBoolean("flag",true);
                        tokenId=token.getToken();
                        editor.putString("token",tokenId);
                        Provider provider=new Provider();
                        Call<LoginResponse> callResponse=apiInterface.getUserDetails("Bearer "+tokenId,provider);
                        callResponse.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                String s=response.body().getName();
                                editor.putInt("userId",response.body().getId());
                                editor.putString("name",s);
                                editor.commit();
                                Toast.makeText(LoginActivity.this,"user name received",Toast.LENGTH_SHORT).show();

                                apiInterface= App7.getClient().create(APIInterface.class);
                                Analytics analytics=new Analytics();
                                analytics.setAction("login");
                                analytics.setUserId(String.valueOf(sharedPreferences.getInt("userId",0)));
                                Call<ResponseBody> callAnalytics=apiInterface.analytics(analytics);
                                callAnalytics.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        Intent intent=new Intent(getBaseContext(),ProfileActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(LoginActivity.this,"Analytics success",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(LoginActivity.this,"Analytics failed",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                Toast.makeText(LoginActivity.this,"Api Fail",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<LoginToken> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                });



//                apiInterface= App6.getClient().create(APIInterface.class);

//                callResponse.enqueue(new Callback<LoginResponse>() {
//                    @Override
//                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                        LoginResponse loginResponse=response.body();
//                        if(loginResponse.getRole()=="null")
//                        {
//                            Role role=new Role();
//                            Call<RoleResponse> callResponse= apiInterface.updateRole("Bearer "+tokenId,role);
//                            callResponse.enqueue(new Callback<RoleResponse>() {
//                                @Override
//                                public void onResponse(Call<RoleResponse> call, Response<RoleResponse> response) {
//                                    if(response.body().isSuccess())
//                                    {
//                                        Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<RoleResponse> call, Throwable t) {
//                                    Toast.makeText(LoginActivity.this,"Server Error",Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            Intent intent=new Intent(getBaseContext(),MainActivity.class);
//                            startActivity(intent);
//                        }
//                        else if(loginResponse.getRole()=="user")
//                        {
//                            Intent intent=new Intent(getBaseContext(),MainActivity.class);
//                            startActivity(intent);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginResponse> call, Throwable t) {
//                        Toast.makeText(LoginActivity.this,"Not an user",Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });



        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
