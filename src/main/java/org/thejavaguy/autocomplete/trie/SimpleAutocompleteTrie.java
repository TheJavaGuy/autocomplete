package org.thejavaguy.autocomplete.trie;

import java.util.Collections;
import java.util.List;

public final class SimpleAutocompleteTrie {
	private final SimpleAutocompleteTrieNode root;

	public SimpleAutocompleteTrie() {
		this.root = SimpleAutocompleteTrieNode.root();
	}

	public void add(final String word) {
		SimpleAutocompleteTrieNode current = root;
		for (int i = 0; i < word.length(); ++i) {
			char letter = word.charAt(i);
			if (!current.hasChild(letter)) {
				current.addChild(letter);
			}
			current = current.childFor(letter);
		}
		current.makeWord();
	}

	public void add(final Iterable<String> words) {
		words.forEach(w -> add(w));
	}

	public List<String> autocomplete(final String userInput) {
		if (root.size() == 0) {
			return Collections.emptyList();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SimpleAutocompleteTrie [root=");
		builder.append(root);
		builder.append("]");
		return builder.toString();
	}
}
