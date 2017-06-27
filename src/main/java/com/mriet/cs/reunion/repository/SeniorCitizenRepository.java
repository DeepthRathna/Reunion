package com.mriet.cs.reunion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mriet.cs.reunion.model.SeniorCitizen;

@Transactional
@Repository("seniorCitizenRepository")
public interface SeniorCitizenRepository extends JpaRepository<SeniorCitizen, Long> {
	
	@Query("select s from SeniorCitizen s where s.userName = :userName")
	SeniorCitizen findByUserName(@Param("userName") String userName);
	
	@Query("select s from SeniorCitizen s where s.userName <> 'admin'")
	List<SeniorCitizen> findAll();

	@Query("select s from SeniorCitizen s where s.id = :id")
	SeniorCitizen findById(@Param("id") Long id);
	
	@Modifying
	@Query("update SeniorCitizen s set s.registerReason = :reason, s.registeredDate = :registerDate, status='approved' where s.id = :id")
	void registerAsMember(@Param("id") Long id, @Param("reason") String reason, @Param("registerDate") Date registerDate);
	
}
