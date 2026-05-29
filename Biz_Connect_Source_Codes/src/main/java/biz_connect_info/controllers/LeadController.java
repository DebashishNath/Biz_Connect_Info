package biz_connect_info.controllers;

import biz_connect_info.models.*;
import biz_connect_info.service.Client.ClientService;
import biz_connect_info.service.Country.CountryService;
import biz_connect_info.service.Lead.LeadService;

import biz_connect_info.service.LeadSource.LeadSourceService;
import biz_connect_info.service.LeadStatus.LeadStatusService;
import biz_connect_info.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "lead")
@CrossOrigin(origins = "*")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private LeadStatusService leadStatusService;

    @Autowired
    private LeadSourceService leadSourceService;

    @PostMapping("/update_lead")
    public Lead updateLead(
            @RequestBody Lead lead
    ) {
        return leadService.updateLead(lead);
    }

    @DeleteMapping("/delete_lead/{leadId}")
    public MessageResponse deleteLead(
            @PathVariable Long leadId
    ) {
        return leadService.deleteLead(leadId);
    }

    @GetMapping("/get_lead/{leadId}")
    public Lead getLeadById(
            @PathVariable Long leadId
    ) {
        return leadService.getLeadById(leadId);
    }

    @GetMapping("/list_leads")
    public List<Lead> getAllLeads() {
        return leadService.getAllLeads();
    }

    @RequestMapping(value="/lead_master_data", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getSaleMasterData() {
        List<Country> countries = (List<Country>) countryService.getAllCountries();
        List<Product> products = (List<Product>) productService.getAllProducts();
        List<LeadStatus> leadStatuses = (List<LeadStatus>) leadStatusService.getAllLeadStatus();
        List<LeadSource> leadSources = (List<LeadSource>) leadSourceService.getAllLeadSources();

        Map<String, Object> response = new HashMap<>();
        response.put("countries", countries);
        response.put("products", products);
        response.put("leadStatuses", leadStatuses);
        response.put("leadSources", leadSources);

        return ResponseEntity.ok(response);
    }
}