package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// LEAD STATUS REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadStatusRepository extends JpaRepository<LeadStatus, Integer> {

    Optional<LeadStatus> findByStatusName(String statusName);
    List<LeadStatus> findByStatusNameContainingIgnoreCase(String statusName);
    List<LeadStatus> findAllByOrderByStatusNameAsc();
}