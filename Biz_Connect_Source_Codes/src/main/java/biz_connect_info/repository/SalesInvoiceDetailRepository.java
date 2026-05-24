package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// SALES INVOICE DETAIL REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.SalesInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SalesInvoiceDetailRepository
        extends JpaRepository<SalesInvoiceDetail, Long> {

    List<SalesInvoiceDetail> findBySalesInvoiceSalesInvoiceId(
            Long salesInvoiceId
    );

    List<SalesInvoiceDetail> findByProductProductId(Long productId);

    List<SalesInvoiceDetail> findTop10ByOrderBySalesInvoiceDetailIdDesc();

    List<SalesInvoiceDetail> findByItemDescriptionContainingIgnoreCase(String itemDescription);

    List<SalesInvoiceDetail> findByQuantityBetween(BigDecimal minQuantity,BigDecimal maxQuantity);

    List<SalesInvoiceDetail> findByUnitPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);

    List<SalesInvoiceDetail> findByDiscountPercentBetween(BigDecimal minDiscount,BigDecimal maxDiscount);

    List<SalesInvoiceDetail> findByTaxPercentBetween(BigDecimal minTax,BigDecimal maxTax);

    List<SalesInvoiceDetail> findByLineTotalBetween(BigDecimal minTotal,BigDecimal maxTotal);


}