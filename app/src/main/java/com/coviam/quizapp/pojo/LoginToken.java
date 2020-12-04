package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

public class LoginToken {

    @SerializedName("accessToken")
    String token;

    @SerializedName("tokenType")
    String tokenType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
