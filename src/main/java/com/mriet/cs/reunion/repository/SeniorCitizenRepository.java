package com.mriet.cs.reunion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mriet.cs.reunion.model.SeniorCitizen;

@Repository("seniorCitizenRepository")
public interface SeniorCitizenRepository extends JpaRepository<SeniorCitizen, Long> {
	
	@Query("select s from SeniorCitizen s where s.userName = :userName")
	SeniorCitizen findByUserName(@Param("userName") String userName);
	
	@Query("select s from SeniorCitizen s where s.userName != 'admin'")
	List<SeniorCitizen> findAll();
	
}
