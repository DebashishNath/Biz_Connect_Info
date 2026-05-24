package biz_connect_info.models;

//////////////////////////////////////////////////////////////////
// SALES INVOICE DETAILS
//////////////////////////////////////////////////////////////////

import jakarta.persistence.*;
import lombok.Data;
import utils.MessageResponse;

import java.math.BigDecimal;

@Entity
@Table(name = "trn_sales_invoice_details")
@Data
public class SalesInvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_invoice_detail_id")
    private Long salesInvoiceDetailId;

    @ManyToOne
    @JoinColumn(name = "sales_invoice_id")
    private SalesInvoice salesInvoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "discount_percent")
    private BigDecimal discountPercent;

    @Column(name = "tax_percent")
    private BigDecimal taxPercent;

    @Column(name = "line_total")
    private BigDecimal lineTotal;

    @Transient
    private MessageResponse returnMessage;

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}