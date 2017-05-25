package org.thejavaguy.autocomplete.trie;

import java.util.Collections;
import java.util.List;

import org.thejavaguy.autocomplete.Entry;

/**
 * @author Ivan Milosavljevic imilosavljevic@novomaticls.com
 */
public final class AutocompleteTrie
{
  private final AutocompleteTrieNode root;

  public AutocompleteTrie()
  {
    this.root = AutocompleteTrieNode.root();
  }

  public void add(final Entry entry)
  {
    //removeWithName(entry.name());
    AutocompleteTrieNode pCrawl = root;
    for (int i = 0; i < entry.name().length(); ++i)
    {
      char currentCharacter = entry.name().charAt(i);
      if (!pCrawl.hasChild(currentCharacter))
      {
        pCrawl.addChild(new AutocompleteTrieNode(currentCharacter, entry.weight()));
      }
      pCrawl = pCrawl.childWith(currentCharacter);
      pCrawl.changeWeight(entry.weight());
    }
    pCrawl.makeLeaf();
  }

  public void addAll(final Iterable<Entry> entries)
  {
    entries.forEach(this::add);
  }

  public void remove(final Entry entry)
  {
  }

  public void removeAll(final Iterable<Entry> entries)
  {
    entries.forEach(this::remove);
  }

  public void removeWithName(final String name)
  {
  }

  public int size()
  {
    return 0;
  }

  public void clear()
  {
  }

  public List<String> autocomplete(final String userInput)
  {
    if (userInput.isEmpty())
    {
      return Collections.emptyList();
    }
    char firstLetter = userInput.charAt(0);
    if (!root.hasChild(firstLetter))
    {
      return Collections.emptyList();
    }
    AutocompleteTrieNode subtrieWithLetter = root.childWith(firstLetter);
    //return all strings in subtrie which are prefixed by whole userInput
    return Collections.emptyList();
  }

  @Override
  public String toString()
  {
    return "";
  }
}
