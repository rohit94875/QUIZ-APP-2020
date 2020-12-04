package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

public class UserPositions{

	@SerializedName("userId")
	private String userName;

	@SerializedName("points")
	private int points;

	@SerializedName("rank")
	private int rank;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setPoints(int points){
		this.points = points;
	}

	public int getPoints(){
		return points;
	}

	@Override
 	public String toString(){
		return 
			"UserPositions{" + 
			"userName = '" + userName + '\'' + 
			",points = '" + points + '\'' + 
			"}";
		}
}