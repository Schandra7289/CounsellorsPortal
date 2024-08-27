package com.example.service;

import java.util.List;

import com.example.dto.ViewEnqsFilterRequest;
import com.example.entity.Enquiry;

public interface EnquiryService {
	
	public boolean addEnquiry(Enquiry enq , Integer counsellorId)throws Exception;
	
	public List<Enquiry> getAllEnquires(Integer counsellorId);
	
	public List<Enquiry>getEnquiresWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId);
	
	public Enquiry getEnquiryById(Integer enqId);
 
}
