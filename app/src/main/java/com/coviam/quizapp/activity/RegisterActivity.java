package com.coviam.quizapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coviam.quizapp.R;

import api.API;
import api.APIInterface;
import okhttp3.ResponseBody;
import pojo.Register;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView userName=findViewById(R.id.editname);
    TextView emailId=findViewById(R.id.editemail);
    TextView password=findViewById(R.id.editpass);
    Button registration=findViewById(R.id.register);
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String user=userName.getText().toString();
        String email=emailId.getText().toString();
        String pass=password.getText().toString();

        final Register register=new Register(user,email,pass);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface= API.getClient().create(APIInterface.class);
                Call<ResponseBody> call=apiInterface.postRegistrationDetails(register);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(RegisterActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
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
