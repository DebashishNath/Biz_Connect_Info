package biz_connect_info.service.LeadFollowup;

import biz_connect_info.models.LeadFollowup;
import utils.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface LeadFollowupService {

    LeadFollowup updateLeadFollowup(LeadFollowup leadFollowup);

    MessageResponse deleteLeadFollowup(Long followupId);

    LeadFollowup getLeadFollowupById(Long followupId);

    List<LeadFollowup> getFollowupsByLeadId(Long leadId);

    List<LeadFollowup> getFollowupsByCreatedBy(Long userId);

    List<LeadFollowup> getFollowupsByCommunicationMode(
            String communicationMode
    );

    List<LeadFollowup> getFollowupsByFollowupStatus(
            String followupStatus
    );

    List<LeadFollowup> getFollowupsByNextFollowupDateBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );

    List<LeadFollowup> getRecentFollowups();

    List<LeadFollowup> getAllLeadFollowups();
}