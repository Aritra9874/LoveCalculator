package com.home.lc.api;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserinfoDTO {

    @NotBlank(message = " * Your Name can't be blank.")
    @Size(min = 3,max = 15,message = " * Your Name should have char between 3-15")
	private String userName;
    @NotBlank(message = " * Crush Name can't be blank.")
    @Size(min = 3,max = 15,message = " * Crush Name should have char between 3-15")
	private String crushName;
	@AssertTrue(message = " * You have to agree to use our app.")
	private boolean termAndCondition;

	public UserinfoDTO() {
		System.out.println("UserinfoDTO cvonstructor get called");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCrushName() {
		return crushName;
	}

	public void setCrushName(String crushName) {
		this.crushName = crushName;
	}

	public boolean isTermAndCondition() {
		return termAndCondition;
	}

	public void setTermAndCondition(boolean termAndCondition) {
		this.termAndCondition = termAndCondition;
	}

	@Override
	public String toString() {
		return "UserinfoDTO [userName=" + userName + ", crushName=" + crushName + "]";
	}

}
