package biz_connect_info.service.LeadSource;

import biz_connect_info.models.LeadSource;

abstract class LeadSourceServiceImpl implements LeadSourceService {

    @Override
    public LeadSource updateLeadSource(LeadSource leadSource) {
        return new LeadSourceServiceDAL().updateLeadSource(leadSource);
    }
}