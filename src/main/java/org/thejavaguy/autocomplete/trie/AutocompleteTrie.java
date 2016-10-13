package org.thejavaguy.autocomplete.trie;

import org.thejavaguy.autocomplete.Entry;

import java.util.Collections;
import java.util.List;

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
    return Collections.emptyList();
  }

  @Override
  public String toString()
  {
    return "";
  }
}
