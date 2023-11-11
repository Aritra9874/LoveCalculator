package com.home.lc.controllers;

import java.util.List;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.lc.api.CommunicationDTO;
import com.home.lc.api.Phone;
import com.home.lc.api.UserRegistrationDTO;

import jakarta.validation.Valid;

@Controller
public class Registrationcontroller {

	@RequestMapping("/register")
	public String showRegistrationPage(@ModelAttribute("userReg") UserRegistrationDTO dto) {
//		dto.setName("Aritra");
		Phone phone = new Phone();
		phone.setCountryCode("91");
		phone.setUserNumber("2233445566");
		
		CommunicationDTO communicationDTO = new CommunicationDTO();
		communicationDTO.setPhone(phone);
		
		dto.setCommunicationDTO(communicationDTO);
		
		
		return "user-registration-page";
	}
	
	@RequestMapping("/registration-success")
	public String processUserReg(@Valid @ModelAttribute("userReg") UserRegistrationDTO dto,BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println("My page has erros..");
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError error : allErrors) {
				System.out.println(error);
			}
			return "user-registration-page";
		}
		return "registration-success";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//binder.setDisallowedFields("name");
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, "name", editor);;
		System.out.println("I am inside the initBinder() method..");
	}
}
