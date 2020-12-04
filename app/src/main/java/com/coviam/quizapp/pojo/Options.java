package com.coviam.quizapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Options implements Serializable {

	@SerializedName("optionA")
	private String A;

	@SerializedName("optionB")
	private String B;

	@SerializedName("optionC")
	private String C;

	@SerializedName("optionD")
	private String D;

	public String getD() { return D; }

	public void setD(String d) { D = d; }

	public void setA(String A){
		this.A = A;
	}

	public String getA(){
		return A;
	}

	public void setB(String B){
		this.B = B;
	}

	public String getB(){
		return B;
	}

	public void setC(String C){
		this.C = C;
	}

	public String getC(){
		return C;
	}


}