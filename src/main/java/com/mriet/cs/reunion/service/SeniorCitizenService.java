package com.mriet.cs.reunion.service;

import java.util.List;

import com.mriet.cs.reunion.model.SeniorCitizen;

public interface SeniorCitizenService {
	SeniorCitizen save(SeniorCitizen seniorCitizen);
	boolean findByLogin(String userName, String password);
	boolean findByUserName(String userName);
	List<SeniorCitizen> findAllEnquiries();
}
