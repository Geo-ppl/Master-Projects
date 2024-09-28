package com.countries.info.domain.v3.v31;


import com.countries.info.domain.base.BaseCountry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents detailed information about a country, extending the generic BaseCountry attributes.
 */

@NoArgsConstructor // Default constructor for creating an instance without setting any attributes initially.
@AllArgsConstructor // Constructor that initializes all fields with provided values.

public class Country extends BaseCountry
{
  private Flag flags;
  private Flag coatOfArms;
  private String startOfWeek;
  private CapitalInformation capitalInfo;
  private Map<String, String> postalCode;
  
  /**
   * Constructor that initializes country with mock data for testing or default setup.
   * @param mockCountry A mock name of the country used for initializing default values.
   */

  public Country(String mockCountry)
  {
    super(); // Calls the constructor of the BaseCountry
    this.flags = new Flag(); 
    this.coatOfArms = new Flag(); 
    this.startOfWeek = "Monday"; 
    this.capitalInfo = new CapitalInformation(); 
    this.postalCode = new HashMap<>(); 
}
  
//Getters and setters for each field

  public Flag getFlags()
  {
    return flags;
  }

  public void setFlags(Flag flags)
  {
    this.flags = flags;
  }

  public Flag getCoatOfArms()
  {
    return coatOfArms;
  }

  public void setCoatOfArms(Flag coatOfArms)
  {
    this.coatOfArms = coatOfArms;
  }

  public String getStartOfWeek()
  {
    return startOfWeek;
  }

  public void setStartOfWeek(String startOfWeek)
  {
    this.startOfWeek = startOfWeek;
  }

  public CapitalInformation getCapitalInfo()
  {
    return capitalInfo;
  }

  public void setCapitalInfo(CapitalInformation capitalInfo)
  {
    this.capitalInfo = capitalInfo;
  }

  public Map<String, String> getPostalCode()
  {
    return postalCode;
  }

  public void setPostalCode(Map<String, String> postalCode)
  {
    this.postalCode = postalCode;
  }
}
