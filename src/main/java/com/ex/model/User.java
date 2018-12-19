package com.ex.model;

public class User {

	private String username;

	private String password;

	private String dateOfBirth;

	private String ssn;

	public User() {

	}

	public User(String username, String password, String dateOfBirth, String ssn) {
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.ssn = ssn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String toString() {
		return "Employee{" + "username = " + username + ", dateOfBirth = '" + dateOfBirth + '\'' + ", ssn = " + ssn
				+ '}';
	}
}
