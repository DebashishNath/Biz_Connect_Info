package biz_connect_info.service.Country;

import biz_connect_info.models.Country;
import utils.MessageResponse;

import java.util.List;

public interface CountryService {

    Country updateCountry(Country country);

    MessageResponse deleteCountry(Integer countryId);

    Country getCountryById(Integer countryId);

    Country getCountryByCountryName(String countryName);

    List<Country> getAllCountries();

    List<Country> getAllCountriesOrderByCountryName();

    boolean existsByCountryName(String countryName);
}