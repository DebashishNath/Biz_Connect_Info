package biz_connect_info.service.LeadSource;

import biz_connect_info.models.LeadSource;
import biz_connect_info.repository.LeadSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

@Service
class LeadSourceServiceDAL extends LeadSourceServiceImpl {

    @Autowired
    private LeadSourceRepository leadSourceRep;

    public LeadSourceServiceDAL() {
    }

    @Override
    public LeadSource updateLeadSource(LeadSource leadSource)
    {
        MessageResponse msgResp;
        try {
            LeadSource leadToUpdate = leadSourceRep.save(leadSource);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(),
                    "Lead source details updated successfully!");
            leadToUpdate.setReturnMessage(msgResp);
            return leadToUpdate;
        } catch (Exception ex)
        {
            System.out.println("Error Is: " + ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),
                    "Failed to update lead details!");
            leadSource.setReturnMessage(msgResp);
            return leadSource;
        }
    }
}