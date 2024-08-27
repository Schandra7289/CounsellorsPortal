package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.DashboardResponse;
import com.example.entity.Counsellor;
import com.example.entity.Enquiry;
import com.example.repository.CounsellorRepository;
import com.example.repository.EnquiryRepository;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepository counsellorRepository;

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Override
	public Counsellor findByEmail(String email) {
		return counsellorRepository.findByEmail(email);
	}

	@Override
	public boolean register(Counsellor counsellor) {
		Counsellor savedCounsellor = counsellorRepository.save(counsellor);
		if (null != savedCounsellor.getCounsellorId()) {
			return true;
		}

		return false;
	}

	@Override
	public Counsellor login(String email, String pwd) {
		return counsellorRepository.findByEmailAndPwd(email, pwd);
		 
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {

		DashboardResponse response = new DashboardResponse();

		List<Enquiry> enqList = enquiryRepository.getAllEnquriesByCounsellorId(counsellorId);

		int totalEnq = enqList.size();
		
		int enrolledEnqs = enqList.stream()
                                .filter(e -> e.getEnqStatus().equals("Enrolled"))
                              .collect(Collectors.toList())
                              .size();


		int openEnqs = enqList.stream()
				              .filter(e -> e.getEnqStatus().equals("Open"))
				              .collect(Collectors.toList())
				              .size();

		
		int lostEnqs = enqList.stream()
				              .filter(e -> e.getEnqStatus().equals("Lost"))
				              .collect(Collectors.toList())
			               	  .size();

		response.setTotalEnquires(totalEnq);
		response.setEnrolledEnquires(enrolledEnqs);
		response.setOpenEnquires(openEnqs);
		response.setLostEnquires(lostEnqs);

		return response;
	}

}
