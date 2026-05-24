package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// LEAD
//////////////////////////////////////////////////////////////////

import auth_info.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;


@Entity
@Table(name = "trn_leads")
@Data
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lead_id")
    private Long leadId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "interested_product_id")
    private Product interestedProduct;

    @ManyToOne
    @JoinColumn(name = "lead_status_id")
    private LeadStatus leadStatus;

    @Column(name = "lead_source")
    private String leadSource;

    @Column(name = "expected_budget")
    private BigDecimal expectedBudget;

    @Column(name = "expected_closure_date")
    private LocalDate expectedClosureDate;

    @Column(name = "priority_level")
    private String priorityLevel;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;
}