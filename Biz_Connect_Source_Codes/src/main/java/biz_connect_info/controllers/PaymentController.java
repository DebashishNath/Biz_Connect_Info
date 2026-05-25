package biz_connect_info.controllers;

import biz_connect_info.models.Payment;
import biz_connect_info.service.Payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/update")
    public Payment updatePayment(
            @RequestBody Payment payment
    ) {
        return paymentService.updatePayment(payment);
    }

    @DeleteMapping("/delete/{paymentId}")
    public void deletePayment(
            @PathVariable Long paymentId
    ) {
        paymentService.deletePayment(paymentId);
    }

    @GetMapping("/{paymentId}")
    public Payment getPaymentById(
            @PathVariable Long paymentId
    ) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
