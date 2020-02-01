package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionDTO implements Serializable {

	@SerializedName("urlAttachment")
	private String urlAttachment;

	@SerializedName("questionId")
	private String questionId;

	@SerializedName("questionFormat")
	private String questionFormat;

	@SerializedName("difficultyLevel")
	private String difficultyLevel;

	@SerializedName("options")
	private Options options;

	@SerializedName("answers")
	private String answers;

	@SerializedName("questionType")
	private int questionType;

	@SerializedName("questionText")
	private String questionText;

	@SerializedName("categoryId")
	private String categoryId;

	public void setUrlAttachment(String urlAttachment){
		this.urlAttachment = urlAttachment;
	}

	public String getUrlAttachment(){
		return urlAttachment;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setQuestionFormat(String questionFormat){
		this.questionFormat = questionFormat;
	}

	public String getQuestionFormat(){
		return questionFormat;
	}

	public void setDifficultyLevel(String difficultyLevel){
		this.difficultyLevel = difficultyLevel;
	}

	public String getDifficultyLevel(){
		return difficultyLevel;
	}

	public void setOptions(Options options){
		this.options = options;
	}

	public Options getOptions(){
		return options;
	}

	public void setAnswers(String answers){
		this.answers = answers;
	}

	public String getAnswers(){
		return answers;
	}

	public void setQuestionType(int questionType){
		this.questionType = questionType;
	}

	public int getQuestionType(){
		return questionType;
	}

	public void setQuestionText(String questionText){
		this.questionText = questionText;
	}

	public String getQuestionText(){
		return questionText;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	@Override
 	public String toString(){
		return 
			"QuestionDTO{" + 
			"urlAttachment = '" + urlAttachment + '\'' + 
			",questionId = '" + questionId + '\'' + 
			",questionFormat = '" + questionFormat + '\'' + 
			",difficultyLevel = '" + difficultyLevel + '\'' + 
			",options = '" + options + '\'' + 
			",answers = '" + answers + '\'' + 
			",questionType = '" + questionType + '\'' + 
			",questionText = '" + questionText + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}