package biz_connect_info.service.Payment;

import biz_connect_info.models.Payment;
import biz_connect_info.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.time.LocalDate;
import java.util.List;

@Service
class PaymentServiceDAL extends PaymentServiceImpl {

    @Autowired
    private PaymentRepository paymentRep;

    public PaymentServiceDAL() {
    }

    @Override
    public Payment updatePayment(Payment payment) {

        MessageResponse msgResp;

        try {

            Payment paymentToUpdate =
                    paymentRep.save(payment);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Payment details updated successfully!"
            );

            paymentToUpdate.setReturnMessage(msgResp);

            return paymentToUpdate;

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update payment details!"
            );

            payment.setReturnMessage(msgResp);

            return payment;
        }
    }

    @Override
    public void deletePayment(Long paymentId) {

        try {

            paymentRep.deleteById(paymentId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );
        }
    }

    @Override
    public Payment getPaymentById(Long paymentId) {

        try {

            return paymentRep
                    .findById(paymentId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Payment> getPaymentsBySalesInvoiceId(
            Long salesInvoiceId
    ) {

        try {

            return paymentRep
                    .findBySalesInvoiceSalesInvoiceId(
                            salesInvoiceId
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Payment> getPaymentsByPaymentMethod(
            String paymentMethod
    ) {

        try {

            return paymentRep
                    .findByPaymentMethod(
                            paymentMethod
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Payment> getPaymentsByCurrencyCode(
            String currencyCode
    ) {

        try {

            return paymentRep
                    .findByCurrencyCode(
                            currencyCode
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Payment> getPaymentsByReceivedBy(
            Long userId
    ) {

        try {

            return paymentRep
                    .findByReceivedById(userId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Payment> getPaymentsByPaymentDate(
            LocalDate paymentDate
    ) {

        try {

            return paymentRep
                    .findByPaymentDate(paymentDate);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Payment> getPaymentsBetweenPaymentDates(
            LocalDate fromDate,
            LocalDate toDate
    ) {

        try {

            return paymentRep
                    .findByPaymentDateBetween(
                            fromDate,
                            toDate
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Payment> searchPaymentsByTransactionReference(
            String transactionReference
    ) {

        try {

            return paymentRep
                    .findByTransactionReferenceContainingIgnoreCase(
                            transactionReference
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Payment> getRecentPayments() {

        try {

            return paymentRep
                    .findTop10ByOrderByCreatedAtDesc();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Payment> getAllPayments() {

        try {

            return paymentRep.findAll();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }
}