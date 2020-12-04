package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Analytics{

	@SerializedName("contestId")
	private String contestId;

	@SerializedName("appId")
	private String appId;

	@SerializedName("contestTime")
	private long contestTime;

	@SerializedName("action")
	private String action;

	@SerializedName("contestName")
	private String contestName;

	@SerializedName("tag")
	private String tagName;

	@SerializedName("userId")
	private String userId;

	public void setContestId(String contestId){
		this.contestId = contestId;
	}

	public String getContestId(){
		return contestId;
	}

	public void setAppId(String appId){
		this.appId = appId;
	}

	public String getAppId(){
		return appId;
	}

	public void setContestTime(long contestTime){
		this.contestTime = contestTime;
	}

	public long getContestTime(){
		return contestTime;
	}

	public void setAction(String action){
		this.action = action;
	}

	public String getAction(){
		return action;
	}

	public void setContestName(String contestName){
		this.contestName = contestName;
	}

	public String getContestName(){
		return contestName;
	}

	public void setTagName(String tagName){
		this.tagName = tagName;
	}

	public String getTagName(){
		return tagName;
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
			"Analytics{" + 
			"contestId = '" + contestId + '\'' + 
			",appId = '" + appId + '\'' + 
			",contestTime = '" + contestTime + '\'' + 
			",action = '" + action + '\'' + 
			",contestName = '" + contestName + '\'' + 
			",tagName = '" + tagName + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}