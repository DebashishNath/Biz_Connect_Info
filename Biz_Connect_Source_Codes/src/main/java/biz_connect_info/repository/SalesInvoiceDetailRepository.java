package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// SALES INVOICE DETAIL REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.SalesInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesInvoiceDetailRepository
        extends JpaRepository<SalesInvoiceDetail, Long> {

    List<SalesInvoiceDetail> findBySalesInvoiceSalesInvoiceId(
            Long salesInvoiceId
    );

    List<SalesInvoiceDetail> findByProductProductId(Long productId);
}