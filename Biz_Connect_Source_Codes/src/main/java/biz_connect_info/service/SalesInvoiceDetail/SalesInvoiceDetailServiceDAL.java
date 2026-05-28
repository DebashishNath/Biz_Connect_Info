package biz_connect_info.service.SalesInvoiceDetail;

import biz_connect_info.models.SalesInvoiceDetail;
import biz_connect_info.repository.SalesInvoiceDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.util.List;

@Service
class SalesInvoiceDetailServiceDAL
        extends SalesInvoiceDetailServiceImpl {

    @Autowired
    private SalesInvoiceDetailRepository
            salesInvoiceDetailRep;

    public SalesInvoiceDetailServiceDAL() {
    }

    @Override
    public SalesInvoiceDetail updateSalesInvoiceDetail(
            SalesInvoiceDetail salesInvoiceDetail
    ) {

        MessageResponse msgResp;

        try {

            SalesInvoiceDetail
                    salesInvoiceDetailToUpdate =
                    salesInvoiceDetailRep.save(
                            salesInvoiceDetail
                    );

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Sales invoice detail updated successfully!"
            );

            salesInvoiceDetailToUpdate
                    .setReturnMessage(msgResp);

            return salesInvoiceDetailToUpdate;

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update sales invoice detail!"
            );

            salesInvoiceDetail
                    .setReturnMessage(msgResp);

            return salesInvoiceDetail;
        }
    }

    @Override
    public MessageResponse deleteSalesInvoiceDetail(Long salesInvoiceDetailId)
    {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            salesInvoiceDetailRep.deleteById(salesInvoiceDetailId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Sales invoice details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete Sales invoice");
            return msgResp;
        }
    }

    @Override
    public SalesInvoiceDetail getSalesInvoiceDetailById(
            Long salesInvoiceDetailId
    ) {

        try {

            return salesInvoiceDetailRep
                    .findById(salesInvoiceDetailId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsBySalesInvoiceId(
            Long salesInvoiceId
    ) {

        try {

            return salesInvoiceDetailRep
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
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByProductId(
            Long productId
    ) {

        try {

            return salesInvoiceDetailRep
                    .findByProductProductId(
                            productId
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoiceDetail>
    searchSalesInvoiceDetailsByItemDescription(
            String itemDescription
    ) {

        try {

            return salesInvoiceDetailRep
                    .findByItemDescriptionContainingIgnoreCase(
                            itemDescription
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByQuantityBetween(
            BigDecimal minQuantity,
            BigDecimal maxQuantity
    ) {

        try {

            return salesInvoiceDetailRep
                    .findByQuantityBetween(
                            minQuantity,
                            maxQuantity
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByUnitPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {

        try {

            return salesInvoiceDetailRep
                    .findByUnitPriceBetween(
                            minPrice,
                            maxPrice
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByDiscountPercentBetween(
            BigDecimal minDiscount,
            BigDecimal maxDiscount
    ) {

        try {

            return salesInvoiceDetailRep
                    .findByDiscountPercentBetween(
                            minDiscount,
                            maxDiscount
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByTaxPercentBetween(
            BigDecimal minTax,
            BigDecimal maxTax
    ) {

        try {

            return salesInvoiceDetailRep
                    .findByTaxPercentBetween(
                            minTax,
                            maxTax
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoiceDetail>
    getSalesInvoiceDetailsByLineTotalBetween(
            BigDecimal minTotal,
            BigDecimal maxTotal
    ) {

        try {

            return salesInvoiceDetailRep
                    .findByLineTotalBetween(
                            minTotal,
                            maxTotal
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoiceDetail>
    getRecentSalesInvoiceDetails() {

        try {

            return salesInvoiceDetailRep
                    .findTop10ByOrderBySalesInvoiceDetailIdDesc();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoiceDetail>
    getAllSalesInvoiceDetails() {

        try {

            return salesInvoiceDetailRep.findAll();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }
}