package com.cashonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cashonline.entity.User;
import com.cashonline.exception.ResourceNotFoundException;
import com.cashonline.service.UserService;

/**
 * UserRestController implementation
 * 
 * @author Braian Varona
 *
 */

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    /**
     * Gets user by id.
     *
     * @param id the user id
     * @return the user
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        User user =  userService.getUser(id);
        return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.saveUser(user);
        return new ResponseEntity<User>(created, new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * Delete user.
     *
     * @param id the user id
     * @return the response
     * @throws ResourceNotFoundException 
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
    	userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    
}