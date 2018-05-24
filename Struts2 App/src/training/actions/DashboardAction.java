package training.actions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import training.entity.Contact;
import training.entity.User;
import training.service.ContactService;
import training.service.ServiceException;

public class DashboardAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> sessionMap;
	private String actionMessage;
	private ContactService contactService = ContactService.getInstace();
	private List<Contact> contactList;
	private Contact contact = new Contact();

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String execute() {
		if (!sessionMap.containsKey("user")) {
			actionMessage = "You must be logged in to access the dashboard";
			return "dashboard-login-failed";
		}

		User currUser = (User) sessionMap.get("user");
		contactList = contactService.getAllContactsForUser(currUser.getEmail());

		return "success";
	}

	public String deleteContact() {
		if (!sessionMap.containsKey("user")) {
			actionMessage = "You must be logged in to access the dashboard";
			return "dashboard-login-failed";
		}

		User currUser = (User) sessionMap.get("user");
		try {
			String idStr = ServletActionContext.getRequest().getParameter("id");
			int id = Integer.parseInt(idStr);
			contactService.deleteContact(currUser.getEmail(), id);
		} catch (ServiceException e) {
			// no use if result type is "redirect"
			actionMessage = e.getMessage();
		} catch (NumberFormatException e) {
			// no use if result type is "redirect"
			actionMessage = "Id supplied was not a number";
		}
		return "success";
	}

	public String addContact() {
		if (!sessionMap.containsKey("user")) {
			actionMessage = "You must be logged in to access the dashboard";
			return "dashboard-login-failed";
		}
		User currUser = (User) sessionMap.get("user");

		setContact(new Contact());
		return "success";
	}

	public String editContact() {
		if (!sessionMap.containsKey("user")) {
			actionMessage = "You must be logged in to access the dashboard";
			return "dashboard-login-failed";
		}
		User currUser = (User) sessionMap.get("user");

		String idStr = ServletActionContext.getRequest().getParameter("id");
		int id = Integer.parseInt(idStr);
		setContact(contactService.getContact(currUser.getEmail(), id));
		return "success";
	}

	public String saveContact() {
		if (!sessionMap.containsKey("user")) {
			actionMessage = "You must be logged in to access the dashboard";
			return "dashboard-login-failed";
		}
		User currUser = (User) sessionMap.get("user");

		System.out.println();
		int id = contact.getId();
		if (id == 0) {
			contactService.addContactForUser(currUser.getEmail(), contact);
		} else {
			contactService.updateContact(currUser.getEmail(), id, contact);
		}

		return "success";
	}

	public String sortContactList() {
		if (!sessionMap.containsKey("user")) {
			actionMessage = "You must be logged in to access the dashboard";
			return "dashboard-login-failed";
		}
		User currUser = (User) sessionMap.get("user");

		contactList = contactService.getAllContactsForUser(currUser.getEmail());
		
		String key = ServletActionContext.getRequest().getParameter("key");
		switch (key) {
		case "name":
			Collections.sort(contactList, (a, b)->{return a.getName().compareTo(b.getName());});
			break;
		case "email":
			Collections.sort(contactList, (a, b)->{return a.getEmail().compareTo(b.getEmail());});
			break;
		case "phone":
			Collections.sort(contactList, (a, b)->{return a.getPhone().compareTo(b.getPhone());});
			break;
		case "city":
			Collections.sort(contactList, (a, b)->{return a.getCity().compareTo(b.getCity());});
			break;

		}
		return "success";
	}

}
