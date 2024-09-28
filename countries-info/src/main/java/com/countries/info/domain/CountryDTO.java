/**
 * Data Transfer Object (DTO) for Country information.
 * This class is used to transfer country data between different layers of the application,
 * encapsulating the country information in a more
 * manageable format. It includes basic country attributes like name, capital, language,
 * currency, population, and continent.**/

package com.countries.info.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CountryDTO
{
  private String name;
  private String capital;
  private String language;
  private String currency;
  private long population;
  private String continent;
}