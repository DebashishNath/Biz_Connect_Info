package biz_connect_info.service.Product;

import biz_connect_info.models.Product;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product updateProduct(Product product);

    MessageResponse deleteProduct(Long productId);

    Product getProductById(Long productId);

    Product getProductByProductCode(String productCode);

    Product getProductByProductName(String productName);

    List<Product> getProductsByProductType(
            String productType
    );

    List<Product> getProductsByIsActive(
            String isActive
    );

    List<Product> getProductsByCreatedBy(
            Long userId
    );

    List<Product> searchProductsByProductName(
            String productName
    );

    List<Product> searchProductsByDescription(
            String description
    );

    List<Product> getProductsByMonthlyPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    List<Product> getProductsByYearlyPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    List<Product> getProductsByOneTimePriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    List<Product> getRecentProducts();

    List<Product> getAllProducts();

    boolean existsByProductCode(String productCode);

    boolean existsByProductName(String productName);
}