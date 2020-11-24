package entity;

import java.io.Serializable;

import control.PasswordMgr;

public abstract class User implements Serializable {
	private String username;
	private byte[] hashedPassword;
	private byte[] salt;

	public User(String username, String password) {
		this.setUsername(username);
		this.setSalt();
		this.setHashedPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getSalt() {
		return this.salt;
	}

	public void setSalt() {
		this.salt = PasswordMgr.getNextSalt();
	}

	public byte[] getHashedPassword() {
		return this.hashedPassword;
	}

	public void setHashedPassword(String password) {
		this.hashedPassword = PasswordMgr.hash(password, this.salt);
	}
}
