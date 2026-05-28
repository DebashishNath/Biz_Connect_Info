package biz_connect_info.service.SalesInvoiceDetail;

import biz_connect_info.models.SalesInvoiceDetail;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.util.List;

abstract class SalesInvoiceDetailServiceImpl
        implements SalesInvoiceDetailService {

    @Override
    public SalesInvoiceDetail updateSalesInvoiceDetail(
            SalesInvoiceDetail salesInvoiceDetail
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .updateSalesInvoiceDetail(
                        salesInvoiceDetail
                );
    }

    @Override
    public MessageResponse deleteSalesInvoiceDetail(
            Long salesInvoiceDetailId
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .deleteSalesInvoiceDetail(
                        salesInvoiceDetailId
                );
    }

    @Override
    public SalesInvoiceDetail getSalesInvoiceDetailById(
            Long salesInvoiceDetailId
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .getSalesInvoiceDetailById(
                        salesInvoiceDetailId
                );
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsBySalesInvoiceId(
            Long salesInvoiceId
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .getSalesInvoiceDetailsBySalesInvoiceId(
                        salesInvoiceId
                );
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByProductId(
            Long productId
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .getSalesInvoiceDetailsByProductId(
                        productId
                );
    }

    @Override
    public List<SalesInvoiceDetail>
    searchSalesInvoiceDetailsByItemDescription(
            String itemDescription
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .searchSalesInvoiceDetailsByItemDescription(
                        itemDescription
                );
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByQuantityBetween(
            BigDecimal minQuantity,
            BigDecimal maxQuantity
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .getSalesInvoiceDetailsByQuantityBetween(
                        minQuantity,
                        maxQuantity
                );
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByUnitPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .getSalesInvoiceDetailsByUnitPriceBetween(
                        minPrice,
                        maxPrice
                );
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByDiscountPercentBetween(
            BigDecimal minDiscount,
            BigDecimal maxDiscount
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .getSalesInvoiceDetailsByDiscountPercentBetween(
                        minDiscount,
                        maxDiscount
                );
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByTaxPercentBetween(
            BigDecimal minTax,
            BigDecimal maxTax
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .getSalesInvoiceDetailsByTaxPercentBetween(
                        minTax,
                        maxTax
                );
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByLineTotalBetween(
            BigDecimal minTotal,
            BigDecimal maxTotal
    ) {
        return new SalesInvoiceDetailServiceDAL()
                .getSalesInvoiceDetailsByLineTotalBetween(
                        minTotal,
                        maxTotal
                );
    }

    @Override
    public List<SalesInvoiceDetail>
    getRecentSalesInvoiceDetails() {
        return new SalesInvoiceDetailServiceDAL()
                .getRecentSalesInvoiceDetails();
    }

    @Override
    public List<SalesInvoiceDetail>
    getAllSalesInvoiceDetails() {
        return new SalesInvoiceDetailServiceDAL()
                .getAllSalesInvoiceDetails();
    }
}