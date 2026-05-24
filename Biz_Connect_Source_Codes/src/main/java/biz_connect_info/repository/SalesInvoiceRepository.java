package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// SALES INVOICE REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {

    Optional<SalesInvoice> findByInvoiceNo(String invoiceNo);

    List<SalesInvoice> findByClientClientId(Long clientId);

    List<SalesInvoice> findByPaymentStatus(String paymentStatus);

    List<SalesInvoice> findByInvoiceStatus(String invoiceStatus);

    List<SalesInvoice> findByInvoiceDateBetween(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<SalesInvoice> findByDueDateBefore(LocalDate dueDate);

    List<SalesInvoice> findTop10ByOrderByCreatedAtDesc();

    List<SalesInvoice> findByNotesContainingIgnoreCase(String notes);

    List<SalesInvoice> findByTotalAmountBetween(BigDecimal minAmount,BigDecimal maxAmount);

    List<SalesInvoice> findByDueDateBeforeAndPaymentStatusNot(LocalDate currentDate,String paymentStatus);

    List<SalesInvoice> findByDueDate(LocalDate dueDate);

    List<SalesInvoice> findByInvoiceDate(LocalDate invoiceDate);

    List<SalesInvoice> findByCurrencyCode(String currencyCode);

    List<SalesInvoice> findByCreatedById(Long userId);

    List<SalesInvoice> findByLeadLeadId(Long leadId);


}