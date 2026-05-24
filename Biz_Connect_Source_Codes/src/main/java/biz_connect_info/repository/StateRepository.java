package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// STATE REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    List<State> findByCountryCountryId(Integer countryId);

    List<State> findAllByOrderByStateNameAsc();

    Optional<State> findByStateName(String stateName);
}