package biz_connect_info.controllers;

import biz_connect_info.models.City;
import biz_connect_info.service.City.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH +  "city")
@CrossOrigin(origins = "*")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/update_city")
    public City updateCity(
            @RequestBody City city
    ) {
        return cityService.updateCity(city);
    }

    @DeleteMapping("/delete_city/{cityId}")
    public MessageResponse deleteCity(
            @PathVariable Integer cityId
    ) {
        return cityService.deleteCity(cityId);
    }

    @GetMapping("/get_city_by_id/{cityId}")
    public City getCityById(
            @PathVariable Integer cityId
    ) {
        return cityService.getCityById(cityId);
    }

    @GetMapping("/cities_by_state/{stateId}")
    public List<City> getCitiesByStateId(
            @PathVariable Integer stateId
    ) {
        return cityService
                .getCitiesByStateId(stateId);
    }

    @GetMapping("/cities_list")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
}