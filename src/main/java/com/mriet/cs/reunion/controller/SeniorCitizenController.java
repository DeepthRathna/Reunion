package com.mriet.cs.reunion.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mriet.cs.reunion.model.SeniorCitizen;
import com.mriet.cs.reunion.model.SeniorCitizenLogin;
import com.mriet.cs.reunion.service.SeniorCitizenService;

@Controller
@SessionAttributes("seniorCitizen")
public class SeniorCitizenController {
	
	@Autowired
	private SeniorCitizenService seniorCitizenService;
		
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
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView  login(@Valid @ModelAttribute("seniorCitizenLogin") SeniorCitizenLogin seniorCitizenLogin, BindingResult result, ModelAndView mav) {
		if (result.hasErrors()) {
			mav.setViewName("login");
			return mav;
		} else {
			boolean found = seniorCitizenService.findByLogin(seniorCitizenLogin.getUserName(), seniorCitizenLogin.getPassword());
			if (found) {
				if(seniorCitizenLogin.getUserName().equals("admin")) {
					mav.setViewName("admin");
					mav.addObject("AllEnquiries", seniorCitizenService.findAllEnquiries());
					return mav;
				}
				mav.addObject("seniorCitizenLogin", seniorCitizenLogin);
				mav.setViewName("success");
				return mav;
			} else {	
				mav.setViewName("failure");
				return mav;
			}
		}
		
	}
}
