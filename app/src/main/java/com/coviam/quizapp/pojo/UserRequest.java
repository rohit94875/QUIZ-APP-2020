package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserRequest implements Serializable {

	@SerializedName("userId")
	private String userId;

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"UserRequest{" + 
			"userId = '" + userId + '\'' + 
			"}";
		}
}