package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Role{

	@SerializedName("channel_channel_id")
	private int channelChannelId=3;

	@SerializedName("role")
	private String role="user";

	public void setChannelChannelId(int channelChannelId){
		this.channelChannelId = channelChannelId;
	}

	public int getChannelChannelId(){
		return channelChannelId;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	@Override
 	public String toString(){
		return 
			"Role{" + 
			"channel_channel_id = '" + channelChannelId + '\'' + 
			",role = '" + role + '\'' + 
			"}";
		}
}