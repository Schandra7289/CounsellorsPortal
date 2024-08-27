package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Counsellor;

@Repository
public interface CounsellorRepository extends JpaRepository<Counsellor,Integer>{

	public Counsellor findByEmailAndPwd(String email, String pwd);
	public Counsellor findByEmail(String email);

	

	
}
