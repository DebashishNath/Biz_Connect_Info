package biz_connect_info.controllers;

import biz_connect_info.models.Client;
import biz_connect_info.service.Client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "client")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/update_client")
    public Client updateClient(@RequestBody Client client)
    {
        return clientService.updateClient(client);
    }

    @DeleteMapping("/delete_client/{clientId}")
    public MessageResponse deleteClient(@PathVariable Long clientId)
    {
        return clientService.deleteClient(clientId);
    }

    @GetMapping("/get_client_by_id/{clientId}")
    public Client getClientById(@PathVariable Long clientId)
    {
        return clientService.getClientById(clientId);
    }

    @GetMapping("/get_all_clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/get_all_clients_by_country/{countryId}")
    public List<Client> get_all_clients_by_country(@PathVariable Integer countryId) {
        return clientService.getAllClientsByCountryId(countryId);
    }
}