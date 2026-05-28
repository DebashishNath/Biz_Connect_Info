package biz_connect_info.service.SalesInvoiceDetail;

import biz_connect_info.models.SalesInvoiceDetail;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.util.List;

public interface SalesInvoiceDetailService {

    SalesInvoiceDetail updateSalesInvoiceDetail(SalesInvoiceDetail salesInvoiceDetail);

    MessageResponse deleteSalesInvoiceDetail(Long salesInvoiceDetailId);

    SalesInvoiceDetail getSalesInvoiceDetailById(Long salesInvoiceDetailId);

    List<SalesInvoiceDetail> getSalesInvoiceDetailsBySalesInvoiceId(Long salesInvoiceId);

    List<SalesInvoiceDetail> getSalesInvoiceDetailsByProductId(Long productId);

    List<SalesInvoiceDetail> searchSalesInvoiceDetailsByItemDescription(String itemDescription);

    List<SalesInvoiceDetail> getSalesInvoiceDetailsByQuantityBetween(BigDecimal minQuantity,BigDecimal maxQuantity);

    List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByUnitPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByDiscountPercentBetween(
            BigDecimal minDiscount,
            BigDecimal maxDiscount
    );

    List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByTaxPercentBetween(
            BigDecimal minTax,
            BigDecimal maxTax
    );

    List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByLineTotalBetween(
            BigDecimal minTotal,
            BigDecimal maxTotal
    );

    List<SalesInvoiceDetail>
    getRecentSalesInvoiceDetails();

    List<SalesInvoiceDetail>
    getAllSalesInvoiceDetails();
}