package com.ex.rest.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.exception.RegisterException;
import com.ex.model.User;
import com.ex.service.UserService;
import com.ex.util.Messages;

@RestController
@RequestMapping("/register")
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	private static final String USERNAME_REGEX = "[A-Za-z0-9]+";
	private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}$";

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> register(@RequestBody User user) {

		try {
			if (validateRequest(user)) {
				logger.info("Request validated, now trying to save");
				userService.save(user);
			}
		} catch (RegisterException e) {
			logger.error("Error occurred: " + e.getMessage());
			RegisterError error = new RegisterError(e.getMessage());
			return ResponseEntity.badRequest().body(error);
		}
		return ResponseEntity.ok().body(user);
	}
	
	private boolean validateRequest(User user) throws RegisterException {
		logger.info("validating request...");
		if (user == null) {
			throw new RegisterException(Messages.REQUEST_BODY_EMPTY);
		}

		String username = user.getUsername();
		if (username == null || !username.matches(USERNAME_REGEX)) {
			throw new RegisterException(Messages.USERNAME_INCORRECT_FORMAT);
		}

		String password = user.getPassword();
		if (password == null || !password.matches(PASSWORD_REGEX)) {
			throw new RegisterException(Messages.PASSWORD_INCORRECT_FORMAT);
		}

		try {
			LocalDate dob = LocalDate.parse(user.getDateOfBirth(), DateTimeFormatter.ISO_LOCAL_DATE);
			if(dob.getYear() < 1000) {
				throw new RegisterException(Messages.DATE_OF_BIRTH_INCORRECT_FORMAT);
			}
		} catch(DateTimeParseException e) {
			throw new RegisterException(Messages.DATE_OF_BIRTH_INCORRECT_FORMAT);
		}
		return true;
	}
}