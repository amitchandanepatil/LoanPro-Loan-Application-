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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'anshika','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),(2,'wuiii','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),(3,'admin1','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),(4,'admin2','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),(5,'admin3','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),(6,'admin4','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),(7,'admin5','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),(8,'arpitad','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
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
  `loan_status` int DEFAULT '0',
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `fk_loan_user` (`user_name`),
  CONSTRAINT `fk_loan_user` FOREIGN KEY (`user_name`) REFERENCES `user_tbl` (`userName`),
  CONSTRAINT `loan_application_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_application`
--

LOCK TABLES `loan_application` WRITE;
/*!40000 ALTER TABLE `loan_application` DISABLE KEYS */;
INSERT INTO `loan_application` VALUES (1,30,789999,900000,1,6,1,'2024-05-12 19:32:22',900000,2,'hanuman'),(2,29,789000,456679,1,5,2,'2024-05-12 19:35:29',455679,1,'sita'),(3,33,89000,90000,1,4,1,'2024-05-13 08:45:50',89000,1,'amit'),(4,34,890000,900000,2,2,1,'2024-05-13 09:11:22',899000,1,'anand2'),(5,35,560000,890000,1,5,1,'2024-05-13 10:07:56',889000,1,'anandkr'),(6,32,890000,8999978,1,4,1,'2024-05-13 17:36:42',8999978,0,'valid1');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repayment_schedule`
--

LOCK TABLES `repayment_schedule` WRITE;
/*!40000 ALTER TABLE `repayment_schedule` DISABLE KEYS */;
INSERT INTO `repayment_schedule` VALUES (1,1,7893500),(2,3,89000),(3,4,899000),(4,6,700000),(5,7,799000),(6,8,79000),(7,9,9999000),(8,10,4677780),(9,11,99000),(10,12,0),(11,15,0),(12,30,339000),(13,2,455679),(14,5,889000);
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
  `contact` varchar(20) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tbl`
--

LOCK TABLES `user_tbl` WRITE;
/*!40000 ALTER TABLE `user_tbl` DISABLE KEYS */;
INSERT INTO `user_tbl` VALUES (14,'we','123','1888-09-09','Mumbai','RTY67777','we@gmail.com','2024-05-07 08:52:46',NULL,NULL),(16,'ty','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1900-09-09','Raipur','RYGUU788','e@hjnk','2024-05-07 08:52:46',NULL,NULL),(17,'amy','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1988-09-09','delhi','ERH677','t@knm','2024-05-07 09:43:29',NULL,NULL),(18,'jamy','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1444-09-09','delhi','FGYGH6777','r@llk','2024-05-07 09:45:27',NULL,NULL),(20,'qui','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1900-09-09','Pune','KHHG899','wer@njnj','2024-05-08 03:45:38',NULL,NULL),(21,'v1','123','1990-09-09','Mumbai','GVNJU13245','v@knk','2024-05-10 07:06:40',NULL,NULL),(22,'ram','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1990-09-09','Patna','UHIHKJK8999','wr@uhjh','2024-05-10 10:07:48',NULL,NULL),(23,'rahul','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1990-09-09','Patna','HFGJ7888','rahul@lkkl','2024-05-11 08:51:39',NULL,NULL),(24,'v','123','1990-09-09','USA','FGJHK788','u@hkk','2024-05-11 09:54:54',NULL,NULL),(25,'hey','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1990-09-09','Canada','HGJHK7788','hey@jhkjjk','2024-05-11 11:25:55',NULL,NULL),(26,'amyy','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1990-09-09','ty','67','y@lkl','2024-05-11 15:27:54',NULL,NULL),(27,'gomu','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1990-09-09','patna','88983958985','yiji@','2024-05-12 15:52:05','5898954',NULL),(29,'sita','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1990-09-09','Patna','GHJ78989','sita@gmail.com','2024-05-12 18:05:32','689999',NULL),(30,'hanuman','8d23cf6c86e834a7aa6eded54c26ce2bb2e74903538c61bdd5d2197997ab2f72','1999-09-09','Mumbai','UUIII9900','hanu@gmail.com','2024-05-12 18:14:52','111111',NULL),(31,'valid','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1990-09-09','wee@22gmail.com','TYU678','valid@22gmail.com','2024-05-12 18:40:54','8989898989',NULL),(32,'valid1','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1990-09-09','Pune','TYY889','va34@gmail.com','2024-05-12 18:48:57','7878787878',NULL),(33,'amit','8d23cf6c86e834a7aa6eded54c26ce2bb2e74903538c61bdd5d2197997ab2f72','1999-08-07','Pune','YUII899','amit@gmail.com','2024-05-13 08:44:55','9999999999',NULL),(34,'anand2','5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5','1998-05-09','Mumbai','TYU7899','anand2@gmail.com','2024-05-13 09:09:40','8989898989',NULL),(35,'anandkr','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1997-05-07','Mumbai','ER577','anandkr@gmail.com','2024-05-13 10:05:56','9988776655',NULL),(36,'hema','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1899-08-07','Pune','IOO9908','hema@gmail.com','2024-05-14 03:59:30','8989898989',NULL),(37,'hemak','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1990-09-09','Delhi','ER4567','hemak@gmail.com','2024-05-14 04:06:44','6789987678','Hema ');
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

-- Dump completed on 2024-05-14 13:46:51
