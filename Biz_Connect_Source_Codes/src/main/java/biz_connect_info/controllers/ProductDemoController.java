package biz_connect_info.controllers;

import biz_connect_info.models.ProductDemo;
import biz_connect_info.service.ProductDemo.ProductDemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "product_demo")
@CrossOrigin(origins = "*")
public class ProductDemoController {

    @Autowired
    private ProductDemoService
            productDemoService;

    @PostMapping("/update_product_demo")
    public ProductDemo updateProductDemo(
            @RequestBody ProductDemo productDemo
    ) {
        return productDemoService
                .updateProductDemo(productDemo);
    }

    @DeleteMapping("/delete_product_demo/{demoId}")
    public void deleteProductDemo(
            @PathVariable Long demoId
    ) {
        productDemoService.deleteProductDemo(demoId);
    }

    @GetMapping("/get_product_demo/{demoId}")
    public ProductDemo getProductDemoById(
            @PathVariable Long demoId
    ) {
        return productDemoService
                .getProductDemoById(demoId);
    }

    @GetMapping("/get_all_product_demos")
    public List<ProductDemo> getAllProductDemos() {
        return productDemoService
                .getAllProductDemos();
    }
}