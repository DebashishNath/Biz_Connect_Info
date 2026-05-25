package biz_connect_info.controllers;

import biz_connect_info.models.ProductDemo;
import biz_connect_info.service.ProductDemo.ProductDemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "product-demo")
@CrossOrigin(origins = "*")
public class ProductDemoController {

    @Autowired
    private ProductDemoService
            productDemoService;

    @PostMapping("/update")
    public ProductDemo updateProductDemo(
            @RequestBody ProductDemo productDemo
    ) {
        return productDemoService
                .updateProductDemo(productDemo);
    }

    @DeleteMapping("/delete/{demoId}")
    public void deleteProductDemo(
            @PathVariable Long demoId
    ) {
        productDemoService.deleteProductDemo(demoId);
    }

    @GetMapping("/{demoId}")
    public ProductDemo getProductDemoById(
            @PathVariable Long demoId
    ) {
        return productDemoService
                .getProductDemoById(demoId);
    }

    @GetMapping("/all")
    public List<ProductDemo> getAllProductDemos() {
        return productDemoService
                .getAllProductDemos();
    }
}