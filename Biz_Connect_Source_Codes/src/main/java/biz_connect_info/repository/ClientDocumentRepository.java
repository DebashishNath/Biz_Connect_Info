package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// CLIENT DOCUMENT REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.ClientDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDocumentRepository
        extends JpaRepository<ClientDocument, Long> {

    List<ClientDocument> findByClientClientId(Long clientId);

    List<ClientDocument> findByLeadLeadId(Long leadId);

    List<ClientDocument> findByDocumentType(String documentType);

    List<ClientDocument> findByUploadedById(Long userId);

    List<ClientDocument> findByDocumentNameContainingIgnoreCase(
            String documentName
    );

    List<ClientDocument> findTop10ByOrderByUploadedAtDesc();
}