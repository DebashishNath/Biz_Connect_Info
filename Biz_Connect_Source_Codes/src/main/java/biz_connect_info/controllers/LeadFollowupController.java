package biz_connect_info.controllers;

import biz_connect_info.models.LeadFollowup;
import biz_connect_info.service.LeadFollowup.LeadFollowupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "lead_followup")
@CrossOrigin(origins = "*")
public class LeadFollowupController {

    @Autowired
    private LeadFollowupService
            leadFollowupService;

    @PostMapping("/update_lead_followup")
    public LeadFollowup updateLeadFollowup(
            @RequestBody LeadFollowup leadFollowup
    ) {
        return leadFollowupService
                .updateLeadFollowup(leadFollowup);
    }

    @DeleteMapping("/delete_follow_up/{followupId}")
    public void deleteLeadFollowup(
            @PathVariable Long followupId
    ) {
        leadFollowupService
                .deleteLeadFollowup(followupId);
    }

    @GetMapping("/get_lead_followup/{followupId}")
    public LeadFollowup getLeadFollowupById(
            @PathVariable Long followupId
    ) {
        return leadFollowupService
                .getLeadFollowupById(followupId);
    }

    @GetMapping("/get_all_follow_ups")
    public List<LeadFollowup>
    getAllLeadFollowups() {
        return leadFollowupService
                .getAllLeadFollowups();
    }
}