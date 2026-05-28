package biz_connect_info.service.LeadStatus;

import biz_connect_info.models.LeadStatus;
import utils.MessageResponse;

import java.util.List;

abstract class LeadStatusServiceImpl
        implements LeadStatusService {

    @Override
    public LeadStatus updateLeadStatus(
            LeadStatus leadStatus
    ) {
        return new LeadStatusServiceDAL()
                .updateLeadStatus(leadStatus);
    }

    @Override
    public MessageResponse deleteLeadStatus(Integer leadStatusId) {
        return new LeadStatusServiceDAL()
                .deleteLeadStatus(leadStatusId);
    }

    @Override
    public LeadStatus getLeadStatusById(
            Integer leadStatusId
    ) {
        return new LeadStatusServiceDAL()
                .getLeadStatusById(leadStatusId);
    }

    @Override
    public LeadStatus getLeadStatusByStatusName(
            String statusName
    ) {
        return new LeadStatusServiceDAL()
                .getLeadStatusByStatusName(statusName);
    }

    @Override
    public List<LeadStatus> searchLeadStatusByStatusName(
            String statusName
    ) {
        return new LeadStatusServiceDAL()
                .searchLeadStatusByStatusName(statusName);
    }

    @Override
    public List<LeadStatus> getAllLeadStatus() {
        return new LeadStatusServiceDAL()
                .getAllLeadStatus();
    }

    @Override
    public List<LeadStatus> getAllLeadStatusOrderByStatusName() {
        return new LeadStatusServiceDAL()
                .getAllLeadStatusOrderByStatusName();
    }

    @Override
    public boolean existsByStatusName(String statusName) {
        return new LeadStatusServiceDAL()
                .existsByStatusName(statusName);
    }
}