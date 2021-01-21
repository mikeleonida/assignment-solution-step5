package com.stackroute.activitystream.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.stackroute.activitystream.model.Circle;
import com.stackroute.activitystream.service.CircleService;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
public class CircleController {

	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement three functionalities regarding circles. They are as
	 * following:
	 * 
	 * 1. Create a circle 2. Get all circles 3. Get all circles which is matching a
	 * keyword
	 * 
	 * we must also ensure that only a user who is logged in should be able to
	 * perform the functionalities mentioned above.
	 * 
	 */

	/*
	 * Autowiring should be implemented for the CircleService. Please note that we
	 * should not create any object using the new keyword
	 */

	@Autowired
	private CircleService circleService;

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * Define a handler method which will create a circle by reading the Serialized
	 * circle object from request body and save the circle in circle table in
	 * database. Please note that the circleName has to be unique and the loggedIn
	 * userID should be taken as the creatorId for the circle. This handler method
	 * should return any one of the status messages basis on different situations:
	 * 1. 201(CREATED - In case of successful creation of the circle 2. 2.
	 * 409(CONFLICT) - In case of duplicate circle ID 3. 401(UNAUTHORIZED) - If the
	 * user is not logged in
	 * 
	 * This handler method should map to the URL "/api/circle" using HTTP POST
	 * method".
	 */

	@RequestMapping(value = "/api/circle", method = RequestMethod.POST)
	public ResponseEntity addNewUser(@RequestBody Circle c) {
		try {
			if (!UserController.userLoggedIn("")) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			Session session = entityManager.unwrap(Session.class);
			String creatorId = (String) session.getProperties().get(UserAuthController.SESSION_USERNAME_KEY);
			c.setCreatorId(creatorId);
			if (circleService.get(c.getCircleName()) == null) {
				circleService.save(c);
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/*
	 * Define a handler method which will retrieve all the available circles. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - In case of success 2. 401(UNAUTHORIZED) -
	 * If the user is not logged in
	 * 
	 * This handler method should map to the URL "/api/circle" using HTTP GET
	 * method".
	 */

	@RequestMapping(value = "/api/circle", method = RequestMethod.GET)
	public ResponseEntity<List<Circle>> getFriendCircles() {
		try {
			if (!UserController.userLoggedIn("")) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			List<Circle> friendCircles = circleService.getAllCircles();
			return new ResponseEntity<>(friendCircles, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/*
	 * Define a handler method which will retrieve all the available circles
	 * matching a search keyword. This handler method should return any one of the
	 * status messages basis on different situations: 1. 200(OK) - In case of
	 * success 2. 401(UNAUTHORIZED) - If the user is not logged in
	 * 
	 * This handler method should map to the URL "/api/circle/search/{searchString}"
	 * using HTTP GET method" where "searchString" should be replaced with the
	 * actual search keyword without the {}
	 */

	@RequestMapping(value = "/api/circle/search/{searchString}", method = RequestMethod.GET)
	public ResponseEntity<List<Circle>> getFriendCirclesByName(
			@RequestParam("searchString") String searchString) {
		try {
			if (!UserController.userLoggedIn("")) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			List<Circle> friendCircles = circleService.getAllCircles(searchString);
			return new ResponseEntity<>(friendCircles, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
