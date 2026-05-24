package biz_connect_info.service.LeadStatus;

import biz_connect_info.models.LeadStatus;

import java.util.List;

public interface LeadStatusService {

    LeadStatus updateLeadStatus(LeadStatus leadStatus);

    void deleteLeadStatus(Integer leadStatusId);

    LeadStatus getLeadStatusById(Integer leadStatusId);

    LeadStatus getLeadStatusByStatusName(String statusName);

    List<LeadStatus> searchLeadStatusByStatusName(
            String statusName
    );

    List<LeadStatus> getAllLeadStatus();

    List<LeadStatus> getAllLeadStatusOrderByStatusName();

    boolean existsByStatusName(String statusName);
}