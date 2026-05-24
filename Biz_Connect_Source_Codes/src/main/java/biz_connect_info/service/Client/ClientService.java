package biz_connect_info.service.Client;

import biz_connect_info.models.Client;

import java.util.List;

public interface ClientService {

    Client updateClient(Client client);

    void deleteClient(Long clientId);

    Client getClientById(Long clientId);

    Client getClientByEmail(String email);

    Client getClientByMobileNo(String mobileNo);

    List<Client> getClientsByClientType(String clientType);

    List<Client> searchClientsByCompanyName(String companyName);

    List<Client> searchClientsByContactPersonName(String contactPersonName);

    List<Client> getClientsByCityId(Integer cityId);

    List<Client> getClientsByCreatedBy(Long userId);

    List<Client> getRecentClients();

    List<Client> getAllClients();

    boolean existsByEmail(String email);

    boolean existsByMobileNo(String mobileNo);
}