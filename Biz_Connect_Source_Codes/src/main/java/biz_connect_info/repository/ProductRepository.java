package biz_connect_info.repository;

//////////////////////////////////////////////////////////////////
// PRODUCT REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}