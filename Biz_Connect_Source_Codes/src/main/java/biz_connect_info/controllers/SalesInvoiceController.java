package biz_connect_info.controllers;

import biz_connect_info.models.SalesInvoice;
import biz_connect_info.service.SalesInvoice.SalesInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "sales_invoice")
@CrossOrigin(origins = "*")
public class SalesInvoiceController {

    @Autowired
    private SalesInvoiceService salesInvoiceService;

    @PostMapping("/update_sales_invoice")
    public SalesInvoice updateSalesInvoice(@RequestBody SalesInvoice salesInvoice)
    {
        return salesInvoiceService.updateSalesInvoice(salesInvoice);
    }

    @DeleteMapping("/delete_sales_invoice/{salesInvoiceId}")
    public MessageResponse deleteSalesInvoice(@PathVariable Long salesInvoiceId)
    {
        return salesInvoiceService.deleteSalesInvoice(salesInvoiceId);
    }

    @GetMapping("/get_sales_invoice_by_id/{salesInvoiceId}")
    public SalesInvoice getSalesInvoiceById(
            @PathVariable Long salesInvoiceId
    ) {
        return salesInvoiceService
                .getSalesInvoiceById(salesInvoiceId);
    }

    @GetMapping("/get_all_sales_invoices")
    public List<SalesInvoice> getAllSalesInvoices()
    {
        return salesInvoiceService.getAllSalesInvoices();
    }
}