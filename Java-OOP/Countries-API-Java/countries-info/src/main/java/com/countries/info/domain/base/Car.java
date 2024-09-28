/**
 * Holds information related to vehicle registration and driving conventions in a country, 
 * such as vehicle registration signs and the side of the road for driving.
 */

package com.countries.info.domain.base;

import java.util.List;

public class Car
{
  private List<String> signs;
  private String side;

  public List<String> getSigns()
  {
    return signs;
  }

  public void setSigns(List<String> signs)
  {
    this.signs = signs;
  }

  public String getSide()
  {
    return side;
  }

  public void setSide(String side)
  {
    this.side = side;
  }
}
