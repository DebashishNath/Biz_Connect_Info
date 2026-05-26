package biz_connect_info.controllers;

import biz_connect_info.models.Country;
import biz_connect_info.service.Country.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "country")
@CrossOrigin(origins = "*")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/update")
    public Country updateCountry(
            @RequestBody Country country
    ) {
        return countryService.updateCountry(country);
    }

    @DeleteMapping("/delete/{countryId}")
    public void deleteCountry(
            @PathVariable Integer countryId
    ) {
        countryService.deleteCountry(countryId);
    }

    @GetMapping("/{countryId}")
    public Country getCountryById(
            @PathVariable Integer countryId
    ) {
        return countryService.getCountryById(countryId);
    }

    @GetMapping("/countries_list")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
}