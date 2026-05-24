package biz_connect_info.service.ClientDocument;

import biz_connect_info.models.ClientDocument;
import biz_connect_info.repository.ClientDocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.util.List;

@Service
class ClientDocumentServiceDAL extends ClientDocumentServiceImpl {

    @Autowired
    private ClientDocumentRepository clientDocumentRep;

    public ClientDocumentServiceDAL() {
    }

    @Override
    public ClientDocument updateClientDocument(ClientDocument clientDocument) {

        MessageResponse msgResp;

        try {

            ClientDocument documentToUpdate =
                    clientDocumentRep.save(clientDocument);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Client document updated successfully!"
            );

            documentToUpdate.setReturnMessage(msgResp);

            return documentToUpdate;

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update client document!"
            );

            clientDocument.setReturnMessage(msgResp);

            return clientDocument;
        }
    }

    @Override
    public void deleteClientDocument(Long documentId) {

        try {

            clientDocumentRep.deleteById(documentId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());
        }
    }

    @Override
    public ClientDocument getClientDocumentById(Long documentId) {

        try {

            return clientDocumentRep
                    .findById(documentId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<ClientDocument> getDocumentsByClientId(Long clientId) {

        try {

            return clientDocumentRep
                    .findByClientClientId(clientId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<ClientDocument> getDocumentsByLeadId(Long leadId) {

        try {

            return clientDocumentRep
                    .findByLeadLeadId(leadId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<ClientDocument> getDocumentsByDocumentType(String documentType) {

        try {

            return clientDocumentRep
                    .findByDocumentType(documentType);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<ClientDocument> getDocumentsByUploadedBy(Long userId) {

        try {

            return clientDocumentRep
                    .findByUploadedById(userId);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<ClientDocument> searchDocumentsByDocumentName(
            String documentName
    ) {

        try {

            return clientDocumentRep
                    .findByDocumentNameContainingIgnoreCase(documentName);

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<ClientDocument> getRecentDocuments() {

        try {

            return clientDocumentRep
                    .findTop10ByOrderByUploadedAtDesc();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }

    @Override
    public List<ClientDocument> getAllDocuments() {

        try {

            return clientDocumentRep.findAll();

        } catch (Exception ex) {

            System.out.println("Error Is: " + ex.getMessage());

            return null;
        }
    }
}