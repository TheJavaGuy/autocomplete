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
		SimpleAutocompleteTrieNode current = root;
		boolean hasPrefix = true;
		for (int i = 0; i < userInput.length(); ++i) {
		    char currentChar = userInput.charAt(i);
		    if (current.hasChild(currentChar)) {
		        current = current.childFor(currentChar);
		    } else {
		        hasPrefix = false;
		        break;
		    }
		}
		if (hasPrefix) {
		    return current.words();
		} else {
		    return Collections.emptyList();		    
		}
	}
	
	public List<String> allWords() {
	    return root.words();
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
