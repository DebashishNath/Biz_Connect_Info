-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: biz_connect_db
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `trn_sales_invoice`
--

DROP TABLE IF EXISTS `trn_sales_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_sales_invoice` (
  `sales_invoice_id` bigint NOT NULL AUTO_INCREMENT,
  `invoice_no` varchar(50) NOT NULL,
  `invoice_date` date NOT NULL,
  `client_id` bigint NOT NULL,
  `lead_id` bigint DEFAULT NULL,
  `subtotal_amount` decimal(12,2) DEFAULT '0.00',
  `discount_amount` decimal(12,2) DEFAULT '0.00',
  `tax_amount` decimal(12,2) DEFAULT '0.00',
  `total_amount` decimal(12,2) DEFAULT '0.00',
  `currency_code` varchar(10) DEFAULT 'EUR',
  `payment_status` enum('PENDING','PARTIAL','PAID','OVERDUE','CANCELLED') DEFAULT 'PENDING',
  `invoice_status` enum('DRAFT','FINAL','CANCELLED') DEFAULT 'DRAFT',
  `due_date` date DEFAULT NULL,
  `notes` text,
  `created_by` bigint DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sales_invoice_id`),
  UNIQUE KEY `uk_invoice_no` (`invoice_no`),
  KEY `fk_invoice_client` (`client_id`),
  KEY `fk_invoice_lead` (`lead_id`),
  KEY `fk_invoice_created_by` (`created_by`),
  CONSTRAINT `fk_invoice_client` FOREIGN KEY (`client_id`) REFERENCES `mst_clients` (`client_id`),
  CONSTRAINT `fk_invoice_created_by` FOREIGN KEY (`created_by`) REFERENCES `mst_users` (`id`),
  CONSTRAINT `fk_invoice_lead` FOREIGN KEY (`lead_id`) REFERENCES `trn_leads` (`lead_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_sales_invoice`
--

LOCK TABLES `trn_sales_invoice` WRITE;
/*!40000 ALTER TABLE `trn_sales_invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `trn_sales_invoice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-25 15:44:39
