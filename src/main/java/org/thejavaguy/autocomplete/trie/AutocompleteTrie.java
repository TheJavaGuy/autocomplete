package org.thejavaguy.autocomplete.trie;

import org.thejavaguy.autocomplete.Entry;

import java.util.List;

/**
 * @author Ivan Milosavljevic imilosavljevic@novomaticls.com
 */
public final class AutocompleteTrie
{
  public void add(final Entry entry)
  {
    removeWithName(entry.name());
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
  }

  public void clear()
  {
  }

  public List<String> autocomplete(final String userInput)
  {
  }

  @Override
  public String toString()
  {
  }
}
