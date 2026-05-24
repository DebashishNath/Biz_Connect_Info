package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// PRODUCT REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductCode(String productCode);

    List<Product> findByProductType(String productType);

    List<Product> findByIsActive(String isActive);

    List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findAllByOrderByProductNameAsc();

    Optional<Product> findByProductName(String productName);

    List<Product> findTop10ByOrderByCreatedAtDesc();

    List<Product> findByOneTimePriceBetween(BigDecimal minPrice,BigDecimal maxPrice);

    List<Product> findByYearlyPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);

    List<Product> findByMonthlyPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);

    List<Product> findByDescriptionContainingIgnoreCase(String description);

    List<Product> findByCreatedById(Long userId);


}