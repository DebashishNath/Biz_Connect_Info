package biz_connect_info.service.Country;

import biz_connect_info.models.Country;

import java.util.List;

abstract class CountryServiceImpl implements CountryService {

    @Override
    public Country updateCountry(Country country) {
        return new CountryServiceDAL().updateCountry(country);
    }

    @Override
    public void deleteCountry(Integer countryId) {
        new CountryServiceDAL().deleteCountry(countryId);
    }

    @Override
    public Country getCountryById(Integer countryId) {
        return new CountryServiceDAL().getCountryById(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return new CountryServiceDAL().getCountryByCountryName(countryName);
    }

    @Override
    public List<Country> getAllCountries() {
        return new CountryServiceDAL().getAllCountries();
    }

    @Override
    public List<Country> getAllCountriesOrderByCountryName() {
        return new CountryServiceDAL().getAllCountriesOrderByCountryName();
    }

    @Override
    public boolean existsByCountryName(String countryName) {
        return new CountryServiceDAL().existsByCountryName(countryName);
    }
}