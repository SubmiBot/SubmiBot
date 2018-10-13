package p2.submibot.resources;

import java.io.Serializable;

public class UserInfo implements Serializable {

	private String name;
	
	private String surname;
	
	private String token;
	
	private static final long serialVersionUID = 1L;

	public UserInfo(String name, String surname, String token) {
		this.name = name;
		this.surname = surname;
		this.token = token;
	}

	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return this.name + " - " + this.surname;
	}
}
