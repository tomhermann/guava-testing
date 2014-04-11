package com.zombietank.guava.optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.base.Optional;
import com.zombietank.guava.users.User;
import com.zombietank.guava.users.UserRepository;
import com.zombietank.guava.users.UserService;

public class OptionalTest {

	@Test
	public void representNullableReference() {
		Optional<Integer> possible = Optional.of(5);

		assertThat(possible.isPresent(), is(true));
		assertThat(possible.get(), is(5));
	}

	@Test
	public void representAbsentReference() {
		Optional<Integer> nothing = Optional.absent();

		assertThat(nothing.isPresent(), is(false));
	}

	@Test(expected = IllegalStateException.class)
	public void gettingAbsentValueThrowsException() {
		Optional<Integer> nothing = Optional.absent();

		nothing.get();
	}
	
	@Test
	public void useOrToSpecifyDefault() throws Exception {
		Optional<Integer> nothing = Optional.absent();

		Integer value = nothing.or(42);
		
		assertThat(value, is(42));
	}

	@Test
	public void useOrNullToGetNullBack() throws Exception {
		Optional<Integer> nothing = Optional.absent();
		
		Integer value = nothing.orNull();
		
		assertThat(value, is(nullValue()));
	}

	@Test
	public void inPracticeWithValidUser() {
		UserService service = new UserService(new UserRepository());
		Optional<User> lookup = service.findUser("larry");
	
		assertThat(lookup.isPresent(), is(true));
		assertThat(lookup.get().getFullName(), is("Larry Fine"));
	}

	@Test
	public void inPracticeWithNonExistentUser() {
		UserService service = new UserService(new UserRepository());
		
		Optional<User> lookup = service.findUser("joederita");
		
		assertThat(lookup.isPresent(), is(false));
	}
	
	@Test(expected=NullPointerException.class)
	public void traditionalLookup() {
		UserService service = new UserService(new UserRepository());
		
		// Does lookup return null when a user isn't found, throw a NoSuchElement exception? Who knows?
		User lookup = service.traditionalFindUser("fredrick");
		
		// Seems like I got a user back, time to get their first name, oops NPE!
		assertEquals("Freddy Krueger", lookup.getFullName());
	}
	
}
