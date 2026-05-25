package biz_connect_info.controllers;

import biz_connect_info.models.Lead;
import biz_connect_info.service.Lead.LeadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "lead")
@CrossOrigin(origins = "*")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @PostMapping("/update")
    public Lead updateLead(
            @RequestBody Lead lead
    ) {
        return leadService.updateLead(lead);
    }

    @DeleteMapping("/delete/{leadId}")
    public void deleteLead(
            @PathVariable Long leadId
    ) {
        leadService.deleteLead(leadId);
    }

    @GetMapping("/{leadId}")
    public Lead getLeadById(
            @PathVariable Long leadId
    ) {
        return leadService.getLeadById(leadId);
    }

    @GetMapping("/all")
    public List<Lead> getAllLeads() {
        return leadService.getAllLeads();
    }
}