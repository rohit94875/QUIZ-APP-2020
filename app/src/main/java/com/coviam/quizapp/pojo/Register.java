package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Register {

    @SerializedName("name")
    private String userName;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public Register(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
