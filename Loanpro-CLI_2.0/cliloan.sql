CREATE DATABASE  IF NOT EXISTS `axis` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `axis`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: axis
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) DEFAULT NULL,
  `admin_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'anshika','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),(2,'wuiii','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_application`
--

DROP TABLE IF EXISTS `loan_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan_application` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `income` double NOT NULL,
  `loan_amount` double NOT NULL,
  `loan_type` int NOT NULL,
  `loan_terms` int DEFAULT NULL,
  `employment_status` int DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `remaining_amount` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `loan_application_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_application`
--

LOCK TABLES `loan_application` WRITE;
/*!40000 ALTER TABLE `loan_application` DISABLE KEYS */;
INSERT INTO `loan_application` VALUES (1,1,890000,7893499,1,NULL,NULL,'2024-05-10 05:14:20',NULL),(2,1,78888888888888,9999999999999,3,NULL,NULL,'2024-05-10 05:14:20',NULL),(3,3,600000,9900000,1,NULL,NULL,'2024-05-10 05:14:20',NULL),(4,4,60000,50000,1,NULL,NULL,'2024-05-10 05:14:20',NULL),(5,1,700000,7899999,3,NULL,NULL,'2024-05-10 05:14:20',NULL),(6,9,700000,700000,1,NULL,NULL,'2024-05-10 05:14:20',NULL),(7,10,600000,799000,2,NULL,NULL,'2024-05-10 05:14:20',NULL),(8,11,78000,79000,2,NULL,NULL,'2024-05-10 05:14:20',NULL),(9,12,700000,9999000,4,NULL,NULL,'2024-05-10 05:14:20',NULL),(10,14,70000,5677777,1,6,1,'2024-05-10 05:14:20',5667777),(11,14,60000,100000,2,6,2,'2024-05-10 05:48:40',NULL);
/*!40000 ALTER TABLE `loan_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repayment_schedule`
--

DROP TABLE IF EXISTS `repayment_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repayment_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `loan_application_id` int NOT NULL,
  `amount` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `loan_application_id` (`loan_application_id`),
  CONSTRAINT `repayment_schedule_ibfk_1` FOREIGN KEY (`loan_application_id`) REFERENCES `loan_application` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repayment_schedule`
--

LOCK TABLES `repayment_schedule` WRITE;
/*!40000 ALTER TABLE `repayment_schedule` DISABLE KEYS */;
INSERT INTO `repayment_schedule` VALUES (1,1,7893500),(2,3,9900000),(3,4,50000),(4,6,700000),(5,7,799000),(6,8,79000),(7,9,9999000),(8,10,5667780);
/*!40000 ALTER TABLE `repayment_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tbl`
--

DROP TABLE IF EXISTS `user_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `dob` date DEFAULT NULL,
  `address` varchar(255) NOT NULL DEFAULT '',
  `pan` varchar(15) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tbl`
--

LOCK TABLES `user_tbl` WRITE;
/*!40000 ALTER TABLE `user_tbl` DISABLE KEYS */;
INSERT INTO `user_tbl` VALUES (14,'we','123','1888-09-09','Mumbai','RTY67777','we@gmail.com','2024-05-07 08:52:46'),(16,'ty','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1900-09-09','Raipur','RYGUU788','e@hjnk','2024-05-07 08:52:46'),(17,'amy','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1988-09-09','delhi','ERH677','t@knm','2024-05-07 09:43:29'),(18,'jamy','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1444-09-09','delhi','FGYGH6777','r@llk','2024-05-07 09:45:27'),(20,'qui','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1900-09-09','Pune','KHHG899','wer@njnj','2024-05-08 03:45:38');
/*!40000 ALTER TABLE `user_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-10 11:30:51
