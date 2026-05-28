package biz_connect_info.service.ProductDemo;

import biz_connect_info.models.ProductDemo;
import utils.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductDemoService {

    ProductDemo updateProductDemo(ProductDemo productDemo);

    MessageResponse deleteProductDemo(Long demoId);

    ProductDemo getProductDemoById(Long demoId);

    List<ProductDemo> getProductDemosByLeadId(
            Long leadId
    );

    List<ProductDemo> getProductDemosByConductedBy(
            Long userId
    );

    List<ProductDemo> getProductDemosByDemoStatus(
            String demoStatus
    );

    List<ProductDemo> getProductDemosByDemoDatetime(
            LocalDateTime demoDatetime
    );

    List<ProductDemo> getProductDemosBetweenDemoDatetime(
            LocalDateTime fromDateTime,
            LocalDateTime toDateTime
    );

    List<ProductDemo> searchProductDemosByMeetingLink(
            String meetingLink
    );

    List<ProductDemo> searchProductDemosByDemoFeedback(
            String demoFeedback
    );

    List<ProductDemo> getRecentProductDemos();

    List<ProductDemo> getAllProductDemos();
}