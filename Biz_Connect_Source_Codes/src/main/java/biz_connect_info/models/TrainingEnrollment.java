package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// TRAINING ENROLLMENT
//////////////////////////////////////////////////////////////////

import auth_info.models.User;
import jakarta.persistence.*;
import lombok.Data;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "trn_training_enrollments")
@Data
public class TrainingEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private TrainingCourse course;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(name = "batch_name")
    private String batchName;

    @Column(name = "fees_amount")
    private BigDecimal feesAmount;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "training_status")
    private String trainingStatus;

    @Column(name = "remarks")
    private String remarks;

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