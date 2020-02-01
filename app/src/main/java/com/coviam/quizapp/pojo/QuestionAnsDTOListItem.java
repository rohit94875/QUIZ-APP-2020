package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionAnsDTOListItem implements Serializable {

	@SerializedName("questionId")
	private String questionId;

	@SerializedName("submittedAns")
	private String answer;

	@SerializedName("timetaken")
	private long timetaken;

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public String getAnswer(){
		return answer;
	}

	public void setTimetaken(long timetaken){
		this.timetaken = timetaken;
	}

	public long getTimetaken(){
		return timetaken;
	}

	@Override
 	public String toString(){
		return 
			"QuestionAnsDTOListItem{" + 
			"questionId = '" + questionId + '\'' + 
			",answer = '" + answer + '\'' + 
			",timetaken = '" + timetaken + '\'' + 
			"}";
		}
}