package com.zombietank.guava.string;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Splitter;

public class SplitterTest {

	@Test
	public void split() throws Exception {
		List<String> split = Splitter.on(",").splitToList("larry,curly,moe");

		assertThat(split.contains("larry"), is(true));
		assertThat(split.contains("curly"), is(true));
		assertThat(split.contains("moe"), is(true));
	}

	@Test
	public void splitWithEmptyElement() throws Exception {
		List<String> split = Splitter.on(",").splitToList("larry,,moe");
		
		assertThat(split.contains(""), is(true));
	}

	@Test
	public void skipEmptyElement() throws Exception {
		List<String> split = Splitter.on(",").omitEmptyStrings().splitToList("larry,,moe");
		
		assertThat(split.contains(""), is(false));
	}
	
	@Test
	public void trimResults() throws Exception {
		List<String> split = Splitter.on(",").trimResults().splitToList("  hello,    weird,   spaces");
		
		assertThat(split.contains("hello"), is(true));
		assertThat(split.contains("weird"), is(true));
		assertThat(split.contains("spaces"), is(true));
	}
	
	@Test
	public void limitResults() throws Exception {
		// Stops splitting after the specified number of strings have been returned.
		List<String> split = Splitter.on(",").limit(3).splitToList("one,two,three,four,five,six");
		
		assertThat(split.contains("one"), is(true));
		assertThat(split.contains("two"), is(true));
		assertThat(split.contains("three,four,five,six"), is(true));
	}
}
