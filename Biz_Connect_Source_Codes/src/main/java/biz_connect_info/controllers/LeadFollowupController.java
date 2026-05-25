package biz_connect_info.controllers;

import biz_connect_info.models.LeadFollowup;
import biz_connect_info.service.LeadFollowup.LeadFollowupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "lead-followup")
@CrossOrigin(origins = "*")
public class LeadFollowupController {

    @Autowired
    private LeadFollowupService
            leadFollowupService;

    @PostMapping("/update")
    public LeadFollowup updateLeadFollowup(
            @RequestBody LeadFollowup leadFollowup
    ) {
        return leadFollowupService
                .updateLeadFollowup(leadFollowup);
    }

    @DeleteMapping("/delete/{followupId}")
    public void deleteLeadFollowup(
            @PathVariable Long followupId
    ) {
        leadFollowupService
                .deleteLeadFollowup(followupId);
    }

    @GetMapping("/{followupId}")
    public LeadFollowup getLeadFollowupById(
            @PathVariable Long followupId
    ) {
        return leadFollowupService
                .getLeadFollowupById(followupId);
    }

    @GetMapping("/all")
    public List<LeadFollowup>
    getAllLeadFollowups() {
        return leadFollowupService
                .getAllLeadFollowups();
    }
}