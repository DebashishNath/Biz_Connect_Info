package biz_connect_info.service.ClientDocument;

import biz_connect_info.models.ClientDocument;
import utils.MessageResponse;

import java.util.List;

public interface ClientDocumentService {

    ClientDocument updateClientDocument(ClientDocument clientDocument);

    MessageResponse deleteClientDocument(Long documentId);

    ClientDocument getClientDocumentById(Long documentId);

    List<ClientDocument> getDocumentsByClientId(Long clientId);

    List<ClientDocument> getDocumentsByLeadId(Long leadId);

    List<ClientDocument> getDocumentsByDocumentType(String documentType);

    List<ClientDocument> getDocumentsByUploadedBy(Long userId);

    List<ClientDocument> searchDocumentsByDocumentName(String documentName);

    List<ClientDocument> getRecentDocuments();

    List<ClientDocument> getAllDocuments();
}