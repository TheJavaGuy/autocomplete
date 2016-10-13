package org.thejavaguy.autocomplete.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Milosavljevic imilosavljevic@novomaticls.com
 */
public final class AutocompleteTrieNode
{
  private static final char ROOT = '*';
  private final char letter;
  private int weight;
  private final List<AutocompleteTrieNode> children;
  private boolean isLeaf;

  public AutocompleteTrieNode(final char letter)
  {
    this(letter, 0);
  }

  public AutocompleteTrieNode(final char letter, final int weight)
  {
    this.letter = letter;
    this.weight = weight;
    this.children = new ArrayList<>();
    this.isLeaf = false;
  }

  public static AutocompleteTrieNode root()
  {
    return new AutocompleteTrieNode(ROOT);
  }

  public void addChild(final AutocompleteTrieNode child)
  {
    children.add(child);
  }

  public boolean hasChild(final char character)
  {
    for (AutocompleteTrieNode child : children)
    {
      if (child.letter() == character)
      {
        return true;
      }
    }
    return false;
  }

  private char letter()
  {
    return letter;
  }

  public AutocompleteTrieNode childWith(final char character)
  {
    for (AutocompleteTrieNode child : children)
    {
      if (child.letter() == character)
      {
        return child;
      }
    }
    return null;
  }

  public void makeLeaf()
  {
    isLeaf = true;
  }
}
