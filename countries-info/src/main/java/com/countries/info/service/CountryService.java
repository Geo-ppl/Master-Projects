/**
 * The CountryService class acts as a service layer to interact with the external
 * REST API (via ExternalRestApi) and map the retrieved data to CountryDTO objects.
 */
package com.countries.info.service;

import com.countries.info.domain.CountryDTO;
import com.countries.info.domain.v3.v31.Country;
import com.countries.info.externalApi.ExternalRestApi;
import com.countries.info.exception.CountryAPIException;

import java.util.List;
import java.util.stream.Collectors;

public class CountryService
{
  private ExternalRestApi restApi;

  /**
   * Constructs a new CountryService instance with a default ExternalRestApi.
   */
  public CountryService()
  {
    this.restApi = new ExternalRestApi();
  }

  /**
   * Retrieves a list of CountryDTO objects representing all countries.
   * @return List of CountryDTO objects for all countries.
   * @throws CountryAPIException if an error occurs while retrieving country data.
   */
  public List<CountryDTO> getAllCountries() throws CountryAPIException
  {
    try {
      return restApi.getAllCountries().stream().map(country -> mappedToCountryDTO(country, ""))
            .collect(Collectors.toList());
    } catch (CountryAPIException e) {
      throw new CountryAPIException("Error while retrieving all countries: " + e.getMessage(), e);
    }
  }

  /**
   * Retrieves a list of CountryDTO objects for countries with the specified name.
   * If the name is empty, retrieves information for all countries.
   * @param name The name of the country to retrieve.
   * @return List of CountryDTO objects for the specified country name.
   * @throws CountryAPIException if an error occurs while retrieving country data.
   */
  public List<CountryDTO> getCountryByName(String name) throws CountryAPIException
  {
    try {
      return restApi.getCountryByName(name).stream().map(country -> mappedToCountryDTO(country, ""))
            .collect(Collectors.toList());
    } catch (CountryAPIException e) {
      throw new CountryAPIException("Error while retrieving country by name: " + e.getMessage(), e);
    }
  }

  /**
   * Retrieves a list of CountryDTO objects for countries with the specified language.
   * If the language is empty, retrieves information for all countries.
   * @param language The language of the countries to retrieve.
   * @return List of CountryDTO objects for the specified language.
   * @throws CountryAPIException if an error occurs while retrieving country data.
   */
  public List<CountryDTO> getCountriesByLanguage(String language) throws CountryAPIException
  {
    try {
      return restApi.getCountriesByLanguage(language).stream().map(country -> mappedToCountryDTO(country, language))
            .collect(Collectors.toList());
    } catch (CountryAPIException e) {
      throw new CountryAPIException("Error while retrieving countries by language: " + e.getMessage(), e);
    }
  }

  /**
   * Retrieves a list of CountryDTO objects for countries with the specified currency.
   * If the currency is empty, retrieves information for all countries.
   * @param currency The currency of the countries to retrieve.
   * @return List of CountryDTO objects for the specified currency.
   *
   */
  public List<CountryDTO> getCountriesByCurrency(String currency) throws CountryAPIException
  {
    try {
      return restApi.getCountriesByCurrency(currency).stream().map(country -> mappedToCountryDTO(country, ""))
            .collect(Collectors.toList());
    } catch (CountryAPIException e) {
      throw new CountryAPIException("Error while retrieving countries by currency: " + e.getMessage(), e);
    }
  }

  /**
   * Maps a Country object to a CountryDTO object with optional language filtering.
   * @param country The Country object to map.
   * @param filter  The language filter (empty string for no filtering).
   * @return CountryDTO object representing the mapped data.
   */
  private CountryDTO mappedToCountryDTO(Country country, String filter)
  {
    CountryDTO countryDTO = CountryDTO.builder()
            .name(country.getName() != null ? country.getName().getCommon() : null)
            .capital(country.getCapital() != null && !country.getCapital().isEmpty() ? country.getCapital().get(0)
                    : null)
            .continent(country.getContinents() != null && !country.getContinents().isEmpty()
                    ? country.getContinents().get(0)
                    : null)
            .population(country.getPopulation()).build();

    if (country.getCurrencies() != null)
    {
      country.getCurrencies().forEach((key, value) -> {
        countryDTO.setCurrency(key);
      });
    }

    if (country.getLanguages() != null)
    {
      country.getLanguages().forEach((key, value) -> {
        if (value.equals(filter) || filter.isEmpty())
        {
          countryDTO.setLanguage(value);
        }
      });
    }

    return countryDTO;
  }
}
