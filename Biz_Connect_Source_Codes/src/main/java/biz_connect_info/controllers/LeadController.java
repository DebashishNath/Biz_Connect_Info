package biz_connect_info.controllers;

import biz_connect_info.models.Client;
import biz_connect_info.models.Lead;
import biz_connect_info.models.LeadStatus;
import biz_connect_info.models.Product;
import biz_connect_info.service.Client.ClientService;
import biz_connect_info.service.Lead.LeadService;

import biz_connect_info.service.LeadStatus.LeadStatusService;
import biz_connect_info.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private LeadStatusService leadStatusService;

    @PostMapping("/update_client_lead")
    public Lead updateLead(
            @RequestBody Lead lead
    ) {
        return leadService.updateLead(lead);
    }

    @DeleteMapping("/delete_lead/{leadId}")
    public void deleteLead(
            @PathVariable Long leadId
    ) {
        leadService.deleteLead(leadId);
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

    @RequestMapping(value="/lead-master-data", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getSaleMasterData() {
        List<Client> customers = (List<Client>) clientService.getAllClients();
        List<Product> products = (List<Product>) productService.getAllProducts();
        List<LeadStatus> leadStatuses = (List<LeadStatus>) leadStatusService.getAllLeadStatus();

        Map<String, Object> response = new HashMap<>();
        response.put("customers", customers);
        response.put("products", products);
        response.put("leadStatuses", leadStatuses);

        return ResponseEntity.ok(response);
    }
}