package biz_connect_info.repository;


//////////////////////////////////////////////////////////////////
// CITY REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByStateStateId(Integer stateId);

    List<City> findAllByOrderByCityNameAsc();

    Optional<City> findByCityName(String cityName);

    List<City> findByCityNameContainingIgnoreCase(String cityName);
}