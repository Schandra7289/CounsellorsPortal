package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Enquiry;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry,Integer>{

	@Query(value= "select * from enquiry_tbl where counsellor_id=:counsellorId " , nativeQuery = true)
	public List<Enquiry> getAllEnquriesByCounsellorId(Integer counsellorId);
}
