package biz_connect_info.models;

import jakarta.persistence.*;
import lombok.Data;
import utils.MessageResponse;

@Entity
@Table(name = "mst_lead_source")
@Data
public class LeadSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lead_source_id")
    private Integer leadSourceId;

    @Column(name = "source_name", nullable = false, length = 100)
    private String sourceName;

    @Transient
    private MessageResponse returnMessage;

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}