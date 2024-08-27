package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.ViewEnqsFilterRequest;
import com.example.entity.Enquiry;
import com.example.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;

	@PostMapping("/filter-enqs")
	public String filterEnquries(ViewEnqsFilterRequest viewEnqsFilterRequest, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		List<Enquiry> enqsList = enquiryService.getEnquiresWithFilter(viewEnqsFilterRequest, counsellorId);
		model.addAttribute("enquiries", enqsList);

		return "viewEnqsPage";
	}

	@GetMapping("/view-enquiries")
	public String getEnquries(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		List<Enquiry> enqsList = enquiryService.getAllEnquires(counsellorId);

		model.addAttribute("enquiries", enqsList);

		ViewEnqsFilterRequest filterReq = new ViewEnqsFilterRequest();
		model.addAttribute("viewEnqsFilterRequest", filterReq);

		return "viewEnqsPage";
	}

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {

		Enquiry enquiry = new Enquiry();

		model.addAttribute("enquiry", enquiry);

		return "enquiryForm";
	}

	@GetMapping("/editEnq")
	public String editEnquiry(@RequestParam("enqId") Integer enqId, Model model) {

		Enquiry enquiry = enquiryService.getEnquiryById(enqId);

		model.addAttribute("enquiry", enquiry);

		return "enquiryForm";
	}

	@PostMapping("/addEnquiry")
	private String handleAddEnquiry(Enquiry enquiry, HttpServletRequest request, Model model) throws Exception {

		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		boolean isSaved = enquiryService.addEnquiry(enquiry, counsellorId);

		if (isSaved) {
			model.addAttribute("smsg", "Enquiry Added");
		} else {
			model.addAttribute("emsg", "Failed to Added Enquiry");
		}

		return "enquiryForm";
	}

}
