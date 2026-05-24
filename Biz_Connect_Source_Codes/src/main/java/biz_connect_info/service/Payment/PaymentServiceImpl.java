package biz_connect_info.service.Payment;

import biz_connect_info.models.Payment;

import java.time.LocalDate;
import java.util.List;

abstract class PaymentServiceImpl
        implements PaymentService {

    @Override
    public Payment updatePayment(Payment payment) {
        return new PaymentServiceDAL()
                .updatePayment(payment);
    }

    @Override
    public void deletePayment(Long paymentId) {
        new PaymentServiceDAL()
                .deletePayment(paymentId);
    }

    @Override
    public Payment getPaymentById(Long paymentId) {
        return new PaymentServiceDAL()
                .getPaymentById(paymentId);
    }

    @Override
    public List<Payment> getPaymentsBySalesInvoiceId(
            Long salesInvoiceId
    ) {
        return new PaymentServiceDAL()
                .getPaymentsBySalesInvoiceId(
                        salesInvoiceId
                );
    }

    @Override
    public List<Payment> getPaymentsByPaymentMethod(
            String paymentMethod
    ) {
        return new PaymentServiceDAL()
                .getPaymentsByPaymentMethod(
                        paymentMethod
                );
    }

    @Override
    public List<Payment> getPaymentsByCurrencyCode(
            String currencyCode
    ) {
        return new PaymentServiceDAL()
                .getPaymentsByCurrencyCode(
                        currencyCode
                );
    }

    @Override
    public List<Payment> getPaymentsByReceivedBy(
            Long userId
    ) {
        return new PaymentServiceDAL()
                .getPaymentsByReceivedBy(userId);
    }

    @Override
    public List<Payment> getPaymentsByPaymentDate(
            LocalDate paymentDate
    ) {
        return new PaymentServiceDAL()
                .getPaymentsByPaymentDate(
                        paymentDate
                );
    }

    @Override
    public List<Payment> getPaymentsBetweenPaymentDates(
            LocalDate fromDate,
            LocalDate toDate
    ) {
        return new PaymentServiceDAL()
                .getPaymentsBetweenPaymentDates(
                        fromDate,
                        toDate
                );
    }

    @Override
    public List<Payment> searchPaymentsByTransactionReference(
            String transactionReference
    ) {
        return new PaymentServiceDAL()
                .searchPaymentsByTransactionReference(
                        transactionReference
                );
    }

    @Override
    public List<Payment> getRecentPayments() {
        return new PaymentServiceDAL()
                .getRecentPayments();
    }

    @Override
    public List<Payment> getAllPayments() {
        return new PaymentServiceDAL()
                .getAllPayments();
    }
}