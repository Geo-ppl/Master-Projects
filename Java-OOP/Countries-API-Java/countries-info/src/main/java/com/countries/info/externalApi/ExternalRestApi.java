/**
 * The ExternalRestApi class provides methods to interact with the external REST API
 * for retrieving information about countries.
 */
package com.countries.info.externalApi;

import com.countries.info.domain.v3.v31.Country;
import com.countries.info.exception.CountryAPIException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import java.util.List;

public class ExternalRestApi
{

  /**
   * The base URL of the external countries REST API.
   */
  private static final String BASE_URL = "https://restcountries.com/v3.1/";

  /**
   * The RestTemplate used for making HTTP requests.
   */
  private RestTemplate restTemplate;

  /**
   * Constructs a new ExternalRestApi instance with a default RestTemplate.
   */
  public ExternalRestApi()
  {
    this.restTemplate = new RestTemplate();
  }

  /**
   * Retrieves a list of all countries from the external API.
   * @return List of Country objects representing all countries.
   * @throws CountryAPIException if an error occurs while retrieving country data from the external API.
   */
  public List<Country> getAllCountries() throws CountryAPIException {
      String url = BASE_URL + "all";
      restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
      try {
          ResponseEntity<List<Country>> responseEntity = restTemplate.exchange(
                  url,
                  HttpMethod.GET,
                  null,
                  new ParameterizedTypeReference<List<Country>>() {}
          );
          return responseEntity.getBody();
      } catch (HttpClientErrorException | HttpServerErrorException e) {
          throw new CountryAPIException("Error during HTTP request for all countries: " + e.getMessage(), e);
      } catch (RestClientException e) {
          throw new CountryAPIException("Client error during HTTP request for all countries: " + e.getMessage(), e);
      } catch (Exception e) {
          throw new CountryAPIException("Unexpected error when fetching all countries", e);
      }
  }

  /**
   * Retrieves a list of countries by name from the external API.
   * If the name is empty, returns a list of all countries.
   * @param name The name of the country to retrieve.
   * @return List of Country objects representing countries with the specified name.
   * @throws CountryAPIException if an error occurs while retrieving country data by name from the external API.
   */
  public List<Country> getCountryByName(String name) throws CountryAPIException {
      if (name == null || name.isEmpty()) {
          return getAllCountries();
      }

      String url = BASE_URL + "name/" + name;
      restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
      try {
          ResponseEntity<List<Country>> responseEntity = restTemplate.exchange(
                  url,
                  HttpMethod.GET,
                  null,
                  new ParameterizedTypeReference<List<Country>>() {}
          );
          return responseEntity.getBody();
      } catch (HttpClientErrorException | HttpServerErrorException e) {
          throw new CountryAPIException("Error during HTTP request for country by name: " + e.getMessage(), e);
      } catch (RestClientException e) {
          throw new CountryAPIException("Client error during HTTP request for country by name: " + e.getMessage(), e);
      } catch (Exception e) {
          throw new CountryAPIException("Unexpected error when fetching country by name", e);
      }
  }


  /**
   * Retrieves a list of countries by language from the external API.
   * If the language is empty, returns a list of all countries.
   * @param language The language of the countries to retrieve.
   * @return List of Country objects representing countries with the specified language.
   *  @throws CountryAPIException if an error occurs while retrieving country data by language from the external API.
   */
  public List<Country> getCountriesByLanguage(String language) throws CountryAPIException {
	    if (language == null || language.isEmpty()) {
	        return getAllCountries();
	    }

	    String url = BASE_URL + "lang/" + language;
	    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	    try {
	        ResponseEntity<List<Country>> responseEntity = restTemplate.exchange(
	                url,
	                HttpMethod.GET,
	                null,
	                new ParameterizedTypeReference<List<Country>>() {}
	        );
	        return responseEntity.getBody();
	    } catch (HttpClientErrorException | HttpServerErrorException e) {
	        throw new CountryAPIException("Error during HTTP request for countries by language: " + e.getMessage(), e);
	    } catch (RestClientException e) {
	        throw new CountryAPIException("Client error during HTTP request for countries by language: " + e.getMessage(), e);
	    } catch (Exception e) {
	        throw new CountryAPIException("Unexpected error when fetching countries by language", e);
	    }
	}


  /**
   * Retrieves a list of countries by currency from the external API.
   * If the currency is empty, returns a list of all countries.
   * @param currency The currency of the countries to retrieve.
   * @return List of Country objects representing countries with the specified currency.
   * @throws CountryAPIException if an error occurs while retrieving country data by currency from the external API.
   */
  public List<Country> getCountriesByCurrency(String currency) throws CountryAPIException {
	    if (currency == null || currency.isEmpty()) {
	        return getAllCountries();
	    }

	    String url = BASE_URL + "currency/" + currency;
	    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	    try {
	        ResponseEntity<List<Country>> responseEntity = restTemplate.exchange(
	                url,
	                HttpMethod.GET,
	                null,
	                new ParameterizedTypeReference<List<Country>>() {}
	        );
	        return responseEntity.getBody();
	    } catch (HttpClientErrorException | HttpServerErrorException e) {
	        throw new CountryAPIException("Error during HTTP request for countries by currency: " + e.getMessage(), e);
	    } catch (RestClientException e) {
	        throw new CountryAPIException("Client error during HTTP request for countries by currency: " + e.getMessage(), e);
	    } catch (Exception e) {
	        throw new CountryAPIException("Unexpected error when fetching countries by currency", e);
	    }
    }
} 