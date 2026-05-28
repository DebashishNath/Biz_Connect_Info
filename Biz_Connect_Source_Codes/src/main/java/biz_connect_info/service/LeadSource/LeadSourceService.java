package biz_connect_info.service.LeadSource;

import biz_connect_info.models.LeadSource;
import utils.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface LeadSourceService {
    LeadSource updateLeadSource(LeadSource leadSource);
    MessageResponse deleteLeadSource(Integer leadSourceId);
    Optional<LeadSource> getLeadSourceById(Integer leadSourceId);
    List<LeadSource> getAllLeadSources();
    boolean existsBySourceName(String sourceName);
    Optional<LeadSource> findBySourceName(String sourceName);
}