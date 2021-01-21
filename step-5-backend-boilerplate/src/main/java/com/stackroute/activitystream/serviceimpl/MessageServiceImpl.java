package com.stackroute.activitystream.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.activitystream.model.Message;
import com.stackroute.activitystream.repository.MessageRepository;
import com.stackroute.activitystream.service.MessageService;

/*
* Service classes are used here to implement additional business logic/validation. 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn’t currently 
* provide any additional behavior over the @Component annotation, but it’s a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */

@Service
public class MessageServiceImpl implements MessageService {

	/*
	 * Autowiring should be implemented for the CircleRepository, UserRepository,
	 * UserTagRepository, MessageRepository, UserCircleRepository. Please note that
	 * we should not create any object using the new keyword
	 */
	
	@Autowired
	private MessageRepository messageRepository;

	/*
	 * This method should be used to get all messages from a specific circle. Call
	 * the corresponding method of Respository interface.
	 */
	public List<Message> getMessagesFromCircle(String circleName, int pageNumber) {
		try {
			return messageRepository.getMessagesFromCircle(circleName);
		} catch(Exception e) {
			
		}
		return null;
	}

	/*
	 * This method should be used to get all messages from a specific user to
	 * another specific user. Call the corresponding method of Respository
	 * interface.
	 */
	public List<Message> getMessagesFromUser(String username, String otherUsername, int pageNumber) {
		try {
			return messageRepository.getMessagesFromUser(username, otherUsername);
		} catch(Exception e) {
			
		}
		return null;
	}

	/*
	 * This method should be used to send messages to a specific circle. Please
	 * validate whether the circle exists and whether the sender is subscribed to
	 * the circle. Call the corresponding method of Respository interface.
	 */
	public boolean sendMessageToCircle(String circleName, Message message) {
		//Still need to implement
	
//		messageRepository.save(new Message(messageRepository));
//		String senderName, String receiverId, String circleName, /*Timestamp postedDate,*/
//		String streamType, String message, String tag
		return false;
	}

	/*
	 * This method should be used to send messages to a specific user. Please
	 * validate whether the sender and receiver are valid users. Call the
	 * corresponding method of Respository interface.
	 */
	public boolean sendMessageToUser(String username, Message message) {
		//Still need to implement
		return false;

	}

	/*
	 * This method should be used to list out all tags from all existing messages.
	 * Call the corresponding method of Respository interface.
	 */
	public List<String> listTags() {
		try {
			return messageRepository.listAllTags();
		} catch(Exception e) {
			
		}
		return null;
	}

	/*
	 * This method should be used to list out all subscribed tags by a specific
	 * user. Call the corresponding method of Respository interface.
	 */
	public List<String> listMyTags(String username) {
		try {
			messageRepository.listMyTags(username);
		} catch(Exception e) {
			
		}
		return null;
	}

	/*
	 * This method should be used to show all public messages(messages sent to
	 * circles) containing a specific tag. Call the corresponding method of
	 * Respository interface.
	 */
	public List<Message> showMessagesWithTag(String tag, int pageNumber) {
		try {
			return messageRepository.showMessagesWithTag(tag);
		} catch(Exception e) {
			
		}
		return null;
	}

	/*
	 * This method should be used to subscribe a user to a specific tag. Call the
	 * corresponding method of Respository interface.
	 */
	public boolean subscribeUserToTag(String username, String tag) {
		//Still need to implement
		return false;
	}

	/*
	 * This method should be used to unsubscribe a user from a specific tag. Call
	 * the corresponding method of Respository interface.
	 */
	public boolean unsubscribeUserToTag(String username, String tag) {
		//Still need to implement
		return false;
	}

}
