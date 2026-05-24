package biz_connect_info.repository;


//////////////////////////////////////////////////////////////////
// TRAINING COURSE REPOSITORY
//////////////////////////////////////////////////////////////////

import biz_connect_info.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingCourseRepository extends JpaRepository<TrainingCourse, Long> {

    List<TrainingCourse> findByTechnologyArea(String technologyArea);

    List<TrainingCourse> findByCourseNameContainingIgnoreCase(String courseName);

    List<TrainingCourse> findByIsActive(String isActive);

    List<TrainingCourse> findAllByOrderByCourseNameAsc();
}

//////////////////////////////////////////////////////////////////
// LEAD REPOSITORY
//////////////////////////////////////////////////////////////////

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    List<Lead> findByClientClientId(Long clientId);

    List<Lead> findByInterestedProductProductId(Long productId);

    List<Lead> findByLeadStatusLeadStatusId(Integer leadStatusId);

    List<Lead> findByAssignedToId(Long userId);

    List<Lead> findByPriorityLevel(String priorityLevel);

    List<Lead> findByExpectedClosureDate(LocalDate expectedClosureDate);

    List<Lead> findByLeadSource(String leadSource);

    List<Lead> findTop10ByOrderByCreatedAtDesc();

    List<Lead> findByRemarksContainingIgnoreCase(String remarks);
}

//////////////////////////////////////////////////////////////////
// LEAD FOLLOWUP REPOSITORY
//////////////////////////////////////////////////////////////////

@Repository
public interface LeadFollowupRepository extends JpaRepository<LeadFollowup, Long> {

    List<LeadFollowup> findByLeadLeadId(Long leadId);

    List<LeadFollowup> findByCreatedById(Long userId);

    List<LeadFollowup> findByCommunicationMode(String communicationMode);

    List<LeadFollowup> findByFollowupStatus(String followupStatus);

    List<LeadFollowup> findByNextFollowupDateBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );

    List<LeadFollowup> findTop10ByOrderByCreatedAtDesc();
}

//////////////////////////////////////////////////////////////////
// PRODUCT DEMO REPOSITORY
//////////////////////////////////////////////////////////////////

@Repository
public interface ProductDemoRepository extends JpaRepository<ProductDemo, Long> {

    List<ProductDemo> findByLeadLeadId(Long leadId);

    List<ProductDemo> findByDemoStatus(String demoStatus);

    List<ProductDemo> findByConductedById(Long userId);

    List<ProductDemo> findByDemoDatetimeBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );

    List<ProductDemo> findTop10ByOrderByCreatedAtDesc();
}

//////////////////////////////////////////////////////////////////
// SALES INVOICE REPOSITORY
//////////////////////////////////////////////////////////////////

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {

    Optional<SalesInvoice> findByInvoiceNo(String invoiceNo);

    List<SalesInvoice> findByClientClientId(Long clientId);

    List<SalesInvoice> findByPaymentStatus(String paymentStatus);

    List<SalesInvoice> findByInvoiceStatus(String invoiceStatus);

    List<SalesInvoice> findByInvoiceDateBetween(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<SalesInvoice> findByDueDateBefore(LocalDate dueDate);

    List<SalesInvoice> findTop10ByOrderByCreatedAtDesc();
}

//////////////////////////////////////////////////////////////////
// SALES INVOICE DETAIL REPOSITORY
//////////////////////////////////////////////////////////////////

@Repository
public interface SalesInvoiceDetailRepository
        extends JpaRepository<SalesInvoiceDetail, Long> {

    List<SalesInvoiceDetail> findBySalesInvoiceSalesInvoiceId(
            Long salesInvoiceId
    );

    List<SalesInvoiceDetail> findByProductProductId(Long productId);
}

//////////////////////////////////////////////////////////////////
// PAYMENT REPOSITORY
//////////////////////////////////////////////////////////////////

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findBySalesInvoiceSalesInvoiceId(Long salesInvoiceId);

    List<Payment> findByPaymentMethod(String paymentMethod);

    List<Payment> findByPaymentDateBetween(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<Payment> findByReceivedById(Long userId);

    Optional<Payment> findByTransactionReference(
            String transactionReference
    );

    List<Payment> findTop10ByOrderByCreatedAtDesc();
}

//////////////////////////////////////////////////////////////////
// TASK REPOSITORY
//////////////////////////////////////////////////////////////////

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssignedToId(Long userId);

    List<Task> findByTaskStatus(String taskStatus);

    List<Task> findByPriority(String priority);

    List<Task> findByRelatedLeadLeadId(Long leadId);

    List<Task> findByDueDateBetween(
            LocalDateTime fromDate,
            LocalDateTime toDate
    );

    List<Task> findByTaskTitleContainingIgnoreCase(String taskTitle);

    List<Task> findTop10ByOrderByCreatedAtDesc();
}

//////////////////////////////////////////////////////////////////
// CLIENT DOCUMENT REPOSITORY
//////////////////////////////////////////////////////////////////

@Repository
public interface ClientDocumentRepository
        extends JpaRepository<ClientDocument, Long> {

    List<ClientDocument> findByClientClientId(Long clientId);

    List<ClientDocument> findByLeadLeadId(Long leadId);

    List<ClientDocument> findByDocumentType(String documentType);

    List<ClientDocument> findByUploadedById(Long userId);

    List<ClientDocument> findByDocumentNameContainingIgnoreCase(
            String documentName
    );

    List<ClientDocument> findTop10ByOrderByUploadedAtDesc();
}

//////////////////////////////////////////////////////////////////
// TRAINING ENROLLMENT REPOSITORY
//////////////////////////////////////////////////////////////////

@Repository
public interface TrainingEnrollmentRepository
        extends JpaRepository<TrainingEnrollment, Long> {

    List<TrainingEnrollment> findByClientClientId(Long clientId);

    List<TrainingEnrollment> findByCourseCourseId(Long courseId);

    List<TrainingEnrollment> findByPaymentStatus(String paymentStatus);

    List<TrainingEnrollment> findByTrainingStatus(String trainingStatus);

    List<TrainingEnrollment> findByBatchName(String batchName);

    List<TrainingEnrollment> findByEnrollmentDateBetween(
            LocalDate fromDate,
            LocalDate toDate
    );

    List<TrainingEnrollment> findTop10ByOrderByCreatedAtDesc();
}