package biz_connect_info.service.LeadFollowup;

import biz_connect_info.models.LeadFollowup;
import biz_connect_info.repository.LeadFollowupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
class LeadFollowupServiceDAL extends LeadFollowupServiceImpl {

    @Autowired
    private LeadFollowupRepository leadFollowupRep;

    public LeadFollowupServiceDAL() {
    }

    @Override
    public LeadFollowup updateLeadFollowup(
            LeadFollowup leadFollowup
    ) {

        MessageResponse msgResp;

        try {

            LeadFollowup followupToUpdate =
                    leadFollowupRep.save(leadFollowup);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Lead followup updated successfully!"
            );

            followupToUpdate.setReturnMessage(msgResp);

            return followupToUpdate;

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update lead followup!"
            );

            leadFollowup.setReturnMessage(msgResp);

            return leadFollowup;
        }
    }

    @Override
    public MessageResponse deleteLeadFollowup(Long followupId) {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            leadFollowupRep.deleteById(followupId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Lead follow up details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete lead follow up");
            return msgResp;
        }
    }

    @Override
    public LeadFollowup getLeadFollowupById(Long followupId) {

        try {

            return leadFollowupRep
                    .findById(followupId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<LeadFollowup> getFollowupsByLeadId(Long leadId) {

        try {

            return leadFollowupRep
                    .findByLeadLeadId(leadId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<LeadFollowup> getFollowupsByCreatedBy(
            Long userId
    ) {

        try {

            return leadFollowupRep
                    .findByCreatedById(userId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<LeadFollowup> getFollowupsByCommunicationMode(
            String communicationMode
    ) {

        try {

            return leadFollowupRep
                    .findByCommunicationMode(communicationMode);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<LeadFollowup> getFollowupsByFollowupStatus(
            String followupStatus
    ) {

        try {

            return leadFollowupRep
                    .findByFollowupStatus(followupStatus);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<LeadFollowup> getFollowupsByNextFollowupDateBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    ) {

        try {

            return leadFollowupRep
                    .findByNextFollowupDateBetween(
                            fromDate,
                            toDate
                    );

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<LeadFollowup> getRecentFollowups() {

        try {

            return leadFollowupRep
                    .findTop10ByOrderByCreatedAtDesc();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<LeadFollowup> getAllLeadFollowups() {

        try {

            return leadFollowupRep.findAll();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }
}