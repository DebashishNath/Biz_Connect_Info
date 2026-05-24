package biz_connect_info.service.SalesInvoice;

import biz_connect_info.models.SalesInvoice;
import biz_connect_info.repository.SalesInvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
class SalesInvoiceServiceDAL
        extends SalesInvoiceServiceImpl {

    @Autowired
    private SalesInvoiceRepository salesInvoiceRep;

    public SalesInvoiceServiceDAL() {
    }

    @Override
    public SalesInvoice updateSalesInvoice(
            SalesInvoice salesInvoice
    ) {

        MessageResponse msgResp;

        try {

            SalesInvoice salesInvoiceToUpdate =
                    salesInvoiceRep.save(salesInvoice);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Sales invoice updated successfully!"
            );

            salesInvoiceToUpdate
                    .setReturnMessage(msgResp);

            return salesInvoiceToUpdate;

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update sales invoice!"
            );

            salesInvoice.setReturnMessage(msgResp);

            return salesInvoice;
        }
    }

    @Override
    public void deleteSalesInvoice(
            Long salesInvoiceId
    ) {

        try {

            salesInvoiceRep.deleteById(
                    salesInvoiceId
            );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );
        }
    }

    @Override
    public SalesInvoice getSalesInvoiceById(
            Long salesInvoiceId
    ) {

        try {

            return salesInvoiceRep
                    .findById(salesInvoiceId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public SalesInvoice getSalesInvoiceByInvoiceNo(
            String invoiceNo
    ) {

        try {

            return salesInvoiceRep
                    .findByInvoiceNo(invoiceNo)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByClientId(
            Long clientId
    ) {

        try {

            return salesInvoiceRep
                    .findByClientClientId(clientId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByLeadId(
            Long leadId
    ) {

        try {

            return salesInvoiceRep
                    .findByLeadLeadId(leadId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice> getSalesInvoicesByCreatedBy(
            Long userId
    ) {

        try {

            return salesInvoiceRep
                    .findByCreatedById(userId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice>
    getSalesInvoicesByPaymentStatus(
            String paymentStatus
    ) {

        try {

            return salesInvoiceRep
                    .findByPaymentStatus(
                            paymentStatus
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice>
    getSalesInvoicesByInvoiceStatus(
            String invoiceStatus
    ) {

        try {

            return salesInvoiceRep
                    .findByInvoiceStatus(
                            invoiceStatus
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice>
    getSalesInvoicesByCurrencyCode(
            String currencyCode
    ) {

        try {

            return salesInvoiceRep
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
    public List<SalesInvoice>
    getSalesInvoicesByInvoiceDate(
            LocalDate invoiceDate
    ) {

        try {

            return salesInvoiceRep
                    .findByInvoiceDate(invoiceDate);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice>
    getSalesInvoicesBetweenInvoiceDates(
            LocalDate fromDate,
            LocalDate toDate
    ) {

        try {

            return salesInvoiceRep
                    .findByInvoiceDateBetween(
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
    public List<SalesInvoice>
    getSalesInvoicesByDueDate(
            LocalDate dueDate
    ) {

        try {

            return salesInvoiceRep
                    .findByDueDate(dueDate);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice> getOverdueInvoices(
            LocalDate currentDate
    ) {

        try {

            return salesInvoiceRep
                    .findByDueDateBeforeAndPaymentStatusNot(
                            currentDate,
                            "PAID"
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice>
    getSalesInvoicesByTotalAmountBetween(
            BigDecimal minAmount,
            BigDecimal maxAmount
    ) {

        try {

            return salesInvoiceRep
                    .findByTotalAmountBetween(
                            minAmount,
                            maxAmount
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice>
    searchSalesInvoicesByNotes(
            String notes
    ) {

        try {

            return salesInvoiceRep
                    .findByNotesContainingIgnoreCase(
                            notes
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice> getRecentSalesInvoices() {

        try {

            return salesInvoiceRep
                    .findTop10ByOrderByCreatedAtDesc();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<SalesInvoice> getAllSalesInvoices() {

        try {

            return salesInvoiceRep.findAll();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public boolean existsByInvoiceNo(
            String invoiceNo
    ) {

        try {

            return salesInvoiceRep
                    .findByInvoiceNo(invoiceNo)
                    .isPresent();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return false;
        }
    }
}