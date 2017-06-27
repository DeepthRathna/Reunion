package com.mriet.cs.reunion.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ApproveCitizen {

	private Long id;

	@NotEmpty
	@Size(min = 4, max = 20)
	private String userName;

	private String registerReason;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getRegisterReason() {
		return registerReason;
	}

	public void setRegisterReason(String registerReason) {
		this.registerReason = registerReason;
	}

	
	
	
	
}
