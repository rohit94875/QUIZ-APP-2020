package com.coviam.quizapp.pojo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AnswerDTO implements Serializable{

	@SerializedName("questionAnsDTOList")
	private List<QuestionAnsDTOListItem> questionAnsDTOList;

	@SerializedName("contestId")
	private String contestId;

	@SerializedName("noOfskips")
	private int noOfskips;

	@SerializedName("starting")
	private int starting;

	@SerializedName("userID")
	private String userID;

	public void setQuestionAnsDTOList(List<QuestionAnsDTOListItem> questionAnsDTOList){
		this.questionAnsDTOList = questionAnsDTOList;
	}

	public List<QuestionAnsDTOListItem> getQuestionAnsDTOList(){
		return questionAnsDTOList;
	}

	public void setContestId(String contestId){
		this.contestId = contestId;
	}

	public String getContestId(){
		return contestId;
	}

	public void setNoOfskips(int noOfskips){
		this.noOfskips = noOfskips;
	}

	public int getNoOfskips(){
		return noOfskips;
	}

	public void setStarting(int starting){
		this.starting = starting;
	}

	public int getStarting(){
		return starting;
	}

	public void setUserID(String userID){
		this.userID = userID;
	}

	public String getUserID(){
		return userID;
	}

	@Override
 	public String toString(){
		return 
			"AnswerDTO{" + 
			"questionAnsDTOList = '" + questionAnsDTOList + '\'' + 
			",contestId = '" + contestId + '\'' + 
			",noOfskips = '" + noOfskips + '\'' + 
			",starting = '" + starting + '\'' + 
			",userID = '" + userID + '\'' + 
			"}";
		}
}