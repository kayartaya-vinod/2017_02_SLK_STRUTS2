package training.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import training.entity.Contact;

public class ContactService {
	private static ContactService instance;
	private Map<String, Map<Integer, Contact>> contactsMap = new HashMap<>();
	private static int idCounter = 0;
	
	static {
		instance = new ContactService();
	}

	private ContactService() {
		addContactForUser("vinod@vinod.co", new Contact("Shyam", "shyamkc@gmail.com", "7988227722", "Bangalore"));
		addContactForUser("vinod@vinod.co", new Contact("Harish", "harish8383@gmail.com", "9983736722", "Bangalore"));
		addContactForUser("vinod@vinod.co", new Contact("Umesh", "umeshmd3345@gmail.com", "8989987722", "Chennai"));
	}

	public static ContactService getInstace() {
		return instance;
	}

	public void addContactForUser(String email, Contact contact) {
		Map<Integer, Contact> map = null;
		if (contactsMap.containsKey(email)) {
			map = contactsMap.get(email);
		} else {
			map = new HashMap<>();
			contactsMap.put(email, map);
		}
		contact.setId(++ContactService.idCounter);
		map.put(contact.getId(), contact);
		System.out.println("Added: " + contact);
	}

	public Contact getContact(String email, int id) {
		if (contactsMap.containsKey(email)) {
			Map<Integer, Contact> map = contactsMap.get(email);
			if (map.containsKey(id)) {
				return map.get(id);
			} else {
				throw new ServiceException("Invalid contact id");
			}
		} else {
			throw new ServiceException("You don't have any contacts");
		}
	}

	public void updateContact(String email, int id, Contact contact) {
		if (contactsMap.containsKey(email)) {
			Map<Integer, Contact> map = contactsMap.get(email);
			if (map.containsKey(id)) {
				map.put(id, contact);
			} else {
				throw new ServiceException("Invalid contact id");
			}
		} else {
			throw new ServiceException("You don't have any contacts");
		}
	}

	public void deleteContact(String email, int id) {
		if (contactsMap.containsKey(email)) {
			Map<Integer, Contact> map = contactsMap.get(email);
			if (map.containsKey(id)) {
				map.remove(id);
			} else {
				throw new ServiceException("Invalid contact id");
			}
		} else {
			throw new ServiceException("You don't have any contacts");
		}
	}

	public List<Contact> getAllContactsForUser(String email) {
		List<Contact> list = new ArrayList<Contact>();
		if (contactsMap.containsKey(email)) {
			list.addAll(contactsMap.get(email).values());
		}
		return list;
	}

}
