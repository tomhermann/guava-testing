package com.zombietank.guava.users;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class UserRepository {
	private ImmutableSet<User> users;
	
	public UserRepository() {
		users = ImmutableSet.of(
					new User("moe", "Moe", "Howard"),
					new User("shemp", "Shemp", "Howard"),
					new User("larry", "Larry", "Fine"),
					new User("curly", "Curly", "Howard")
				);
	}
	
	public Set<User> getUsers() {
		return users;
	}
}
