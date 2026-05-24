package biz_connect_info.service.SalesInvoice;

import biz_connect_info.models.SalesInvoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SalesInvoiceService {

    SalesInvoice updateSalesInvoice(
            SalesInvoice salesInvoice
    );

    void deleteSalesInvoice(Long salesInvoiceId);

    SalesInvoice getSalesInvoiceById(
            Long salesInvoiceId
    );

    SalesInvoice getSalesInvoiceByInvoiceNo(
            String invoiceNo
    );

    List<SalesInvoice> getSalesInvoicesByClientId(
            Long clientId
    );

    List<SalesInvoice> getSalesInvoicesByLeadId(
            Long leadId
    );

    List<SalesInvoice> getSalesInvoicesByCreatedBy(
            Long userId
    );

    List<SalesInvoice> getSalesInvoicesByPaymentStatus(
            String paymentStatus
    );

    List<SalesInvoice> getSalesInvoicesByInvoiceStatus(
            String invoiceStatus
    );

    List<SalesInvoice> getSalesInvoicesByCurrencyCode(
            String currencyCode
    );

    List<SalesInvoice> getSalesInvoicesByInvoiceDate(
            LocalDate invoiceDate
    );

    List<SalesInvoice> getSalesInvoicesBetweenInvoiceDates(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<SalesInvoice> getSalesInvoicesByDueDate(
            LocalDate dueDate
    );

    List<SalesInvoice> getOverdueInvoices(
            LocalDate currentDate
    );

    List<SalesInvoice> getSalesInvoicesByTotalAmountBetween(
            BigDecimal minAmount,
            BigDecimal maxAmount
    );

    List<SalesInvoice> searchSalesInvoicesByNotes(
            String notes
    );

    List<SalesInvoice> getRecentSalesInvoices();

    List<SalesInvoice> getAllSalesInvoices();

    boolean existsByInvoiceNo(String invoiceNo);
}