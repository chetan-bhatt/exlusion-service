package com.ex.service;

import org.springframework.stereotype.Service;

import com.ex.exception.RegisterException;
import com.ex.model.User;

/**
 * The {@code UserService} interface represents the service exposed to the
 * controller
 * 
 * @author cbhatt
 * @see UserServiceImpl
 *
 */
@Service
public interface UserService {

	public void save(User user) throws RegisterException;

}
