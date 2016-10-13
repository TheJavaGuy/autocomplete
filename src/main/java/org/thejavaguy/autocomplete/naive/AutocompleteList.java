package org.thejavaguy.autocomplete.naive;

import org.thejavaguy.autocomplete.Entry;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan Milosavljevic imilosavljevic@novomaticls.com
 */
public final class AutocompleteList
{
  private final List<Entry> entries;

  public AutocompleteList()
  {
    this.entries = new LinkedList<>();
  }

  public void add(final Entry entry)
  {
    removeWithName(entry.name());
    entries.add(entry);
  }

  public void addAll(final Iterable<Entry> entries)
  {
    entries.forEach(this::add);
  }

  public void remove(final Entry entry)
  {
    entries.remove(entry);
  }

  public void removeAll(final Iterable<Entry> entries)
  {
    entries.forEach(this::remove);
  }

  public void removeWithName(final String name)
  {
    for (Entry currentEntry : entries)
    {
      if (currentEntry.name().equals(name))
      {
        entries.remove(currentEntry);
        return;
      }
    }
  }

  public int size()
  {
    return entries.size();
  }

  public void clear()
  {
    entries.clear();
  }

  public List<String> autocomplete(final String userInput)
  {
    List<Entry> ret = entries
      .stream()
      .filter(en -> en.name().startsWith(userInput))
      .collect(Collectors.toList());
    Collections.sort(ret);
    return Collections.unmodifiableList(ret.stream().map(Entry::name).collect(Collectors.toList()));
  }

  @Override
  public String toString()
  {
    final StringBuilder sb = new StringBuilder("AutocompleteList{");
    sb.append("entries=").append(entries);
    sb.append('}');
    return sb.toString();
  }
}
