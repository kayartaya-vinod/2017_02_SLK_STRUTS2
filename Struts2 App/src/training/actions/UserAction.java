package training.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import training.entity.User;
import training.service.ServiceException;
import training.service.UserService;

public class UserAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> sessionMap;
	private User user;
	private String actionMessage;
	private UserService userService = UserService.getInstace();
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}
	
	// custom validation logic
	@Override
	public void validate() {
		if(user==null) return;
		
		if(user.getEmail().trim().equals("")){
			addFieldError("email", "Email id is required");
		}
		if(user.getPassword().trim().equals("")){
			addFieldError("password", "Password is required");
		}
	}

	// is invoked when the user submits a login form
	public String login() {

		if (user == null) {
			user = new User();
			return "login-form";
		}

		try {
			User currUser = userService.getUser(user.getEmail(), user.getPassword());
			sessionMap.put("user", currUser);
			return "login-success";
		} catch (ServiceException e) {
			actionMessage = e.getMessage();
			return "login-failed";
		}

	}

	// is invoked when the user clicks on the logout button
	public String logout() {
		if (sessionMap.containsKey("user")) {
			sessionMap.remove("user");
			actionMessage = "You have been successfully logged out.";
		}
		return "logout-success";
	}

	// is invoked when the user submits the registration form
	public String register() {
		return null;
	}

}
