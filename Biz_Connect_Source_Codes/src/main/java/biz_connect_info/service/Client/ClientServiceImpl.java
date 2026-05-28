package biz_connect_info.service.Client;

import biz_connect_info.models.Client;
import utils.MessageResponse;

import java.util.List;

abstract class ClientServiceImpl implements ClientService {

    @Override
    public Client updateClient(Client client) {
        return new ClientServiceDAL().updateClient(client);
    }

    @Override
    public MessageResponse deleteClient(Long clientId) {
        return new ClientServiceDAL().deleteClient(clientId);
    }

    @Override
    public Client getClientById(Long clientId) {
        return new ClientServiceDAL().getClientById(clientId);
    }

    @Override
    public Client getClientByEmail(String email) {
        return new ClientServiceDAL().getClientByEmail(email);
    }

    @Override
    public Client getClientByMobileNo(String mobileNo) {
        return new ClientServiceDAL().getClientByMobileNo(mobileNo);
    }

    @Override
    public List<Client> getClientsByClientType(String clientType) {
        return new ClientServiceDAL().getClientsByClientType(clientType);
    }

    @Override
    public List<Client> searchClientsByCompanyName(String companyName) {
        return new ClientServiceDAL().searchClientsByCompanyName(companyName);
    }

    @Override
    public List<Client> searchClientsByContactPersonName(String contactPersonName) {
        return new ClientServiceDAL().searchClientsByContactPersonName(contactPersonName);
    }

    @Override
    public List<Client> getClientsByCityId(Integer cityId) {
        return new ClientServiceDAL().getClientsByCityId(cityId);
    }

    @Override
    public List<Client> getClientsByCreatedBy(Long userId) {
        return new ClientServiceDAL().getClientsByCreatedBy(userId);
    }

    @Override
    public List<Client> getRecentClients() {
        return new ClientServiceDAL().getRecentClients();
    }

    @Override
    public List<Client> getAllClients() {
        return new ClientServiceDAL().getAllClients();
    }

    @Override
    public boolean existsByEmail(String email) {
        return new ClientServiceDAL().existsByEmail(email);
    }

    @Override
    public boolean existsByMobileNo(String mobileNo) {
        return new ClientServiceDAL().existsByMobileNo(mobileNo);
    }
}