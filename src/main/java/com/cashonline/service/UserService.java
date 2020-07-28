package com.cashonline.service;

import com.cashonline.entity.User;
import com.cashonline.exception.ResourceNotFoundException;

/**
 * Interface UserService
 * 
 * @author Braian Varona
 *
 */

public interface UserService {

	public User getUser(Long id) throws ResourceNotFoundException;
	    
	public User saveUser(User user);

	public void deleteUser(Long id) throws ResourceNotFoundException;
}
