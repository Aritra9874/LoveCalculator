package com.home.lc.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class LCAppEmailServiceImpl implements LCAppEmailService {

	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	@Override
	public void sendEmail(String userName, String userEmail, String result) {
		SimpleMailMessage newEmail = new SimpleMailMessage();
		newEmail.setTo(userEmail);
		newEmail.setSubject("Love Calculator Application Result");
		newEmail.setText("HI " + userName + " The Result predicted by the LCApp is " + result);

		javaMailSenderImpl.send(newEmail);
	}
}
