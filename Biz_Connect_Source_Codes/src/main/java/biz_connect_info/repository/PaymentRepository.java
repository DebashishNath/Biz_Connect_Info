package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// PAYMENT REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findBySalesInvoiceSalesInvoiceId(Long salesInvoiceId);

    List<Payment> findByPaymentMethod(String paymentMethod);

    List<Payment> findByPaymentDateBetween(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<Payment> findByReceivedById(Long userId);

    Optional<Payment> findByTransactionReference(
            String transactionReference
    );

    List<Payment> findTop10ByOrderByCreatedAtDesc();
}