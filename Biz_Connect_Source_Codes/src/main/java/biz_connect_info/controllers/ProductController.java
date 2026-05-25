package biz_connect_info.controllers;

import biz_connect_info.models.Product;
import biz_connect_info.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/update")
    public Product updateProduct(
            @RequestBody Product product
    ) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(
            @PathVariable Long productId
    ) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}")
    public Product getProductById(
            @PathVariable Long productId
    ) {
        return productService.getProductById(productId);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}