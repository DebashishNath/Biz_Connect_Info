package biz_connect_info.models;


//////////////////////////////////////////////////////////////////
// TASK
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
@Table(name = "trn_tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_description")
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "related_lead_id")
    private Lead relatedLead;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @Column(name = "task_status")
    private String taskStatus;

    @Column(name = "priority")
    private String priority;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

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