package org.thejavaguy.autocomplete.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
	
    public List<String> words() {
        List<String> ret = new ArrayList<>();
        Stack<SimpleAutocompleteTrieNode> stack = new Stack<>();
        stack.push(this);
        List<SimpleAutocompleteTrieNode> visited = new ArrayList<>();
        List<SimpleAutocompleteTrieNode> prefixed = new ArrayList<>();
        StringBuilder prefixSoFar = new StringBuilder();
        while (!stack.isEmpty()) {
            SimpleAutocompleteTrieNode current = stack.pop();
            if (current.letter != ROOT && !prefixed.contains(current)) {
                prefixSoFar.append(current.letter);
                prefixed.add(current);
                if (current.isWord) {
                    ret.add(prefixSoFar.toString());
                }
            }
            List<SimpleAutocompleteTrieNode> unvisited = current.unvisitedChildren(visited);
            if (unvisited.isEmpty()) {
                if (prefixSoFar.length() > 0) {
                    prefixSoFar.setLength(prefixSoFar.length() - 1);                    
                }
                visited.add(current);
            } else {
                stack.push(current);
                unvisited.forEach(n -> stack.push(n));                
            }
        }
        return ret;
    }   
	
	private List<SimpleAutocompleteTrieNode> unvisitedChildren(List<SimpleAutocompleteTrieNode> visited) {
	    return children
	            .values()
	            .stream()
	            .filter(n -> !visited.contains(n))
	            .collect(Collectors.toList());
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
