package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// CLIENT DOCUMENT
//////////////////////////////////////////////////////////////////

import auth_info.models.User;
import jakarta.persistence.*;
import lombok.Data;
import utils.MessageResponse;

import java.sql.Timestamp;

@Entity
@Table(name = "trn_client_documents")
@Data
public class ClientDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "document_type")
    private String documentType;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;

    @Column(name = "uploaded_at")
    private Timestamp uploadedAt;

    @Transient
    private MessageResponse returnMessage;

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}