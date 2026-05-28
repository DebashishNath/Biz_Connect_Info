package biz_connect_info.controllers;

import biz_connect_info.models.LeadStatus;
import biz_connect_info.service.LeadStatus.LeadStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "lead_status")
@CrossOrigin(origins = "*")
public class LeadStatusController {

    @Autowired
    private LeadStatusService leadStatusService;

    @PostMapping("/update_lead_status")
    public LeadStatus updateLeadStatus(@RequestBody LeadStatus leadStatus)
    {
        return leadStatusService.updateLeadStatus(leadStatus);
    }

    @DeleteMapping("/delete_lead_status/{leadStatusId}")
    public MessageResponse deleteLeadStatus(@PathVariable Integer leadStatusId)
    {
        return leadStatusService.deleteLeadStatus(leadStatusId);
    }

    @GetMapping("/get_lead_status_id/{leadStatusId}")
    public LeadStatus getLeadStatusById(
            @PathVariable Integer leadStatusId
    ) {
        return leadStatusService
                .getLeadStatusById(leadStatusId);
    }

    @GetMapping("/get_all_lead_statuses")
    public List<LeadStatus> getAllLeadStatuses() {
        return leadStatusService
                .getAllLeadStatus();
    }
}