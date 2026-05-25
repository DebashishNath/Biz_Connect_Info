package biz_connect_info.controllers;

import biz_connect_info.models.SalesInvoice;
import biz_connect_info.service.SalesInvoice.SalesInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "sales-invoice")
@CrossOrigin(origins = "*")
public class SalesInvoiceController {

    @Autowired
    private SalesInvoiceService
            salesInvoiceService;

    @PostMapping("/update")
    public SalesInvoice updateSalesInvoice(
            @RequestBody SalesInvoice salesInvoice
    ) {
        return salesInvoiceService
                .updateSalesInvoice(salesInvoice);
    }

    @DeleteMapping("/delete/{salesInvoiceId}")
    public void deleteSalesInvoice(
            @PathVariable Long salesInvoiceId
    ) {
        salesInvoiceService
                .deleteSalesInvoice(salesInvoiceId);
    }

    @GetMapping("/{salesInvoiceId}")
    public SalesInvoice getSalesInvoiceById(
            @PathVariable Long salesInvoiceId
    ) {
        return salesInvoiceService
                .getSalesInvoiceById(salesInvoiceId);
    }

    @GetMapping("/all")
    public List<SalesInvoice>
    getAllSalesInvoices() {
        return salesInvoiceService
                .getAllSalesInvoices();
    }
}