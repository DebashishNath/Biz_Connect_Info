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
-- Table structure for table `trn_tasks`
--

DROP TABLE IF EXISTS `trn_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trn_tasks` (
  `task_id` bigint NOT NULL AUTO_INCREMENT,
  `task_title` varchar(200) NOT NULL,
  `task_description` text,
  `related_lead_id` bigint DEFAULT NULL,
  `assigned_to` bigint DEFAULT NULL,
  `task_status` enum('OPEN','IN_PROGRESS','COMPLETED','CANCELLED') DEFAULT NULL,
  `priority` enum('LOW','MEDIUM','HIGH') DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_id`),
  KEY `fk_task_lead` (`related_lead_id`),
  KEY `fk_task_assigned` (`assigned_to`),
  CONSTRAINT `fk_task_assigned` FOREIGN KEY (`assigned_to`) REFERENCES `mst_users` (`id`),
  CONSTRAINT `fk_task_lead` FOREIGN KEY (`related_lead_id`) REFERENCES `trn_leads` (`lead_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trn_tasks`
--

LOCK TABLES `trn_tasks` WRITE;
/*!40000 ALTER TABLE `trn_tasks` DISABLE KEYS */;
/*!40000 ALTER TABLE `trn_tasks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-23 11:56:17
