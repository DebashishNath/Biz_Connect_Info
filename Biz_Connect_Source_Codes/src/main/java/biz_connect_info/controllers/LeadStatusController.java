package biz_connect_info.controllers;

import biz_connect_info.models.LeadStatus;
import biz_connect_info.service.LeadStatus.LeadStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "lead-status")
@CrossOrigin(origins = "*")
public class LeadStatusController {

    @Autowired
    private LeadStatusService leadStatusService;

    @PostMapping("/update")
    public LeadStatus updateLeadStatus(
            @RequestBody LeadStatus leadStatus
    ) {
        return leadStatusService
                .updateLeadStatus(leadStatus);
    }

    @DeleteMapping("/delete/{leadStatusId}")
    public void deleteLeadStatus(
            @PathVariable Integer leadStatusId
    ) {
        leadStatusService
                .deleteLeadStatus(leadStatusId);
    }

    @GetMapping("/{leadStatusId}")
    public LeadStatus getLeadStatusById(
            @PathVariable Integer leadStatusId
    ) {
        return leadStatusService
                .getLeadStatusById(leadStatusId);
    }

    @GetMapping("/all")
    public List<LeadStatus> getAllLeadStatuses() {
        return leadStatusService
                .getAllLeadStatus();
    }
}