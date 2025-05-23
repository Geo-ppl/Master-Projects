/**
 * Represents the international direct dialing (IDD) information for a country, 
 * including the IDD root and any suffixes. Essential for handling international communication formats.
 */
package com.countries.info.domain.base;

import java.util.List;

public class Idd
{
  private String root;
  private List<String> suffixes;

  public String getRoot()
  {
    return root;
  }

  public void setRoot(String root)
  {
    this.root = root;
  }

  public List<String> getSuffixes()
  {
    return suffixes;
  }

  public void setSuffixes(List<String> suffixes)
  {
    this.suffixes = suffixes;
  }
}
