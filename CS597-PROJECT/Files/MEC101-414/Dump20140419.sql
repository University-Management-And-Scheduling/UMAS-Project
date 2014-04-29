CREATE DATABASE  IF NOT EXISTS `university` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `university`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: university
-- ------------------------------------------------------
-- Server version	5.5.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applicationdetails`
--

DROP TABLE IF EXISTS `applicationdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationdetails` (
  `ApplicationID` int(12) NOT NULL AUTO_INCREMENT,
  `ApplicantUIN` int(12) NOT NULL,
  `WorkExperience` int(11) DEFAULT '0',
  `Skillset1` varchar(45) DEFAULT NULL,
  `Skillset2` varchar(45) DEFAULT NULL,
  `Skillset3` varchar(45) DEFAULT NULL,
  `Skillset4` varchar(45) DEFAULT NULL,
  `Skillset5` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ApplicationID`),
  UNIQUE KEY `ApplicantUIN_UNIQUE` (`ApplicantUIN`),
  KEY `ApplicantUIN_idx` (`ApplicantUIN`),
  CONSTRAINT `ApplicantUIN` FOREIGN KEY (`ApplicantUIN`) REFERENCES `people` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationdetails`
--

LOCK TABLES `applicationdetails` WRITE;
/*!40000 ALTER TABLE `applicationdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicationdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroom` (
  `ClassroomID` int(12) NOT NULL AUTO_INCREMENT,
  `ClassroomName` varchar(45) NOT NULL,
  `ClassroomLocation` varchar(45) NOT NULL,
  `ClassroomCapacity` int(12) NOT NULL,
  PRIMARY KEY (`ClassroomID`)
) ENGINE=InnoDB AUTO_INCREMENT=289 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,'CLASS1','LOCATION1',50),(2,'CLASS2','LOCATION1',50),(3,'CLASS3','LOCATION1',50),(4,'CLASS4','LOCATION1',50),(5,'CLASS5','LOCATION1',50),(6,'CLASS6','LOCATION1',50),(7,'CLASS7','LOCATION1',50),(8,'CLASS8','LOCATION1',50),(9,'CLASS9','LOCATION1',50),(10,'CLASS10','LOCATION1',50),(11,'CLASS11','LOCATION1',50),(12,'CLASS12','LOCATION1',50),(13,'CLASS13','LOCATION1',50),(14,'CLASS14','LOCATION1',50),(15,'CLASS15','LOCATION1',50),(16,'CLASS16','LOCATION1',50),(17,'CLASS17','LOCATION1',50),(18,'CLASS18','LOCATION1',50),(19,'CLASS19','LOCATION1',50),(20,'CLASS20','LOCATION1',50),(21,'CLASS21','LOCATION1',50),(22,'CLASS22','LOCATION1',50),(23,'CLASS23','LOCATION1',50),(24,'CLASS24','LOCATION1',50),(25,'CLASS1','LOCATION2',50),(26,'CLASS2','LOCATION2',50),(27,'CLASS3','LOCATION2',50),(28,'CLASS4','LOCATION2',50),(29,'CLASS5','LOCATION2',50),(30,'CLASS6','LOCATION2',50),(31,'CLASS7','LOCATION2',50),(32,'CLASS8','LOCATION2',50),(33,'CLASS9','LOCATION2',50),(34,'CLASS10','LOCATION2',50),(35,'CLASS11','LOCATION2',50),(36,'CLASS12','LOCATION2',50),(37,'CLASS13','LOCATION2',50),(38,'CLASS14','LOCATION2',50),(39,'CLASS15','LOCATION2',50),(40,'CLASS16','LOCATION2',50),(41,'CLASS17','LOCATION2',50),(42,'CLASS18','LOCATION2',50),(43,'CLASS19','LOCATION2',50),(44,'CLASS20','LOCATION2',50),(45,'CLASS21','LOCATION2',50),(46,'CLASS22','LOCATION2',50),(47,'CLASS23','LOCATION2',50),(48,'CLASS24','LOCATION2',50),(49,'CLASS1','LOCATION3',50),(50,'CLASS2','LOCATION3',50),(51,'CLASS3','LOCATION3',50),(52,'CLASS4','LOCATION3',50),(53,'CLASS5','LOCATION3',50),(54,'CLASS6','LOCATION3',50),(55,'CLASS7','LOCATION3',50),(56,'CLASS8','LOCATION3',50),(57,'CLASS9','LOCATION3',50),(58,'CLASS10','LOCATION3',50),(59,'CLASS11','LOCATION3',50),(60,'CLASS12','LOCATION3',50),(61,'CLASS13','LOCATION3',50),(62,'CLASS14','LOCATION3',50),(63,'CLASS15','LOCATION3',50),(64,'CLASS16','LOCATION3',50),(65,'CLASS17','LOCATION3',50),(66,'CLASS18','LOCATION3',50),(67,'CLASS19','LOCATION3',50),(68,'CLASS20','LOCATION3',50),(69,'CLASS21','LOCATION3',50),(70,'CLASS22','LOCATION3',50),(71,'CLASS23','LOCATION3',50),(72,'CLASS24','LOCATION3',50),(73,'CLASS1','LOCATION4',50),(74,'CLASS2','LOCATION4',50),(75,'CLASS3','LOCATION4',50),(76,'CLASS4','LOCATION4',50),(77,'CLASS5','LOCATION4',50),(78,'CLASS6','LOCATION4',50),(79,'CLASS7','LOCATION4',50),(80,'CLASS8','LOCATION4',50),(81,'CLASS9','LOCATION4',50),(82,'CLASS10','LOCATION4',50),(83,'CLASS11','LOCATION4',50),(84,'CLASS12','LOCATION4',50),(85,'CLASS13','LOCATION4',50),(86,'CLASS14','LOCATION4',50),(87,'CLASS15','LOCATION4',50),(88,'CLASS16','LOCATION4',50),(89,'CLASS17','LOCATION4',50),(90,'CLASS18','LOCATION4',50),(91,'CLASS19','LOCATION4',50),(92,'CLASS20','LOCATION4',50),(93,'CLASS21','LOCATION4',50),(94,'CLASS22','LOCATION4',50),(95,'CLASS23','LOCATION4',50),(96,'CLASS24','LOCATION4',50),(97,'CLASS1','LOCATION5',50),(98,'CLASS2','LOCATION5',50),(99,'CLASS3','LOCATION5',50),(100,'CLASS4','LOCATION5',50),(101,'CLASS5','LOCATION5',50),(102,'CLASS6','LOCATION5',50),(103,'CLASS7','LOCATION5',50),(104,'CLASS8','LOCATION5',50),(105,'CLASS9','LOCATION5',50),(106,'CLASS10','LOCATION5',50),(107,'CLASS11','LOCATION5',50),(108,'CLASS12','LOCATION5',50),(109,'CLASS13','LOCATION5',50),(110,'CLASS14','LOCATION5',50),(111,'CLASS15','LOCATION5',50),(112,'CLASS16','LOCATION5',50),(113,'CLASS17','LOCATION5',50),(114,'CLASS18','LOCATION5',50),(115,'CLASS19','LOCATION5',50),(116,'CLASS20','LOCATION5',50),(117,'CLASS21','LOCATION5',50),(118,'CLASS22','LOCATION5',50),(119,'CLASS23','LOCATION5',50),(120,'CLASS24','LOCATION5',50),(121,'CLASS1','LOCATION6',50),(122,'CLASS2','LOCATION6',50),(123,'CLASS3','LOCATION6',50),(124,'CLASS4','LOCATION6',50),(125,'CLASS5','LOCATION6',50),(126,'CLASS6','LOCATION6',50),(127,'CLASS7','LOCATION6',50),(128,'CLASS8','LOCATION6',50),(129,'CLASS9','LOCATION6',50),(130,'CLASS10','LOCATION6',50),(131,'CLASS11','LOCATION6',50),(132,'CLASS12','LOCATION6',50),(133,'CLASS13','LOCATION6',50),(134,'CLASS14','LOCATION6',50),(135,'CLASS15','LOCATION6',50),(136,'CLASS16','LOCATION6',50),(137,'CLASS17','LOCATION6',50),(138,'CLASS18','LOCATION6',50),(139,'CLASS19','LOCATION6',50),(140,'CLASS20','LOCATION6',50),(141,'CLASS21','LOCATION6',50),(142,'CLASS22','LOCATION6',50),(143,'CLASS23','LOCATION6',50),(144,'CLASS24','LOCATION6',50),(145,'CLASS1','LOCATION7',50),(146,'CLASS2','LOCATION7',50),(147,'CLASS3','LOCATION7',50),(148,'CLASS4','LOCATION7',50),(149,'CLASS5','LOCATION7',50),(150,'CLASS6','LOCATION7',50),(151,'CLASS7','LOCATION7',50),(152,'CLASS8','LOCATION7',50),(153,'CLASS9','LOCATION7',50),(154,'CLASS10','LOCATION7',50),(155,'CLASS11','LOCATION7',50),(156,'CLASS12','LOCATION7',50),(157,'CLASS13','LOCATION7',50),(158,'CLASS14','LOCATION7',50),(159,'CLASS15','LOCATION7',50),(160,'CLASS16','LOCATION7',50),(161,'CLASS17','LOCATION7',50),(162,'CLASS18','LOCATION7',50),(163,'CLASS19','LOCATION7',50),(164,'CLASS20','LOCATION7',50),(165,'CLASS21','LOCATION7',50),(166,'CLASS22','LOCATION7',50),(167,'CLASS23','LOCATION7',50),(168,'CLASS24','LOCATION7',50),(169,'CLASS1','LOCATION8',50),(170,'CLASS2','LOCATION8',50),(171,'CLASS3','LOCATION8',50),(172,'CLASS4','LOCATION8',50),(173,'CLASS5','LOCATION8',50),(174,'CLASS6','LOCATION8',50),(175,'CLASS7','LOCATION8',50),(176,'CLASS8','LOCATION8',50),(177,'CLASS9','LOCATION8',50),(178,'CLASS10','LOCATION8',50),(179,'CLASS11','LOCATION8',50),(180,'CLASS12','LOCATION8',50),(181,'CLASS13','LOCATION8',50),(182,'CLASS14','LOCATION8',50),(183,'CLASS15','LOCATION8',50),(184,'CLASS16','LOCATION8',50),(185,'CLASS17','LOCATION8',50),(186,'CLASS18','LOCATION8',50),(187,'CLASS19','LOCATION8',50),(188,'CLASS20','LOCATION8',50),(189,'CLASS21','LOCATION8',50),(190,'CLASS22','LOCATION8',50),(191,'CLASS23','LOCATION8',50),(192,'CLASS24','LOCATION8',50),(193,'CLASS1','LOCATION9',50),(194,'CLASS2','LOCATION9',50),(195,'CLASS3','LOCATION9',50),(196,'CLASS4','LOCATION9',50),(197,'CLASS5','LOCATION9',50),(198,'CLASS6','LOCATION9',50),(199,'CLASS7','LOCATION9',50),(200,'CLASS8','LOCATION9',50),(201,'CLASS9','LOCATION9',50),(202,'CLASS10','LOCATION9',50),(203,'CLASS11','LOCATION9',50),(204,'CLASS12','LOCATION9',50),(205,'CLASS13','LOCATION9',50),(206,'CLASS14','LOCATION9',50),(207,'CLASS15','LOCATION9',50),(208,'CLASS16','LOCATION9',50),(209,'CLASS17','LOCATION9',50),(210,'CLASS18','LOCATION9',50),(211,'CLASS19','LOCATION9',50),(212,'CLASS20','LOCATION9',50),(213,'CLASS21','LOCATION9',50),(214,'CLASS22','LOCATION9',50),(215,'CLASS23','LOCATION9',50),(216,'CLASS24','LOCATION9',50),(217,'CLASS1','LOCATION10',50),(218,'CLASS2','LOCATION10',50),(219,'CLASS3','LOCATION10',50),(220,'CLASS4','LOCATION10',50),(221,'CLASS5','LOCATION10',50),(222,'CLASS6','LOCATION10',50),(223,'CLASS7','LOCATION10',50),(224,'CLASS8','LOCATION10',50),(225,'CLASS9','LOCATION10',50),(226,'CLASS10','LOCATION10',50),(227,'CLASS11','LOCATION10',50),(228,'CLASS12','LOCATION10',50),(229,'CLASS13','LOCATION10',50),(230,'CLASS14','LOCATION10',50),(231,'CLASS15','LOCATION10',50),(232,'CLASS16','LOCATION10',50),(233,'CLASS17','LOCATION10',50),(234,'CLASS18','LOCATION10',50),(235,'CLASS19','LOCATION10',50),(236,'CLASS20','LOCATION10',50),(237,'CLASS21','LOCATION10',50),(238,'CLASS22','LOCATION10',50),(239,'CLASS23','LOCATION10',50),(240,'CLASS24','LOCATION10',50),(241,'CLASS1','LOCATION11',50),(242,'CLASS2','LOCATION11',50),(243,'CLASS3','LOCATION11',50),(244,'CLASS4','LOCATION11',50),(245,'CLASS5','LOCATION11',50),(246,'CLASS6','LOCATION11',50),(247,'CLASS7','LOCATION11',50),(248,'CLASS8','LOCATION11',50),(249,'CLASS9','LOCATION11',50),(250,'CLASS10','LOCATION11',50),(251,'CLASS11','LOCATION11',50),(252,'CLASS12','LOCATION11',50),(253,'CLASS13','LOCATION11',50),(254,'CLASS14','LOCATION11',50),(255,'CLASS15','LOCATION11',50),(256,'CLASS16','LOCATION11',50),(257,'CLASS17','LOCATION11',50),(258,'CLASS18','LOCATION11',50),(259,'CLASS19','LOCATION11',50),(260,'CLASS20','LOCATION11',50),(261,'CLASS21','LOCATION11',50),(262,'CLASS22','LOCATION11',50),(263,'CLASS23','LOCATION11',50),(264,'CLASS24','LOCATION11',50),(265,'CLASS1','LOCATION12',50),(266,'CLASS2','LOCATION12',50),(267,'CLASS3','LOCATION12',50),(268,'CLASS4','LOCATION12',50),(269,'CLASS5','LOCATION12',50),(270,'CLASS6','LOCATION12',50),(271,'CLASS7','LOCATION12',50),(272,'CLASS8','LOCATION12',50),(273,'CLASS9','LOCATION12',50),(274,'CLASS10','LOCATION12',50),(275,'CLASS11','LOCATION12',50),(276,'CLASS12','LOCATION12',50),(277,'CLASS13','LOCATION12',50),(278,'CLASS14','LOCATION12',50),(279,'CLASS15','LOCATION12',50),(280,'CLASS16','LOCATION12',50),(281,'CLASS17','LOCATION12',50),(282,'CLASS18','LOCATION12',50),(283,'CLASS19','LOCATION12',50),(284,'CLASS20','LOCATION12',50),(285,'CLASS21','LOCATION12',50),(286,'CLASS22','LOCATION12',50),(287,'CLASS23','LOCATION12',50),(288,'CLASS24','LOCATION12',50);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `CourseID` int(12) NOT NULL AUTO_INCREMENT,
  `CourseName` varchar(45) NOT NULL,
  `DepartmentID` int(12) NOT NULL,
  PRIMARY KEY (`CourseID`,`CourseName`),
  KEY `Department_idx` (`DepartmentID`),
  CONSTRAINT `DepartmentCourse` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (19,'DCW219',1),(34,'HPY579',1),(36,'HSU797',1),(51,'LMI869',1),(67,'QFC158',1),(71,'RIT276',1),(86,'XBW196',1),(94,'OGH935',1),(1,'CS422',2),(2,'CS423',2),(3,'ACN647',2),(4,'AWG854',2),(13,'BNL161',2),(31,'GWE488',2),(44,'JLC841',2),(48,'KRE544',2),(63,'PRZ482',2),(84,'WSS172',2),(87,'XWP374',2),(9,'BFE121',3),(16,'COI967',3),(39,'IUV276',3),(50,'LGL587',3),(52,'LXJ479',3),(54,'MFY237',3),(79,'VFO268',3),(95,'PZI766',3),(10,'BGK574',4),(14,'BYU517',4),(18,'CZD316',4),(25,'EVO724',4),(28,'FVA985',4),(32,'HKF596',4),(53,'LZC379',4),(56,'NOQ721',4),(68,'QGZ526',4),(81,'WHM164',4),(85,'WWH561',4),(96,'QKX886',4),(11,'BJN913',5),(15,'CJU378',5),(24,'EUH316',5),(27,'FUW253',5),(29,'GEN323',5),(30,'GRA957',5),(38,'IOG144',5),(62,'PMA979',5),(66,'QEK316',5),(70,'QXS922',5),(72,'RRQ942',5),(73,'TQU338',5),(77,'USX346',5),(97,'RAG356',5),(23,'EKU261',6),(26,'EXN476',6),(33,'HNR864',6),(37,'IHQ168',6),(41,'JAS224',6),(49,'LCQ179',6),(57,'NZK993',6),(69,'QRN954',6),(82,'WOF376',6),(93,'IPQ551',6),(5,'AWT688',7),(20,'DEI726',7),(35,'HSB226',7),(40,'IXI832',7),(55,'NKD236',7),(75,'UMN352',7),(78,'VBW291',7),(90,'YVH757',7),(98,'SAP913',7),(99,'SGN569',7),(100,'ZKV518',7),(6,'AYL565',8),(21,'DLC863',8),(22,'DUI521',8),(43,'JKG247',8),(47,'KNF981',8),(61,'PIJ316',8),(89,'YQR985',8),(91,'ZHR424',8),(92,'HTF453',8),(7,'BAS355',9),(8,'BES361',9),(12,'BJO897',9),(17,'CUO272',9),(42,'JFG544',9),(45,'JSF796',9),(46,'KBV581',9),(58,'OCD327',9),(59,'OIM993',9),(60,'OQA728',9),(64,'QAB396',9),(65,'QAC981',9),(74,'UCL957',9),(76,'UPM889',9),(80,'VKW781',9),(83,'WQB553',9),(88,'YBR584',9);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courseschedule`
--

DROP TABLE IF EXISTS `courseschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courseschedule` (
  `OfferID` int(12) NOT NULL,
  `TimeSlotID` int(12) NOT NULL,
  `ClassroomID` int(12) NOT NULL,
  PRIMARY KEY (`TimeSlotID`,`OfferID`,`ClassroomID`),
  UNIQUE KEY `OfferID_UNIQUE` (`OfferID`),
  KEY `OfferID_idx` (`OfferID`),
  KEY `TimeID_idx` (`TimeSlotID`),
  KEY `ClassName_idx` (`ClassroomID`),
  CONSTRAINT `ClassOfferID` FOREIGN KEY (`OfferID`) REFERENCES `coursesoffered` (`OfferID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `ClassroomID` FOREIGN KEY (`ClassroomID`) REFERENCES `classroom` (`ClassroomID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TimeID` FOREIGN KEY (`TimeSlotID`) REFERENCES `timeslots` (`TimeSlotID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courseschedule`
--

LOCK TABLES `courseschedule` WRITE;
/*!40000 ALTER TABLE `courseschedule` DISABLE KEYS */;
INSERT INTO `courseschedule` VALUES (295,27,6),(296,19,2),(297,29,9),(298,27,1),(299,18,9),(300,27,3),(301,24,3),(302,30,2),(303,20,4),(304,22,4),(305,30,7),(306,24,11),(307,21,9),(308,17,2),(309,26,2),(310,19,8),(311,21,8),(312,25,5),(313,23,1),(314,18,5),(315,30,4),(316,30,3),(317,29,11),(318,18,11),(319,24,10),(320,18,4),(321,22,10),(322,21,3),(323,18,7),(324,21,2),(325,26,3),(326,23,5),(327,29,1),(328,22,2),(329,20,1),(330,19,4),(331,25,1),(332,26,12),(333,18,3),(334,23,4),(335,23,2),(336,29,2),(337,20,11),(338,17,3),(339,23,10),(340,27,2),(341,16,10),(342,19,11),(343,28,10),(344,21,12),(345,17,9),(346,24,2),(347,22,12),(348,18,10),(349,25,4),(350,17,11),(351,30,12),(352,20,3),(353,24,4),(354,26,11),(355,17,4),(356,16,2),(357,21,4),(358,16,12),(359,17,8),(360,16,3),(361,20,12),(362,16,8),(363,19,1),(364,16,11),(365,26,8),(366,18,2),(367,16,7),(368,21,5),(369,19,12),(370,27,12),(371,28,6),(372,23,3),(373,27,5),(374,22,11),(375,29,4),(376,25,3),(377,21,11),(378,16,5),(379,22,3),(380,20,2),(381,17,12),(382,28,9),(383,28,5),(384,26,9),(385,27,4),(386,24,12),(387,16,9),(388,25,12),(389,19,5),(390,28,4),(391,28,12),(392,28,3),(393,17,7),(394,28,11),(395,23,7),(405,21,10),(406,25,11),(407,32,6);
/*!40000 ALTER TABLE `courseschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursesoffered`
--

DROP TABLE IF EXISTS `coursesoffered`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursesoffered` (
  `OfferID` int(12) NOT NULL AUTO_INCREMENT,
  `CourseID` int(12) NOT NULL,
  `SemesterID` int(12) NOT NULL,
  `TotalCapacity` int(11) NOT NULL,
  `SeatsFilled` int(11) NOT NULL,
  `TaughtBy` int(12) NOT NULL,
  PRIMARY KEY (`OfferID`),
  KEY `CourseID_idx` (`CourseID`),
  KEY `PeopleID_idx` (`TaughtBy`),
  KEY `SemesterOffered_idx` (`SemesterID`),
  CONSTRAINT `CourseID` FOREIGN KEY (`CourseID`) REFERENCES `courses` (`CourseID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Professor` FOREIGN KEY (`TaughtBy`) REFERENCES `people` (`UIN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Semester` FOREIGN KEY (`SemesterID`) REFERENCES `semester` (`SemesterID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=408 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursesoffered`
--

LOCK TABLES `coursesoffered` WRITE;
/*!40000 ALTER TABLE `coursesoffered` DISABLE KEYS */;
INSERT INTO `coursesoffered` VALUES (295,1,1,40,37,310),(296,2,1,40,0,272),(297,3,1,40,0,331),(298,4,1,40,0,317),(299,5,1,40,0,331),(300,6,1,40,0,321),(301,7,1,40,0,338),(302,8,1,40,0,306),(303,9,1,40,0,274),(304,10,1,40,0,350),(305,11,1,40,0,336),(306,12,1,40,0,307),(307,13,1,40,0,346),(308,14,1,40,0,295),(309,15,1,40,0,273),(310,16,1,40,0,328),(311,17,1,40,0,337),(312,18,1,40,0,327),(313,19,1,40,0,303),(314,20,1,40,0,290),(315,21,1,40,0,337),(316,22,1,40,0,326),(317,23,1,40,0,303),(318,24,1,40,0,328),(319,25,1,40,0,299),(320,26,1,40,0,300),(321,27,1,40,0,339),(322,28,1,40,0,313),(323,29,1,40,0,280),(324,30,1,40,0,292),(325,31,1,40,0,273),(326,32,1,40,0,320),(327,33,1,40,0,291),(328,34,1,40,0,301),(329,35,1,40,0,316),(330,36,1,40,0,284),(331,37,1,40,0,347),(332,38,1,40,0,321),(333,39,1,40,0,339),(334,40,1,40,0,291),(335,41,1,40,0,346),(336,42,1,40,0,330),(337,43,1,40,0,315),(338,44,1,40,0,331),(339,45,1,40,0,284),(340,46,1,40,0,335),(341,47,1,40,0,293),(342,48,1,40,0,334),(343,49,1,40,0,277),(344,50,1,40,0,322),(345,51,1,40,0,288),(346,52,1,40,0,287),(347,53,1,40,0,345),(348,54,1,40,0,336),(349,55,1,40,0,338),(350,56,1,50,0,345),(351,57,1,50,0,285),(352,58,1,50,0,300),(353,59,1,50,0,307),(354,60,1,50,0,326),(355,61,1,50,0,279),(356,62,1,50,0,279),(357,63,1,50,0,323),(358,64,1,50,0,346),(359,65,1,50,0,354),(360,66,1,50,0,303),(361,67,1,50,0,282),(362,68,1,50,0,287),(363,69,1,50,0,296),(364,70,1,50,0,281),(365,71,1,50,0,1),(366,72,1,50,0,353),(367,73,1,50,0,339),(368,74,1,50,0,300),(369,75,1,50,0,348),(370,76,1,50,0,273),(371,77,1,50,0,291),(372,78,1,50,0,311),(373,79,1,50,0,352),(374,80,1,50,0,306),(375,81,1,50,0,315),(376,82,1,50,0,282),(377,83,1,50,0,325),(378,84,1,50,0,350),(379,85,1,50,0,319),(380,86,1,50,0,295),(381,87,1,50,0,327),(382,88,1,50,0,339),(383,89,1,50,0,321),(384,90,1,50,0,344),(385,91,1,50,0,348),(386,92,1,50,0,295),(387,93,1,50,0,276),(388,94,1,50,0,282),(389,95,1,50,0,282),(390,96,1,50,0,329),(391,97,1,50,0,312),(392,98,1,50,0,276),(393,99,1,50,0,299),(394,100,1,50,0,303),(395,36,1,50,0,1),(405,19,1,40,0,1),(406,11,1,40,0,281),(407,23,1,40,0,279);
/*!40000 ALTER TABLE `coursesoffered` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `DepartmentID` int(12) NOT NULL AUTO_INCREMENT,
  `DepartmentName` varchar(45) NOT NULL,
  PRIMARY KEY (`DepartmentID`,`DepartmentName`),
  UNIQUE KEY `DepartmentID_UNIQUE` (`DepartmentID`),
  UNIQUE KEY `DepartmentName_UNIQUE` (`DepartmentName`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Computer Science'),(2,'Duis A LLP'),(3,'Class Aptent Industries'),(4,'EXTC'),(5,'Feugiat Institute'),(6,'Duis A Mi Institute'),(7,'Cras Sed Consulting'),(8,'Cum Sociis Limited'),(9,'Dolor Dapibus Gravida Consulting'),(10,'Dapibus Ligula Aliquam Foundation'),(11,'Auctor Nunc Nulla Consulting'),(13,'Vel Convallis In Incorporated'),(14,'Eu Odio Tristique Associates'),(15,'Neque Sed Eget Corporation'),(16,'Morbi Foundation'),(17,'Ac Metus Ltd');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emailedwaitlist`
--

DROP TABLE IF EXISTS `emailedwaitlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailedwaitlist` (
  `StudentUIN` int(12) NOT NULL,
  `OfferID` int(12) NOT NULL,
  `TimeEmailed` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`StudentUIN`,`OfferID`),
  KEY `WaitCourseOfferID_idx` (`OfferID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailedwaitlist`
--

LOCK TABLES `emailedwaitlist` WRITE;
/*!40000 ALTER TABLE `emailedwaitlist` DISABLE KEYS */;
INSERT INTO `emailedwaitlist` VALUES (452,295,'2014-04-17 07:30:29');
/*!40000 ALTER TABLE `emailedwaitlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `UIN` int(12) NOT NULL,
  `Salary` decimal(9,2) NOT NULL,
  `OfficeAddress` varchar(45) DEFAULT NULL,
  `OfficeHours` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UIN`),
  CONSTRAINT `EmpUIN` FOREIGN KEY (`UIN`) REFERENCES `people` (`UIN`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (272,40000.00,'to be decided','to be decided'),(273,40000.00,'to be decided','to be decided'),(274,40000.00,'to be decided','to be decided'),(275,40000.00,'to be decided','to be decided'),(276,40000.00,'to be decided','to be decided'),(277,40000.00,'to be decided','to be decided'),(278,40000.00,'to be decided','to be decided'),(279,40000.00,'to be decided','to be decided'),(280,40000.00,'to be decided','to be decided'),(281,40000.00,'to be decided','to be decided'),(282,40000.00,'to be decided','to be decided'),(283,40000.00,'to be decided','to be decided'),(284,40000.00,'to be decided','to be decided'),(285,40000.00,'to be decided','to be decided'),(286,40000.00,'to be decided','to be decided'),(287,40000.00,'to be decided','to be decided'),(288,40000.00,'to be decided','to be decided'),(289,40000.00,'to be decided','to be decided'),(290,40000.00,'to be decided','to be decided'),(291,40000.00,'to be decided','to be decided'),(292,40000.00,'to be decided','to be decided'),(293,40000.00,'to be decided','to be decided'),(294,40000.00,'to be decided','to be decided'),(295,40000.00,'to be decided','to be decided'),(296,40000.00,'to be decided','to be decided'),(297,40000.00,'to be decided','to be decided'),(298,40000.00,'to be decided','to be decided'),(299,40000.00,'to be decided','to be decided'),(300,40000.00,'to be decided','to be decided'),(301,40000.00,'to be decided','to be decided'),(302,40000.00,'to be decided','to be decided'),(303,40000.00,'to be decided','to be decided'),(304,40000.00,'to be decided','to be decided'),(305,40000.00,'to be decided','to be decided'),(306,40000.00,'to be decided','to be decided'),(307,40000.00,'to be decided','to be decided'),(308,40000.00,'to be decided','to be decided'),(309,40000.00,'to be decided','to be decided'),(310,40000.00,'to be decided','to be decided'),(311,40000.00,'to be decided','to be decided'),(312,40000.00,'to be decided','to be decided'),(313,40000.00,'to be decided','to be decided'),(314,40000.00,'to be decided','to be decided'),(315,40000.00,'to be decided','to be decided'),(316,40000.00,'to be decided','to be decided'),(317,40000.00,'to be decided','to be decided'),(318,40000.00,'to be decided','to be decided'),(319,40000.00,'to be decided','to be decided'),(320,40000.00,'to be decided','to be decided'),(321,40000.00,'to be decided','to be decided'),(322,40000.00,'to be decided','to be decided'),(323,40000.00,'to be decided','to be decided'),(324,40000.00,'to be decided','to be decided'),(325,40000.00,'to be decided','to be decided'),(326,40000.00,'to be decided','to be decided'),(327,40000.00,'to be decided','to be decided'),(328,40000.00,'to be decided','to be decided'),(329,40000.00,'to be decided','to be decided'),(330,40000.00,'to be decided','to be decided'),(331,40000.00,'to be decided','to be decided'),(332,40000.00,'to be decided','to be decided'),(333,40000.00,'to be decided','to be decided'),(334,40000.00,'to be decided','to be decided'),(335,40000.00,'to be decided','to be decided'),(336,40000.00,'to be decided','to be decided'),(337,40000.00,'to be decided','to be decided'),(338,40000.00,'to be decided','to be decided'),(339,40000.00,'to be decided','to be decided'),(340,40000.00,'to be decided','to be decided'),(341,40000.00,'to be decided','to be decided'),(342,40000.00,'to be decided','to be decided'),(343,40000.00,'to be decided','to be decided'),(344,40000.00,'to be decided','to be decided'),(345,40000.00,'to be decided','to be decided'),(346,40000.00,'to be decided','to be decided'),(347,40000.00,'to be decided','to be decided'),(348,40000.00,'to be decided','to be decided'),(349,40000.00,'to be decided','to be decided'),(350,40000.00,'to be decided','to be decided'),(351,40000.00,'to be decided','to be decided'),(352,40000.00,'to be decided','to be decided'),(353,40000.00,'to be decided','to be decided'),(354,40000.00,'to be decided','to be decided'),(541,40000.00,'to be decided','to be decided'),(542,40000.00,'to be decided','to be decided'),(543,40000.00,'to be decided','to be decided');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `FileID` int(12) NOT NULL AUTO_INCREMENT,
  `OfferID` int(12) NOT NULL,
  `FileLocation` varchar(45) NOT NULL,
  `FileName` varchar(45) NOT NULL,
  PRIMARY KEY (`FileID`),
  KEY `OfferID_idx` (`OfferID`),
  CONSTRAINT `FileOfferID` FOREIGN KEY (`OfferID`) REFERENCES `coursesoffered` (`OfferID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobpostings`
--

DROP TABLE IF EXISTS `jobpostings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobpostings` (
  `JobID` int(12) NOT NULL AUTO_INCREMENT,
  `PostedByUIN` int(12) NOT NULL,
  `JobInDepartment` int(12) NOT NULL,
  `ReqdMinimumGPA` int(11) DEFAULT NULL,
  `ReqdMinimumWorkExperience` int(11) DEFAULT NULL,
  `ReqdSkillset1` varchar(45) DEFAULT NULL,
  `ReqdSkillset2` varchar(45) DEFAULT NULL,
  `ReqdSkillset3` varchar(45) DEFAULT NULL,
  `ReqdSkillset4` varchar(45) DEFAULT NULL,
  `ReqdSkillset5` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`JobID`),
  UNIQUE KEY `PostedByUIN_UNIQUE` (`PostedByUIN`),
  KEY `PostedByUIN_idx` (`PostedByUIN`),
  KEY `JobInDepartment_idx` (`JobInDepartment`),
  CONSTRAINT `JobInDepartment` FOREIGN KEY (`JobInDepartment`) REFERENCES `department` (`DepartmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PostedByUIN` FOREIGN KEY (`PostedByUIN`) REFERENCES `people` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobpostings`
--

LOCK TABLES `jobpostings` WRITE;
/*!40000 ALTER TABLE `jobpostings` DISABLE KEYS */;
/*!40000 ALTER TABLE `jobpostings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logindetails`
--

DROP TABLE IF EXISTS `logindetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logindetails` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logindetails`
--

LOCK TABLES `logindetails` WRITE;
/*!40000 ALTER TABLE `logindetails` DISABLE KEYS */;
INSERT INTO `logindetails` VALUES ('aksha','NV9ZZq2a'),('alfon','8Sg3RNbM'),('alfon86','Mrf6OMem'),('allen','X2qlW02v'),('amber','oIip0ror'),('andre','EM2zshvN'),('anjol','9F5awCsK'),('arden','n2PIHv0j'),('arden81','P5amnu7O'),('arihant','arihant'),('ashel','E8AwbYPb'),('athen','8r5baxu6'),('athirk2','athirk2'),('augus','6XMRaZXv'),('austi','zYUAMbFv'),('autum','yi7FwFP1'),('baxte','z6mmKXPR'),('belle','lxH9qxHZ'),('bened','L5ZGyP0J'),('brend','hCUwwr3n'),('brody','Ks5HvF0G'),('burto','ifRkjZ2h'),('calli','g9uM2fa5'),('cara','AbnmIkW2'),('carla','gWrlxfkb'),('carso','sjmVW3Pp'),('celes','xEd5Pi8h'),('chanc','jAAzjWiS'),('chann','2YPw2GMJ'),('cheye','U7Bu54TK'),('clare','Yi06s2EU'),('color','BLncfxGP'),('conan','dsIBQYyg'),('cruz','wQx2V00U'),('culle','msZiE37j'),('cynth','MVyloQRX'),('dahli','OMKGjrim'),('dalto','rJdIyDtC'),('diete','pDGeAiKF'),('drake','hDWRGhVP'),('eliza','q7ohdgyz'),('elmo','spfiJxb8'),('emers','8PU9BypH'),('ferdi','nhLJ4r5a'),('finn','MmpuRRUg'),('fiona','sy0S31ug'),('garri','PuWG9MGH'),('gavin','i8kXx5Ho'),('grace','zlStgWlz'),('graha','bFqDSkB4'),('graid','jnOu6Qxs'),('gray','lLx5u56M'),('griff','36EY1eKv'),('guine','nHTWduaY'),('hadle','xgGwRj3W'),('hakee','dlwfr1pZ'),('hamil','Zy5YbHoC'),('hanae','kxOSv32U'),('hermi','izNGM2S4'),('hilla','4Cog0es8'),('hilla39','4XxQUBuR'),('hiram','XQWpRp01'),('hu','smwCs9tI'),('illia','x5GGyKDO'),('jamal','VW34bFK2'),('jana','xr6b1L23'),('jared','8unVpjuf'),('jeane','TgA1jfkp'),('jenna','RvQvBXLq'),('jerem','1WBhG9wE'),('jessi','weGqQ0OB'),('joan','fgWJRkXu'),('john','mPGNWki1'),('jorda','Er3FQl1E'),('jorda82','q13CRW7o'),('justi','BqGTOenf'),('kalia','tulChJtT'),('kasim','Y2MHAu6L'),('katel','JbGsGGC5'),('kathl','nSozWq2V'),('kato','2AkNPHxG'),('kenne','Zry402u3'),('kenyo','RuQsPWM8'),('kevyn','WyhpwRge'),('kiaya','LtQFzP0o'),('kyla','QQRUiyGb'),('kylie','7SM0haAf'),('kylyn','skKRMC9C'),('kyra','CxEsUito'),('lacot','mkZKRCxb'),('lacy','kYLxgJPH'),('larei','t3ml8i3x'),('leand','1YfTQc24'),('len','V2d2zrHb'),('lesle','9ESfk5ip'),('lilli','VNJ3nLrd'),('lisan','sraXAO4n'),('lucy','V7IW6fnT'),('magee','yOto02Zo'),('maggi','1uw6Ezig'),('maggi94','JXUBhWTs'),('maxin','LoFmOxEx'),('melod','rM2vijHG'),('melys','YM6NWK6l'),('natha','tTQtPY36'),('nell','CkoyMbqY'),('noel','UAO4SQdF'),('nolan','oW73qby3'),('ocean','zRwKxZi7'),('octav','mgNCjnSS'),('odyss','QVCc66fB'),('olga','Qaa6Hihh'),('olymp','ZPkMB7re'),('paula','uy0necfh'),('phela','5FMRFX9X'),('pnair','pnair'),('prana','HiFvNuaE'),('prana33','E6zS2mXj'),('priya','tDKlT4u5'),('quama','MIex0N4l'),('quinl','7T7USnFg'),('quon','Ki2r3z5h'),('rae','A2kgJmcE'),('ralph','yI5H5Hoq'),('ramon','G4BBF65t'),('raya','es2GxjI5'),('reube','XJivo1MB'),('rina','PjMUN7zm'),('rogan','8N7ppD6x'),('roone','ACOWpbpT'),('rosal','JdelJ4vf'),('rose','eZDiEekD'),('ruby','Acp5rcBX'),('ruth','JlWSDn9E'),('rylee','nnB8cVHR'),('sage','XFKfoJBV'),('sasha','I0P7UDVn'),('scarl','dQvdlt3w'),('sebas','Fy0MgoU1'),('seren','jXAQ9jrL'),('shana','LvbHKJ1S'),('shana90','YgCgCN9j'),('shea','KgL9tNlZ'),('signe','bovymfb2'),('siman','KyUu1Cx5'),('siman79','luMiTfdc'),('siman8','Z5NHhpEj'),('slade','5oZRcoaI'),('spuroh6','simant'),('stacy','XyywO6AM'),('stacy0','w6DeooZg'),('steph','4lHjl7af'),('steph38','5kUuxWGG'),('sydne','mJUmxNH8'),('sylve','sWyDRks7'),('sylvi','ANuQ0y10'),('tad','sAZSShEI'),('tate','l9695tab'),('tatia','nG6vgplB'),('taylo','YqUxFReR'),('thane','ZcjvLm0q'),('troy','KBxHj2fP'),('tyler','grJCdxXA'),('tyler50','Z7eX2NsP'),('ulyss','fjoK26QR'),('valen','x5Maun5j'),('vanna','6GIWKRot'),('wylie','1CnVitKa'),('wyomi','L2MLbCpp'),('xande','GjTJqYDN'),('yetta','NddEToBm'),('yolan','qgd30GDU'),('yvett','9vPfmAj6'),('zahir','pLsyF7n7'),('zelda','nyhhK2aM'),('zelen','XQPG9mre'),('zephr','xanrMaiR');
/*!40000 ALTER TABLE `logindetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `names`
--

DROP TABLE IF EXISTS `names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `names` (
  ` name` varchar(45) NOT NULL,
  PRIMARY KEY (` name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `names`
--

LOCK TABLES `names` WRITE;
/*!40000 ALTER TABLE `names` DISABLE KEYS */;
INSERT INTO `names` VALUES ('Addison'),('Akshay'),('Alfonso'),('Arden'),('Ashely'),('Athena'),('Austin'),('Belle'),('Brenda'),('Brent'),('Camilla'),('Cara'),('Carson'),('Celeste'),('Chancellor'),('Channing'),('Cheyenne'),('Colorado'),('Cullen'),('Dahlia'),('Elizabeth'),('Elmo'),('Ferdinand'),('Ferris'),('Finn'),('Fiona'),('Flynn'),('Garrison'),('Grace'),('Griffin'),('Hadley'),('Hamilton'),('Hammett'),('Hillary'),('Hiram'),('Hu'),('Illiana'),('Jamalia'),('Jeanette'),('Jenette'),('Jenna'),('Jessica'),('John'),('Jordan'),('Justina'),('Kato'),('Kay'),('Kenneth'),('Kyla'),('Lacota'),('Lance'),('Leandra'),('Lesley'),('Lillian'),('Lisandra'),('Magee'),('Maggie'),('Mason'),('Melyssa'),('Nathan'),('Neve'),('Ocean'),('Olga'),('Olympia'),('Paula'),('Phelan'),('Quamar'),('Quinlan'),('Ralph'),('Ramona'),('Rashad'),('Raya'),('Rina'),('Rogan'),('Rooney'),('Rosalyn'),('Rose'),('Ruby'),('Rylee'),('Sasha'),('Shana'),('Simant'),('Stacy'),('Stephen'),('Sylvia'),('Tate'),('Troy'),('Tyler'),('Valentine'),('Vanna'),('Wylie'),('Xander'),('Yetta'),('Zelenia'),('Zephr');
/*!40000 ALTER TABLE `names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `names1`
--

DROP TABLE IF EXISTS `names1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `names1` (
  `name1` varchar(45) NOT NULL,
  PRIMARY KEY (`name1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `names1`
--

LOCK TABLES `names1` WRITE;
/*!40000 ALTER TABLE `names1` DISABLE KEYS */;
INSERT INTO `names1` VALUES ('ACN647'),('AWG854'),('AWT688'),('AYL565'),('BAS355'),('BES361'),('BFE121'),('BGK574'),('BJN913'),('BJO897'),('BNL161'),('BYU517'),('CFH637'),('CJU378'),('COI967'),('CUO272'),('CZD316'),('DCW219'),('DEI726'),('DLC863'),('DUI521'),('EKU261'),('EUH316'),('EVO724'),('EXN476'),('FUW253'),('FVA985'),('GEN323'),('GRA957'),('GWE488'),('HKF596'),('HNR864'),('HPY579'),('HSB226'),('HSU797'),('HTF453'),('IHQ168'),('IOG144'),('IPQ551'),('IUV276'),('IXI832'),('JAS224'),('JFG544'),('JKG247'),('JLC841'),('JSF796'),('KBV581'),('KNF981'),('KRE544'),('LCQ179'),('LGL587'),('LMI869'),('LXJ479'),('LZC379'),('MFY237'),('NKD236'),('NOQ721'),('NZK993'),('OCD327'),('OGH935'),('OIM993'),('OQA728'),('PIJ316'),('PMA979'),('PRZ482'),('PZI766'),('QAB396'),('QAC981'),('QEK316'),('QFC158'),('QGZ526'),('QKX886'),('QRN954'),('QXS922'),('RAG356'),('RIT276'),('RRQ942'),('SAP913'),('SGN569'),('TQU338'),('UCL957'),('UMN352'),('UPM889'),('USX346'),('VBW291'),('VFO268'),('VKW781'),('WHM164'),('WOF376'),('WQB553'),('WSS172'),('WWH561'),('WZT983'),('XBW196'),('XWP374'),('YBR584'),('YQR985'),('YVH757'),('ZHR424'),('ZKV518');
/*!40000 ALTER TABLE `names1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `names2`
--

DROP TABLE IF EXISTS `names2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `names2` (
  `names2` varchar(45) NOT NULL,
  PRIMARY KEY (`names2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `names2`
--

LOCK TABLES `names2` WRITE;
/*!40000 ALTER TABLE `names2` DISABLE KEYS */;
INSERT INTO `names2` VALUES ('Alana'),('Alfonso'),('Allen'),('Amber'),('Andrew'),('Anjolie'),('Arden'),('Audra'),('August'),('Autumn'),('Baxter'),('Benedict'),('Brody'),('Burton'),('Callie'),('Carla'),('Charity'),('Chelsea'),('Clare'),('Coby'),('Conan'),('Cruz'),('Cynthia'),('Dalton'),('Dieter'),('Drake'),('Emerson'),('Faith'),('Gavin'),('Graham'),('Graiden'),('Gray'),('Guinevere'),('Hakeem'),('Hanae'),('Hannah'),('Hermione'),('Hillary'),('Illiana'),('Jana'),('Jared'),('Jeremy'),('Joan'),('Jordan'),('Kalia'),('Kasimir'),('Katell'),('Kathleen'),('Kenyon'),('Kevyn'),('Kiayada'),('Kylie'),('Kylynn'),('Kyra'),('Lacy'),('Lareina'),('Len'),('Leroy'),('Lucy'),('Macey'),('Maggie'),('Maggy'),('Maxine'),('Melodie'),('Nell'),('Noel'),('Nolan'),('Octavia'),('Odysseus'),('Quon'),('Rae'),('Reuben'),('Richard'),('Roth'),('Ruth'),('Sage'),('Scarlett'),('Sebastian'),('Serena'),('Shana'),('Shea'),('Signe'),('Slade'),('Stacy'),('Stephen'),('Sydney'),('Sylvester'),('Tad'),('Tanner'),('Tatiana'),('Taylor'),('Thane'),('Tyler'),('Ulysses'),('Wyoming'),('Yolanda'),('Yvette'),('Zahir'),('Zelda'),('Zia');
/*!40000 ALTER TABLE `names2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `UIN` int(12) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `DepartmentID` int(12) NOT NULL,
  `PositionID` int(12) NOT NULL,
  PRIMARY KEY (`UIN`),
  KEY `Username_idx` (`Username`),
  KEY `Department_idx` (`DepartmentID`),
  KEY `Position` (`PositionID`),
  CONSTRAINT `Department` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Position` FOREIGN KEY (`PositionID`) REFERENCES `position` (`PositionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Username` FOREIGN KEY (`Username`) REFERENCES `logindetails` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=544 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (1,'Simant','spuroh6',1,2),(2,'Akshay','athirk2',2,1),(3,'Prasad','pnair',3,3),(4,'Arihant','arihant',5,4),(272,'Akshay','aksha',2,2),(273,'Alfonso','alfon',7,2),(274,'Arden','arden',3,2),(275,'Ashely','ashel',8,2),(276,'Athena','athen',1,2),(277,'Austin','austi',1,2),(278,'Belle','belle',8,2),(279,'Brenda','brend',6,2),(280,'Cara','cara',7,2),(281,'Carson','carso',5,2),(282,'Celeste','celes',8,2),(283,'Chancellor','chanc',8,2),(284,'Channing','chann',5,2),(285,'Cheyenne','cheye',7,2),(286,'Colorado','color',5,2),(287,'Cullen','culle',7,2),(288,'Dahlia','dahli',6,2),(289,'Elizabeth','eliza',9,2),(290,'Elmo','elmo',5,2),(291,'Ferdinand','ferdi',6,2),(292,'Finn','finn',9,2),(293,'Fiona','fiona',6,2),(294,'Garrison','garri',4,2),(295,'Grace','grace',3,2),(296,'Griffin','griff',1,2),(297,'Hadley','hadle',4,2),(298,'Hamilton','hamil',1,2),(299,'Hillary','hilla',6,2),(300,'Hiram','hiram',5,2),(301,'Hu','hu',2,2),(302,'Illiana','illia',6,2),(303,'Jamalia','jamal',6,2),(304,'Jeanette','jeane',3,2),(305,'Jenna','jenna',8,2),(306,'Jessica','jessi',5,2),(307,'John','john',6,2),(308,'Jordan','jorda',4,2),(309,'Justina','justi',8,2),(310,'Kato','kato',8,2),(311,'Kenneth','kenne',9,2),(312,'Kyla','kyla',1,2),(313,'Lacota','lacot',9,2),(314,'Leandra','leand',9,2),(315,'Lesley','lesle',8,2),(316,'Lillian','lilli',7,2),(317,'Lisandra','lisan',8,2),(318,'Magee','magee',3,2),(319,'Maggie','maggi',1,2),(320,'Melyssa','melys',8,2),(321,'Nathan','natha',4,2),(322,'Ocean','ocean',6,2),(323,'Olga','olga',7,2),(324,'Olympia','olymp',5,2),(325,'Paula','paula',8,2),(326,'Phelan','phela',2,2),(327,'Quamar','quama',6,2),(328,'Quinlan','quinl',9,2),(329,'Ralph','ralph',9,2),(330,'Ramona','ramon',2,2),(331,'Raya','raya',6,2),(332,'Rina','rina',3,2),(333,'Rogan','rogan',7,2),(334,'Rooney','roone',7,2),(335,'Rosalyn','rosal',1,2),(336,'Rose','rose',4,2),(337,'Ruby','ruby',4,2),(338,'Rylee','rylee',8,2),(339,'Sasha','sasha',9,2),(340,'Shana','shana',8,2),(341,'Simant','siman',8,2),(342,'Stacy','stacy',5,2),(343,'Stephen','steph',4,2),(344,'Sylvia','sylvi',5,2),(345,'Tate','tate',1,2),(346,'Troy','troy',3,2),(347,'Tyler','tyler',6,2),(348,'Valentine','valen',5,2),(349,'Vanna','vanna',4,2),(350,'Wylie','wylie',8,2),(351,'Xander','xande',1,2),(352,'Yetta','yetta',2,2),(353,'Zelenia','zelen',8,2),(354,'Zephr','zephr',7,2),(449,'Alfonso','alfon86',7,3),(450,'Allen','allen',5,3),(451,'Amber','amber',4,3),(452,'Andrew','andre',4,3),(453,'Anjolie','anjol',7,3),(454,'Arden','arden81',1,3),(455,'August','augus',8,3),(456,'Autumn','autum',6,3),(457,'Baxter','baxte',8,3),(458,'Benedict','bened',8,3),(459,'Brody','brody',7,3),(460,'Burton','burto',4,3),(461,'Callie','calli',8,3),(462,'Carla','carla',6,3),(463,'Clare','clare',6,3),(464,'Conan','conan',3,3),(465,'Cruz','cruz',4,3),(466,'Cynthia','cynth',5,3),(467,'Dalton','dalto',9,3),(468,'Dieter','diete',2,3),(469,'Drake','drake',7,3),(470,'Emerson','emers',9,3),(471,'Gavin','gavin',9,3),(472,'Graham','graha',2,3),(473,'Graiden','graid',2,3),(474,'Gray','gray',7,3),(475,'Guinevere','guine',2,3),(476,'Hakeem','hakee',9,3),(477,'Hanae','hanae',9,3),(478,'Hermione','hermi',9,3),(479,'Hillary','hilla39',1,3),(480,'Jana','jana',2,3),(481,'Jared','jared',9,3),(482,'Jeremy','jerem',8,3),(483,'Joan','joan',4,3),(484,'Jordan','jorda82',1,3),(485,'Kalia','kalia',1,3),(486,'Kasimir','kasim',7,3),(487,'Katell','katel',1,3),(488,'Kathleen','kathl',2,3),(489,'Kenyon','kenyo',2,3),(490,'Kevyn','kevyn',5,3),(491,'Kiayada','kiaya',9,3),(492,'Kylie','kylie',8,3),(493,'Kylynn','kylyn',2,3),(494,'Kyra','kyra',8,3),(495,'Lacy','lacy',4,3),(496,'Lareina','larei',1,3),(497,'Len','len',2,3),(498,'Lucy','lucy',2,3),(499,'Maggie','maggi94',4,3),(500,'Maxine','maxin',3,3),(501,'Melodie','melod',9,3),(502,'Nell','nell',6,3),(503,'Noel','noel',9,3),(504,'Nolan','nolan',7,3),(505,'Octavia','octav',5,3),(506,'Odysseus','odyss',1,3),(507,'Quon','quon',2,3),(508,'Rae','rae',5,3),(509,'Reuben','reube',4,3),(510,'Ruth','ruth',1,3),(511,'Sage','sage',5,3),(512,'Scarlett','scarl',3,3),(513,'Sebastian','sebas',9,3),(514,'Serena','seren',6,3),(515,'Shana','shana90',9,3),(516,'Shea','shea',1,3),(517,'Signe','signe',7,3),(518,'Slade','slade',9,3),(519,'Stacy','stacy0',1,3),(520,'Stephen','steph38',7,3),(521,'Sydney','sydne',3,3),(522,'Sylvester','sylve',7,3),(523,'Tad','tad',6,3),(524,'Tatiana','tatia',4,3),(525,'Taylor','taylo',2,3),(526,'Thane','thane',4,3),(527,'Tyler','tyler50',9,3),(528,'Ulysses','ulyss',7,3),(529,'Wyoming','wyomi',6,3),(530,'Yolanda','yolan',8,3),(531,'Yvette','yvett',9,3),(532,'Zahir','zahir',2,3),(533,'Zelda','zelda',1,3),(537,'Pranav','prana',1,3),(538,'Pranav','prana33',1,3),(541,'Simant Purohit','siman79',1,2),(542,'priyanka','priya',2,2),(543,'Simant','siman8',2,2);
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `PositionID` int(12) NOT NULL AUTO_INCREMENT,
  `PositionName` varchar(45) NOT NULL,
  `PositionLevel` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`PositionID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Admin','1'),(2,'Professor','2'),(3,'TA','4'),(4,'Student','3');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester` (
  `SemesterID` int(12) NOT NULL,
  `SemesterName` varchar(45) NOT NULL,
  `SemesterYear` year(4) NOT NULL,
  `IsCurrent` int(12) NOT NULL,
  PRIMARY KEY (`SemesterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,'FALL',2013,1),(2,'SPRING',2013,0),(3,'SUMMER',2013,0);
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skillsetlist`
--

DROP TABLE IF EXISTS `skillsetlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skillsetlist` (
  `SkillsetID` int(12) NOT NULL AUTO_INCREMENT,
  `SkillsetName` varchar(45) NOT NULL,
  PRIMARY KEY (`SkillsetID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skillsetlist`
--

LOCK TABLES `skillsetlist` WRITE;
/*!40000 ALTER TABLE `skillsetlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `skillsetlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `UIN` int(12) NOT NULL,
  `GPA` decimal(3,2) NOT NULL DEFAULT '4.00',
  `Level` varchar(2) NOT NULL,
  PRIMARY KEY (`UIN`),
  KEY `UIN_idx` (`UIN`),
  CONSTRAINT `UIN` FOREIGN KEY (`UIN`) REFERENCES `people` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,3.00,'1'),(449,4.00,'1'),(450,4.00,'2'),(451,4.00,'1'),(452,4.00,'3'),(453,4.00,'3'),(454,4.00,'1'),(455,4.00,'2'),(457,4.00,'2'),(459,4.00,'3'),(461,4.00,'3'),(463,4.00,'2'),(464,4.00,'2'),(465,4.00,'1'),(468,4.00,'3'),(469,4.00,'2'),(470,4.00,'2'),(471,4.00,'2'),(473,4.00,'2'),(474,4.00,'3'),(475,4.00,'3'),(476,4.00,'1'),(478,4.00,'2'),(480,4.00,'1'),(481,4.00,'2'),(482,4.00,'3'),(483,4.00,'1'),(484,4.00,'1'),(485,4.00,'1'),(486,4.00,'3'),(488,4.00,'3'),(489,4.00,'1'),(490,4.00,'3'),(491,4.00,'2'),(492,4.00,'1'),(494,4.00,'1'),(495,4.00,'1'),(496,4.00,'3'),(497,4.00,'1'),(498,4.00,'2'),(499,4.00,'1'),(500,4.00,'3'),(501,4.00,'3'),(503,4.00,'2'),(504,4.00,'2'),(507,4.00,'2'),(508,4.00,'3'),(509,4.00,'3'),(511,4.00,'1'),(512,4.00,'1'),(513,4.00,'3'),(514,4.00,'3'),(515,4.00,'1'),(517,4.00,'1'),(518,4.00,'2'),(519,4.00,'1'),(520,4.00,'2'),(521,4.00,'2'),(522,4.00,'3'),(524,4.00,'3'),(526,4.00,'3'),(527,4.00,'2'),(532,4.00,'1'),(533,4.00,'1'),(537,4.00,'1'),(538,4.00,'1');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentenrollment`
--

DROP TABLE IF EXISTS `studentenrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentenrollment` (
  `EnrollmentID` int(12) NOT NULL AUTO_INCREMENT,
  `UIN` int(12) NOT NULL,
  `OfferID` int(12) NOT NULL,
  `Status` varchar(10) NOT NULL,
  PRIMARY KEY (`EnrollmentID`),
  KEY `OfferID_idx` (`OfferID`),
  KEY `UIN_idx` (`UIN`),
  CONSTRAINT `OfferID` FOREIGN KEY (`OfferID`) REFERENCES `coursesoffered` (`OfferID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `StudentUIN` FOREIGN KEY (`UIN`) REFERENCES `student` (`UIN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=296 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentenrollment`
--

LOCK TABLES `studentenrollment` WRITE;
/*!40000 ALTER TABLE `studentenrollment` DISABLE KEYS */;
INSERT INTO `studentenrollment` VALUES (295,451,295,'TBD');
/*!40000 ALTER TABLE `studentenrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timeslots`
--

DROP TABLE IF EXISTS `timeslots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timeslots` (
  `TimeSlotID` int(12) NOT NULL AUTO_INCREMENT,
  `StartHour` int(2) NOT NULL,
  `EndHour` int(2) NOT NULL,
  `TimeslotType` int(1) NOT NULL,
  PRIMARY KEY (`TimeSlotID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timeslots`
--

LOCK TABLES `timeslots` WRITE;
/*!40000 ALTER TABLE `timeslots` DISABLE KEYS */;
INSERT INTO `timeslots` VALUES (16,6,7,1),(17,7,8,1),(18,8,9,1),(19,9,10,1),(20,10,11,1),(21,11,12,1),(22,12,13,1),(23,13,14,1),(24,14,15,1),(25,15,16,1),(26,16,17,1),(27,17,18,1),(28,18,19,1),(29,19,20,1),(30,20,21,1),(31,6,8,2),(32,8,10,2),(33,10,12,2),(34,12,14,2),(35,14,16,2),(36,16,18,2),(37,18,20,2),(38,7,9,2),(39,9,11,2);
/*!40000 ALTER TABLE `timeslots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waitlist`
--

DROP TABLE IF EXISTS `waitlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `waitlist` (
  `UIN` int(12) NOT NULL,
  `OfferID` int(12) NOT NULL,
  `QueuePos` int(12) NOT NULL,
  PRIMARY KEY (`UIN`,`OfferID`,`QueuePos`),
  KEY `WaitOfferID_idx` (`OfferID`),
  CONSTRAINT `WaitIUIN` FOREIGN KEY (`UIN`) REFERENCES `people` (`UIN`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `WaitOfferID` FOREIGN KEY (`OfferID`) REFERENCES `coursesoffered` (`OfferID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waitlist`
--

LOCK TABLES `waitlist` WRITE;
/*!40000 ALTER TABLE `waitlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `waitlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-19  2:38:43
