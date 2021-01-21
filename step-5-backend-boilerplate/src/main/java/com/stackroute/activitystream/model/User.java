package com.stackroute.activitystream.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * The class "User" will be acting as the data model for the user Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 *
 * Please note that you will have to use @Component annotation on this class if wish
 * to autowire the class from any other components of the application
 */

@Entity
public class User implements Serializable {

	/*
	 * This class should have three fields (username,name,password). Out of these
	 * three fields, the field username should be the primary key. This class should
	 * also contain the getters and setters for the fields.
	 */
	@Id
	private String username;
	
	private String name;
	
	private String password;

	public User(String string, String string2, String string3) {
		this.username = string;
		this.name = string2;
		this.password = string3;
	}

	public User() {
		
	}

	public void setName(String string) {
		name = string;
	}

	public void setPassword(String string) {
		password = string;
	}

	public void setUsername(String string) {
		username = string;
	}

	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}
	
	public static boolean isValidUsername(String username) {
		if (username.length() > 7 && (!username.contains(" "))) {
			return true;
		}
		return false;
	}
	
}
