/**
 * The CountryServiceTest class contains test cases for the CountryService class.
 * It uses Mockito for mocking dependencies and JUnit 5 for testing.
 */
package com.countries.info.service;

import com.countries.info.exception.CountryAPIException;
import com.countries.info.domain.CountryDTO;
import com.countries.info.domain.base.Currency;
import com.countries.info.domain.base.Name;
import com.countries.info.domain.v3.v31.Country;
import com.countries.info.externalApi.ExternalRestApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest
{

  @Mock
  private ExternalRestApi externalRestApi;

  @Mock
  private Country countryMock;

  @InjectMocks
  private CountryService countryService;

  /**
   * Setup method to initialize mocks and mock country details.
   */
  @BeforeEach
  void setUp()
  {
    MockitoAnnotations.openMocks(this);
    mockCountryDetails();
  }

  /**
   * Test case for the getAllCountries method.
   */
  @Test
  void testGetAllCountries() throws CountryAPIException {
    // Mock data and behavior
    List<Country> mockCountries = Collections.singletonList(countryMock);
    when(externalRestApi.getAllCountries()).thenReturn(mockCountries);

    // Test and assertion
    List<CountryDTO> result = countryService.getAllCountries();
    assertMockedCountry(result);   
  }

  /**
   * Test case for the getCountryByName method.
   */
  @Test
  void testGetCountryByName() throws CountryAPIException {
    // Mock data and behavior
    List<Country> mockCountries = Collections.singletonList(countryMock);
    when(externalRestApi.getCountryByName("MockName")).thenReturn(mockCountries);

    // Test and assertion
    List<CountryDTO> result = countryService.getCountryByName("MockName");
    assertMockedCountry(result);
  }

  /**
   * Test case for the getCountriesByLanguage method.
   */
  @Test
  void testGetCountriesByLanguage() throws CountryAPIException {
  
    // Mock data and behavior
    List<Country> mockCountries = Collections.singletonList(countryMock);
    when(externalRestApi.getCountriesByLanguage("MockLanguageValue")).thenReturn(mockCountries);

    // Test and assertion
    List<CountryDTO> result = countryService.getCountriesByLanguage("MockLanguageValue");
    assertMockedCountry(result);
  }

  /**
   * Test case for the getCountriesByCurrency method.
   */
  @Test
  void testGetCountriesByCurrency() throws CountryAPIException {
    // Mock data and behavior
    List<Country> mockCountries = Collections.singletonList(countryMock);
    when(externalRestApi.getCountriesByCurrency("MockCurrency")).thenReturn(mockCountries);

    // Test and assertion
    List<CountryDTO> result = countryService.getCountriesByCurrency("MockCurrency");
    assertMockedCountry(result);
  }

  /**
   * Helper method to assert the details of the mocked country.
   * @param countries List of CountryDTO objects to assert.
   */
  private void assertMockedCountry(List<CountryDTO> countries)
  {
    CountryDTO result = countries.stream().findFirst().get();
    assertEquals("MockCommon", result.getName());
    assertEquals("MockCapital", result.getCapital());
    assertEquals("MockContinent", result.getContinent());
    assertEquals(1000, result.getPopulation());
    assertEquals("MockCurrency", result.getCurrency());
    assertEquals("MockLanguageValue", result.getLanguage());
  }

  /**
   * Helper method to mock country details for testing.
   */
  private void mockCountryDetails()
  {
    Name name = new Name();
    name.setCommon("MockCommon");
    Currency currency = new Currency();
    currency.setName("MockCurrencyValue");

    when(countryMock.getName()).thenReturn(name);
    when(countryMock.getCapital()).thenReturn(Collections.singletonList("MockCapital"));
    when(countryMock.getContinents()).thenReturn(Collections.singletonList("MockContinent"));
    when(countryMock.getPopulation()).thenReturn(1000);
    when(countryMock.getCurrencies()).thenReturn(Collections.singletonMap("MockCurrency", currency));
    when(countryMock.getLanguages()).thenReturn(Collections.singletonMap("MockLanguage", "MockLanguageValue"));
  }
}