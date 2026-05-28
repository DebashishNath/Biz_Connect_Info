package biz_connect_info.service.LeadSource;

import biz_connect_info.models.LeadSource;
import biz_connect_info.repository.LeadSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.util.List;
import java.util.Optional;

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

    @Override
    public MessageResponse deleteLeadSource(Integer leadSourceId) {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            leadSourceRep.deleteById(leadSourceId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Lead source details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete lead source");
            return msgResp;
        }
    }

    @Override
    public Optional<LeadSource> getLeadSourceById(Integer leadSourceId){
        return leadSourceRep.findById(leadSourceId);
    }

    @Override
    public List<LeadSource> getAllLeadSources(){
        return leadSourceRep.findAll();
    }

    @Override
    public boolean existsBySourceName(String sourceName){
        return leadSourceRep.existsBySourceName(sourceName);
    }

    @Override
    public Optional<LeadSource> findBySourceName(String sourceName){
        return leadSourceRep.findBySourceName(sourceName);
    }
}