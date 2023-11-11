package com.home.lc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.home.lc.api.UserinfoDTO;
import com.home.lc.service.LCAppService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes({"userInfo"})
public class LCAppController {
	
	@Autowired
	private LCAppService lcAppService;

	@RequestMapping("/")
	public String showHomepage(Model model){
//		Cookie[] cookies = request.getCookies();
//		for (Cookie cookie : cookies) {
//			if ("lcApp.userName".equals(cookie.getName())) {
//				String myUserName = cookie.getValue();
//				userinfoDTO.setUserName(myUserName);
//			}
//		}
		model.addAttribute("userInfo", new UserinfoDTO());
		return "home-page";
	}
	
	@RequestMapping("/process-homepage")
	public String showResultPage(@Valid @ModelAttribute("userInfo") UserinfoDTO userinfoDTO, BindingResult result,HttpServletRequest request,Model model) {
//		System.out.println("User Name is:"+userinfoDTO.getUserName());
//		System.out.println("Crush Name is:"+userinfoDTO.getCrushName());
		System.out.println(userinfoDTO.isTermAndCondition());
		
		if (result.hasErrors()) {
			
			List<ObjectError> allErrors = result.getAllErrors();
		    for (ObjectError temp : allErrors) {
				System.out.println(temp);
			}
			
			return "home-page";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("userName", userinfoDTO.getUserName());
		session.setMaxInactiveInterval(120);
		
		String appResult = lcAppService.calculateLove(userinfoDTO.getUserName(), userinfoDTO.getCrushName());
		model.addAttribute("result", appResult);
		
		//Creating cookie for userName
//		Cookie theCookie = new Cookie("lcApp.userName", userinfoDTO.getUserName());
//		theCookie.setMaxAge(60*60*24);
//		
//		response.addCookie(theCookie);
		
		return "result-page";
	}
}
