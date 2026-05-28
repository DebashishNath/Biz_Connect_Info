package biz_connect_info.service.City;

import biz_connect_info.models.City;
import utils.MessageResponse;

import java.util.List;

public interface CityService {

    City updateCity(City city);

    MessageResponse deleteCity(Integer cityId);

    City getCityById(Integer cityId);

    City getCityByCityName(String cityName);

    List<City> getCitiesByStateId(Integer stateId);

    List<City> searchCitiesByCityName(String cityName);

    List<City> getAllCities();

    List<City> getAllCitiesOrderByCityName();

    boolean existsByCityName(String cityName);
}