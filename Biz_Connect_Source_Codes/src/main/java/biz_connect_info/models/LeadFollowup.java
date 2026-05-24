package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// LEAD FOLLOWUP
//////////////////////////////////////////////////////////////////

import auth_info.models.User;
import jakarta.persistence.*;
import lombok.Data;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "trn_lead_followups")
@Data
public class LeadFollowup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followup_id")
    private Long followupId;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;

    @Column(name = "followup_date")
    private LocalDateTime followupDate;

    @Column(name = "next_followup_date")
    private LocalDateTime nextFollowupDate;

    @Column(name = "communication_mode")
    private String communicationMode;

    @Column(name = "discussion_summary")
    private String discussionSummary;

    @Column(name = "followup_status")
    private String followupStatus;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Transient
    private MessageResponse returnMessage;

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}