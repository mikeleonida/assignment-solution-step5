package com.stackroute.activitystream.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * The class "Message" will be acting as the data model for the message Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 * 
 * Please note that you will have to use @Component annotation on this class if wish
 * to autowire the class from any other components of the application
 */

@Entity
public class Message implements Serializable {

	/*
	 * This class should have eight fields
	 * (messageId,senderName,receiverId,circleName,postedDate,streamType,message,tag
	 * ). Out of these eight fields, the field messageId should be auto-generated.
	 * This class should also contain the getters and setters for the fields. The
	 * value of postedDate should not be accepted from the user but should be always
	 * initialized with the system date
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer messageId;
	
	private String senderName;
	private String receiverId;
	private String circleName;
	private Timestamp postedDate;
	private String streamType;
	private String message;
	private String tag;

	public Message() {
		
	}
	
	public Message(String senderName, String receiverId, String circleName, Timestamp postedDate,
			String streamType, String message, String tag) {
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.circleName = circleName;
		this.postedDate = new Timestamp(System.currentTimeMillis());
		this.streamType = streamType;
		this.message = message;
		this.tag = tag;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String string) {
		this.senderName = string;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public Timestamp getPostedDate() {
		return postedDate;
	}

	public void setPostedDate() {
		//this.postedDate = new Timestamp(System.currentTimeMillis());
	}

	public String getStreamType() {
		return streamType;
	}

	public void setStreamType(String string) {
		this.streamType = string;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String string) {
		this.message = string;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String string) {
		this.tag = string;
	}



}
