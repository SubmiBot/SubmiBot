package p2.submibot.services;

import java.io.Serializable;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token, matr;

	public UserInfo(String token, String matr) {
		this.token = token;
		this.matr = matr;
	}

	public String getToken() {
		return token;
	}

	public String getMatr() {
		return this.matr;
	}

	@Override
	public String toString() {
		return this.matr + " - " + this.token;
	}
}
