package com.stackroute.activitystream.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.model.User;
import com.stackroute.activitystream.service.UserService;


/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */

@RestController
public class UserAuthController {

	/*
	 * Autowiring should be implemented for the UserService. Please note that we
	 * should not create any object using the new keyword
	 */

	public static String SESSION_USERNAME_KEY = "username";

	@Autowired
	private UserService userService;

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * Define a handler method which will authenticate a user by reading the
	 * Serialized user object from request body containing the username and password
	 * and validating the same. Post login, the username will have to be stored into
	 * session object, so that we can check whether the user is logged in for all
	 * other services. This handler method should return any one of the status
	 * messages basis on different situations: 1. 200(OK) - If login is successful
	 * 2. 401(UNAUTHORIZED) - If login is not successful
	 * 
	 * This handler method should map to the URL "/api/authenticate" using HTTP POST
	 * method
	 */

	@RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
	public ResponseEntity loginUser(@RequestBody User u) {
		try {
			if (userService.validate(u.getUsername(), u.getPassword())) {
				Session session = entityManager.unwrap(Session.class);
				session.setProperty(SESSION_USERNAME_KEY, u.getUsername());
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	/*
	 * Define a handler method which will perform logout. Post logout, the user
	 * session is to be destroyed. This handler method should return any one of the
	 * status messages basis on different situations: 1. 200(OK) - If logout is
	 * successful 2. 400(BAD REQUEST) - If logout has failed
	 * 
	 * This handler method should map to the URL "/api/logout" using HTTP PUT method
	 */

	@RequestMapping(value = "/api/logout", method = RequestMethod.PUT)
	public ResponseEntity logoutUser() {
		try {
			Session session = entityManager.unwrap(Session.class);
			session.setProperty(SESSION_USERNAME_KEY, null);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
