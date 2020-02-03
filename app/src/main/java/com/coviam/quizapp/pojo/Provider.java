package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Provider {

    @SerializedName("provider")
    private long provider=3;

    public long getProvider() {
        return provider;
    }

    public void setProvider(long provider) {
        this.provider = provider;
    }
}
