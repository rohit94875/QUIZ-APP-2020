package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

public class ReportCreate{

	@SerializedName("contestId")
	private String contestId;

	public void setContestId(String contestId){
		this.contestId = contestId;
	}

	public String getContestId(){
		return contestId;
	}

	@Override
 	public String toString(){
		return 
			"ReportCreate{" + 
			"contestId = '" + contestId + '\'' + 
			"}";
		}
}