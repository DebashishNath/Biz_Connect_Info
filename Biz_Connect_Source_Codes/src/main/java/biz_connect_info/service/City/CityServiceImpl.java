package biz_connect_info.service.City;

import biz_connect_info.models.City;

import java.util.List;

abstract class CityServiceImpl implements CityService {

    @Override
    public City updateCity(City city) {
        return new CityServiceDAL().updateCity(city);
    }

    @Override
    public void deleteCity(Integer cityId) {
        new CityServiceDAL().deleteCity(cityId);
    }

    @Override
    public City getCityById(Integer cityId) {
        return new CityServiceDAL().getCityById(cityId);
    }

    @Override
    public City getCityByCityName(String cityName) {
        return new CityServiceDAL().getCityByCityName(cityName);
    }

    @Override
    public List<City> getCitiesByStateId(Integer stateId) {
        return new CityServiceDAL().getCitiesByStateId(stateId);
    }

    @Override
    public List<City> searchCitiesByCityName(String cityName) {
        return new CityServiceDAL().searchCitiesByCityName(cityName);
    }

    @Override
    public List<City> getAllCities() {
        return new CityServiceDAL().getAllCities();
    }

    @Override
    public List<City> getAllCitiesOrderByCityName() {
        return new CityServiceDAL().getAllCitiesOrderByCityName();
    }

    @Override
    public boolean existsByCityName(String cityName) {
        return new CityServiceDAL().existsByCityName(cityName);
    }
}