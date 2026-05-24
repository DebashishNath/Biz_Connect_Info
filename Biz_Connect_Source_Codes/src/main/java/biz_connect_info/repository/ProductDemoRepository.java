package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// PRODUCT DEMO REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.ProductDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductDemoRepository extends JpaRepository<ProductDemo, Long> {

    List<ProductDemo> findByLeadLeadId(Long leadId);

    List<ProductDemo> findByDemoStatus(String demoStatus);

    List<ProductDemo> findByConductedById(Long userId);

    List<ProductDemo> findByDemoDatetimeBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );

    List<ProductDemo> findTop10ByOrderByCreatedAtDesc();
}