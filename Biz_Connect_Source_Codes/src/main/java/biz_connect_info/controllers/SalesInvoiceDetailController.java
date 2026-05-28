package biz_connect_info.controllers;

import biz_connect_info.models.SalesInvoiceDetail;
import biz_connect_info.service.SalesInvoiceDetail.SalesInvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.MessageResponse;

import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "sales_invoice_detail")
@CrossOrigin(origins = "*")
public class SalesInvoiceDetailController {

    @Autowired
    private SalesInvoiceDetailService
            salesInvoiceDetailService;

    @PostMapping("/update_sales_invoice_detail")
    public SalesInvoiceDetail updateSalesInvoiceDetail(@RequestBody SalesInvoiceDetail salesInvoiceDetail)
    {
        return salesInvoiceDetailService.updateSalesInvoiceDetail(salesInvoiceDetail);
    }

    @DeleteMapping("/delete_sales_invoice_detail/{salesInvoiceDetailId}")
    public MessageResponse deleteSalesInvoiceDetail(@PathVariable Long salesInvoiceDetailId)
    {
        return salesInvoiceDetailService.deleteSalesInvoiceDetail(salesInvoiceDetailId);
    }

    @GetMapping("/get_sales_invoice_detail_by_id/{salesInvoiceDetailId}")
    public SalesInvoiceDetail getSalesInvoiceDetailById(@PathVariable Long salesInvoiceDetailId)
    {
        return salesInvoiceDetailService.getSalesInvoiceDetailById(salesInvoiceDetailId);
    }

    @GetMapping("/get_all_sales_invoice_detail")
    public List<SalesInvoiceDetail> getAllSalesInvoiceDetails()
    {
        return salesInvoiceDetailService.getAllSalesInvoiceDetails();
    }
}