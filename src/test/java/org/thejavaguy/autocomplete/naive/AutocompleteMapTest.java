package org.thejavaguy.autocomplete.naive;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thejavaguy.autocomplete.Entry;

/**
 * @author Ivan Milosavljevic imilosavljevic@novomaticls.com
 */
public class AutocompleteMapTest
{
  private AutocompleteMap sut;

  @Before
  public void setUp() throws Exception
  {
    sut = new AutocompleteMap();
  }

  @After
  public void tearDown() throws Exception
  {
    sut = null;
  }

  @Test
  public void autocomplete_OnEmptyObject_ReturnsEmptySet() throws Exception
  {
    List<String> actual = sut.autocomplete("doesnt matter");
    assertThat(actual, is(not(nullValue())));
    assertThat(actual, hasSize(0));
  }

  @Test
  public void autocomplete_OnOneEntry_ReturnsThatEntryName() throws Exception
  {
    Entry entry = new Entry("Amsterdam", 10);
    sut.add(entry);
    List<String> actual = sut.autocomplete("A");
    assertThat(actual, hasSize(1));
  }

  @Test
  public void autocomplete_OnNonexistingEntry_ReturnsEmptySet() throws Exception
  {
    Entry entry = new Entry("Amsterdam", 10);
    sut.add(entry);
    List<String> actual = sut.autocomplete("B");
    assertThat(actual, hasSize(0));
  }

  @Test
  public void autocomplete_IsCaseSensitive() throws Exception
  {
    Entry entry = new Entry("Amsterdam", 10);
    sut.add(entry);
    List<String> actual = sut.autocomplete("a");
    assertThat(actual, hasSize(0));
  }

  @Test
  public void autocomplete_OnSeveralEntries_ReturnsOnlyMatchedOnes() throws Exception
  {
    sut.add(new Entry("Amstelveen", 7));
    sut.add(new Entry("Amsterdam", 11));
    sut.add(new Entry("Belgrade", 11));
    sut.add(new Entry("Caracas", 1));
    List<String> actual = sut.autocomplete("A");
    assertThat(actual, hasSize(2));
    assertThat(actual, contains("Amsterdam", "Amstelveen"));
  }

  @Test
  public void autocomplete_OnEntriesWithSameWeight_ReturnsThemInOrder() throws Exception
  {
    sut.add(new Entry("Amstelveen", 10));
    sut.add(new Entry("Amsterdam", 10));
    sut.add(new Entry("Antwerpen", 10));
    sut.add(new Entry("Belgrade", 10));
    List<String> actual = sut.autocomplete("A");
    assertThat(actual, hasSize(3));
    assertThat(actual, contains("Amstelveen", "Amsterdam", "Antwerpen"));
  }
}
