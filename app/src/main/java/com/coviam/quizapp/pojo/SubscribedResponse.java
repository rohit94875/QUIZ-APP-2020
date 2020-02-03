package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubscribedResponse implements Serializable {

	@SerializedName("contestId")
	private String contestId;

	@SerializedName("contestName")
	private String contestName;


	public void setContestId(String contestId){
		this.contestId = contestId;
	}

	public String getContestId(){
		return contestId;
	}

	public void setContestName(String contestName){
		this.contestName = contestName;
	}

	public String getContestName(){
		return contestName;
	}

	@Override
 	public String toString(){
		return 
			"SubscribedResponse{" + 
			"contestId = '" + contestId + '\'' + 
			",contestName = '" + contestName + '\'' + 
			"}";
		}
}