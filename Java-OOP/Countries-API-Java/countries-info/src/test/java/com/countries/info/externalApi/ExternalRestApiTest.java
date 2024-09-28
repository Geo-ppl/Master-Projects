/**
 * The ExternalRestApiTest class contains test cases for the ExternalRestApi class.
 * It uses Mockito for mocking dependencies and JUnit 5 for testing.
 */
package com.countries.info.externalApi;

import com.countries.info.exception.CountryAPIException;
import com.countries.info.domain.v3.v31.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class ExternalRestApiTest
{
  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private ExternalRestApi externalRestApi;

  /**
   * Test case for the getAllCountries method.
   */
  @Test
  void testGetAllCountries() throws CountryAPIException {
    // Mock data and behavior
    List<Country> mockCountries = Collections.singletonList(new Country("MockCountry"));
    ResponseEntity<List<Country>> mockResponseEntity = ResponseEntity.ok(mockCountries);
    Mockito.when(restTemplate.exchange(
            eq("https://restcountries.com/v3.1/all"),
            eq(HttpMethod.GET),
            any(),
            eq(new ParameterizedTypeReference<List<Country>>()
            {
            })
    )).thenReturn(mockResponseEntity);

    // Test and assertion
    List<Country> result = externalRestApi.getAllCountries();
    assertEquals(mockCountries, result);  
    
  }

  /**
   * Test case for the getCountryByName method.
   */
  @Test
  void testGetCountryByName() throws CountryAPIException {
    // Mock data and behavior
    List<Country> mockCountries = Collections.singletonList(new Country("MockCountry"));
    ResponseEntity<List<Country>> mockResponseEntity = ResponseEntity.ok(mockCountries);
    Mockito.when(restTemplate.exchange(
            eq("https://restcountries.com/v3.1/name/MockName"),
            eq(HttpMethod.GET),
            any(),
            eq(new ParameterizedTypeReference<List<Country>>()
            {
            })
    )).thenReturn(mockResponseEntity);

    // Test and assertion
    List<Country> result = externalRestApi.getCountryByName("MockName");
    assertEquals(mockCountries, result);
    
  }

  /**
   * Test case for the getCountriesByLanguage method.
   */
  @Test
  void testGetCountriesByLanguage() throws CountryAPIException {
    // Mock data and behavior
    List<Country> mockCountries = Collections.singletonList(new Country("MockCountry"));
    ResponseEntity<List<Country>> mockResponseEntity = ResponseEntity.ok(mockCountries);
    Mockito.when(restTemplate.exchange(
            eq("https://restcountries.com/v3.1/lang/MockLanguage"),
            eq(HttpMethod.GET),
            any(),
            eq(new ParameterizedTypeReference<List<Country>>()
            {
            })
    )).thenReturn(mockResponseEntity);

    // Test and assertion
    List<Country> result = externalRestApi.getCountriesByLanguage("MockLanguage");
    assertEquals(mockCountries, result);
    
  }

  /**
   * Test case for the getCountriesByCurrency method.
   */
  @Test
  void testGetCountriesByCurrency() throws CountryAPIException {
    // Mock data and behavior
    List<Country> mockCountries = Collections.singletonList(new Country("MockCountry"));
    ResponseEntity<List<Country>> mockResponseEntity = ResponseEntity.ok(mockCountries);
    Mockito.when(restTemplate.exchange(
            eq("https://restcountries.com/v3.1/currency/MockCurrency"),
            eq(HttpMethod.GET),
            any(),
            eq(new ParameterizedTypeReference<List<Country>>()
            {
            })
    )).thenReturn(mockResponseEntity);

    // Test and assertion
    List<Country> result = externalRestApi.getCountriesByCurrency("MockCurrency");
    assertEquals(mockCountries, result);
    
  }
}
