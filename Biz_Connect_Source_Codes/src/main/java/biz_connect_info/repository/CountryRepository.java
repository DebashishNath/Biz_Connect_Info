package biz_connect_info.repository;

import biz_connect_info.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//////////////////////////////////////////////////////////////////
// COUNTRY REPOSITORY
//////////////////////////////////////////////////////////////////

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    Optional<Country> findByCountryName(String countryName);

    List<Country> findAllByOrderByCountryNameAsc();
}