package biz_connect_info.service.Country;

import biz_connect_info.models.Country;
import biz_connect_info.repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.util.List;

@Service
class CountryServiceDAL extends CountryServiceImpl {

    @Autowired
    private CountryRepository countryRep;

    public CountryServiceDAL() {
    }

    @Override
    public Country updateCountry(Country country) {

        MessageResponse msgResp;

        try {

            Country countryToUpdate = countryRep.save(country);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Country details updated successfully!"
            );

            countryToUpdate.setReturnMessage(msgResp);

            return countryToUpdate;

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update country details!"
            );

            country.setReturnMessage(msgResp);

            return country;
        }
    }

    @Override
    public void deleteCountry(Integer countryId) {

        try {
            countryRep.deleteById(countryId);
        } catch (Exception ex) {
            System.out.println("Error Is: " + ex.getMessage());
        }
    }

    @Override
    public Country getCountryById(Integer countryId) {

        try {

            return countryRep
                    .findById(countryId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public Country getCountryByCountryName(String countryName) {

        try {

            return countryRep
                    .findByCountryName(countryName)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Country> getAllCountries() {

        try {

            return countryRep.findAll();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Country> getAllCountriesOrderByCountryName() {

        try {

            return countryRep.findAllByOrderByCountryNameAsc();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public boolean existsByCountryName(String countryName) {

        try {

            return countryRep
                    .findByCountryName(countryName)
                    .isPresent();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return false;
        }
    }
}