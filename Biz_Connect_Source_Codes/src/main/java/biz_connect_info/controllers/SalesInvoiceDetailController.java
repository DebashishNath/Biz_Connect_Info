package biz_connect_info.controllers;

import biz_connect_info.models.SalesInvoiceDetail;
import biz_connect_info.service.SalesInvoiceDetail.SalesInvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "sales-invoice-detail")
@CrossOrigin(origins = "*")
public class SalesInvoiceDetailController {

    @Autowired
    private SalesInvoiceDetailService
            salesInvoiceDetailService;

    @PostMapping("/update")
    public SalesInvoiceDetail
    updateSalesInvoiceDetail(
            @RequestBody
            SalesInvoiceDetail salesInvoiceDetail
    ) {
        return salesInvoiceDetailService
                .updateSalesInvoiceDetail(
                        salesInvoiceDetail
                );
    }

    @DeleteMapping("/delete/{salesInvoiceDetailId}")
    public void deleteSalesInvoiceDetail(
            @PathVariable Long salesInvoiceDetailId
    ) {
        salesInvoiceDetailService
                .deleteSalesInvoiceDetail(
                        salesInvoiceDetailId
                );
    }

    @GetMapping("/{salesInvoiceDetailId}")
    public SalesInvoiceDetail
    getSalesInvoiceDetailById(
            @PathVariable Long salesInvoiceDetailId
    ) {
        return salesInvoiceDetailService
                .getSalesInvoiceDetailById(
                        salesInvoiceDetailId
                );
    }

    @GetMapping("/all")
    public List<SalesInvoiceDetail>
    getAllSalesInvoiceDetails() {
        return salesInvoiceDetailService
                .getAllSalesInvoiceDetails();
    }
}