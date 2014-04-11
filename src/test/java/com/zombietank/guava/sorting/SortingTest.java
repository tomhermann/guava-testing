package com.zombietank.guava.sorting;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class SortingTest {

	@Test
	public void naturalOrdering() throws Exception {
		
		List<Integer> sortedCopy = Ordering.natural().sortedCopy(Arrays.asList(10, 9, 4, 5, 1, 6 ,8, 12));

		assertEquals(Arrays.asList(1, 4, 5, 6, 8, 9, 10, 12), sortedCopy);
	}

	@Test
	public void naturalOrderingWithStrings() throws Exception {
		List<String> unsorted = Arrays.asList("AAA", "aaa", "BBB", "ccc", "ddd");
		
		List<String> sortedCopy = Ordering.natural().sortedCopy(unsorted);
		
		assertEquals(Arrays.asList("AAA", "BBB", "aaa", "ccc", "ddd"), sortedCopy);
	}
	
	@Test
	public void naturalOrderingWithStringsVsCollectionSort() throws Exception {
		List<String> unsorted = Arrays.asList("AAA", "aaa", "BBB", "ccc", "ddd");
		
		List<String> sortedMutatedCopy = Lists.newArrayList(unsorted);
		Collections.sort(sortedMutatedCopy);
		
		assertEquals(sortedMutatedCopy, Ordering.natural().sortedCopy(unsorted));
	}
}
