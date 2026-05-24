package biz_connect_info.service.Product;

import biz_connect_info.models.Product;

import java.math.BigDecimal;
import java.util.List;

abstract class ProductServiceImpl
        implements ProductService {

    @Override
    public Product updateProduct(Product product) {
        return new ProductServiceDAL()
                .updateProduct(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        new ProductServiceDAL()
                .deleteProduct(productId);
    }

    @Override
    public Product getProductById(Long productId) {
        return new ProductServiceDAL()
                .getProductById(productId);
    }

    @Override
    public Product getProductByProductCode(
            String productCode
    ) {
        return new ProductServiceDAL()
                .getProductByProductCode(productCode);
    }

    @Override
    public Product getProductByProductName(
            String productName
    ) {
        return new ProductServiceDAL()
                .getProductByProductName(productName);
    }

    @Override
    public List<Product> getProductsByProductType(
            String productType
    ) {
        return new ProductServiceDAL()
                .getProductsByProductType(productType);
    }

    @Override
    public List<Product> getProductsByIsActive(
            String isActive
    ) {
        return new ProductServiceDAL()
                .getProductsByIsActive(isActive);
    }

    @Override
    public List<Product> getProductsByCreatedBy(
            Long userId
    ) {
        return new ProductServiceDAL()
                .getProductsByCreatedBy(userId);
    }

    @Override
    public List<Product> searchProductsByProductName(
            String productName
    ) {
        return new ProductServiceDAL()
                .searchProductsByProductName(
                        productName
                );
    }

    @Override
    public List<Product> searchProductsByDescription(
            String description
    ) {
        return new ProductServiceDAL()
                .searchProductsByDescription(
                        description
                );
    }

    @Override
    public List<Product> getProductsByMonthlyPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {
        return new ProductServiceDAL()
                .getProductsByMonthlyPriceBetween(
                        minPrice,
                        maxPrice
                );
    }

    @Override
    public List<Product> getProductsByYearlyPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {
        return new ProductServiceDAL()
                .getProductsByYearlyPriceBetween(
                        minPrice,
                        maxPrice
                );
    }

    @Override
    public List<Product> getProductsByOneTimePriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {
        return new ProductServiceDAL()
                .getProductsByOneTimePriceBetween(
                        minPrice,
                        maxPrice
                );
    }

    @Override
    public List<Product> getRecentProducts() {
        return new ProductServiceDAL()
                .getRecentProducts();
    }

    @Override
    public List<Product> getAllProducts() {
        return new ProductServiceDAL()
                .getAllProducts();
    }

    @Override
    public boolean existsByProductCode(
            String productCode
    ) {
        return new ProductServiceDAL()
                .existsByProductCode(productCode);
    }

    @Override
    public boolean existsByProductName(
            String productName
    ) {
        return new ProductServiceDAL()
                .existsByProductName(productName);
    }
}