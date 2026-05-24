package biz_connect_info.service.City;

import biz_connect_info.models.City;
import biz_connect_info.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.util.List;

@Service
class CityServiceDAL extends CityServiceImpl {

    @Autowired
    private CityRepository cityRep;

    public CityServiceDAL() {
    }

    @Override
    public City updateCity(City city) {

        MessageResponse msgResp;

        try {

            City cityToUpdate = cityRep.save(city);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "City details updated successfully!"
            );

            cityToUpdate.setReturnMessage(msgResp);

            return cityToUpdate;

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update city details!"
            );

            city.setReturnMessage(msgResp);

            return city;
        }
    }

    @Override
    public void deleteCity(Integer cityId) {

        try {

            cityRep.deleteById(cityId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());
        }
    }

    @Override
    public City getCityById(Integer cityId) {

        try {

            return cityRep
                    .findById(cityId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public City getCityByCityName(String cityName) {

        try {

            return cityRep
                    .findByCityName(cityName)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<City> getCitiesByStateId(Integer stateId) {

        try {

            return cityRep.findByStateStateId(stateId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<City> searchCitiesByCityName(String cityName) {

        try {

            return cityRep.findByCityNameContainingIgnoreCase(cityName);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<City> getAllCities() {

        try {

            return cityRep.findAll();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<City> getAllCitiesOrderByCityName() {

        try {

            return cityRep.findAllByOrderByCityNameAsc();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public boolean existsByCityName(String cityName) {

        try {

            return cityRep
                    .findByCityName(cityName)
                    .isPresent();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return false;
        }
    }
}