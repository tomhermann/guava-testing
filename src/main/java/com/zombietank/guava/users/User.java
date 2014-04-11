package com.zombietank.guava.users;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class User implements Comparable<User>{
	private String username;
	private String firstName;
	private String lastName;

	public User() {
	}

	public User(String username, String firstName, String lastName) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return Joiner.on(" ").join(firstName, lastName);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("username", username)
				.add("firstName", firstName)
				.add("lastName", lastName).toString();
	}

	@Override
	public int compareTo(User that) {
		return ComparisonChain.start()
				.compare(this.lastName, that.lastName)
		        .compare(this.firstName, that.firstName)
		        .compare(this.username, that.username, Ordering.natural().nullsLast())
		       .result();
	}
	
	@Override
	public int hashCode() {
		return java.util.Objects.hash(firstName, lastName, username);
	}
}