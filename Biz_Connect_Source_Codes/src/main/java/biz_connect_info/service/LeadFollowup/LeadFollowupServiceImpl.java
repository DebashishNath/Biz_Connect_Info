package biz_connect_info.service.LeadFollowup;

import biz_connect_info.models.LeadFollowup;

import java.time.LocalDateTime;
import java.util.List;

abstract class LeadFollowupServiceImpl
        implements LeadFollowupService {

    @Override
    public LeadFollowup updateLeadFollowup(
            LeadFollowup leadFollowup
    ) {
        return new LeadFollowupServiceDAL()
                .updateLeadFollowup(leadFollowup);
    }

    @Override
    public void deleteLeadFollowup(Long followupId) {
        new LeadFollowupServiceDAL()
                .deleteLeadFollowup(followupId);
    }

    @Override
    public LeadFollowup getLeadFollowupById(Long followupId) {
        return new LeadFollowupServiceDAL()
                .getLeadFollowupById(followupId);
    }

    @Override
    public List<LeadFollowup> getFollowupsByLeadId(Long leadId) {
        return new LeadFollowupServiceDAL()
                .getFollowupsByLeadId(leadId);
    }

    @Override
    public List<LeadFollowup> getFollowupsByCreatedBy(Long userId) {
        return new LeadFollowupServiceDAL()
                .getFollowupsByCreatedBy(userId);
    }

    @Override
    public List<LeadFollowup> getFollowupsByCommunicationMode(
            String communicationMode
    ) {
        return new LeadFollowupServiceDAL()
                .getFollowupsByCommunicationMode(communicationMode);
    }

    @Override
    public List<LeadFollowup> getFollowupsByFollowupStatus(
            String followupStatus
    ) {
        return new LeadFollowupServiceDAL()
                .getFollowupsByFollowupStatus(followupStatus);
    }

    @Override
    public List<LeadFollowup> getFollowupsByNextFollowupDateBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    ) {
        return new LeadFollowupServiceDAL()
                .getFollowupsByNextFollowupDateBetween(
                        fromDate,
                        toDate
                );
    }

    @Override
    public List<LeadFollowup> getRecentFollowups() {
        return new LeadFollowupServiceDAL()
                .getRecentFollowups();
    }

    @Override
    public List<LeadFollowup> getAllLeadFollowups() {
        return new LeadFollowupServiceDAL()
                .getAllLeadFollowups();
    }
}