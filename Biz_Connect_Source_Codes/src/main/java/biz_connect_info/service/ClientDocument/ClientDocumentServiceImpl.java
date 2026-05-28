package biz_connect_info.service.ClientDocument;

import biz_connect_info.models.ClientDocument;
import utils.MessageResponse;

import java.util.List;

abstract class ClientDocumentServiceImpl implements ClientDocumentService {

    @Override
    public ClientDocument updateClientDocument(ClientDocument clientDocument) {
        return new ClientDocumentServiceDAL().updateClientDocument(clientDocument);
    }

    @Override
    public MessageResponse deleteClientDocument(Long documentId) {
        return new ClientDocumentServiceDAL().deleteClientDocument(documentId);
    }

    @Override
    public ClientDocument getClientDocumentById(Long documentId) {
        return new ClientDocumentServiceDAL().getClientDocumentById(documentId);
    }

    @Override
    public List<ClientDocument> getDocumentsByClientId(Long clientId) {
        return new ClientDocumentServiceDAL().getDocumentsByClientId(clientId);
    }

    @Override
    public List<ClientDocument> getDocumentsByLeadId(Long leadId) {
        return new ClientDocumentServiceDAL().getDocumentsByLeadId(leadId);
    }

    @Override
    public List<ClientDocument> getDocumentsByDocumentType(String documentType) {
        return new ClientDocumentServiceDAL().getDocumentsByDocumentType(documentType);
    }

    @Override
    public List<ClientDocument> getDocumentsByUploadedBy(Long userId) {
        return new ClientDocumentServiceDAL().getDocumentsByUploadedBy(userId);
    }

    @Override
    public List<ClientDocument> searchDocumentsByDocumentName(String documentName) {
        return new ClientDocumentServiceDAL().searchDocumentsByDocumentName(documentName);
    }

    @Override
    public List<ClientDocument> getRecentDocuments() {
        return new ClientDocumentServiceDAL().getRecentDocuments();
    }

    @Override
    public List<ClientDocument> getAllDocuments() {
        return new ClientDocumentServiceDAL().getAllDocuments();
    }
}