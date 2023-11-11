package com.home.lc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.home.lc.api.EmailDTO;
import com.home.lc.api.UserinfoDTO;
import com.home.lc.service.LCAppEmailServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmailController {
	
	@Autowired
	private LCAppEmailServiceImpl lcAppEmailService;

	@RequestMapping("/sendEmail")
	public String sendEmail( Model model) {

//		model.addAttribute("userName", userName);
		model.addAttribute("emailDTO", new EmailDTO());

		return "send-email-page";
	}

	@RequestMapping("/process-email")
	public String processEmail(@SessionAttribute("userInfo") UserinfoDTO userinfoDTO,@ModelAttribute("emailDTO") EmailDTO emailDTO,HttpSession session,Model model) {
		
		String userName = (String) session.getAttribute("userName");
		String newUserName = "Mr. "+userName;
		model.addAttribute("userName", newUserName);
		
		try {
			lcAppEmailService.sendEmail(userinfoDTO.getUserName(),emailDTO.getUserEmail(),"FRIEND");
		} catch (Exception e) {
			System.out.println(e);
			return "exception-handler";
		}
		
		return "process-email-page";
	}
}
