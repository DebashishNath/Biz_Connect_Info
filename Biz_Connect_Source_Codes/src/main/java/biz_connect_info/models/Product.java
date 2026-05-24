package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// PRODUCT
//////////////////////////////////////////////////////////////////

import auth_info.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "mst_products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "description")
    private String description;

    @Column(name = "monthly_price")
    private BigDecimal monthlyPrice;

    @Column(name = "yearly_price")
    private BigDecimal yearlyPrice;

    @Column(name = "one_time_price")
    private BigDecimal oneTimePrice;

    @Column(name = "is_active")
    private String isActive;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;
}