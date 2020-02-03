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

import com.coviam.quizapp.api.App6;
import com.coviam.quizapp.pojo.Register;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView userName;
    TextView emailId;
    TextView password;
    Button registration;
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         userName=findViewById(R.id.editname);
         emailId=findViewById(R.id.editemail);
         password=findViewById(R.id.editpass);
         registration=findViewById(R.id.register);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=userName.getText().toString();
                String email=emailId.getText().toString();
                String pass=password.getText().toString();

                final Register register=new Register(user,email,pass);
                apiInterface= App6.getClient().create(APIInterface.class);
                Call<ResponseBody> call=apiInterface.postRegistrationDetails(register);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(RegisterActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getBaseContext(),LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
