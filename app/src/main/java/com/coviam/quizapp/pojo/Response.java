package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("status")
	private boolean status;

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"status = '" + status + '\'' + 
			"}";
		}
}