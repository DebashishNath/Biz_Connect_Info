package biz_connect_info.service.Product;

import biz_connect_info.models.Product;
import biz_connect_info.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.math.BigDecimal;
import java.util.List;

@Service
class ProductServiceDAL extends ProductServiceImpl {

    @Autowired
    private ProductRepository productRep;

    public ProductServiceDAL() {
    }

    @Override
    public Product updateProduct(Product product) {

        MessageResponse msgResp;

        try {

            Product productToUpdate =
                    productRep.save(product);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Product details updated successfully!"
            );

            productToUpdate.setReturnMessage(msgResp);

            return productToUpdate;

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update product details!"
            );

            product.setReturnMessage(msgResp);

            return product;
        }
    }

    @Override
    public MessageResponse deleteProduct(Long productId) {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            productRep.deleteById(productId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Product details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete product");
            return msgResp;
        }
    }

    @Override
    public Product getProductById(Long productId) {

        try {

            return productRep
                    .findById(productId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public Product getProductByProductCode(
            String productCode
    ) {

        try {

            return productRep
                    .findByProductCode(productCode)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public Product getProductByProductName(
            String productName
    ) {

        try {

            return productRep
                    .findByProductName(productName)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Product> getProductsByProductType(
            String productType
    ) {

        try {

            return productRep
                    .findByProductType(productType);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Product> getProductsByIsActive(
            String isActive
    ) {

        try {

            return productRep
                    .findByIsActive(isActive);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Product> getProductsByCreatedBy(
            Long userId
    ) {

        try {

            return productRep
                    .findByCreatedById(userId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Product> searchProductsByProductName(
            String productName
    ) {

        try {

            return productRep
                    .findByProductNameContainingIgnoreCase(
                            productName
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Product> searchProductsByDescription(
            String description
    ) {

        try {

            return productRep
                    .findByDescriptionContainingIgnoreCase(
                            description
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Product> getProductsByMonthlyPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {

        try {

            return productRep
                    .findByMonthlyPriceBetween(
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
    public List<Product> getProductsByYearlyPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {

        try {

            return productRep
                    .findByYearlyPriceBetween(
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
    public List<Product> getProductsByOneTimePriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {

        try {

            return productRep
                    .findByOneTimePriceBetween(
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
    public List<Product> getRecentProducts() {

        try {

            return productRep
                    .findTop10ByOrderByCreatedAtDesc();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {

        try {

            return productRep.findAll();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public boolean existsByProductCode(
            String productCode
    ) {

        try {

            return productRep
                    .findByProductCode(productCode)
                    .isPresent();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean existsByProductName(
            String productName
    ) {

        try {

            return productRep
                    .findByProductName(productName)
                    .isPresent();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return false;
        }
    }
}