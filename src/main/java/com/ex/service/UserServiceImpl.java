package com.ex.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.exception.RegisterException;
import com.ex.model.User;
import com.ex.repository.UserRepository;
import com.ex.util.Messages;

/**
 * The {@code UserServiceImpl} implements {@code UserService}. It is used to
 * communicate with the {@code UserRepository} to persist the user data.
 * 
 * @author cbhatt
 * @see UserService
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repository;

	private ExclusionService exclusionService;

	@PostConstruct
	public void init() {
		exclusionService = new ExclusionServiceImpl();
	}

	@Override
	public void save(User user) throws RegisterException {
		logger.info("validating user");
		if (!exclusionService.validate(user.getDateOfBirth(), user.getSsn())) {
			throw new RegisterException(Messages.USER_IN_EXCLUDED_LIST);
		}
		repository.save(user);
	}
}
