package com.ex.repository;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ex.exception.RegisterException;
import com.ex.model.User;
import com.ex.service.UserIdentifier;
import com.ex.util.Messages;

/**
 * The {@code UserRepositoryImpl} implements the {@code UserRepository}
 * interface. The main purpose of this class is to persist user objects.
 * 
 * @author cbhatt
 * @see UserRepository
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	private final Map<UserIdentifier, User> userCache = new HashMap<>();

	@Override
	public void save(User user) throws RegisterException {
		logger.info("saving user:" + user);
		UserIdentifier key = new UserIdentifier(user.getDateOfBirth(), user.getSsn());
		if (userCache.containsKey(key)) {
			throw new RegisterException(Messages.USER_ALREADY_EXIST);
		}
		userCache.put(key, user);
	}
}
