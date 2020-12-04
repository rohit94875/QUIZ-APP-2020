package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

public class ContestList {

    @SerializedName("contestName")
    private String contestName;

    @SerializedName("contestId")
    private String contestId;

    @SerializedName("categoryId")
    private String categoryId;

    @SerializedName("contestTimeLimit")
    private String contestTimeLimit;

    @SerializedName("noOfQuestions")
    private String noOfQuestions;

    @SerializedName("contestStartTime")
    private String contestStartTime;

    @SerializedName("noOfSkipsAllowed")
    private String noOfSkipsAllowed;

    @SerializedName("userId")
    private String userId;

    @SerializedName("isCompleted")
    private Boolean isComlpeted;

    public Boolean getComlpeted() {
        return isComlpeted;
    }

    public void setComlpeted(Boolean comlpeted) {
        isComlpeted = comlpeted;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getContestTimeLimit() {
        return contestTimeLimit;
    }

    public void setContestTimeLimit(String contestTimeLimit) {
        this.contestTimeLimit = contestTimeLimit;
    }

    public String getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(String noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public String getContestStartTime() {
        return contestStartTime;
    }

    public void setContestStartTime(String contestStartTime) {
        this.contestStartTime = contestStartTime;
    }

    public String getNoOfSkipsAllowed() {
        return noOfSkipsAllowed;
    }

    public void setNoOfSkipsAllowed(String noOfSkipsAllowed) {
        this.noOfSkipsAllowed = noOfSkipsAllowed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
