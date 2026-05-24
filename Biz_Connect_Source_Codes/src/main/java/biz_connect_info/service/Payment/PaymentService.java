package biz_connect_info.service.Payment;

import biz_connect_info.models.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {

    Payment updatePayment(Payment payment);

    void deletePayment(Long paymentId);

    Payment getPaymentById(Long paymentId);

    List<Payment> getPaymentsBySalesInvoiceId(
            Long salesInvoiceId
    );

    List<Payment> getPaymentsByPaymentMethod(
            String paymentMethod
    );

    List<Payment> getPaymentsByCurrencyCode(
            String currencyCode
    );

    List<Payment> getPaymentsByReceivedBy(
            Long userId
    );

    List<Payment> getPaymentsByPaymentDate(
            LocalDate paymentDate
    );

    List<Payment> getPaymentsBetweenPaymentDates(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<Payment> searchPaymentsByTransactionReference(
            String transactionReference
    );

    List<Payment> getRecentPayments();

    List<Payment> getAllPayments();
}