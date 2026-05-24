package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// PRODUCT DEMO
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
@Table(name = "trn_product_demos")
@Data
public class ProductDemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demo_id")
    private Long demoId;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;

    @Column(name = "demo_datetime")
    private LocalDateTime demoDatetime;

    @Column(name = "demo_status")
    private String demoStatus;

    @Column(name = "meeting_link")
    private String meetingLink;

    @Column(name = "demo_feedback")
    private String demoFeedback;

    @ManyToOne
    @JoinColumn(name = "conducted_by")
    private User conductedBy;

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