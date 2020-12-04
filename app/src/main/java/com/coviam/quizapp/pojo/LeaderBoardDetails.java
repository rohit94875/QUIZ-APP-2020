package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LeaderBoardDetails implements Serializable {

	@SerializedName("contestId")
	private String contestId;

	@SerializedName("userId")
	private String userId;

	public void setContestId(String contestId){
		this.contestId = contestId;
	}

	public String getContestId(){
		return contestId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"LeaderBoardDetails{" + 
			"contestId = '" + contestId + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}