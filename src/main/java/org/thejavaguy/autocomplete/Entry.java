package org.thejavaguy.autocomplete;

/**
 * @author Ivan Milosavljevic imilosavljevic@novomaticls.com
 */
public final class Entry implements Comparable<Entry>
{
  private final String name;
  private final int weight;

  public Entry(String name, int weight)
  {
    this.name = name;
    this.weight = weight;
  }

  public String name()
  {
    return name;
  }

  public int weight()
  {
    return weight;
  }

  @Override
  public int compareTo(Entry other)
  {
    if (weight < other.weight) {
      return 1;
    }
    if (weight == other.weight) {
      return 0;
    }
    return -1;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Entry entry = (Entry) o;

    if (weight != entry.weight) return false;
    return name.equals(entry.name);
  }

  @Override
  public int hashCode()
  {
    int result = name.hashCode();
    result = 31 * result + weight;
    return result;
  }

  @Override
  public String toString()
  {
    final StringBuilder sb = new StringBuilder("Entry{");
    sb.append("name='").append(name).append('\'');
    sb.append(", weight=").append(weight);
    sb.append('}');
    return sb.toString();
  }
}
