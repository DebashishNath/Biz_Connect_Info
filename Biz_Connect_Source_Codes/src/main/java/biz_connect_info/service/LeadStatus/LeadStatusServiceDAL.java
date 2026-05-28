package biz_connect_info.service.LeadStatus;

import biz_connect_info.models.LeadStatus;
import biz_connect_info.repository.LeadStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.util.List;

@Service
class LeadStatusServiceDAL extends LeadStatusServiceImpl {

    @Autowired
    private LeadStatusRepository leadStatusRep;

    public LeadStatusServiceDAL() {
    }

    @Override
    public LeadStatus updateLeadStatus(
            LeadStatus leadStatus
    ) {

        MessageResponse msgResp;

        try {

            LeadStatus leadStatusToUpdate =
                    leadStatusRep.save(leadStatus);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Lead status updated successfully!"
            );

            leadStatusToUpdate.setReturnMessage(msgResp);

            return leadStatusToUpdate;

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update lead status!"
            );

            leadStatus.setReturnMessage(msgResp);

            return leadStatus;
        }
    }

    @Override
    public MessageResponse deleteLeadStatus(Integer leadStatusId) {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            leadStatusRep.deleteById(leadStatusId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Lead status details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete lead status");
            return msgResp;
        }
    }

    @Override
    public LeadStatus getLeadStatusById(
            Integer leadStatusId
    ) {

        try {

            return leadStatusRep
                    .findById(leadStatusId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public LeadStatus getLeadStatusByStatusName(
            String statusName
    ) {

        try {

            return leadStatusRep
                    .findByStatusName(statusName)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<LeadStatus> searchLeadStatusByStatusName(
            String statusName
    ) {

        try {

            return leadStatusRep
                    .findByStatusNameContainingIgnoreCase(
                            statusName
                    );

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<LeadStatus> getAllLeadStatus() {

        try {

            return leadStatusRep.findAll();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<LeadStatus> getAllLeadStatusOrderByStatusName() {

        try {

            return leadStatusRep
                    .findAllByOrderByStatusNameAsc();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public boolean existsByStatusName(String statusName) {

        try {

            return leadStatusRep
                    .findByStatusName(statusName)
                    .isPresent();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return false;
        }
    }
}