package com.mriet.cs.reunion.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mriet.cs.reunion.model.ApproveCitizen;
import com.mriet.cs.reunion.model.SeniorCitizen;
import com.mriet.cs.reunion.model.SeniorCitizenLogin;
import com.mriet.cs.reunion.service.MessagingService;
import com.mriet.cs.reunion.service.SeniorCitizenService;

@Controller
@SessionAttributes("seniorCitizen")
public class SeniorCitizenController {
	
	@Autowired
	private SeniorCitizenService seniorCitizenService;
	
	@Autowired
	private MessagingService messagingService;
		
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup(Model model) {
		SeniorCitizen seniorCitizen = new SeniorCitizen();		
		model.addAttribute("seniorCitizen", seniorCitizen);		
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid @ModelAttribute("seniorCitizen") SeniorCitizen seniorCitizen, BindingResult result, Model model) {		
		if(result.hasErrors()) {
			return "signup";
		} else if(seniorCitizenService.findByUserName(seniorCitizen.getUserName())) {
			model.addAttribute("message", "User Name exists. Try another user name");
			return "signup";
		} else {
			seniorCitizenService.save(seniorCitizen);
			model.addAttribute("message", "Saved senior citizen details");
			return "redirect:login.html";
		}
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {			
		SeniorCitizenLogin seniorCitizenLogin = new SeniorCitizenLogin();		
		model.addAttribute("seniorCitizenLogin", seniorCitizenLogin);
		return "login";
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public ModelAndView admin(ModelAndView mav, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SeniorCitizenLogin seniorCitizenLogin = (SeniorCitizenLogin)session.getAttribute("admin");
		if(seniorCitizenLogin != null) {
			System.out.println(seniorCitizenLogin.getUserName());
			if(seniorCitizenLogin.getUserName().equals("admin")) {
				mav.addObject("AllEnquiries", seniorCitizenService.findAllEnquiries());
				return mav;
			}
		}
		mav.setViewName("redirect:/login.html");
		return mav;
	}
	
	@RequestMapping(value="/approve", method=RequestMethod.GET)
	public String getApprove(@RequestParam Long id, Model model, HttpServletRequest request) {
		System.out.println("ID"+ id);
		HttpSession session = request.getSession();
		SeniorCitizenLogin adminUser = (SeniorCitizenLogin)session.getAttribute("admin");
		if(adminUser != null) {
			System.out.println("adminUser.getUserName() "+adminUser.getUserName());
			if(adminUser.getUserName().equals("admin")) {
				SeniorCitizen seniorCitizen = seniorCitizenService.findById(id);
				if(seniorCitizen != null) {
					model.addAttribute("approveSeniorCitizen", seniorCitizen);
					return "approve";
				}
			}
		}
		return "redirect:/login.html";
	}
	
	
	@RequestMapping(value="/approve", method=RequestMethod.POST)
	public String approve(@Valid @ModelAttribute("approveSeniorCitizen") ApproveCitizen approveCitizen, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SeniorCitizenLogin adminUser = (SeniorCitizenLogin)session.getAttribute("admin");
		if(adminUser != null) {
			if(adminUser.getUserName().equals("admin")) {
				if(approveCitizen != null) {
					SeniorCitizen seniorCitizen = seniorCitizenService.findById(approveCitizen.getId());
					if(seniorCitizen != null) {
						seniorCitizen.setStatus("approved");
						seniorCitizen.setRegisteredDate(new Date());
						seniorCitizen.setRegisterReason("Approved by admin");
						seniorCitizenService.registerAsMember(seniorCitizen);
						messagingService.sendMessage(seniorCitizen.getPhoneNumber(), "Hello, "+seniorCitizen.getFirstName()+" "+seniorCitizen.getLastName()+", your membership has been approved.");
					}
				}
			}
		}
		return "redirect:/admin.html";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView  login(@Valid @ModelAttribute("seniorCitizenLogin") SeniorCitizenLogin seniorCitizenLogin, BindingResult result, ModelAndView mav, HttpServletRequest request) {
		if (result.hasErrors()) {
			mav.setViewName("login");
			return mav;
		} else {
			boolean found = seniorCitizenService.findByLogin(seniorCitizenLogin.getUserName(), seniorCitizenLogin.getPassword());
			if (found) {
				if(seniorCitizenLogin.getUserName().equals("admin")) {
					HttpSession session = request.getSession(true);
					session.setAttribute("admin", seniorCitizenLogin);
					mav.addObject("admin", seniorCitizenLogin);
					mav.setViewName("redirect:/admin.html");
					return mav;
				}
				mav.addObject("seniorCitizenLogin", seniorCitizenLogin);
				mav.setViewName("redirect:success");
				return mav;
			} else {	
				mav.setViewName("failure");
				return mav;
			}
		}
		
	}
}
