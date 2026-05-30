package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// LEAD REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    List<Lead> findByClientClientId(Long clientId);

    List<Lead> findByInterestedProductProductId(Long productId);

    List<Lead> findByLeadStatusLeadStatusId(Integer leadStatusId);

    List<Lead> findByAssignedToId(Long userId);

    List<Lead> findByPriorityLevel(String priorityLevel);

    List<Lead> findByExpectedClosureDate(LocalDate expectedClosureDate);

    List<Lead> findByLeadSource(String leadSource);

    List<Lead> findTop10ByOrderByCreatedAtDesc();

    List<Lead> findByRemarksContainingIgnoreCase(String remarks);

    List<Lead> findByClientCityStateCountryCountryId(Integer countryId);
}