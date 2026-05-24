package biz_connect_info.service.Country;

import biz_connect_info.models.Country;

import java.util.List;

public interface CountryService {

    Country updateCountry(Country country);

    void deleteCountry(Integer countryId);

    Country getCountryById(Integer countryId);

    Country getCountryByCountryName(String countryName);

    List<Country> getAllCountries();

    List<Country> getAllCountriesOrderByCountryName();

    boolean existsByCountryName(String countryName);
}