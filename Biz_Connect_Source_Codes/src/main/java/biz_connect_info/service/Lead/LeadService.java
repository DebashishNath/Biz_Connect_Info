package biz_connect_info.service.Lead;

import biz_connect_info.models.Lead;
import utils.MessageResponse;

import java.time.LocalDate;
import java.util.List;

public interface LeadService {

    Lead updateLead(Lead lead);

    MessageResponse deleteLead(Long leadId);

    Lead getLeadById(Long leadId);

    List<Lead> getLeadsByClientId(Long clientId);

    List<Lead> getLeadsByProductId(Long productId);

    List<Lead> getLeadsByLeadStatusId(Integer leadStatusId);

    List<Lead> getLeadsByAssignedTo(Long userId);

    List<Lead> getLeadsByPriorityLevel(String priorityLevel);

    List<Lead> getLeadsByExpectedClosureDate(LocalDate expectedClosureDate);

    List<Lead> getLeadsByLeadSource(String leadSource);

    List<Lead> searchLeadsByRemarks(String remarks);

    List<Lead> getRecentLeads();

    List<Lead> getAllLeads();
}