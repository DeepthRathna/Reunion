package com.mriet.cs.reunion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mriet.cs.reunion.model.SeniorCitizen;
import com.mriet.cs.reunion.repository.SeniorCitizenRepository;

@Service("seniorCitizenService")
public class SeniorCitizenServiceImpl implements SeniorCitizenService {

	@Autowired
	private SeniorCitizenRepository seniorCitizenRepository;
	
	@Transactional
	public SeniorCitizen save(SeniorCitizen seniorCitizen) {
		return seniorCitizenRepository.save(seniorCitizen);
	}

	public boolean findByLogin(String userName, String password) {	
		SeniorCitizen stud = seniorCitizenRepository.findByUserName(userName);
		
		if(stud != null && stud.getPassword().equals(password)) {
			return true;
		} 
		
		return false;		
	}

	public boolean findByUserName(String userName) {
		SeniorCitizen stud = seniorCitizenRepository.findByUserName(userName);
		
		if(stud != null) {
			return true;
		}
		
		return false;
	}
	
	public List<SeniorCitizen> findAllEnquiries() {
		return seniorCitizenRepository.findAll();
	}

	public SeniorCitizen findById(Long id) {
		return seniorCitizenRepository.findById(id);
	}

	public void registerAsMember(SeniorCitizen seniorCitizen) {
		seniorCitizenRepository.registerAsMember(seniorCitizen.getId(), seniorCitizen.getRegisterReason(), seniorCitizen.getRegisteredDate());
	}

}
