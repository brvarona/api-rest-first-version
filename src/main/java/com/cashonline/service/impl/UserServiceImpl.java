package com.cashonline.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashonline.entity.User;
import com.cashonline.exception.ResourceNotFoundException;
import com.cashonline.repository.UserRepository;
import com.cashonline.service.UserService;

/**
 * UserService implementation
 * 
 * @author Braian Varona
 *
 */

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find user by id.
     *
     * @param id the user id
     * @return the user by id
     * @throws ResourceNotFoundException 
     */
    public User getUser(Long id) throws ResourceNotFoundException {
    	logger.info("Getting the user with id:" + id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    /**
     * Store user in database.
     *
     * @param user the user
     * @return the user
     */
    public User saveUser(User user) {
    	logger.info("Saving user...");
        User userToSave = userRepository.save(user);
        return userToSave;        
    }

    /**
     * Remove user from database.
     *
     * @param userId the user id
     * @throws ResourceNotFoundException 
     * @throws Exception the exception
     */
    public void deleteUser(Long id) throws ResourceNotFoundException {        
    	logger.info("Deleting user with id" + id);
        User user = userRepository.findById(id)
        	.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));            
        userRepository.delete(user);
         
    }
}
