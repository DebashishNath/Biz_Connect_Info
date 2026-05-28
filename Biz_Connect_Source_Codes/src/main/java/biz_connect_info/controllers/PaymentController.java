package biz_connect_info.controllers;

import biz_connect_info.models.Payment;
import biz_connect_info.service.Payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/update_payment")
    public Payment updatePayment(@RequestBody Payment payment) {
        return paymentService.updatePayment(payment);
    }

    @DeleteMapping("/delete_payment/{paymentId}")
    public MessageResponse deletePayment(@PathVariable Long paymentId)
    {
        return paymentService.deletePayment(paymentId);
    }

    @GetMapping("/get_payment_by_id/{paymentId}")
    public Payment getPaymentById(
            @PathVariable Long paymentId
    ) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/get_all_payments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
