package biz_connect_info.service.ProductDemo;

import biz_connect_info.models.ProductDemo;
import utils.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;

abstract class ProductDemoServiceImpl
        implements ProductDemoService {

    @Override
    public ProductDemo updateProductDemo(
            ProductDemo productDemo
    ) {
        return new ProductDemoServiceDAL()
                .updateProductDemo(productDemo);
    }

    @Override
    public MessageResponse deleteProductDemo(Long demoId) {
        return new ProductDemoServiceDAL()
                .deleteProductDemo(demoId);
    }

    @Override
    public ProductDemo getProductDemoById(Long demoId) {
        return new ProductDemoServiceDAL()
                .getProductDemoById(demoId);
    }

    @Override
    public List<ProductDemo> getProductDemosByLeadId(
            Long leadId
    ) {
        return new ProductDemoServiceDAL()
                .getProductDemosByLeadId(leadId);
    }

    @Override
    public List<ProductDemo> getProductDemosByConductedBy(
            Long userId
    ) {
        return new ProductDemoServiceDAL()
                .getProductDemosByConductedBy(userId);
    }

    @Override
    public List<ProductDemo> getProductDemosByDemoStatus(
            String demoStatus
    ) {
        return new ProductDemoServiceDAL()
                .getProductDemosByDemoStatus(demoStatus);
    }

    @Override
    public List<ProductDemo> getProductDemosByDemoDatetime(
            LocalDateTime demoDatetime
    ) {
        return new ProductDemoServiceDAL()
                .getProductDemosByDemoDatetime(
                        demoDatetime
                );
    }

    @Override
    public List<ProductDemo> getProductDemosBetweenDemoDatetime(
            LocalDateTime fromDateTime,
            LocalDateTime toDateTime
    ) {
        return new ProductDemoServiceDAL()
                .getProductDemosBetweenDemoDatetime(
                        fromDateTime,
                        toDateTime
                );
    }

    @Override
    public List<ProductDemo> searchProductDemosByMeetingLink(
            String meetingLink
    ) {
        return new ProductDemoServiceDAL()
                .searchProductDemosByMeetingLink(
                        meetingLink
                );
    }

    @Override
    public List<ProductDemo> searchProductDemosByDemoFeedback(
            String demoFeedback
    ) {
        return new ProductDemoServiceDAL()
                .searchProductDemosByDemoFeedback(
                        demoFeedback
                );
    }

    @Override
    public List<ProductDemo> getRecentProductDemos() {
        return new ProductDemoServiceDAL()
                .getRecentProductDemos();
    }

    @Override
    public List<ProductDemo> getAllProductDemos() {
        return new ProductDemoServiceDAL()
                .getAllProductDemos();
    }
}