/**
 * Captures the native names of a country, including both the common and official names.
 * Useful for localized data presentation.
 */
package com.countries.info.domain.base;

public class NativeName
{
  private String official;
  private String common;

  public String getOfficial()
  {
    return official;
  }

  public void setOfficial(String official)
  {
    this.official = official;
  }

  public String getCommon()
  {
    return common;
  }

  public void setCommon(String common)
  {
    this.common = common;
  }
}
