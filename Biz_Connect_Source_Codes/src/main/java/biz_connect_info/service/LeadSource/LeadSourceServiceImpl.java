package biz_connect_info.service.LeadSource;

import biz_connect_info.models.LeadSource;
import utils.MessageResponse;

import java.util.List;
import java.util.Optional;

abstract class LeadSourceServiceImpl implements LeadSourceService {

    @Override
    public LeadSource updateLeadSource(LeadSource leadSource) {
        return new LeadSourceServiceDAL().updateLeadSource(leadSource);
    }

    @Override
    public MessageResponse deleteLeadSource(Integer leadSourceId) {
        return new LeadSourceServiceDAL().deleteLeadSource(leadSourceId);
    }

    @Override
    public Optional<LeadSource> getLeadSourceById(Integer leadSourceId){
        return new LeadSourceServiceDAL().getLeadSourceById(leadSourceId);
    }

    @Override
    public List<LeadSource> getAllLeadSources(){
        return new LeadSourceServiceDAL().getAllLeadSources();
    }

    @Override
    public boolean existsBySourceName(String sourceName){
        return new LeadSourceServiceDAL().existsBySourceName(sourceName);
    }

    @Override
    public Optional<LeadSource> findBySourceName(String sourceName){
        return new LeadSourceServiceDAL().findBySourceName(sourceName);
    }
}