package com.stackroute.activitystream.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.model.User;
import com.stackroute.activitystream.service.UserService;

/* As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */

@RestController
public class UserController {
	/*
	 * Autowiring should be implemented for the UserService. Please note that we
	 * should not create any object using the new keyword
	 */

	@Autowired
	private UserService userService;

	@PersistenceContext
	private static EntityManager entityManager;

	/*
	 * Define a handler method which will list all the available users. This handler
	 * method should return any one of the status messages basis on different
	 * situations: 1. 200(OK) - If login is successful 2. 401(UNAUTHORIZED) - If the
	 * user is not logged in
	 * 
	 * This handler method should map to the URL "/api/user" using HTTP GET method
	 */

	@RequestMapping(value = "/api/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			if (userLoggedIn("")) {
				List<User> users = userService.list();
				return new ResponseEntity<>(users, HttpStatus.OK);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	/*
	 * Define a handler method which will show details of a specific user. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If login is successful 2.
	 * 401(UNAUTHORIZED) - If the user is not logged in 3. 404(NOT FOUND) - If the
	 * user with the searched for name is not found This handler method should map to the
	 * URL "/api/user/{username}" using HTTP GET method where "username" should be
	 * replaced by a username without {}
	 */
	
	@RequestMapping(value = "/api/user/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserByNo(@PathVariable ("username")  String username ) {
		try {
			if (!userLoggedIn("")) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			User u = userService.get(username);
			if (u!=null) {
				return new ResponseEntity<User>(u, HttpStatus.OK);
			}
		} catch(EntityNotFoundException e) {
			
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in user table
	 * in database. This handler method should return any one of the status messages
	 * basis on different situations: 1. 201(CREATED) - If the user is successfully
	 * created 2. 409(CONFLICT) - If the username conflicts with any existing user
	 * 
	 * Note: ------ This method can be called without being logged in as well as
	 * when a new user will use the app, he will register himself first before
	 * login. This handler method should map to the URL "/api/user" using HTTP POST
	 * method
	 */
	
	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
	public ResponseEntity addNewUser(@RequestBody User u) {
		try {
			return new ResponseEntity<>(userService.save(u), HttpStatus.CREATED);
		} catch(IllegalArgumentException e) {
			
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

	/*
	 * Define a handler method which will update an specific user by reading the
	 * Serialized object from request body and save the updated user details in user
	 * table in database. This handler method should return any one of the status
	 * messages basis on different situations: 1. 200(OK) - If the user is
	 * successfully updated 2. 404(NOT FOUND) - If the user with specified username
	 * is not found 3. 401(UNAUTHORIZED) - If the user trying to perform the action
	 * has not logged in
	 * 
	 * This handler method should map to the URL "/api/user/{username}" using HTTP
	 * PUT method
	 */

	@RequestMapping(value = "/api/user/{username}", method = RequestMethod.POST)
	public ResponseEntity updateUser(@RequestBody User u, @PathVariable("username") String username) {
		try {
			if (!userLoggedIn("")) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			if (userService.exists(username)) {
				u.setUsername(username);
				userService.save(u); 
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch(IllegalArgumentException e) {
			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	/*
	 * This utility method checks whether a user is logged in. If the argument is 
	 * the empty string "", the method will check if ANY user is logged in
	 */
	static boolean userLoggedIn(String username) {
		if (username == null) {
			return false;
		}
		try {
			Session session = entityManager.unwrap(Session.class);
			String storedUser = (String) session.getProperties()
					.get(UserAuthController.SESSION_USERNAME_KEY);
			if ( (storedUser!=null && (username.equalsIgnoreCase(""))) ||
					username.equals(storedUser)) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
