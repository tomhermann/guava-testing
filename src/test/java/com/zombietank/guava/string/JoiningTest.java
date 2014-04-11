package com.zombietank.guava.string;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class JoiningTest {

	@Test
	public void joiningStrings() {
		String join = Joiner.on(", ").join("larry", "curly", "moe");
	
		assertEquals("larry, curly, moe", join);
	}
	
	@Test
	public void joiningCollections() throws Exception {
		String join = Joiner.on(", ").join(ImmutableList.of("larry", "curly", "moe"));
		
		assertEquals("larry, curly, moe", join);
	}
	
	@Test
	public void joiningMaps() throws Exception {
		Map<Integer, String> numbers = ImmutableMap.of(1, "one", 2, "two", 3, "three");
		
		String join = Joiner.on("; ").withKeyValueSeparator(": ").join(numbers);
		
		assertEquals("1: one; 2: two; 3: three", join);
	}
	
	@Test(expected=NullPointerException.class)
	public void joiningWithNulls() throws Exception {
		Joiner.on(", ").join("larry", "curly", null, "moe");
	}

	@Test
	public void skippingNull() throws Exception {
		String join = Joiner.on(", ").skipNulls().join("larry", "curly", null, "moe");
		
		assertEquals("larry, curly, moe", join);
	}

	@Test
	public void userForNull() throws Exception {
		String join = Joiner.on(", ").useForNull("why are you passing me null?").join(null, "larry", "curly", "moe");
		
		assertEquals("why are you passing me null?, larry, curly, moe", join);
	}
}
