-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: k11a606.p.ssafy.io    Database: cloudy
-- ------------------------------------------------------
-- Server version	8.4.3

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
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm` (
  `alarm_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `content` varchar(100) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `server_name` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`alarm_id`),
  KEY `FK53dra8a3h29id86y823i3blxk` (`member_id`),
  CONSTRAINT `FK53dra8a3h29id86y823i3blxk` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (1,'2024-11-17 04:15:59.743394','2024-11-17 04:15:59.743394','비용이 초과되었습니다.',_binary '\0','cloudy','비용 초과',1),(2,'2024-11-17 04:16:25.783156','2024-11-17 04:16:25.783156','임계치 비용 거의 다가옴',_binary '\0','cloudy','임계치 거의 달성',1),(3,'2024-11-18 07:57:23.934265','2024-11-18 07:57:23.934265','비용 초과',_binary '\0','cloudy','비용 초과',1);
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `container`
--

DROP TABLE IF EXISTS `container`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `container` (
  `container_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `container_name` varchar(100) NOT NULL,
  `server_id` bigint NOT NULL,
  PRIMARY KEY (`container_id`),
  KEY `FKkobnbh2vi51l8setxe4hbhar3` (`server_id`),
  CONSTRAINT `FKkobnbh2vi51l8setxe4hbhar3` FOREIGN KEY (`server_id`) REFERENCES `server` (`server_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `container`
--

LOCK TABLES `container` WRITE;
/*!40000 ALTER TABLE `container` DISABLE KEYS */;
INSERT INTO `container` VALUES (1,'2024-11-15 09:48:23.908435','2024-11-15 09:48:23.908435','company',1),(2,'2024-11-15 09:48:38.471030','2024-11-15 09:48:38.471030','commercial',1),(3,'2024-11-15 09:48:48.920130','2024-11-15 09:48:48.920130','ssafy',1);
/*!40000 ALTER TABLE `container` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instance`
--

DROP TABLE IF EXISTS `instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instance` (
  `instance_id` bigint NOT NULL AUTO_INCREMENT,
  `cloud_type` varchar(20) NOT NULL,
  `cost_per_hour` double NOT NULL,
  `cpu` varchar(20) NOT NULL,
  `instance_name` varchar(100) NOT NULL,
  `instance_period_type` varchar(20) NOT NULL,
  `location` varchar(20) NOT NULL,
  `memory` varchar(20) NOT NULL,
  `network_bandwidth` varchar(255) NOT NULL,
  `os_type` varchar(20) NOT NULL,
  PRIMARY KEY (`instance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instance`
--

LOCK TABLES `instance` WRITE;
/*!40000 ALTER TABLE `instance` DISABLE KEYS */;
INSERT INTO `instance` VALUES (1,'AWS',0.003,'2','t4g.nano','ON','아시아 태평양(서울)','0.5','최대 5','LINUX'),(2,'AWS',0.003,'2','t4g.nano','ONE','아시아 태평양(서울)','0.5','최대 5','LINUX'),(3,'AWS',0.003,'2','t4g.nano','THREE','아시아 태평양(서울)','0.5','최대 5','LINUX'),(4,'AWS',0.0104,'2','t4g.micro','ON','아시아 태평양(서울)','1','최대 5','LINUX'),(5,'AWS',0.006,'2','t4g.micro','ONE','아시아 태평양(서울)','1','최대 5','LINUX'),(6,'AWS',0.005,'2','t4g.micro','THREE','아시아 태평양(서울)','1','최대 5','LINUX'),(7,'AWS',0.0208,'2','t4g.small','ON','아시아 태평양(서울)','2','최대 5','LINUX'),(8,'AWS',0.013,'2','t4g.small','ONE','아시아 태평양(서울)','2','최대 5','LINUX'),(9,'AWS',0.009,'2','t4g.small','THREE','아시아 태평양(서울)','2','최대 5','LINUX'),(10,'AWS',0.0416,'2','t4g.medium','ON','아시아 태평양(서울)','4','최대 5','LINUX'),(11,'AWS',0.025,'2','t4g.medium','ONE','아시아 태평양(서울)','4','최대 5','LINUX'),(12,'AWS',0.018,'2','t4g.medium','THREE','아시아 태평양(서울)','4','최대 5','LINUX'),(13,'AWS',0.0832,'2','t4g.large','ON','아시아 태평양(서울)','8','최대 5','LINUX'),(14,'AWS',0.05,'2','t4g.large','ONE','아시아 태평양(서울)','8','최대 5','LINUX'),(15,'AWS',0.036,'2','t4g.large','THREE','아시아 태평양(서울)','8','최대 5','LINUX'),(16,'AWS',0.1664,'4','t4g.xlarge','ON','아시아 태평양(서울)','16','최대 5','LINUX'),(17,'AWS',0.101,'4','t4g.xlarge','ONE','아시아 태평양(서울)','16','최대 5','LINUX'),(18,'AWS',0.072,'4','t4g.xlarge','THREE','아시아 태평양(서울)','16','최대 5','LINUX'),(19,'AWS',0.3328,'8','t4g.2xlarge','ON','아시아 태평양(서울)','32','최대 5','LINUX'),(20,'AWS',0.201,'8','t4g.2xlarge','ONE','아시아 태평양(서울)','32','최대 5','LINUX'),(21,'AWS',0.144,'8','t4g.2xlarge','THREE','아시아 태평양(서울)','32','최대 5','LINUX'),(22,'AWS',0.0065,'2','t3.nano','ON','아시아 태평양(서울)','0.5','최대 5','LINUX'),(23,'AWS',0.004,'2','t3.nano','ONE','아시아 태평양(서울)','0.5','최대 5','LINUX'),(24,'AWS',0.003,'2','t3.nano','THREE','아시아 태평양(서울)','0.5','최대 5','LINUX'),(25,'AWS',0.013,'2','t3.micro','ON','아시아 태평양(서울)','1','최대 5','LINUX'),(26,'AWS',0.008,'2','t3.micro','ONE','아시아 태평양(서울)','1','최대 5','LINUX'),(27,'AWS',0.006,'2','t3.micro','THREE','아시아 태평양(서울)','1','최대 5','LINUX'),(28,'AWS',0.026,'2','t3.small','ON','아시아 태평양(서울)','2','최대 5','LINUX'),(29,'AWS',0.016,'2','t3.small','ONE','아시아 태평양(서울)','2','최대 5','LINUX'),(30,'AWS',0.011,'2','t3.small','THREE','아시아 태평양(서울)','2','최대 5','LINUX'),(31,'AWS',0.052,'2','t3.medium','ON','아시아 태평양(서울)','4','최대 5','LINUX'),(32,'AWS',0.031,'2','t3.medium','ONE','아시아 태평양(서울)','4','최대 5','LINUX'),(33,'AWS',0.023,'2','t3.medium','THREE','아시아 태평양(서울)','4','최대 5','LINUX'),(34,'AWS',0.104,'2','t3.large','ON','아시아 태평양(서울)','8','최대 5','LINUX'),(35,'AWS',0.063,'2','t3.large','ONE','아시아 태평양(서울)','8','최대 5','LINUX'),(36,'AWS',0.045,'2','t3.large','THREE','아시아 태평양(서울)','8','최대 5','LINUX'),(37,'AWS',0.208,'2','t3.xlarge','ON','아시아 태평양(서울)','16','최대 5','LINUX'),(38,'AWS',0.126,'2','t3.xlarge','ONE','아시아 태평양(서울)','16','최대 5','LINUX'),(39,'AWS',0.09,'2','t3.xlarge','THREE','아시아 태평양(서울)','16','최대 5','LINUX'),(40,'AWS',0.416,'2','t3.2xlarge','ON','아시아 태평양(서울)','32','최대 5','LINUX'),(41,'AWS',0.251,'2','t3.2xlarge','ONE','아시아 태평양(서울)','32','최대 5','LINUX'),(42,'AWS',0.18,'2','t3.2xlarge','THREE','아시아 태평양(서울)','32','최대 5','LINUX'),(43,'AWS',0.0059,'2','t3a.nano','ON','아시아 태평양(서울)','0.5','최대 5','LINUX'),(44,'AWS',0.004,'2','t3a.nano','ONE','아시아 태평양(서울)','0.5','최대 5','LINUX'),(45,'AWS',0.003,'2','t3a.nano','THREE','아시아 태평양(서울)','0.5','최대 5','LINUX'),(46,'AWS',0.0117,'2','t3a.micro','ON','아시아 태평양(서울)','1','최대 5','LINUX'),(47,'AWS',0.007,'2','t3a.micro','ONE','아시아 태평양(서울)','1','최대 5','LINUX'),(48,'AWS',0.005,'2','t3a.micro','THREE','아시아 태평양(서울)','1','최대 5','LINUX'),(49,'AWS',0.0234,'2','t3a.small','ON','아시아 태평양(서울)','2','최대 5','LINUX'),(50,'AWS',0.014,'2','t3a.small','ONE','아시아 태평양(서울)','2','최대 5','LINUX'),(51,'AWS',0.01,'2','t3a.small','THREE','아시아 태평양(서울)','2','최대 5','LINUX'),(52,'AWS',0.0468,'2','t3a.medium','ON','아시아 태평양(서울)','4','최대 5','LINUX'),(53,'AWS',0.028,'2','t3a.medium','ONE','아시아 태평양(서울)','4','최대 5','LINUX'),(54,'AWS',0.02,'2','t3a.medium','THREE','아시아 태평양(서울)','4','최대 5','LINUX'),(55,'AWS',0.0936,'2','t3a.large','ON','아시아 태평양(서울)','8','최대 5','LINUX'),(56,'AWS',0.057,'2','t3a.large','ONE','아시아 태평양(서울)','8','최대 5','LINUX'),(57,'AWS',0.04,'2','t3a.large','THREE','아시아 태평양(서울)','8','최대 5','LINUX'),(58,'AWS',0.1872,'2','t3a.xlarge','ON','아시아 태평양(서울)','16','최대 5','LINUX'),(59,'AWS',0.113,'2','t3a.xlarge','ONE','아시아 태평양(서울)','16','최대 5','LINUX'),(60,'AWS',0.081,'2','t3a.xlarge','THREE','아시아 태평양(서울)','16','최대 5','LINUX'),(61,'AWS',0.3744,'8','t3a.2xlarge','ON','아시아 태평양(서울)','32','최대 5','LINUX'),(62,'AWS',0.226,'8','t3a.2xlarge','ONE','아시아 태평양(서울)','32','최대 5','LINUX'),(63,'AWS',0.162,'8','t3a.2xlarge','THREE','아시아 태평양(서울)','32','최대 5','LINUX'),(64,'AWS',0.0072,'1','t2.nano','ON','아시아 태평양(서울)','0.5','최대 5','LINUX'),(65,'AWS',0.004,'1','t2.nano','ONE','아시아 태평양(서울)','0.5','최대 5','LINUX'),(66,'AWS',0.003,'1','t2.nano','THREE','아시아 태평양(서울)','0.5','최대 5','LINUX'),(67,'AWS',0.0144,'1','t2.micro','ON','아시아 태평양(서울)','1','최대 5','LINUX'),(68,'AWS',0.009,'1','t2.micro','ONE','아시아 태평양(서울)','1','최대 5','LINUX'),(69,'AWS',0.006,'1','t2.micro','THREE','아시아 태평양(서울)','1','최대 5','LINUX'),(70,'AWS',0.0288,'1','t2.small','ON','아시아 태평양(서울)','2','최대 5','LINUX'),(71,'AWS',0.017,'1','t2.small','ONE','아시아 태평양(서울)','2','최대 5','LINUX'),(72,'AWS',0.013,'1','t2.small','THREE','아시아 태평양(서울)','2','최대 5','LINUX'),(73,'AWS',0.0576,'2','t2.medium','ON','아시아 태평양(서울)','4','최대 5','LINUX'),(74,'AWS',0.034,'2','t2.medium','ONE','아시아 태평양(서울)','4','최대 5','LINUX'),(75,'AWS',0.025,'2','t2.medium','THREE','아시아 태평양(서울)','4','최대 5','LINUX'),(76,'AWS',0.1152,'2','t2.large','ON','아시아 태평양(서울)','8','최대 5','LINUX'),(77,'AWS',0.069,'2','t2.large','ONE','아시아 태평양(서울)','8','최대 5','LINUX'),(78,'AWS',0.051,'2','t2.large','THREE','아시아 태평양(서울)','8','최대 5','LINUX'),(79,'AWS',0.2304,'4','t2.xlarge','ON','아시아 태평양(서울)','16','최대 5','LINUX'),(80,'AWS',0.138,'4','t2.xlarge','ONE','아시아 태평양(서울)','16','최대 5','LINUX'),(81,'AWS',0.101,'4','t2.xlarge','THREE','아시아 태평양(서울)','16','최대 5','LINUX'),(82,'AWS',0.4608,'8','t2.2xlarge','ON','아시아 태평양(서울)','32','최대 5','LINUX'),(83,'AWS',0.275,'8','t2.2xlarge','ONE','아시아 태평양(서울)','32','최대 5','LINUX'),(84,'AWS',0.202,'8','t2.2xlarge','THREE','아시아 태평양(서울)','32','최대 5','LINUX'),(85,'AZURE',0.081,'1','A1v2','ON','한국 중부','2','-','LINUX'),(86,'AZURE',0.0749,'1','A1v2','ONE','한국 중부','2','-','LINUX'),(87,'AZURE',0.0647,'1','A1v2','THREE','한국 중부','2','-','LINUX'),(88,'AZURE',0.17,'2','A2v2','ON','한국 중부','4','-','LINUX'),(89,'AZURE',0.1522,'2','A2v2','ONE','한국 중부','4','-','LINUX'),(90,'AZURE',0.1308,'2','A2v2','THREE','한국 중부','4','-','LINUX'),(91,'AZURE',0.356,'4','A4v2','ON','한국 중부','8','-','LINUX'),(92,'AZURE',0.3103,'4','A4v2','ONE','한국 중부','8','-','LINUX'),(93,'AZURE',0.2654,'4','A4v2','THREE','한국 중부','8','-','LINUX'),(94,'AZURE',0.748,'8','A8v2','ON','한국 중부','16','-','LINUX'),(95,'AZURE',0.6336,'8','A8v2','ONE','한국 중부','16','-','LINUX'),(96,'AZURE',0.539,'8','A8v2','THREE','한국 중부','16','-','LINUX'),(97,'AZURE',0.22,'2','A2mv2','ON','한국 중부','16','-','LINUX'),(98,'AZURE',0.1705,'2','A2mv2','ONE','한국 중부','16','-','LINUX'),(99,'AZURE',0.1426,'2','A2mv2','THREE','한국 중부','16','-','LINUX'),(100,'AZURE',0.454,'4','A4mv2','ON','한국 중부','32','-','LINUX'),(101,'AZURE',0.3436,'4','A4mv2','ONE','한국 중부','32','-','LINUX'),(102,'AZURE',0.2868,'4','A4mv2','THREE','한국 중부','32','-','LINUX'),(103,'AZURE',0.909,'8','A8mv2','ON','한국 중부','64','-','LINUX'),(104,'AZURE',0.6872,'8','A8mv2','ONE','한국 중부','64','-','LINUX'),(105,'AZURE',0.5736,'8','A8mv2','THREE','한국 중부','64','-','LINUX');
/*!40000 ALTER TABLE `instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `business_registration_number` varchar(255) DEFAULT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  `is_use_service_alarm` bit(1) NOT NULL,
  `login_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('NORMAL','SUPER') DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2024-11-15 09:44:29.971187','2024-11-15 09:44:29.971187','1145930','개발자',_binary '\0','cloudy','$2a$10$dSI7wt5DzHVZkkzHLjrBJ.RQrYSJvXUH0myPY0aZbrNzWCNPJbnCy','SUPER'),(3,'2024-11-15 09:45:00.965752','2024-11-15 09:45:00.965752','1145930','재무',_binary '\0','cloudy@naver.com','$2a$10$3QsV3WPBndMlk8cBZkYoaeDJvqwlFZs89j.lhjs/qOcJq7xMZRhBS','NORMAL'),(4,'2024-11-15 09:45:09.761024','2024-11-15 09:45:09.761024','1145930','개발자',_binary '\0','cloudy@ssafy.com','$2a$10$qg.2cZWm15PxLgksTd8oB./CV1OOtnT0qdRoK76Vo141vtHcfRHBu','NORMAL'),(5,'2024-11-17 03:20:58.880933','2024-11-17 03:20:58.880933','1145930','인사',_binary '\0','cloudy@gmail.com','$2a$10$7tPohIGMVV0yGfHKamRAreGkTPhY.8F/Qmgm3UBEemwVzJaQ0Tggi','NORMAL');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `server`
--

DROP TABLE IF EXISTS `server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `server` (
  `server_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `payment_type` varchar(100) NOT NULL,
  `server_limit` int NOT NULL,
  `server_name` varchar(100) NOT NULL,
  `instance_id` bigint NOT NULL,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`server_id`),
  KEY `FKbma6s5y52lq9itpj7g8l53wv1` (`instance_id`),
  KEY `FKtl4jbohvcjqopkiavsshp6j5j` (`member_id`),
  CONSTRAINT `FKbma6s5y52lq9itpj7g8l53wv1` FOREIGN KEY (`instance_id`) REFERENCES `instance` (`instance_id`),
  CONSTRAINT `FKtl4jbohvcjqopkiavsshp6j5j` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server`
--

LOCK TABLES `server` WRITE;
/*!40000 ALTER TABLE `server` DISABLE KEYS */;
INSERT INTO `server` VALUES (1,'2024-11-15 09:48:18.277511','2024-11-17 08:07:51.662989','ON',1000,'cloudy',67,1),(2,'2024-11-18 06:33:05.176350','2024-11-18 06:33:05.176350','ON',0,'spring',25,3);
/*!40000 ALTER TABLE `server` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_usage`
--

DROP TABLE IF EXISTS `service_usage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_usage` (
  `service_usage_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `service_cost` double DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `service_type` varchar(255) DEFAULT NULL,
  `container_id` bigint NOT NULL,
  PRIMARY KEY (`service_usage_id`),
  KEY `FK29o7euveecary8erci8xipgde` (`container_id`),
  CONSTRAINT `FK29o7euveecary8erci8xipgde` FOREIGN KEY (`container_id`) REFERENCES `container` (`container_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_usage`
--

LOCK TABLES `service_usage` WRITE;
/*!40000 ALTER TABLE `service_usage` DISABLE KEYS */;
INSERT INTO `service_usage` VALUES (1,'2024-11-15 09:52:12.272033','2024-11-15 09:52:12.272033',0.25,'/external-data/post','external',1),(2,'2024-11-15 09:52:20.942782','2024-11-15 09:52:20.942782',0.15,'/external-data/get','external',1),(3,'2024-11-15 09:52:43.008124','2024-11-15 09:52:43.008124',0.2,'/auth/sms/send','external',1),(4,'2024-11-15 09:52:46.223113','2024-11-15 09:52:46.223113',0.2,'/auth/sms/send','external',2),(5,'2024-11-15 09:52:48.580990','2024-11-15 09:52:48.580990',0.2,'/auth/sms/send','external',3),(6,'2024-11-15 09:53:07.810335','2024-11-15 09:53:07.810335',0.15,'/auth/sms/verify','external',3),(7,'2024-11-15 09:53:10.722084','2024-11-15 09:53:10.722084',0.15,'/auth/sms/verify','external',2),(8,'2024-11-15 09:53:13.238706','2024-11-15 09:53:13.238706',0.15,'/auth/sms/verify','external',1),(9,'2024-11-15 09:53:29.050596','2024-11-15 09:53:29.050596',0.25,'/external-data/post','external',2),(10,'2024-11-15 09:53:31.521587','2024-11-15 09:53:31.521587',0.25,'/external-data/post','external',3),(11,'2024-11-15 09:53:41.279510','2024-11-15 09:53:41.279510',0.15,'/external-data/get','external',3),(12,'2024-11-15 09:53:43.704281','2024-11-15 09:53:43.704281',0.15,'/external-data/get','external',2);
/*!40000 ALTER TABLE `service_usage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-18 19:38:30
