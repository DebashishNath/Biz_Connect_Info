package biz_connect_info.service.Lead;

import biz_connect_info.models.Lead;
import utils.MessageResponse;

import java.time.LocalDate;
import java.util.List;

abstract class LeadServiceImpl implements LeadService {

    @Override
    public Lead updateLead(Lead lead) {
        return new LeadServiceDAL().updateLead(lead);
    }

    @Override
    public MessageResponse deleteLead(Long leadId) {
        return new LeadServiceDAL().deleteLead(leadId);
    }

    @Override
    public Lead getLeadById(Long leadId) {
        return new LeadServiceDAL().getLeadById(leadId);
    }

    @Override
    public List<Lead> getLeadsByClientId(Long clientId) {
        return new LeadServiceDAL().getLeadsByClientId(clientId);
    }

    @Override
    public List<Lead> getLeadsByProductId(Long productId) {
        return new LeadServiceDAL().getLeadsByProductId(productId);
    }

    @Override
    public List<Lead> getLeadsByLeadStatusId(Integer leadStatusId) {
        return new LeadServiceDAL().getLeadsByLeadStatusId(leadStatusId);
    }

    @Override
    public List<Lead> getLeadsByAssignedTo(Long userId) {
        return new LeadServiceDAL().getLeadsByAssignedTo(userId);
    }

    @Override
    public List<Lead> getLeadsByPriorityLevel(String priorityLevel) {
        return new LeadServiceDAL().getLeadsByPriorityLevel(priorityLevel);
    }

    @Override
    public List<Lead> getLeadsByExpectedClosureDate(
            LocalDate expectedClosureDate
    ) {
        return new LeadServiceDAL()
                .getLeadsByExpectedClosureDate(expectedClosureDate);
    }

    @Override
    public List<Lead> getLeadsByLeadSource(String leadSource) {
        return new LeadServiceDAL().getLeadsByLeadSource(leadSource);
    }

    @Override
    public List<Lead> searchLeadsByRemarks(String remarks) {
        return new LeadServiceDAL().searchLeadsByRemarks(remarks);
    }

    @Override
    public List<Lead> getRecentLeads() {
        return new LeadServiceDAL().getRecentLeads();
    }

    @Override
    public List<Lead> getAllLeads() {
        return new LeadServiceDAL().getAllLeads();
    }

    @Override
    public List<Lead> getAllLeadsByCountry(Integer countryId)
    {
        return new LeadServiceDAL().getAllLeadsByCountry(countryId);
    }
}