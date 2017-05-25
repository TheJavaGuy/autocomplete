package org.thejavaguy.autocomplete.naive;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.thejavaguy.autocomplete.Entry;

/**
 * @author Ivan Milosavljevic imilosavljevic@novomaticls.com
 */
public final class AutocompleteMap
{
  private final Map<Character, List<Entry>> entriesByFirstLetter;

  public AutocompleteMap()
  {
    this.entriesByFirstLetter = new HashMap<>();
  }

  public void add(final Entry entry)
  {
    removeWithName(entry.name());
    if (entriesByFirstLetter.containsKey(entry.name().charAt(0)))
    {
      entriesByFirstLetter.get(entry.name().charAt(0)).add(entry);
    }
    else
    {
      List<Entry> currentEntries = new LinkedList<>();
      currentEntries.add(entry);
      entriesByFirstLetter.put(entry.name().charAt(0), currentEntries);
    }
  }

  public void addAll(final Iterable<Entry> entries)
  {
    entries.forEach(this::add);
  }

  public void remove(final Entry entry)
  {
    if (!entriesByFirstLetter.containsKey(entry.name().charAt(0)))
    {
      return;
    }
    List<Entry> entries = entriesByFirstLetter.get(entry.name().charAt(0));
    entries.remove(entry);
  }

  public void removeAll(final Iterable<Entry> entries)
  {
    entries.forEach(this::remove);
  }

  public void removeWithName(final String name)
  {
    if (!entriesByFirstLetter.containsKey(name.charAt(0)))
    {
      return;
    }
    List<Entry> entries = entriesByFirstLetter.get(name.charAt(0));
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
    int ret = 0;
    Set<Character> keys = entriesByFirstLetter.keySet();
    for (Character currentKey : keys)
    {
      ret += entriesByFirstLetter.get(currentKey).size();
    }
    return ret;
  }

  public void clear()
  {
    entriesByFirstLetter.clear();
  }

  public List<String> autocomplete(final String userInput)
  {
    Character potentialKey = userInput.charAt(0);
    if (!entriesByFirstLetter.containsKey(potentialKey))
    {
      return Collections.emptyList();
    }
    List<Entry> ret = entriesByFirstLetter.get(potentialKey)
      .stream()
      .filter(en -> en.name().startsWith(userInput))
      .collect(Collectors.toList());
    Collections.sort(ret);
    return Collections.unmodifiableList(ret.stream().map(Entry::name).collect(Collectors.toList()));
  }
}
