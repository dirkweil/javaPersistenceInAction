package de.gedoplan.beantrial.jpia.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Helferklasse zur Verarbeitung von Listen.
 * 
 * @author dw
 * 
 */
public final class ListUtil
{
  /**
   * Liste von Strings zu einem konkatenieren, getrennt durch '\n'.
   * 
   * @param strings Liste von Strings
   * @return Konkatenierte Strings
   */
  public static String toString(List<String> strings)
  {
    StringBuilder buf = new StringBuilder();
    boolean first = true;
    for (String line : strings)
    {
      if (!first)
      {
        buf.append('\n');
      }
      buf.append(line);
      first = false;
    }
    return buf.toString();
  }

  /**
   * String in Liste aufsplitten, Trennung an Zeilenwechseln.
   * 
   * @param string String
   * @return Liste
   */
  public static List<String> toList(String string)
  {
    List<String> result = new ArrayList<>();
    if (string != null)
    {
      for (String line : string.trim().split("[ \\t\\x0B\\f\\r]*\\n[ \\t\\x0B\\f\\r]*"))
      {
        if (!line.isEmpty())
        {
          result.add(line);
        }
      }
    }
    return result;
  }

  private ListUtil()
  {
  }
}
