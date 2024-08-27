package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.dto.ViewEnqsFilterRequest;
import com.example.entity.Counsellor;
import com.example.entity.Enquiry;
import com.example.repository.CounsellorRepository;
import com.example.repository.EnquiryRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class EnquiryServiceImpl implements EnquiryService{

	@Autowired
	private EnquiryRepository enquiryRepository;
	
	@Autowired
	private CounsellorRepository counsellorRepository;
	
	@Override
	public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception{
		
		Counsellor counsellor = counsellorRepository.findById(counsellorId).orElse(null);
		
		
		if(counsellor == null) {
			throw new Exception("No Counsellor Found");
		}
		
		enq.setCounsellor(counsellor);
		
		Enquiry save =enquiryRepository.save(enq);
		
		if(save.getEnqId() != null) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<Enquiry> getAllEnquires(Integer counsellorId) {
		return enquiryRepository.getAllEnquriesByCounsellorId(counsellorId);
	}

	@Override
	public Enquiry getEnquiryById(Integer enqId) {
		return enquiryRepository.findById(enqId).orElse(null);
	}
	
	@Override
	public List<Enquiry> getEnquiresWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId) {
		
		Enquiry enq =new Enquiry();
		
		if(StringUtils.isNotEmpty(filterReq.getClassMode())) {
			enq.setClassMode(filterReq.getClassMode());
		}
		
		
		  if(StringUtils.isNotEmpty(filterReq.getCourseName())) {
		  enq.setCourseName(filterReq.getCourseName()); }
		 
		
		
		if(StringUtils.isNotEmpty(filterReq.getEnqStatus())) {
			enq.setEnqStatus(filterReq.getEnqStatus());
		}
		
		Counsellor c =counsellorRepository.findById(counsellorId).orElse(null);
		enq.setCounsellor(c);
		
		Example<Enquiry> of =Example.of(enq);
		
		List<Enquiry> enqList = enquiryRepository.findAll(of);
		
		return enqList;
	}

	
	
	

}
