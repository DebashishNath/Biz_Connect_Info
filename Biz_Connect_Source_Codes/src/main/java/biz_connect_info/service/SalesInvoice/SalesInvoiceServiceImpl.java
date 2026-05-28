package biz_connect_info.service.SalesInvoice;

import biz_connect_info.models.SalesInvoice;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

abstract class SalesInvoiceServiceImpl
        implements SalesInvoiceService {

    @Override
    public SalesInvoice updateSalesInvoice(
            SalesInvoice salesInvoice
    ) {
        return new SalesInvoiceServiceDAL()
                .updateSalesInvoice(salesInvoice);
    }

    @Override
    public MessageResponse deleteSalesInvoice(
            Long salesInvoiceId
    ) {
        return new SalesInvoiceServiceDAL()
                .deleteSalesInvoice(salesInvoiceId);
    }

    @Override
    public SalesInvoice getSalesInvoiceById(
            Long salesInvoiceId
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoiceById(salesInvoiceId);
    }

    @Override
    public SalesInvoice getSalesInvoiceByInvoiceNo(
            String invoiceNo
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoiceByInvoiceNo(invoiceNo);
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByClientId(
            Long clientId
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoicesByClientId(clientId);
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByLeadId(
            Long leadId
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoicesByLeadId(leadId);
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByCreatedBy(
            Long userId
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoicesByCreatedBy(userId);
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByPaymentStatus(
            String paymentStatus
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoicesByPaymentStatus(
                        paymentStatus
                );
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByInvoiceStatus(
            String invoiceStatus
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoicesByInvoiceStatus(
                        invoiceStatus
                );
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByCurrencyCode(
            String currencyCode
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoicesByCurrencyCode(
                        currencyCode
                );
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByInvoiceDate(
            LocalDate invoiceDate
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoicesByInvoiceDate(
                        invoiceDate
                );
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesBetweenInvoiceDates(
            LocalDate fromDate,
            LocalDate toDate
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoicesBetweenInvoiceDates(
                        fromDate,
                        toDate
                );
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByDueDate(
            LocalDate dueDate
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoicesByDueDate(
                        dueDate
                );
    }

    @Override
    public List<SalesInvoice> getOverdueInvoices(
            LocalDate currentDate
    ) {
        return new SalesInvoiceServiceDAL()
                .getOverdueInvoices(currentDate);
    }

    @Override
    public List<SalesInvoice>
    getSalesInvoicesByTotalAmountBetween(
            BigDecimal minAmount,
            BigDecimal maxAmount
    ) {
        return new SalesInvoiceServiceDAL()
                .getSalesInvoicesByTotalAmountBetween(
                        minAmount,
                        maxAmount
                );
    }

    @Override
    public List<SalesInvoice> searchSalesInvoicesByNotes(
            String notes
    ) {
        return new SalesInvoiceServiceDAL()
                .searchSalesInvoicesByNotes(notes);
    }

    @Override
    public List<SalesInvoice> getRecentSalesInvoices() {
        return new SalesInvoiceServiceDAL()
                .getRecentSalesInvoices();
    }

    @Override
    public List<SalesInvoice> getAllSalesInvoices() {
        return new SalesInvoiceServiceDAL()
                .getAllSalesInvoices();
    }

    @Override
    public boolean existsByInvoiceNo(
            String invoiceNo
    ) {
        return new SalesInvoiceServiceDAL()
                .existsByInvoiceNo(invoiceNo);
    }
}