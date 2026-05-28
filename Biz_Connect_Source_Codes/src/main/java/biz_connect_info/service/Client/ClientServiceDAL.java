package biz_connect_info.service.Client;

import biz_connect_info.models.Client;
import biz_connect_info.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.util.List;

@Service
class ClientServiceDAL extends ClientServiceImpl {

    @Autowired
    private ClientRepository clientRep;

    public ClientServiceDAL() {
    }

    @Override
    public Client updateClient(Client client) {

        MessageResponse msgResp;

        try {

            Client clientToUpdate = clientRep.save(client);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Client details updated successfully!"
            );

            clientToUpdate.setReturnMessage(msgResp);

            return clientToUpdate;

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update client details!"
            );

            client.setReturnMessage(msgResp);

            return client;
        }
    }

    @Override
    public MessageResponse deleteClient(Long clientId) {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            clientRep.deleteById(clientId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Client details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete client");
            return msgResp;
        }
    }

    @Override
    public Client getClientById(Long clientId) {

        try {

            return clientRep
                    .findById(clientId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public Client getClientByEmail(String email) {

        try {

            return clientRep
                    .findByEmail(email)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public Client getClientByMobileNo(String mobileNo) {

        try {

            return clientRep
                    .findByMobileNo(mobileNo)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Client> getClientsByClientType(String clientType) {

        try {

            return clientRep.findByClientType(clientType);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Client> searchClientsByCompanyName(String companyName) {

        try {

            return clientRep
                    .findByCompanyNameContainingIgnoreCase(companyName);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Client> searchClientsByContactPersonName(String contactPersonName) {

        try {

            return clientRep
                    .findByContactPersonNameContainingIgnoreCase(contactPersonName);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Client> getClientsByCityId(Integer cityId) {

        try {

            return clientRep.findByCityCityId(cityId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Client> getClientsByCreatedBy(Long userId) {

        try {

            return clientRep.findByCreatedById(userId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Client> getRecentClients() {

        try {

            return clientRep.findTop10ByOrderByCreatedAtDesc();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<Client> getAllClients() {

        try {

            return clientRep.findAll();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public boolean existsByEmail(String email) {

        try {

            return clientRep
                    .findByEmail(email)
                    .isPresent();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return false;
        }
    }

    @Override
    public boolean existsByMobileNo(String mobileNo) {

        try {

            return clientRep
                    .findByMobileNo(mobileNo)
                    .isPresent();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return false;
        }
    }
}