package org.thejavaguy.autocomplete.trie;

import java.util.Map;
import java.util.TreeMap;

public final class SimpleAutocompleteTrieNode {
	private static final char ROOT = '*';
	private final char letter;
	private final Map<Character, SimpleAutocompleteTrieNode> children;
	private boolean isWord;

	public SimpleAutocompleteTrieNode(final char letter) {
		this.letter = letter;
		this.children = new TreeMap<>();
		this.isWord = false;
	}

	public static SimpleAutocompleteTrieNode root() {
		return new SimpleAutocompleteTrieNode(ROOT);
	}

	public void addChild(final char letter) {
		if (children.containsKey(Character.valueOf(letter))) {
			return;
		}
		children.put(Character.valueOf(letter), new SimpleAutocompleteTrieNode(letter));
	}

	public boolean hasChild(final char letter) {
		return children.containsKey(Character.valueOf(letter));
	}

	public SimpleAutocompleteTrieNode childFor(final char letter) {
		if (!hasChild(letter)) {
			throw new IllegalArgumentException("This node doesn't have child for letter " + letter);
		}
		return children.get(Character.valueOf(letter));
	}

	public int size() {
		return children.size();
	}

	public void makeWord() {
		isWord = true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("S.A.T.N. [letter=");
		builder.append(letter);
		builder.append(", children=");
		builder.append(children);
		builder.append(", isWord=");
		builder.append(isWord);
		builder.append("]");
		return builder.toString();
	}
}
