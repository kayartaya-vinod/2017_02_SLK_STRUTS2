package training.service;

import java.util.HashMap;
import java.util.Map;

import training.entity.User;
import training.service.ServiceException;

public class UserService {
	private Map<String, User> userMap = new HashMap<>();
	private static UserService instance = new UserService();
	private static int idCounter = 0;

	private UserService() {
		User user = new User();
		user.setEmail("vinod@vinod.co");
		user.setPassword("topsecret");
		user.setName("Vinod Kumar");
		createUser(user);
		
		user = new User();
		user.setEmail("kumar@vinod.co");
		user.setPassword("secret");
		user.setName("Kumar Vinod");
		createUser(user);
	}

	public static UserService getInstace() {
		return instance;
	}

	public boolean isUser(String email) {
		return userMap.containsKey(email);
	}

	public User getUser(String email, String password) {
		if (userMap.containsKey(email)) {
			User user = userMap.get(email);
			if (user.getPassword().equals(password)) {
				return user;
			}
		}
		throw new ServiceException("Invalid email/password");
	}

	public void createUser(User user) {
		if(userMap.containsKey(user.getEmail())){
			throw new ServiceException("Email already registered");
		}
		user.setId(++idCounter);
		userMap.put(user.getEmail(), user);
	}
}
