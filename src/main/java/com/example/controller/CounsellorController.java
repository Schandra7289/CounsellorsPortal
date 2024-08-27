package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dto.DashboardResponse;
import com.example.entity.Counsellor;
import com.example.service.CounsellorService;
import com.example.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class CounsellorController {
	
	
      @Autowired	
	  private CounsellorService counsellorService;
	  
	  @Autowired
	  private EnquiryService enquiryService;
	@GetMapping("/")
	public String index(Model model) {
		
		Counsellor cobj = new Counsellor();
		
		model.addAttribute("counsellor" , cobj);
		
		
		return "index";
	}
	
	@PostMapping("/login")
	public String handleLoginBtn(Counsellor counsellor , HttpServletRequest request,  Model model) {
		
		Counsellor c = counsellorService.login(counsellor.getEmail() , counsellor.getPwd());
		
		if(c == null) {
			
			model.addAttribute("emsg", "Invalid Credentails");
			
			return "index";
			
		}else {
			
			
			  HttpSession session =request.getSession(true);
			  session.setAttribute("counsellorId", c.getCounsellorId());
			 
			
				
				/*
				 * DashboardResponse dbresobj=
				 * counsellorService.getDashboardInfo(c.getCounsellorId());
				 * 
				 * model.addAttribute("dashboardInfo", dbresobj);
				 */
			
			return "redirect:/dashboard";
		}
		
	}
	
	@GetMapping("/dashboard")
	public String displayDashboard( HttpServletRequest request , Model model) {
		
		HttpSession session =request.getSession(false);
		 Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		  
		
		 DashboardResponse dbresobj= counsellorService.getDashboardInfo(counsellorId);
			
			model.addAttribute("dashboardInfo", dbresobj);
			
			return "dashboard";
		
	}
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		
        Counsellor cobj = new Counsellor();
		
		model.addAttribute("counsellor" , cobj);
		
		return "register";
	}
	
	
	@PostMapping("/register")
	public String handleRegistration(Counsellor counsellor, Model model) {
		
		Counsellor byEmail =counsellorService.findByEmail(counsellor.getEmail());
		
		if(byEmail != null) {
			model.addAttribute("emsg", "Duplicate mail");
			return "register" ;
		}
		
		
		boolean isRegistered =counsellorService.register(counsellor);
		
		if(isRegistered) {
		
		model.addAttribute("smsg", "Registration Succesful");
		
		}else {
			model.addAttribute("emsg", "Registration Failed");
		}
		return "register";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		return "redirect:/";
		
	}
}
