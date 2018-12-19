package com.ex.repository;

import org.springframework.stereotype.Repository;

import com.ex.exception.RegisterException;
import com.ex.model.User;

/**
 * The {@code UserRepository} interface contains methods for persisting user
 * object.
 * 
 * @author cbhatt
 * @see UserRepositoryImpl
 *
 */
@Repository
public interface UserRepository {

	public void save(User user) throws RegisterException;

}
