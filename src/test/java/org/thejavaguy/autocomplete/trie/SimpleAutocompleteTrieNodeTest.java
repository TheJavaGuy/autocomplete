package org.thejavaguy.autocomplete.trie;

import static org.junit.Assert.fail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Before;
import org.junit.Test;

public class SimpleAutocompleteTrieNodeTest {
	private SimpleAutocompleteTrieNode sut;

	@Before
	public void setUp() throws Exception {
		sut = new SimpleAutocompleteTrieNode('a');
	}

	@Test
	public void hasChild_WhenHasNoChildren_ReturnsFalse() {
		assertThat(sut.hasChild('b'), is(false));
	}

	@Test
	public void hasChild_WhenAddedOneChild_ReturnsTrue() {
		sut.addChild('b');
		assertThat(sut.hasChild('b'), is(true));
	}

	@Test
	public void size_WhenHasNoChildren_ReturnsZero() {
		assertThat(sut.size(), is(0));
	}

	@Test
	public void size_WhenAddedSeveralChildren_ReturnsNumberOfChildren() {
		sut.addChild('a');
		sut.addChild('b');
		sut.addChild('c');
		sut.addChild('d');
		assertThat(sut.size(), is(4));
		System.out.println(sut.toString());
	}

	@Test
	public void size_WhenAddedSameChildTwice_ReturnsOne() {
		sut.addChild('b');
		sut.addChild('b');
		assertThat(sut.size(), is(1));
	}

}
