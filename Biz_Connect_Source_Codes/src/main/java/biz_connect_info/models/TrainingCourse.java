package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// TRAINING COURSE
//////////////////////////////////////////////////////////////////

import auth_info.models.User;
import jakarta.persistence.*;
import lombok.Data;
import utils.MessageResponse;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "mst_training_courses")
@Data
public class TrainingCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "technology_area")
    private String technologyArea;

    @Column(name = "description")
    private String description;

    @Column(name = "duration_in_days")
    private Integer durationInDays;

    @Column(name = "fees")
    private BigDecimal fees;

    @Column(name = "is_active")
    private String isActive;

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