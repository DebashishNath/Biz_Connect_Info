package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// PAYMENT
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
@Table(name = "trn_payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "sales_invoice_id")
    private SalesInvoice salesInvoice;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "payment_notes")
    private String paymentNotes;

    @ManyToOne
    @JoinColumn(name = "received_by")
    private User receivedBy;

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