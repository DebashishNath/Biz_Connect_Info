package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// LEAD STATUS
//////////////////////////////////////////////////////////////////

import jakarta.persistence.*;
import lombok.Data;
import utils.MessageResponse;

@Entity
@Table(name = "mst_lead_status")
@Data
public class LeadStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lead_status_id")
    private Integer leadStatusId;

    @Column(name = "status_name")
    private String statusName;

    @Transient
    private MessageResponse returnMessage;

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}