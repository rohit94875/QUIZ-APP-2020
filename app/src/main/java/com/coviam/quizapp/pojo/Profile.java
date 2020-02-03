package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("userId")
    private String userName;

    @SerializedName("easy")
    private String easyAns;

    @SerializedName("medium")
    private String medAns;

    @SerializedName("difficult")
    private String diffAns;

    @SerializedName("points")
    private String points;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEasyAns() {
        return easyAns;
    }

    public void setEasyAns(String easyAns) {
        this.easyAns = easyAns;
    }

    public String getMedAns() {
        return medAns;
    }

    public void setMedAns(String medAns) {
        this.medAns = medAns;
    }

    public String getDiffAns() {
        return diffAns;
    }

    public void setDiffAns(String diffAns) {
        this.diffAns = diffAns;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
