package com.ex.service;

import java.util.HashMap;
import java.util.Map;

import com.ex.model.User;

/**
 * The {@code ExclusionServiceImpl} class implements the
 * {@code ExclusionService} interface. It also contains a static list of
 * excluded users. This class offers a method for validating the incoming user
 * against the excluded user's list.
 * 
 * @author cbhatt
 * @see ExclusionService
 *
 */
public class ExclusionServiceImpl implements ExclusionService {

	private final Map<UserIdentifier, User> exclusionMap = new HashMap<>();

	public ExclusionServiceImpl() {
		User user = new User("adaLovelace", "Analytical3ngineRulz", "1815-12-10", "85385075");
		exclusionMap.put(new UserIdentifier(user.getDateOfBirth(), user.getSsn()), user);
		user = new User("alanTuring", "eniGmA123", "1912-06-23", "123456789");
		exclusionMap.put(new UserIdentifier(user.getDateOfBirth(), user.getSsn()), user);
		user = new User("konradZuse", "zeD1", "1910-06-22", "987654321");
		exclusionMap.put(new UserIdentifier(user.getDateOfBirth(), user.getSsn()), user);
	}

	@Override
	public boolean validate(String dateOfBirth, String ssn) {
		if (exclusionMap.containsKey(new UserIdentifier(dateOfBirth, ssn))) {
			return false;
		}
		return true;
	}
}
