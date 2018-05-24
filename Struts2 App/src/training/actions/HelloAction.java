package training.actions;

public class HelloAction {

	// field
	private String message;
	private String username = "Mr.Nobody";

	// getter/setter is known as a "property"
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String execute() {
		message = String.format("Hello %s, from Struts2 action class.", username);

		System.out.println("----- From inside of training.actions.HelloAction.execute() method.");
		return "success";
	}

}
