package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// LEAD STATUS
//////////////////////////////////////////////////////////////////

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}