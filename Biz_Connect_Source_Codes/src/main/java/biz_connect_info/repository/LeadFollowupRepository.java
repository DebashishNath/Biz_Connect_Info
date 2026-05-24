package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// LEAD FOLLOWUP REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.LeadFollowup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LeadFollowupRepository extends JpaRepository<LeadFollowup, Long> {

    List<LeadFollowup> findByLeadLeadId(Long leadId);

    List<LeadFollowup> findByCreatedById(Long userId);

    List<LeadFollowup> findByCommunicationMode(String communicationMode);

    List<LeadFollowup> findByFollowupStatus(String followupStatus);

    List<LeadFollowup> findByNextFollowupDateBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );

    List<LeadFollowup> findTop10ByOrderByCreatedAtDesc();
}