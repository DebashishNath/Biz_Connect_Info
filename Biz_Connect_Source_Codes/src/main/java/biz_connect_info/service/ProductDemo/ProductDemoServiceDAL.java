package biz_connect_info.service.ProductDemo;

import biz_connect_info.models.ProductDemo;
import biz_connect_info.repository.ProductDemoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.CodeConstants;
import utils.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
class ProductDemoServiceDAL
        extends ProductDemoServiceImpl {

    @Autowired
    private ProductDemoRepository productDemoRep;

    public ProductDemoServiceDAL() {
    }

    @Override
    public ProductDemo updateProductDemo(
            ProductDemo productDemo
    ) {

        MessageResponse msgResp;

        try {

            ProductDemo productDemoToUpdate =
                    productDemoRep.save(productDemo);

            msgResp = new MessageResponse(
                    CodeConstants.SUCCESS.getID(),
                    "Product demo updated successfully!"
            );

            productDemoToUpdate
                    .setReturnMessage(msgResp);

            return productDemoToUpdate;

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            msgResp = new MessageResponse(
                    CodeConstants.FAILURE.getID(),
                    "Failed to update product demo!"
            );

            productDemo.setReturnMessage(msgResp);

            return productDemo;
        }
    }

    @Override
    public MessageResponse deleteProductDemo(Long demoId) {
        MessageResponse msgResp = new MessageResponse();
        try
        {
            productDemoRep.deleteById(demoId);
            msgResp = new MessageResponse(CodeConstants.SUCCESS.getID(), "Product demo details deleted successfully!");
            return msgResp;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            msgResp = new MessageResponse(CodeConstants.FAILURE.getID(),"Failed to delete product demo");
            return msgResp;
        }
    }

    @Override
    public ProductDemo getProductDemoById(
            Long demoId
    ) {

        try {

            return productDemoRep
                    .findById(demoId)
                    .orElse(null);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<ProductDemo> getProductDemosByLeadId(
            Long leadId
    ) {

        try {

            return productDemoRep
                    .findByLeadLeadId(leadId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<ProductDemo> getProductDemosByConductedBy(
            Long userId
    ) {

        try {

            return productDemoRep
                    .findByConductedById(userId);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<ProductDemo> getProductDemosByDemoStatus(
            String demoStatus
    ) {

        try {

            return productDemoRep
                    .findByDemoStatus(demoStatus);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<ProductDemo> getProductDemosByDemoDatetime(
            LocalDateTime demoDatetime
    ) {

        try {

            return productDemoRep
                    .findByDemoDatetime(demoDatetime);

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<ProductDemo> getProductDemosBetweenDemoDatetime(
            LocalDateTime fromDateTime,
            LocalDateTime toDateTime
    ) {

        try {

            return productDemoRep
                    .findByDemoDatetimeBetween(
                            fromDateTime,
                            toDateTime
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<ProductDemo> searchProductDemosByMeetingLink(
            String meetingLink
    ) {

        try {

            return productDemoRep
                    .findByMeetingLinkContainingIgnoreCase(
                            meetingLink
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<ProductDemo> searchProductDemosByDemoFeedback(
            String demoFeedback
    ) {

        try {

            return productDemoRep
                    .findByDemoFeedbackContainingIgnoreCase(
                            demoFeedback
                    );

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<ProductDemo> getRecentProductDemos() {

        try {

            return productDemoRep
                    .findTop10ByOrderByCreatedAtDesc();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }

    @Override
    public List<ProductDemo> getAllProductDemos() {

        try {

            return productDemoRep.findAll();

        } catch (Exception ex) {

            System.out.println(
                    "Error Is: " + ex.getMessage()
            );

            return null;
        }
    }
}