package org.thejavaguy.autocomplete.trie;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SimpleAutocompleteTrieTest {
	private SimpleAutocompleteTrie sut;

	@Before
	public void setUp() throws Exception {
		sut = new SimpleAutocompleteTrie();
	}

	@Test
	public void allWords_ReturnAllAddedWords() {
	    List<String> words = new ArrayList<>();
	    words.add("Ivan");
	    words.add("Damir");
	    words.add("Ivana");
	    words.add("IvanChe");
	    words.add("Firefox");
	    words.add("FirefoxNightly");
	    sut.add(words);
		assertThat(sut.allWords().size(), is(words.size()));
	}
	
	@Test
	public void autocomplete_OnEmptyTrie_ReturnsEmptyList() {
	    assertThat(sut.autocomplete("aaaa").size(), is(0));
	}
    
	@Test
	public void autocomplete_WhenPrefixExists_ReturnsAllWordsWithThatPrefix() {
	    List<String> words = new ArrayList<>();
	    words.add("Ivan");
	    words.add("Damir");
	    words.add("Ivana");
	    words.add("IvanChe");
	    words.add("Firefox");
	    words.add("FirefoxNightly");
	    sut.add(words);
	    assertThat(sut.autocomplete("Iv").size(), is(3));
	}
}
