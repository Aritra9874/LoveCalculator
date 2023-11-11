package com.home.lc.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.home.lc.api.Phone;

public class PhoneNumberFormatter implements Formatter<Phone> {

	@Override
	public String print(Phone phone, Locale locale) {
		// TODO Auto-generated method stub
		System.out.println("Inside the print method of the PhoneNumberFormatter class");
		String completePhoneNumber = phone.getCountryCode() + "-" + phone.getUserNumber();
		return completePhoneNumber;
	}

	@Override
	public Phone parse(String completePhoneNumber, Locale locale) throws ParseException {
		// TODO Auto-generated method stub

		System.out.println("Inside the parse method of the PhoneNumberFormatter class");

		Phone phone = new Phone();
		// logic-split the string received from the user..
		String[] phoneNumberArray = completePhoneNumber.split("-");

		// If the number consists of -
		int index = completePhoneNumber.indexOf("-");
		if (index == -1 || completePhoneNumber.startsWith("-")) {
			// If the "-" is not found,then add 91 as the default country code..
			phone.setCountryCode("91");
			if (completePhoneNumber.startsWith("-")) {
				phone.setUserNumber(phoneNumberArray[1]);
			} else {
				phone.setUserNumber(phoneNumberArray[0]);
			}
		} else {

			phone.setCountryCode(phoneNumberArray[0]);
			phone.setUserNumber(phoneNumberArray[1]);
		}
		return phone;
	}

}
