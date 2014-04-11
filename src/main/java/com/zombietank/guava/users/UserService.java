package com.zombietank.guava.users;

import java.util.Collection;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Optional<User> findUser(final String username) {
		return Iterables.tryFind(userRepository.getUsers(), new Predicate<User>() {
			@Override
			public boolean apply(User input) {
				return input.getUsername().equals(username);
			}
		});
	}
	
	public User traditionalFindUser(String username) {
		for (User user : userRepository.getUsers()) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public Collection<String> getAllUsernames() {
		return Collections2.transform(userRepository.getUsers(), new Function<User, String>() {
			@Override
			public String apply(User input) {
				return input.getUsername();
			}
		});
	}
}
