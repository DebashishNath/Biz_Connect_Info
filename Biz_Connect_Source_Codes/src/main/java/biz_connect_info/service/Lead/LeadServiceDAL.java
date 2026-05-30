package biz_connect_info.service.Lead;

import biz_connect_info.models.Lead;
import biz_connect_info.repository.LeadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.time.LocalDate;
import java.util.List;

@Service
class LeadServiceDAL extends LeadServiceImpl {

    @Autowired
    private LeadRepository leadRep;

    public LeadServiceDAL() {
    }

    @Override
    public Lead updateLead(Lead lead) {

        MessageResponse msgResp;

        try {

            Lead leadToUpdate = leadRep.save(lead);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Lead details updated successfully!"
            );

            leadToUpdate.setReturnMessage(msgResp);

            return leadToUpdate;

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update lead details!"
            );

            lead.setReturnMessage(msgResp);

            return lead;
        }
    }

    @Override
    public MessageResponse deleteLead(Long leadId) {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            leadRep.deleteById(leadId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Lead details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete lead");
            return msgResp;
        }
    }

    @Override
    public Lead getLeadById(Long leadId) {

        try {

            return leadRep
                    .findById(leadId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> getLeadsByClientId(Long clientId) {

        try {

            return leadRep.findByClientClientId(clientId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> getLeadsByProductId(Long productId) {

        try {

            return leadRep
                    .findByInterestedProductProductId(productId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> getLeadsByLeadStatusId(Integer leadStatusId) {

        try {

            return leadRep
                    .findByLeadStatusLeadStatusId(leadStatusId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> getLeadsByAssignedTo(Long userId) {

        try {

            return leadRep.findByAssignedToId(userId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> getLeadsByPriorityLevel(String priorityLevel) {

        try {

            return leadRep.findByPriorityLevel(priorityLevel);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> getLeadsByExpectedClosureDate(
            LocalDate expectedClosureDate
    ) {

        try {

            return leadRep
                    .findByExpectedClosureDate(expectedClosureDate);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> getLeadsByLeadSource(String leadSource) {

        try {

            return leadRep.findByLeadSource(leadSource);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> searchLeadsByRemarks(String remarks) {

        try {

            return leadRep
                    .findByRemarksContainingIgnoreCase(remarks);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> getRecentLeads() {

        try {

            return leadRep.findTop10ByOrderByCreatedAtDesc();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> getAllLeads() {

        try {

            return leadRep.findAll();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Lead> getAllLeadsByCountry(Integer countryId)
    {
        try
        {
            return leadRep.findByClientCityStateCountryCountryId(countryId);
        } catch (Exception ex) {
            System.out.println("Error Is: " + ex.getMessage());
            return null;
        }
    }
}