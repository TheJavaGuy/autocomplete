package org.thejavaguy.autocomplete.trie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimpleAutocompleteTrieTest {
	private SimpleAutocompleteTrie sut;

	@Before
	public void setUp() throws Exception {
		sut = new SimpleAutocompleteTrie();
	}

	@Test
	public void test() {
		sut.add("Amazon");
		sut.add("Amazonka");
		System.out.println(sut.toString());
	}
}
