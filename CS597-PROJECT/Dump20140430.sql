CREATE DATABASE  IF NOT EXISTS `university` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `university`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: university
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `acn6474221`
--

DROP TABLE IF EXISTS `acn6474221`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acn6474221` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `ACN6474221studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ACN6474221studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acn6474221`
--

LOCK TABLES `acn6474221` WRITE;
/*!40000 ALTER TABLE `acn6474221` DISABLE KEYS */;
INSERT INTO `acn6474221` VALUES (710,61,0.0,0.0,0.0),(819,60,0.0,0.0,0.0),(843,58,0.0,0.0,0.0),(926,62,0.0,0.0,0.0),(953,59,0.0,0.0,0.0);
/*!40000 ALTER TABLE `acn6474221` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acn6474221structure`
--

DROP TABLE IF EXISTS `acn6474221structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acn6474221structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acn6474221structure`
--

LOCK TABLES `acn6474221structure` WRITE;
/*!40000 ALTER TABLE `acn6474221structure` DISABLE KEYS */;
INSERT INTO `acn6474221structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `acn6474221structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adc4724381`
--

DROP TABLE IF EXISTS `adc4724381`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adc4724381` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `ADC4724381studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ADC4724381studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adc4724381`
--

LOCK TABLES `adc4724381` WRITE;
/*!40000 ALTER TABLE `adc4724381` DISABLE KEYS */;
INSERT INTO `adc4724381` VALUES (799,139,0.0,0.0,0.0),(844,142,0.0,0.0,0.0),(849,140,0.0,0.0,0.0),(901,141,0.0,0.0,0.0),(993,138,0.0,0.0,0.0);
/*!40000 ALTER TABLE `adc4724381` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adc4724381structure`
--

DROP TABLE IF EXISTS `adc4724381structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adc4724381structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adc4724381structure`
--

LOCK TABLES `adc4724381structure` WRITE;
/*!40000 ALTER TABLE `adc4724381structure` DISABLE KEYS */;
INSERT INTO `adc4724381structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `adc4724381structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aiy4034421`
--

DROP TABLE IF EXISTS `aiy4034421`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aiy4034421` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `AIY4034421studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `AIY4034421studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aiy4034421`
--

LOCK TABLES `aiy4034421` WRITE;
/*!40000 ALTER TABLE `aiy4034421` DISABLE KEYS */;
INSERT INTO `aiy4034421` VALUES (709,160,0.0,0.0,0.0),(796,159,0.0,0.0,0.0),(831,161,0.0,0.0,0.0),(885,158,0.0,0.0,0.0),(943,162,0.0,0.0,0.0);
/*!40000 ALTER TABLE `aiy4034421` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aiy4034421structure`
--

DROP TABLE IF EXISTS `aiy4034421structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aiy4034421structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aiy4034421structure`
--

LOCK TABLES `aiy4034421structure` WRITE;
/*!40000 ALTER TABLE `aiy4034421structure` DISABLE KEYS */;
INSERT INTO `aiy4034421structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `aiy4034421structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aml4344431`
--

DROP TABLE IF EXISTS `aml4344431`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aml4344431` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `AML4344431studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `AML4344431studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aml4344431`
--

LOCK TABLES `aml4344431` WRITE;
/*!40000 ALTER TABLE `aml4344431` DISABLE KEYS */;
INSERT INTO `aml4344431` VALUES (743,166,0.0,0.0,0.0),(846,163,0.0,0.0,0.0),(896,167,0.0,0.0,0.0),(898,164,0.0,0.0,0.0),(972,165,0.0,0.0,0.0);
/*!40000 ALTER TABLE `aml4344431` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aml4344431structure`
--

DROP TABLE IF EXISTS `aml4344431structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aml4344431structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aml4344431structure`
--

LOCK TABLES `aml4344431structure` WRITE;
/*!40000 ALTER TABLE `aml4344431structure` DISABLE KEYS */;
INSERT INTO `aml4344431structure` VALUES ('assign1',30),('assign2',30),('assign3',30);
/*!40000 ALTER TABLE `aml4344431structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationdetails`
--

DROP TABLE IF EXISTS `applicationdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationdetails` (
  `ApplicationID` int(12) NOT NULL AUTO_INCREMENT,
  `ApplicantUIN` int(12) NOT NULL,
  `WorkExperience` double DEFAULT '0',
  `Skillset1` varchar(45) DEFAULT NULL,
  `Skillset2` varchar(45) DEFAULT NULL,
  `Skillset3` varchar(45) DEFAULT NULL,
  `Skillset4` varchar(45) DEFAULT NULL,
  `Skillset5` varchar(45) DEFAULT NULL,
  `Scaledscore` double DEFAULT NULL,
  PRIMARY KEY (`ApplicationID`),
  UNIQUE KEY `ApplicantUIN_UNIQUE` (`ApplicantUIN`),
  KEY `ApplicantUIN_idx` (`ApplicantUIN`),
  CONSTRAINT `ApplicantUIN` FOREIGN KEY (`ApplicantUIN`) REFERENCES `people` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationdetails`
--

LOCK TABLES `applicationdetails` WRITE;
/*!40000 ALTER TABLE `applicationdetails` DISABLE KEYS */;
INSERT INTO `applicationdetails` VALUES (15,600,4,'1','1','1','1','1',10),(16,602,1.5,'1','0','0','0','0',5.25),(17,589,1.5,'1','0','0','0','0',5.25),(18,585,1.5,'1','0','0','0','0',5.25),(19,598,1.5,'1','0','0','0','0',5.25),(20,831,2.5,'0','0','1','0','1',6.75),(21,865,2,'0','0','0','1','1',6.5),(22,838,2.5,'0','1','0','0','0',5.75),(23,776,2.5,'1','1','1','1','1',9.75),(24,772,1,'0','1','0','1','0',6.25),(25,882,4,'0','1','1','1','0',8),(26,913,2.5,'0','1','1','1','0',7.75),(27,965,1,'1','1','1','1','0',8.25),(28,967,1,'0','0','0','1','1',6.25),(29,809,3,'0','1','0','1','0',7),(30,798,1,'0','1','0','1','0',6.25),(31,716,1.5,'1','0','0','0','1',6.25),(32,991,2,'1','0','0','1','0',6.5),(33,912,2,'1','0','1','1','0',7.5),(34,885,3,'0','0','0','0','0',5),(35,730,4,'0','0','1','0','0',6),(36,876,3.5,'1','0','0','0','1',7),(37,712,2,'1','0','0','1','0',6.5),(38,812,2,'1','1','1','1','0',8.5),(39,813,2.5,'0','1','1','1','0',7.75),(40,903,3.5,'1','0','0','0','0',6),(41,761,2,'0','0','0','0','1',5.5),(42,899,1.5,'1','0','1','0','1',7.25),(43,711,1.5,'0','1','1','0','1',7.25),(44,883,3,'1','0','0','0','1',7),(45,723,3.5,'0','0','0','1','1',7),(46,601,2.5,'0','0','0','1','1',6.75),(47,926,1.5,'1','1','0','0','1',7.25),(48,928,3,'1','0','1','1','1',9),(49,943,1.5,'0','0','1','1','0',6.25),(50,741,1,'0','0','1','1','1',7.25),(51,921,3.5,'1','0','1','0','0',7),(52,781,3.5,'0','1','0','1','1',8),(53,729,1,'0','0','1','1','1',7.25),(54,886,3,'0','0','0','1','1',7),(55,835,3,'0','0','1','0','1',7),(56,715,3.5,'1','1','1','1','0',9),(57,857,1.5,'0','0','0','0','1',5.25),(58,796,4,'1','1','1','0','0',8),(59,909,3.5,'1','1','1','0','1',9),(60,970,1,'1','0','0','0','1',6.25),(61,860,1.5,'1','0','0','1','1',7.25),(62,940,4,'1','0','0','0','1',7),(63,933,2.5,'1','1','1','0','1',8.75),(64,941,2,'0','1','0','1','0',6.5),(65,736,4,'1','1','0','0','0',7),(66,842,3,'1','0','1','1','1',9),(67,945,2,'1','1','0','1','1',8.5),(68,587,2.5,'0','1','0','0','0',5.75),(69,948,2,'0','1','0','1','0',6.5),(70,953,4,'0','0','0','0','1',6),(71,908,2.5,'0','1','0','1','1',7.75),(72,858,3.5,'1','1','0','0','0',7),(73,819,2.5,'1','1','1','0','0',7.75),(74,725,1,'0','1','1','0','1',7.25),(75,773,3,'0','1','1','0','0',7),(76,917,2.5,'0','1','0','1','0',6.75),(77,954,2,'1','0','1','1','0',7.5),(78,756,2,'0','0','0','1','1',6.5),(79,822,3,'0','1','0','0','0',6),(80,827,1.5,'1','0','1','0','1',7.25),(81,733,1.5,'1','0','0','1','1',7.25),(82,731,4,'0','1','0','0','0',6),(83,997,3,'1','1','0','0','0',7),(84,884,1,'1','1','1','1','1',9.25),(85,949,2,'0','0','0','0','0',4.5),(86,726,2,'1','1','1','1','1',9.5),(87,751,3.5,'0','1','0','1','0',7),(88,752,2,'1','1','1','0','1',8.5),(89,951,3,'0','1','0','0','1',7),(90,897,2,'0','0','1','0','0',5.5),(91,962,1,'1','1','0','1','1',8.25),(92,744,1.5,'0','0','1','1','0',6.25),(93,845,2,'0','0','1','0','0',5.5),(94,925,1.5,'1','1','1','1','1',9.25),(95,817,1.5,'0','0','0','0','1',5.25),(96,732,3.5,'0','0','0','1','1',7),(97,996,4,'0','0','0','1','0',6),(98,719,2,'0','0','1','0','1',6.5),(99,800,1.5,'1','0','0','1','1',7.25),(100,828,3.5,'1','0','0','0','0',6),(101,868,4,'0','1','0','0','0',6),(102,706,1,'1','1','1','0','0',7.25),(103,793,1.5,'1','1','1','0','0',7.25),(104,836,4,'0','1','0','1','0',7),(105,720,4,'0','1','1','1','1',9),(106,986,3,'0','0','0','1','0',6),(107,745,3.5,'1','0','0','1','1',8),(108,959,1,'1','1','0','1','0',7.25),(109,754,2.5,'0','0','0','0','0',4.75),(110,811,2.5,'1','1','1','1','1',9.75),(111,840,4,'0','0','0','0','0',5),(112,950,3.5,'0','0','1','1','1',8),(113,902,2,'0','0','1','0','0',5.5),(114,861,3.5,'1','0','0','0','1',7),(115,790,1,'1','0','1','0','1',7.25),(116,816,4,'1','1','0','0','1',8),(117,900,4,'1','0','1','0','0',7),(118,735,2.5,'0','0','1','0','1',6.75),(119,872,1,'0','1','1','1','1',8.25),(120,837,2,'1','0','1','0','1',7.5),(121,853,4,'0','1','1','1','0',8),(122,855,3.5,'0','0','0','1','0',6),(123,834,1.5,'1','1','0','0','0',6.25),(124,767,1,'1','0','0','0','1',6.25),(125,866,3,'0','0','0','0','1',6),(126,871,2,'1','0','1','0','1',7.5),(127,896,1.5,'0','0','1','1','1',7.25),(128,769,1,'0','1','0','1','0',6.25),(129,739,1.5,'1','1','1','1','1',9.25),(130,823,4,'1','1','0','0','1',8),(131,820,3.5,'1','1','1','1','0',9),(132,987,2,'1','1','1','0','1',8.5),(133,863,3.5,'1','0','1','1','1',9),(134,844,3.5,'1','0','1','0','0',7),(135,874,4,'0','1','0','0','1',7),(136,738,1,'0','1','0','1','0',6.25),(137,806,2,'1','0','0','0','0',5.5),(138,584,3.5,'0','0','0','0','0',4.15),(139,713,4,'0','1','0','0','1',7),(140,758,3.5,'1','1','1','0','0',8),(141,807,4,'1','1','0','0','0',7),(142,936,3,'0','1','0','0','1',7),(143,978,3.5,'1','1','0','1','1',9),(144,888,1,'0','0','1','1','0',6.25),(145,955,4,'0','1','1','1','0',8),(146,864,3,'1','1','1','0','0',8),(147,892,3,'0','1','0','1','0',7),(148,911,4,'0','0','1','0','0',6),(149,737,4,'0','0','0','1','0',6),(150,961,2,'1','0','1','1','0',7.5),(151,846,1.5,'0','1','1','1','0',7.25),(152,907,2.5,'0','0','1','0','0',5.75),(153,762,1.5,'0','0','1','1','0',6.25),(154,966,3.5,'1','1','0','1','0',8),(155,983,2.5,'0','0','0','0','0',4.75),(156,969,1.5,'0','0','1','1','0',6.25),(157,848,2.5,'1','1','1','0','0',7.75),(158,938,2.5,'1','0','0','1','0',6.75),(159,717,3.5,'1','0','0','0','1',7),(160,973,1,'1','0','1','0','0',6.25),(161,770,2,'0','0','1','0','1',6.5),(162,889,2.5,'0','1','0','0','1',6.75),(163,710,3.5,'1','1','0','1','0',8),(164,881,1.5,'1','0','0','1','1',7.25),(165,708,1.5,'0','0','0','0','0',4.25),(166,753,3,'1','1','0','1','0',8),(167,887,3.5,'0','1','0','1','0',7);
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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,'CLASS1','LOCATION1',8),(2,'CLASS2','LOCATION1',8),(3,'CLASS3','LOCATION1',8),(4,'CLASS4','LOCATION1',8),(5,'CLASS5','LOCATION1',8),(6,'CLASS6','LOCATION1',8),(7,'CLASS7','LOCATION1',8),(8,'CLASS8','LOCATION1',8),(9,'CLASS9','LOCATION1',8),(10,'CLASS10','LOCATION1',8),(25,'CLASS1','LOCATION2',10),(26,'CLASS2','LOCATION2',10),(27,'CLASS3','LOCATION2',10),(28,'CLASS4','LOCATION2',10),(29,'CLASS5','LOCATION2',10),(30,'CLASS6','LOCATION2',10),(31,'CLASS7','LOCATION2',10),(32,'CLASS8','LOCATION2',10),(33,'CLASS9','LOCATION2',10),(34,'CLASS10','LOCATION2',10),(49,'CLASS1','LOCATION3',5),(50,'CLASS2','LOCATION3',5),(51,'CLASS3','LOCATION3',5),(52,'CLASS4','LOCATION3',5),(53,'CLASS5','LOCATION3',5),(54,'CLASS6','LOCATION3',5),(55,'CLASS7','LOCATION3',5),(56,'CLASS8','LOCATION3',5),(57,'CLASS9','LOCATION3',5),(58,'CLASS10','LOCATION3',5);
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
) ENGINE=InnoDB AUTO_INCREMENT=312 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (102,'CS300',1),(103,'CS301',1),(104,'CS480',1),(105,'CS422',1),(106,'CS502',1),(107,'MEC101',18),(108,'MEC222',18),(109,'MEC550',18),(110,'MEC410',18),(111,'MEC110',18),(130,'BNL161',18),(153,'EID585',18),(159,'FDX848',18),(181,'HSB226',18),(200,'JSF796',18),(219,'MKP842',18),(220,'MPE415',18),(221,'MSZ380',18),(235,'PBI352',18),(250,'QKX886',18),(274,'UPM889',18),(287,'WPA767',18),(289,'WRM334',18),(299,'YGC819',18),(302,'YQN172',18),(112,'ACN647',19),(126,'BFE121',19),(132,'BZJ525',19),(152,'EAY297',19),(155,'ETB561',19),(179,'HPY579',19),(201,'JXD982',19),(203,'KNF981',19),(213,'LXJ479',19),(218,'MJL110',19),(222,'NKD236',19),(243,'QAB396',19),(259,'SAP913',19),(261,'SGN569',19),(267,'TQU338',19),(278,'VFO268',19),(279,'VKW781',19),(292,'WWH561',19),(307,'ZBR597',19),(147,'DRL138',20),(148,'DTH282',20),(151,'DUI521',20),(182,'HSU797',20),(189,'IPQ551',20),(192,'IWA519',20),(204,'KRE544',20),(207,'LFC440',20),(215,'LZC379',20),(216,'MFY237',20),(223,'NKF371',20),(227,'NZK993',20),(230,'OIM993',20),(231,'ONB921',20),(236,'PER336',20),(237,'PIJ316',20),(244,'QAC981',20),(245,'QEK316',20),(247,'QGZ526',20),(256,'RDR752',20),(270,'UCL957',20),(306,'YYN687',20),(121,'AXX174',21),(133,'CFH637',21),(136,'CSZ973',21),(137,'CUO272',21),(143,'DFK369',21),(149,'DTW249',21),(156,'EUH316',21),(164,'FNV482',21),(168,'GEN323',21),(170,'GQA430',21),(177,'HNR864',21),(183,'HTF453',21),(193,'IXI832',21),(196,'JJI785',21),(202,'KBV581',21),(210,'LJW398',21),(211,'LMI869',21),(214,'LXY445',21),(249,'QKK858',21),(280,'VSF146',21),(283,'WEZ124',21),(309,'ZKV518',21),(116,'AIY910',22),(120,'AWT688',22),(123,'AYW223',22),(124,'BAS355',22),(134,'CJU378',22),(140,'DCW219',22),(166,'FVA985',22),(188,'IOG144',22),(190,'IUT840',22),(225,'NQS618',22),(232,'OQA728',22),(238,'PMA979',22),(241,'PXD739',22),(252,'QSX315',22),(258,'RRQ942',22),(260,'SEC333',22),(281,'VXX409',22),(294,'WYN174',22),(295,'WZT983',22),(296,'XBW196',22),(113,'ADA612',23),(122,'AYL565',23),(125,'BES361',23),(128,'BJN913',23),(131,'BYU517',23),(141,'DDC448',23),(144,'DFZ953',23),(157,'EVO724',23),(161,'FFU695',23),(169,'GEY256',23),(171,'GRA957',23),(173,'GST781',23),(175,'GYV483',23),(178,'HNY179',23),(187,'INM716',23),(191,'IUV276',23),(194,'JAS224',23),(198,'JLC841',23),(199,'JNI477',23),(226,'NRC941',23),(239,'PQQ865',23),(248,'QHR384',23),(254,'QXS922',23),(255,'RAG356',23),(264,'TEE732',23),(268,'TUE852',23),(273,'UMN352',23),(275,'USN389',23),(282,'WDN111',23),(288,'WQB553',23),(304,'YTH757',23),(305,'YVH757',23),(311,'ZYX261',23),(114,'ADC472',24),(119,'AWG854',24),(129,'BJO897',24),(142,'DEI726',24),(146,'DLC863',24),(158,'EXN476',24),(172,'GSQ119',24),(180,'HQA204',24),(185,'IHC888',24),(186,'IHQ168',24),(197,'JKG247',24),(206,'LCQ179',24),(208,'LFF407',24),(224,'NOQ721',24),(253,'QVQ983',24),(265,'TIQ275',24),(266,'TNI947',24),(271,'UIV136',24),(272,'UJU965',24),(284,'WHM164',24),(300,'YJG561',24),(303,'YQR985',24),(310,'ZSL643',24),(138,'CUV308',25),(139,'CZD316',25),(145,'DIQ792',25),(160,'FFC874',25),(163,'FMV316',25),(167,'FVX421',25),(184,'ICN499',25),(217,'MIV243',25),(228,'OCD327',25),(229,'OGH935',25),(234,'ORN925',25),(240,'PRZ482',25),(285,'WHQ226',25),(298,'YBR584',25),(115,'AIY403',26),(117,'AML434',26),(118,'ATF941',26),(127,'BGK574',26),(135,'COI967',26),(150,'DUD354',26),(154,'EKU261',26),(162,'FIA908',26),(165,'FUW253',26),(174,'GWE488',26),(176,'HKF596',26),(195,'JFG544',26),(205,'KTY725',26),(209,'LGL587',26),(212,'LWX305',26),(233,'OQS850',26),(242,'PZI766',26),(246,'QFC158',26),(251,'QRN954',26),(257,'RIT276',26),(262,'SYV320',26),(263,'TCM760',26),(269,'TVG979',26),(276,'USX346',26),(277,'VBW291',26),(286,'WOF376',26),(290,'WSS172',26),(291,'WVH955',26),(293,'WYM851',26),(297,'XWP374',26),(301,'YNP380',26),(308,'ZHR424',26);
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
INSERT INTO `courseschedule` VALUES (410,24,10),(411,26,28),(414,22,2),(415,26,10),(416,25,10),(417,18,28),(418,19,5),(419,29,10),(420,23,33),(421,30,1),(422,28,1),(423,26,27),(424,28,10),(425,18,2),(426,21,27),(427,27,5),(428,18,10),(429,18,25),(430,23,7),(431,23,9),(432,23,32),(433,17,5),(434,21,3),(435,29,34),(436,23,5),(437,24,6),(438,23,27),(439,27,6),(440,16,7),(441,28,31),(442,16,3),(443,21,4),(444,17,25),(445,24,7),(446,16,10),(447,27,28),(448,20,7),(449,20,6),(450,20,29),(451,18,5),(452,17,1),(453,30,31),(454,27,2),(455,20,8),(456,21,34),(457,21,9),(458,30,4),(459,28,29);
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
) ENGINE=InnoDB AUTO_INCREMENT=460 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursesoffered`
--

LOCK TABLES `coursesoffered` WRITE;
/*!40000 ALTER TABLE `coursesoffered` DISABLE KEYS */;
INSERT INTO `coursesoffered` VALUES (410,102,1,5,5,582),(411,103,1,10,5,590),(414,107,1,5,5,597),(415,103,1,5,5,583),(416,106,1,8,5,582),(417,102,1,10,5,591),(418,111,1,5,5,670),(419,107,1,8,5,676),(420,181,1,10,5,595),(421,259,1,5,5,657),(422,112,1,8,5,641),(423,203,1,10,5,640),(424,306,1,5,5,690),(425,247,1,8,5,702),(426,151,1,10,5,667),(427,168,1,5,5,672),(428,143,1,8,5,638),(429,280,1,10,5,650),(430,232,1,5,5,686),(431,225,1,8,5,643),(432,140,1,10,5,633),(433,191,1,5,5,693),(434,169,1,8,5,685),(435,161,1,10,5,647),(436,284,1,5,5,635),(437,265,1,8,5,637),(438,114,1,10,5,614),(439,139,1,5,5,703),(440,228,1,8,5,688),(441,229,1,10,5,661),(442,115,1,5,5,654),(443,117,1,8,5,674),(444,293,1,10,5,628),(445,104,1,5,5,592),(446,103,1,8,5,591),(447,102,1,10,5,583),(448,235,1,5,5,593),(449,153,1,8,5,596),(450,221,1,10,5,617),(451,152,1,5,5,631),(452,179,1,8,5,694),(453,155,1,10,5,697),(454,215,1,5,5,667),(455,151,1,8,5,702),(456,189,1,10,5,690),(457,149,1,5,5,649),(458,202,1,8,5,623),(459,164,1,10,5,669);
/*!40000 ALTER TABLE `coursesoffered` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3004101`
--

DROP TABLE IF EXISTS `cs3004101`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3004101` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `Assign1` decimal(4,1) DEFAULT '0.0',
  `Assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `CS3004101studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CS3004101studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3004101`
--

LOCK TABLES `cs3004101` WRITE;
/*!40000 ALTER TABLE `cs3004101` DISABLE KEYS */;
INSERT INTO `cs3004101` VALUES (584,7,20.0,30.0,0.0),(589,10,15.0,10.0,0.0),(598,12,20.0,50.0,0.0),(600,6,10.0,40.0,0.0),(602,13,5.0,20.0,0.0);
/*!40000 ALTER TABLE `cs3004101` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3004101structure`
--

DROP TABLE IF EXISTS `cs3004101structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3004101structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3004101structure`
--

LOCK TABLES `cs3004101structure` WRITE;
/*!40000 ALTER TABLE `cs3004101structure` DISABLE KEYS */;
INSERT INTO `cs3004101structure` VALUES ('Assign1',20),('Assign2',50),('assign3',20);
/*!40000 ALTER TABLE `cs3004101structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3004171`
--

DROP TABLE IF EXISTS `cs3004171`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3004171` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `CS3004171studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CS3004171studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3004171`
--

LOCK TABLES `cs3004171` WRITE;
/*!40000 ALTER TABLE `cs3004171` DISABLE KEYS */;
INSERT INTO `cs3004171` VALUES (728,34,0.0,0.0,0.0),(757,37,0.0,0.0,0.0),(782,35,0.0,0.0,0.0),(842,36,0.0,0.0,0.0),(888,33,0.0,0.0,0.0);
/*!40000 ALTER TABLE `cs3004171` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3004171structure`
--

DROP TABLE IF EXISTS `cs3004171structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3004171structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3004171structure`
--

LOCK TABLES `cs3004171structure` WRITE;
/*!40000 ALTER TABLE `cs3004171structure` DISABLE KEYS */;
INSERT INTO `cs3004171structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `cs3004171structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3004471`
--

DROP TABLE IF EXISTS `cs3004471`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3004471` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `CS3004471studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CS3004471studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3004471`
--

LOCK TABLES `cs3004471` WRITE;
/*!40000 ALTER TABLE `cs3004471` DISABLE KEYS */;
INSERT INTO `cs3004471` VALUES (601,184,0.0,0.0,0.0),(724,186,0.0,0.0,0.0),(928,187,0.0,0.0,0.0),(955,185,0.0,0.0,0.0),(966,183,0.0,0.0,0.0);
/*!40000 ALTER TABLE `cs3004471` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3004471structure`
--

DROP TABLE IF EXISTS `cs3004471structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3004471structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3004471structure`
--

LOCK TABLES `cs3004471structure` WRITE;
/*!40000 ALTER TABLE `cs3004471structure` DISABLE KEYS */;
INSERT INTO `cs3004471structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `cs3004471structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3014111`
--

DROP TABLE IF EXISTS `cs3014111`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3014111` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `CS3014111studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CS3014111studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3014111`
--

LOCK TABLES `cs3014111` WRITE;
/*!40000 ALTER TABLE `cs3014111` DISABLE KEYS */;
INSERT INTO `cs3014111` VALUES (817,14,0.0,0.0,0.0),(835,17,0.0,0.0,0.0),(840,18,0.0,0.0,0.0),(890,15,0.0,0.0,0.0),(902,16,0.0,0.0,0.0);
/*!40000 ALTER TABLE `cs3014111` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3014111structure`
--

DROP TABLE IF EXISTS `cs3014111structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3014111structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3014111structure`
--

LOCK TABLES `cs3014111structure` WRITE;
/*!40000 ALTER TABLE `cs3014111structure` DISABLE KEYS */;
INSERT INTO `cs3014111structure` VALUES ('assign1',30),('assign2',30),('assign3',30);
/*!40000 ALTER TABLE `cs3014111structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3014151`
--

DROP TABLE IF EXISTS `cs3014151`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3014151` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `CS3014151studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CS3014151studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3014151`
--

LOCK TABLES `cs3014151` WRITE;
/*!40000 ALTER TABLE `cs3014151` DISABLE KEYS */;
INSERT INTO `cs3014151` VALUES (766,24,0.0,0.0,0.0),(807,23,0.0,0.0,0.0),(929,25,0.0,0.0,0.0),(956,27,0.0,0.0,0.0),(977,26,0.0,0.0,0.0);
/*!40000 ALTER TABLE `cs3014151` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3014151structure`
--

DROP TABLE IF EXISTS `cs3014151structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3014151structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3014151structure`
--

LOCK TABLES `cs3014151structure` WRITE;
/*!40000 ALTER TABLE `cs3014151structure` DISABLE KEYS */;
INSERT INTO `cs3014151structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `cs3014151structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3014461`
--

DROP TABLE IF EXISTS `cs3014461`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3014461` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `CS3014461studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CS3014461studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3014461`
--

LOCK TABLES `cs3014461` WRITE;
/*!40000 ALTER TABLE `cs3014461` DISABLE KEYS */;
INSERT INTO `cs3014461` VALUES (707,180,0.0,0.0,0.0),(711,182,0.0,0.0,0.0),(727,179,0.0,0.0,0.0),(789,181,0.0,0.0,0.0),(983,178,0.0,0.0,0.0);
/*!40000 ALTER TABLE `cs3014461` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs3014461structure`
--

DROP TABLE IF EXISTS `cs3014461structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs3014461structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs3014461structure`
--

LOCK TABLES `cs3014461structure` WRITE;
/*!40000 ALTER TABLE `cs3014461structure` DISABLE KEYS */;
INSERT INTO `cs3014461structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `cs3014461structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs4804451`
--

DROP TABLE IF EXISTS `cs4804451`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs4804451` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `CS4804451studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CS4804451studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs4804451`
--

LOCK TABLES `cs4804451` WRITE;
/*!40000 ALTER TABLE `cs4804451` DISABLE KEYS */;
INSERT INTO `cs4804451` VALUES (588,177,0.0,0.0,0.0),(712,176,0.0,0.0,0.0),(764,173,0.0,0.0,0.0),(905,175,0.0,0.0,0.0),(965,174,0.0,0.0,0.0);
/*!40000 ALTER TABLE `cs4804451` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs4804451structure`
--

DROP TABLE IF EXISTS `cs4804451structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs4804451structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs4804451structure`
--

LOCK TABLES `cs4804451structure` WRITE;
/*!40000 ALTER TABLE `cs4804451structure` DISABLE KEYS */;
INSERT INTO `cs4804451structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `cs4804451structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs5024161`
--

DROP TABLE IF EXISTS `cs5024161`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs5024161` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `CS5024161studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CS5024161studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs5024161`
--

LOCK TABLES `cs5024161` WRITE;
/*!40000 ALTER TABLE `cs5024161` DISABLE KEYS */;
INSERT INTO `cs5024161` VALUES (729,28,0.0,0.0,0.0),(891,30,0.0,0.0,0.0),(942,32,0.0,0.0,0.0),(959,29,0.0,0.0,0.0),(980,31,0.0,0.0,0.0);
/*!40000 ALTER TABLE `cs5024161` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs5024161structure`
--

DROP TABLE IF EXISTS `cs5024161structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs5024161structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs5024161structure`
--

LOCK TABLES `cs5024161structure` WRITE;
/*!40000 ALTER TABLE `cs5024161structure` DISABLE KEYS */;
INSERT INTO `cs5024161structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `cs5024161structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `czd3164391`
--

DROP TABLE IF EXISTS `czd3164391`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `czd3164391` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `CZD3164391studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CZD3164391studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `czd3164391`
--

LOCK TABLES `czd3164391` WRITE;
/*!40000 ALTER TABLE `czd3164391` DISABLE KEYS */;
INSERT INTO `czd3164391` VALUES (740,147,0.0,0.0,0.0),(780,143,0.0,0.0,0.0),(833,144,0.0,0.0,0.0),(960,145,0.0,0.0,0.0),(997,146,0.0,0.0,0.0);
/*!40000 ALTER TABLE `czd3164391` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `czd3164391structure`
--

DROP TABLE IF EXISTS `czd3164391structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `czd3164391structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `czd3164391structure`
--

LOCK TABLES `czd3164391structure` WRITE;
/*!40000 ALTER TABLE `czd3164391structure` DISABLE KEYS */;
INSERT INTO `czd3164391structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `czd3164391structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dcw2194321`
--

DROP TABLE IF EXISTS `dcw2194321`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dcw2194321` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `DCW2194321studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `DCW2194321studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dcw2194321`
--

LOCK TABLES `dcw2194321` WRITE;
/*!40000 ALTER TABLE `dcw2194321` DISABLE KEYS */;
INSERT INTO `dcw2194321` VALUES (742,109,0.0,0.0,0.0),(755,110,0.0,0.0,0.0),(777,108,0.0,0.0,0.0),(790,111,0.0,0.0,0.0),(873,112,0.0,0.0,0.0);
/*!40000 ALTER TABLE `dcw2194321` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dcw2194321structure`
--

DROP TABLE IF EXISTS `dcw2194321structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dcw2194321structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dcw2194321structure`
--

LOCK TABLES `dcw2194321structure` WRITE;
/*!40000 ALTER TABLE `dcw2194321structure` DISABLE KEYS */;
INSERT INTO `dcw2194321structure` VALUES ('assign1',30),('assign2',30),('assign3',30);
/*!40000 ALTER TABLE `dcw2194321structure` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Computer Science'),(18,'Mechanical Engineering'),(19,'Electrical Engineering'),(20,'Chemical Engineering'),(21,'Psychology Department'),(22,'Business Management'),(23,'MIS'),(24,'Sociolgy'),(25,'Geology'),(26,'Kinesiology');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dfk3694281`
--

DROP TABLE IF EXISTS `dfk3694281`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dfk3694281` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `DFK3694281studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `DFK3694281studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dfk3694281`
--

LOCK TABLES `dfk3694281` WRITE;
/*!40000 ALTER TABLE `dfk3694281` DISABLE KEYS */;
INSERT INTO `dfk3694281` VALUES (598,91,0.0,0.0,0.0),(769,90,0.0,0.0,0.0),(859,92,0.0,0.0,0.0),(877,89,0.0,0.0,0.0),(958,88,0.0,0.0,0.0);
/*!40000 ALTER TABLE `dfk3694281` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dfk3694281structure`
--

DROP TABLE IF EXISTS `dfk3694281structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dfk3694281structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dfk3694281structure`
--

LOCK TABLES `dfk3694281structure` WRITE;
/*!40000 ALTER TABLE `dfk3694281structure` DISABLE KEYS */;
INSERT INTO `dfk3694281structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `dfk3694281structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dtw2494571`
--

DROP TABLE IF EXISTS `dtw2494571`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dtw2494571` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `DTW2494571studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `DTW2494571studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dtw2494571`
--

LOCK TABLES `dtw2494571` WRITE;
/*!40000 ALTER TABLE `dtw2494571` DISABLE KEYS */;
INSERT INTO `dtw2494571` VALUES (732,236,0.0,0.0,0.0),(879,235,0.0,0.0,0.0),(903,237,0.0,0.0,0.0),(907,233,0.0,0.0,0.0),(919,234,0.0,0.0,0.0);
/*!40000 ALTER TABLE `dtw2494571` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dtw2494571structure`
--

DROP TABLE IF EXISTS `dtw2494571structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dtw2494571structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dtw2494571structure`
--

LOCK TABLES `dtw2494571structure` WRITE;
/*!40000 ALTER TABLE `dtw2494571structure` DISABLE KEYS */;
INSERT INTO `dtw2494571structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `dtw2494571structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dui5214261`
--

DROP TABLE IF EXISTS `dui5214261`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dui5214261` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `DUI5214261studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `DUI5214261studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dui5214261`
--

LOCK TABLES `dui5214261` WRITE;
/*!40000 ALTER TABLE `dui5214261` DISABLE KEYS */;
INSERT INTO `dui5214261` VALUES (765,78,0.0,0.0,0.0),(795,80,0.0,0.0,0.0),(850,81,0.0,0.0,0.0),(866,79,0.0,0.0,0.0),(963,82,0.0,0.0,0.0);
/*!40000 ALTER TABLE `dui5214261` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dui5214261structure`
--

DROP TABLE IF EXISTS `dui5214261structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dui5214261structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dui5214261structure`
--

LOCK TABLES `dui5214261structure` WRITE;
/*!40000 ALTER TABLE `dui5214261structure` DISABLE KEYS */;
INSERT INTO `dui5214261structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `dui5214261structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dui5214551`
--

DROP TABLE IF EXISTS `dui5214551`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dui5214551` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `DUI5214551studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `DUI5214551studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dui5214551`
--

LOCK TABLES `dui5214551` WRITE;
/*!40000 ALTER TABLE `dui5214551` DISABLE KEYS */;
INSERT INTO `dui5214551` VALUES (717,227,0.0,0.0,0.0),(797,224,0.0,0.0,0.0),(862,223,0.0,0.0,0.0),(912,225,0.0,0.0,0.0),(921,226,0.0,0.0,0.0);
/*!40000 ALTER TABLE `dui5214551` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dui5214551structure`
--

DROP TABLE IF EXISTS `dui5214551structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dui5214551structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dui5214551structure`
--

LOCK TABLES `dui5214551structure` WRITE;
/*!40000 ALTER TABLE `dui5214551structure` DISABLE KEYS */;
INSERT INTO `dui5214551structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `dui5214551structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eay2974511`
--

DROP TABLE IF EXISTS `eay2974511`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eay2974511` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `EAY2974511studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `EAY2974511studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eay2974511`
--

LOCK TABLES `eay2974511` WRITE;
/*!40000 ALTER TABLE `eay2974511` DISABLE KEYS */;
INSERT INTO `eay2974511` VALUES (814,204,0.0,0.0,0.0),(815,206,0.0,0.0,0.0),(855,203,0.0,0.0,0.0),(914,207,0.0,0.0,0.0),(996,205,0.0,0.0,0.0);
/*!40000 ALTER TABLE `eay2974511` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eay2974511structure`
--

DROP TABLE IF EXISTS `eay2974511structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eay2974511structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eay2974511structure`
--

LOCK TABLES `eay2974511structure` WRITE;
/*!40000 ALTER TABLE `eay2974511structure` DISABLE KEYS */;
INSERT INTO `eay2974511structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `eay2974511structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eid5854491`
--

DROP TABLE IF EXISTS `eid5854491`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eid5854491` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `EID5854491studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `EID5854491studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eid5854491`
--

LOCK TABLES `eid5854491` WRITE;
/*!40000 ALTER TABLE `eid5854491` DISABLE KEYS */;
INSERT INTO `eid5854491` VALUES (786,193,0.0,0.0,0.0),(864,197,0.0,0.0,0.0),(875,195,0.0,0.0,0.0),(925,194,0.0,0.0,0.0),(948,196,0.0,0.0,0.0);
/*!40000 ALTER TABLE `eid5854491` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eid5854491structure`
--

DROP TABLE IF EXISTS `eid5854491structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eid5854491structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eid5854491structure`
--

LOCK TABLES `eid5854491structure` WRITE;
/*!40000 ALTER TABLE `eid5854491structure` DISABLE KEYS */;
INSERT INTO `eid5854491structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `eid5854491structure` ENABLE KEYS */;
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
  `Salary` double NOT NULL,
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
INSERT INTO `employee` VALUES (1,48000,'TBD','TBD'),(581,62370.659999999996,'TBD','TBD'),(582,40000,'to be decided','to be decided'),(583,40000,'to be decided','to be decided'),(586,40000,'to be decided','to be decided'),(590,40000,'to be decided','to be decided'),(591,40000,'to be decided','to be decided'),(592,40000,'to be decided','to be decided'),(593,40000,'to be decided','to be decided'),(594,40000,'to be decided','to be decided'),(595,40000,'to be decided','to be decided'),(596,44000,'to be decided','to be decided'),(597,40000,'TB decided','TB decided'),(603,40000,'to be decided','to be decided'),(604,40000,'to be decided','to be decided'),(605,40000,'to be decided','to be decided'),(606,40000,'to be decided','to be decided'),(607,40000,'to be decided','to be decided'),(608,40000,'to be decided','to be decided'),(609,40000,'to be decided','to be decided'),(610,40000,'to be decided','to be decided'),(611,40000,'to be decided','to be decided'),(612,40000,'to be decided','to be decided'),(613,40000,'to be decided','to be decided'),(614,40000,'to be decided','to be decided'),(615,40000,'to be decided','to be decided'),(616,40000,'to be decided','to be decided'),(617,40000,'to be decided','to be decided'),(618,40000,'to be decided','to be decided'),(619,40000,'to be decided','to be decided'),(620,40000,'to be decided','to be decided'),(621,40000,'to be decided','to be decided'),(622,40000,'to be decided','to be decided'),(623,40000,'to be decided','to be decided'),(624,40000,'to be decided','to be decided'),(625,40000,'to be decided','to be decided'),(626,40000,'to be decided','to be decided'),(627,40000,'to be decided','to be decided'),(628,40000,'to be decided','to be decided'),(629,40000,'to be decided','to be decided'),(630,40000,'to be decided','to be decided'),(631,40000,'to be decided','to be decided'),(632,40000,'to be decided','to be decided'),(633,40000,'to be decided','to be decided'),(634,40000,'to be decided','to be decided'),(635,40000,'to be decided','to be decided'),(636,40000,'to be decided','to be decided'),(637,40000,'to be decided','to be decided'),(638,40000,'to be decided','to be decided'),(639,40000,'to be decided','to be decided'),(640,40000,'to be decided','to be decided'),(641,40000,'to be decided','to be decided'),(642,40000,'to be decided','to be decided'),(643,40000,'to be decided','to be decided'),(644,40000,'to be decided','to be decided'),(645,40000,'to be decided','to be decided'),(646,40000,'to be decided','to be decided'),(647,40000,'to be decided','to be decided'),(648,40000,'to be decided','to be decided'),(649,40000,'to be decided','to be decided'),(650,40000,'to be decided','to be decided'),(651,40000,'to be decided','to be decided'),(652,40000,'to be decided','to be decided'),(653,40000,'to be decided','to be decided'),(654,40000,'to be decided','to be decided'),(655,40000,'to be decided','to be decided'),(656,40000,'to be decided','to be decided'),(657,40000,'to be decided','to be decided'),(658,40000,'to be decided','to be decided'),(659,40000,'to be decided','to be decided'),(660,40000,'to be decided','to be decided'),(661,40000,'to be decided','to be decided'),(662,40000,'to be decided','to be decided'),(663,40000,'to be decided','to be decided'),(664,40000,'to be decided','to be decided'),(665,40000,'to be decided','to be decided'),(666,40000,'to be decided','to be decided'),(667,40000,'to be decided','to be decided'),(668,40000,'to be decided','to be decided'),(669,40000,'to be decided','to be decided'),(670,40000,'to be decided','to be decided'),(671,40000,'to be decided','to be decided'),(672,40000,'to be decided','to be decided'),(673,40000,'to be decided','to be decided'),(674,40000,'to be decided','to be decided'),(675,40000,'to be decided','to be decided'),(676,40000,'to be decided','to be decided'),(677,40000,'to be decided','to be decided'),(678,40000,'to be decided','to be decided'),(679,40000,'to be decided','to be decided'),(680,40000,'to be decided','to be decided'),(681,40000,'to be decided','to be decided'),(682,40000,'to be decided','to be decided'),(683,40000,'to be decided','to be decided'),(684,40000,'to be decided','to be decided'),(685,40000,'to be decided','to be decided'),(686,40000,'to be decided','to be decided'),(687,40000,'to be decided','to be decided'),(688,40000,'to be decided','to be decided'),(689,40000,'to be decided','to be decided'),(690,40000,'to be decided','to be decided'),(691,40000,'to be decided','to be decided'),(692,40000,'to be decided','to be decided'),(693,40000,'to be decided','to be decided'),(694,40000,'to be decided','to be decided'),(695,40000,'to be decided','to be decided'),(696,40000,'to be decided','to be decided'),(697,40000,'to be decided','to be decided'),(698,40000,'to be decided','to be decided'),(699,40000,'to be decided','to be decided'),(700,40000,'to be decided','to be decided'),(701,40000,'to be decided','to be decided'),(702,40000,'to be decided','to be decided'),(703,40000,'to be decided','to be decided'),(704,40000,'to be decided','to be decided'),(705,40000,'to be decided','to be decided');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etb5614531`
--

DROP TABLE IF EXISTS `etb5614531`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `etb5614531` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `ETB5614531studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ETB5614531studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etb5614531`
--

LOCK TABLES `etb5614531` WRITE;
/*!40000 ALTER TABLE `etb5614531` DISABLE KEYS */;
INSERT INTO `etb5614531` VALUES (708,216,0.0,0.0,0.0),(722,214,0.0,0.0,0.0),(962,217,0.0,0.0,0.0),(986,215,0.0,0.0,0.0),(989,213,0.0,0.0,0.0);
/*!40000 ALTER TABLE `etb5614531` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etb5614531structure`
--

DROP TABLE IF EXISTS `etb5614531structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `etb5614531structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etb5614531structure`
--

LOCK TABLES `etb5614531structure` WRITE;
/*!40000 ALTER TABLE `etb5614531structure` DISABLE KEYS */;
INSERT INTO `etb5614531structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `etb5614531structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ffu6954351`
--

DROP TABLE IF EXISTS `ffu6954351`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ffu6954351` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `FFU6954351studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FFU6954351studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ffu6954351`
--

LOCK TABLES `ffu6954351` WRITE;
/*!40000 ALTER TABLE `ffu6954351` DISABLE KEYS */;
INSERT INTO `ffu6954351` VALUES (857,126,0.0,0.0,0.0),(884,123,0.0,0.0,0.0),(897,124,0.0,0.0,0.0),(904,127,0.0,0.0,0.0),(949,125,0.0,0.0,0.0);
/*!40000 ALTER TABLE `ffu6954351` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ffu6954351structure`
--

DROP TABLE IF EXISTS `ffu6954351structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ffu6954351structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ffu6954351structure`
--

LOCK TABLES `ffu6954351structure` WRITE;
/*!40000 ALTER TABLE `ffu6954351structure` DISABLE KEYS */;
INSERT INTO `ffu6954351structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `ffu6954351structure` ENABLE KEYS */;
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
  `FileLocation` varchar(100) NOT NULL,
  `FileName` varchar(100) NOT NULL,
  PRIMARY KEY (`FileID`),
  UNIQUE KEY `FileName_UNIQUE` (`FileName`),
  KEY `OfferID_idx` (`OfferID`),
  CONSTRAINT `FileOfferID` FOREIGN KEY (`OfferID`) REFERENCES `coursesoffered` (`OfferID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (1,410,'/Files/CS300-410','CS300410-details.txt'),(2,411,'/Files/CS301-411','CS301411-details.txt'),(3,414,'/Files/MEC101-414','MEC101414-details.txt'),(4,414,'/Files/MEC101-414','Database1.accdb'),(5,415,'/Files/CS301-415','CS301415-details.txt'),(6,416,'/Files/CS502-416','CS502416-details.txt'),(7,417,'/Files/CS300-417','CS300417-details.txt'),(8,418,'/Files/MEC110-418','MEC110418-details.txt'),(9,419,'/Files/MEC101-419','MEC101419-details.txt'),(10,420,'/Files/HSB226-420','HSB226420-details.txt'),(11,421,'/Files/SAP913-421','SAP913421-details.txt'),(12,422,'/Files/ACN647-422','ACN647422-details.txt'),(13,423,'/Files/KNF981-423','KNF981423-details.txt'),(14,424,'/Files/YYN687-424','YYN687424-details.txt'),(15,425,'/Files/QGZ526-425','QGZ526425-details.txt'),(16,426,'/Files/DUI521-426','DUI521426-details.txt'),(17,427,'/Files/GEN323-427','GEN323427-details.txt'),(18,428,'/Files/DFK369-428','DFK369428-details.txt'),(19,429,'/Files/VSF146-429','VSF146429-details.txt'),(20,430,'/Files/OQA728-430','OQA728430-details.txt'),(21,431,'/Files/NQS618-431','NQS618431-details.txt'),(22,432,'/Files/DCW219-432','DCW219432-details.txt'),(23,433,'/Files/IUV276-433','IUV276433-details.txt'),(24,434,'/Files/GEY256-434','GEY256434-details.txt'),(25,435,'/Files/FFU695-435','FFU695435-details.txt'),(26,436,'/Files/WHM164-436','WHM164436-details.txt'),(27,437,'/Files/TIQ275-437','TIQ275437-details.txt'),(28,438,'/Files/ADC472-438','ADC472438-details.txt'),(29,439,'/Files/CZD316-439','CZD316439-details.txt'),(30,440,'/Files/OCD327-440','OCD327440-details.txt'),(31,441,'/Files/OGH935-441','OGH935441-details.txt'),(32,442,'/Files/AIY403-442','AIY403442-details.txt'),(33,443,'/Files/AML434-443','AML434443-details.txt'),(34,444,'/Files/WYM851-444','WYM851444-details.txt'),(35,445,'/Files/CS480-445','CS480445-details.txt'),(36,446,'/Files/CS301-446','CS301446-details.txt'),(37,447,'/Files/CS300-447','CS300447-details.txt'),(38,448,'/Files/PBI352-448','PBI352448-details.txt'),(39,449,'/Files/EID585-449','EID585449-details.txt'),(40,450,'/Files/MSZ380-450','MSZ380450-details.txt'),(41,451,'/Files/EAY297-451','EAY297451-details.txt'),(42,452,'/Files/HPY579-452','HPY579452-details.txt'),(43,453,'/Files/ETB561-453','ETB561453-details.txt'),(44,454,'/Files/LZC379-454','LZC379454-details.txt'),(45,455,'/Files/DUI521-455','DUI521455-details.txt'),(46,456,'/Files/IPQ551-456','IPQ551456-details.txt'),(47,457,'/Files/DTW249-457','DTW249457-details.txt'),(48,458,'/Files/KBV581-458','KBV581458-details.txt'),(49,459,'/Files/FNV482-459','FNV482459-details.txt');
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fnv4824591`
--

DROP TABLE IF EXISTS `fnv4824591`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fnv4824591` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `FNV4824591studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FNV4824591studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fnv4824591`
--

LOCK TABLES `fnv4824591` WRITE;
/*!40000 ALTER TABLE `fnv4824591` DISABLE KEYS */;
INSERT INTO `fnv4824591` VALUES (826,246,0.0,0.0,0.0),(920,243,0.0,0.0,0.0),(935,245,0.0,0.0,0.0),(936,244,0.0,0.0,0.0),(976,247,0.0,0.0,0.0);
/*!40000 ALTER TABLE `fnv4824591` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fnv4824591structure`
--

DROP TABLE IF EXISTS `fnv4824591structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fnv4824591structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fnv4824591structure`
--

LOCK TABLES `fnv4824591structure` WRITE;
/*!40000 ALTER TABLE `fnv4824591structure` DISABLE KEYS */;
INSERT INTO `fnv4824591structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `fnv4824591structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen3234271`
--

DROP TABLE IF EXISTS `gen3234271`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen3234271` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `GEN3234271studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `GEN3234271studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen3234271`
--

LOCK TABLES `gen3234271` WRITE;
/*!40000 ALTER TABLE `gen3234271` DISABLE KEYS */;
INSERT INTO `gen3234271` VALUES (589,87,0.0,0.0,0.0),(759,85,0.0,0.0,0.0),(798,83,0.0,0.0,0.0),(803,84,0.0,0.0,0.0),(909,86,0.0,0.0,0.0);
/*!40000 ALTER TABLE `gen3234271` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen3234271structure`
--

DROP TABLE IF EXISTS `gen3234271structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen3234271structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen3234271structure`
--

LOCK TABLES `gen3234271structure` WRITE;
/*!40000 ALTER TABLE `gen3234271structure` DISABLE KEYS */;
INSERT INTO `gen3234271structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `gen3234271structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gey2564341`
--

DROP TABLE IF EXISTS `gey2564341`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gey2564341` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `GEY2564341studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `GEY2564341studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gey2564341`
--

LOCK TABLES `gey2564341` WRITE;
/*!40000 ALTER TABLE `gey2564341` DISABLE KEYS */;
INSERT INTO `gey2564341` VALUES (791,120,0.0,0.0,0.0),(895,122,0.0,0.0,0.0),(941,118,0.0,0.0,0.0),(947,119,0.0,0.0,0.0),(979,121,0.0,0.0,0.0);
/*!40000 ALTER TABLE `gey2564341` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gey2564341structure`
--

DROP TABLE IF EXISTS `gey2564341structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gey2564341structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gey2564341structure`
--

LOCK TABLES `gey2564341structure` WRITE;
/*!40000 ALTER TABLE `gey2564341structure` DISABLE KEYS */;
INSERT INTO `gey2564341structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `gey2564341structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gradingsystem`
--

DROP TABLE IF EXISTS `gradingsystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gradingsystem` (
  `Grade` varchar(2) NOT NULL,
  `GradeLevel` int(2) NOT NULL,
  PRIMARY KEY (`Grade`),
  UNIQUE KEY `GradeLevel_UNIQUE` (`GradeLevel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gradingsystem`
--

LOCK TABLES `gradingsystem` WRITE;
/*!40000 ALTER TABLE `gradingsystem` DISABLE KEYS */;
INSERT INTO `gradingsystem` VALUES ('A',1),('A-',2),('B+',3),('B',4),('B-',5),('C',6),('D',7);
/*!40000 ALTER TABLE `gradingsystem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hpy5794521`
--

DROP TABLE IF EXISTS `hpy5794521`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hpy5794521` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `HPY5794521studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `HPY5794521studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hpy5794521`
--

LOCK TABLES `hpy5794521` WRITE;
/*!40000 ALTER TABLE `hpy5794521` DISABLE KEYS */;
INSERT INTO `hpy5794521` VALUES (758,210,0.0,0.0,0.0),(824,211,0.0,0.0,0.0),(854,212,0.0,0.0,0.0),(934,209,0.0,0.0,0.0),(967,208,0.0,0.0,0.0);
/*!40000 ALTER TABLE `hpy5794521` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hpy5794521structure`
--

DROP TABLE IF EXISTS `hpy5794521structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hpy5794521structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hpy5794521structure`
--

LOCK TABLES `hpy5794521structure` WRITE;
/*!40000 ALTER TABLE `hpy5794521structure` DISABLE KEYS */;
INSERT INTO `hpy5794521structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `hpy5794521structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hsb2264201`
--

DROP TABLE IF EXISTS `hsb2264201`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hsb2264201` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `HSB2264201studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `HSB2264201studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hsb2264201`
--

LOCK TABLES `hsb2264201` WRITE;
/*!40000 ALTER TABLE `hsb2264201` DISABLE KEYS */;
INSERT INTO `hsb2264201` VALUES (745,52,0.0,0.0,0.0),(788,49,0.0,0.0,0.0),(841,51,0.0,0.0,0.0),(851,48,0.0,0.0,0.0),(893,50,0.0,0.0,0.0);
/*!40000 ALTER TABLE `hsb2264201` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hsb2264201structure`
--

DROP TABLE IF EXISTS `hsb2264201structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hsb2264201structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hsb2264201structure`
--

LOCK TABLES `hsb2264201structure` WRITE;
/*!40000 ALTER TABLE `hsb2264201structure` DISABLE KEYS */;
INSERT INTO `hsb2264201structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `hsb2264201structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ipq5514561`
--

DROP TABLE IF EXISTS `ipq5514561`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ipq5514561` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `IPQ5514561studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `IPQ5514561studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ipq5514561`
--

LOCK TABLES `ipq5514561` WRITE;
/*!40000 ALTER TABLE `ipq5514561` DISABLE KEYS */;
INSERT INTO `ipq5514561` VALUES (736,230,0.0,0.0,0.0),(785,231,0.0,0.0,0.0),(794,229,0.0,0.0,0.0),(805,232,0.0,0.0,0.0),(952,228,0.0,0.0,0.0);
/*!40000 ALTER TABLE `ipq5514561` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ipq5514561structure`
--

DROP TABLE IF EXISTS `ipq5514561structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ipq5514561structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ipq5514561structure`
--

LOCK TABLES `ipq5514561structure` WRITE;
/*!40000 ALTER TABLE `ipq5514561structure` DISABLE KEYS */;
INSERT INTO `ipq5514561structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `ipq5514561structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iuv2764331`
--

DROP TABLE IF EXISTS `iuv2764331`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iuv2764331` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `IUV2764331studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `IUV2764331studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iuv2764331`
--

LOCK TABLES `iuv2764331` WRITE;
/*!40000 ALTER TABLE `iuv2764331` DISABLE KEYS */;
INSERT INTO `iuv2764331` VALUES (749,113,0.0,0.0,0.0),(774,116,0.0,0.0,0.0),(813,114,0.0,0.0,0.0),(876,117,0.0,0.0,0.0),(990,115,0.0,0.0,0.0);
/*!40000 ALTER TABLE `iuv2764331` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iuv2764331structure`
--

DROP TABLE IF EXISTS `iuv2764331structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iuv2764331structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iuv2764331structure`
--

LOCK TABLES `iuv2764331structure` WRITE;
/*!40000 ALTER TABLE `iuv2764331structure` DISABLE KEYS */;
INSERT INTO `iuv2764331structure` VALUES ('assign1',30),('assign2',30),('assign3',30);
/*!40000 ALTER TABLE `iuv2764331structure` ENABLE KEYS */;
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
  `ReqdMinimumGPA` double DEFAULT NULL,
  `ReqdMinimumWorkExperience` double DEFAULT NULL,
  `ReqdSkillset1` varchar(45) DEFAULT NULL,
  `ReqdSkillset2` varchar(45) DEFAULT NULL,
  `ReqdSkillset3` varchar(45) DEFAULT NULL,
  `ReqdSkillset4` varchar(45) DEFAULT NULL,
  `ReqdSkillset5` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`JobID`),
  KEY `PostedByUIN_idx` (`PostedByUIN`),
  KEY `JobInDepartment_idx` (`JobInDepartment`),
  CONSTRAINT `JobInDepartment` FOREIGN KEY (`JobInDepartment`) REFERENCES `department` (`DepartmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PostedByUIN` FOREIGN KEY (`PostedByUIN`) REFERENCES `people` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobpostings`
--

LOCK TABLES `jobpostings` WRITE;
/*!40000 ALTER TABLE `jobpostings` DISABLE KEYS */;
INSERT INTO `jobpostings` VALUES (39,597,18,4,3,'0','1','1','1','0'),(40,582,1,2.5,1,'1','0','0','0','0'),(41,640,19,3.7,2,'0','1','1','0','1'),(42,691,18,3.8,3.5,'0','0','1','1','0'),(43,616,25,3.5,1,'0','0','1','1','0'),(44,666,19,3.8,1.5,'0','1','1','0','0'),(45,705,21,3.7,1.5,'1','1','0','1','0'),(46,591,1,3.5,4,'1','0','1','1','1'),(47,649,21,3.6,3.5,'0','1','0','1','0'),(48,652,18,2.5,2.5,'1','1','1','1','1'),(49,582,1,3.7,2.5,'0','0','1','1','1'),(50,677,24,3,1,'1','1','1','1','0'),(51,693,23,3.8,4,'1','1','0','0','0'),(52,643,22,2.5,1.5,'0','0','1','1','0'),(53,703,25,2.5,2,'1','1','1','1','1'),(54,619,24,3.5,2,'0','1','0','0','1'),(55,683,25,3.5,2,'0','1','1','0','1'),(56,627,19,2.5,2,'1','1','1','0','1'),(57,618,20,4,1,'1','1','1','1','1'),(58,623,21,3.5,2,'1','0','0','1','0'),(59,654,26,3.8,3,'0','0','0','0','0'),(60,611,18,3,3.5,'1','1','1','0','0'),(61,694,19,3.5,3.5,'0','0','1','0','0'),(62,630,25,3,1,'1','0','1','1','0'),(63,613,20,3.6,1.5,'0','0','0','0','1'),(64,634,18,3,1.5,'0','1','0','0','0'),(65,614,24,3.7,3,'1','0','1','1','0'),(66,644,25,4,1.5,'1','1','0','0','1'),(67,631,19,3.8,2,'1','1','1','0','1'),(68,696,18,4,2.5,'0','0','0','0','0'),(69,668,18,3,1,'1','0','0','1','0'),(70,680,26,3.8,3,'0','0','1','0','0'),(71,684,21,2.5,2,'0','1','0','1','0'),(72,669,21,3.8,4,'1','0','0','0','1'),(73,624,22,2.5,2,'1','1','1','0','1'),(74,688,25,3.6,3,'1','1','1','0','1'),(75,597,18,4,3,'0','1','1','1','0'),(76,692,24,3.8,2,'0','0','1','1','0'),(77,664,22,4,2,'0','1','1','1','1'),(78,648,26,2.5,1.5,'1','0','1','1','0'),(79,621,19,3.7,2.5,'0','1','0','1','0'),(80,629,24,4,1.5,'0','0','1','0','1'),(81,704,26,3.6,3.5,'0','1','1','0','1'),(82,583,1,3.6,1.5,'1','0','1','1','1'),(83,637,24,3.5,2.5,'0','0','0','1','1'),(84,656,24,3,1.5,'0','0','0','0','1'),(85,635,24,2.5,3,'0','1','1','1','0'),(86,700,22,3.7,2,'0','0','0','0','1'),(87,633,22,3.6,4,'1','1','1','1','1'),(88,647,23,4,3,'0','1','1','1','1'),(89,658,26,3.7,4,'0','0','1','0','0'),(90,615,25,3.6,2.5,'1','1','0','0','1');
/*!40000 ALTER TABLE `jobpostings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobroster`
--

DROP TABLE IF EXISTS `jobroster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobroster` (
  `JobID` int(11) DEFAULT NULL,
  `UIN` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobroster`
--

LOCK TABLES `jobroster` WRITE;
/*!40000 ALTER TABLE `jobroster` DISABLE KEYS */;
INSERT INTO `jobroster` VALUES (39,600),(39,602);
/*!40000 ALTER TABLE `jobroster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kbv5814581`
--

DROP TABLE IF EXISTS `kbv5814581`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kbv5814581` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `KBV5814581studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `KBV5814581studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kbv5814581`
--

LOCK TABLES `kbv5814581` WRITE;
/*!40000 ALTER TABLE `kbv5814581` DISABLE KEYS */;
INSERT INTO `kbv5814581` VALUES (750,239,0.0,0.0,0.0),(830,242,0.0,0.0,0.0),(911,241,0.0,0.0,0.0),(916,240,0.0,0.0,0.0),(975,238,0.0,0.0,0.0);
/*!40000 ALTER TABLE `kbv5814581` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kbv5814581structure`
--

DROP TABLE IF EXISTS `kbv5814581structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kbv5814581structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kbv5814581structure`
--

LOCK TABLES `kbv5814581structure` WRITE;
/*!40000 ALTER TABLE `kbv5814581structure` DISABLE KEYS */;
INSERT INTO `kbv5814581structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `kbv5814581structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `knf9814231`
--

DROP TABLE IF EXISTS `knf9814231`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knf9814231` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `KNF9814231studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `KNF9814231studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `knf9814231`
--

LOCK TABLES `knf9814231` WRITE;
/*!40000 ALTER TABLE `knf9814231` DISABLE KEYS */;
INSERT INTO `knf9814231` VALUES (772,65,0.0,0.0,0.0),(822,66,0.0,0.0,0.0),(838,63,0.0,0.0,0.0),(852,64,0.0,0.0,0.0),(968,67,0.0,0.0,0.0);
/*!40000 ALTER TABLE `knf9814231` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `knf9814231structure`
--

DROP TABLE IF EXISTS `knf9814231structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knf9814231structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `knf9814231structure`
--

LOCK TABLES `knf9814231structure` WRITE;
/*!40000 ALTER TABLE `knf9814231structure` DISABLE KEYS */;
INSERT INTO `knf9814231structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `knf9814231structure` ENABLE KEYS */;
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
INSERT INTO `logindetails` VALUES ('aaron','ylSo1N8m'),('aaron68','tobBY4hA'),('abel','3Kjw3pIJ'),('abiga','RWrMwLpy'),('adara','uSmeNoe1'),('addis','qI3OtBQF'),('addis33','IovqyrNy'),('addis72','ticuM2C0'),('adria','WKcoumZI'),('akeem','NhxJCmhJ'),('aksha','dY6ujqtj'),('alana','2hJu5RMQ'),('alec','IudIpqtZ'),('alexa','Zx409PEy'),('alexi','U78LPQPz'),('alfon','xPw1egnv'),('alfon3','7XN2oR5q'),('alfon88','amt8oXXs'),('alfre','5ahOwF9p'),('allen','WaVYluOY'),('allis','NGQdg1uH'),('alma','MMaCmN6g'),('amber','TH07ZRqv'),('ameli','XK6LFpQ3'),('andre','NrP4XESj'),('anjol','voJCzpu0'),('anjol80','Qff27w7w'),('aphro','dbgEOnWa'),('april','19rrhjK3'),('arden','DaCC4NPm'),('arden13','lLpyUQQX'),('arden3','dfrxZYza'),('arian','7Q5Px88h'),('arist','P3pQ5u8P'),('ashel','Igz7LLII'),('ashel41','tcsp6zo2'),('athen','jgHLJSl6'),('audra','Cqr4p7ai'),('augus','5zZbibkr'),('aurel','sdTX6IQs'),('austi','SL4F33FI'),('austi96','KUJUUeRW'),('autum','IOoSlwBV'),('autum72','uMeQmj8c'),('baxte','LMlelb6C'),('belle','eZ5S2S0I'),('belle72','grXzYCnt'),('bened','59ClYLCr'),('benja','Gfi9cncT'),('blair','JiUxCNqE'),('blaze','Gjo9p9H3'),('borri','WLWW8NPs'),('brady','JU1ttwBU'),('brand','naRtoydh'),('brend','U6fOvzsT'),('brent','eQ3MmCro'),('brent69','liX3UwLF'),('brian','14K8YeiS'),('brita','LWa0Kxgb'),('brody','BwgOAlN6'),('burto','EfWs6yuN'),('caili','XZWpN0lL'),('calli','ngA8RI8S'),('callu','a6ZD00kB'),('camer','camer'),('camer63','YZSeEFOa'),('camer64','729drX4C'),('camil','zd43pqvq'),('camil26','Fqxsvhw3'),('cara','4gTH6xmd'),('cara18','dRjGwwZl'),('carla','r9zPVPsv'),('carso','NwBsUbvZ'),('caryn','LiT0SmVB'),('casey','WD0SRnML'),('cassa','p7GLh4Ql'),('casto','Wio5Y5uC'),('celes','76qXriwa'),('chadw','rptHQ7Dg'),('chaim','R6a6li1D'),('chanc','bcrlXMEx'),('chand','cpPEpAch'),('chann','v1xKdncJ'),('chard','dYGaKvC1'),('chari','fM3BiX1g'),('chase','CxcUDri0'),('chase1','y8XUfbse'),('chels','ywHIPpU4'),('chels76','Nxn2C7F2'),('cheye','KINyE68w'),('chiqu','nQuRRzwR'),('chris','a7DZ7JkO'),('clare','t82njCVs'),('clark','lZRW460e'),('coby','3xgovK49'),('colle','5uikkjdq'),('color','7ufiPAbv'),('colto','mELHpLom'),('conan','XbUU9drB'),('court','B1DEP9vD'),('cruz','8nGs4bGv'),('culle','g9AVOhRq'),('curra','ywnElw41'),('cynth','5N0YKBhm'),('dacey','QsL6N5EX'),('dahli','WgaxCNLY'),('dakot','xokT69xK'),('dalto','B57xFELq'),('damia','LypnhoGh'),('damon','Vp0Hblto'),('damon81','rGFL9HCx'),('daphn','dhsNpEB2'),('daphn46','Gx1MIYgf'),('david','y97utmrZ'),('delil','51f0BiIo'),('devin','nzKOkTXZ'),('diete','G3cnNLc5'),('dolan','stFgwEnR'),('doris','YDFDrIzU'),('drake','UNaUVtFU'),('dunca','XmosJvDx'),('dylan','67H3nWHx'),('ebony','1bjgdNOI'),('edwar','BxJKCKdU'),('elean','V84lUqb8'),('eliza','SX1T9ApY'),('ella','ella'),('ellio','qI4jUtsP'),('elmo','iP8iYvZ0'),('emers','d4kxWJUw'),('erasm','deaYdDOl'),('erich','d1XThyvi'),('ezeki','9K2LM8tZ'),('faith','S8c94Kta'),('felix','a4MaMTUE'),('ferdi','IlVHLlC4'),('ferri','gpQyM4pn'),('finn','vvTouhmx'),('fiona','6lrXYw9O'),('flore','HRQu0dPF'),('flynn','Bym6pM6I'),('franc','vFMyqxIp'),('garri','xHY45joq'),('gavin','tqvS0rBE'),('gavin7','h1oRpRju'),('georg','4pQIHa5x'),('gilli','u0dc02kw'),('gisel','taPsND4w'),('glori','uc7AIoSB'),('grace','RgjXe9so'),('grady','gRqooix9'),('graha','W4UPbBjs'),('graid','wq1o3Yd5'),('grant','YK8O6KwX'),('gray','RWbEyO3F'),('gray78','DFTEAedw'),('grego','grego'),('griff','griff'),('griff29','pIy9weGB'),('guine','Plvs9wa2'),('guine12','dHhfbpmQ'),('hadle','KhjLdPvS'),('hakee','LARNKshG'),('hakee41','19YgXxTu'),('hakee92','jjrgAYY7'),('haley','N9P11Smp'),('halla','NwqzYTWt'),('hamil','J85ssblB'),('hamme','qXTzcIKk'),('hamme35','kGqczm9e'),('hanae','ZF74rdGk'),('hanna','61O4eyjW'),('hanna8','BwpEeKEJ'),('harpe','5AaxDywA'),('harpe65','YjO7DFXM'),('hermi','9LLleOUP'),('hilla','h00Y6Xhs'),('hilla31','4IXBZbTO'),('hiram','Y5WAZrMT'),('hiram0','HnDL8Yrc'),('hu','ZuSYtEb0'),('hunte','djNc27r3'),('idola','r0CCgyrg'),('ila','VOn7Xgrv'),('illia','qbJVVDm8'),('illia93','JMLZThwW'),('india','t3DqEe6n'),('indig','LeaJt2yo'),('indir','tVpwGrtZ'),('ingri','ingri'),('isaia','60bmTFys'),('issac','issac'),('ivor','i4AqfqlQ'),('ivory','869iUSRX'),('jada','c2MzbjhM'),('jade','jade'),('jade98','NQXS4J5O'),('jaden','dCJy4T6K'),('jakee','cIOI2PN5'),('jamal','G1Xg2VTq'),('jamal34','bGpGcYeQ'),('james','vdKWmMnh'),('jana','gC7Cjgnf'),('jared','Z6kUC9fH'),('jared73','Utr8ZW3n'),('jason','WcSRon2B'),('jason38','1RnQev7d'),('jeane','bxSuqiTP'),('jenet','GadLfYfT'),('jenna','RCfWx9rN'),('jerem','IMM6WHdP'),('jesci','CTaWmQMj'),('jessi','vRYATi5F'),('joan','QOWs5JKL'),('joan10','IwLGk93o'),('john','AqIh40b9'),('jorda','FvvMHmuO'),('jorda44','CBPLeE4w'),('justi','pqxK2mbx'),('kalia','UJFPbiJV'),('kamal','kamal'),('kasim','4SHV6IPU'),('katel','yYLAsaDX'),('kathl','4fKPCYLp'),('kato','iIUlic6m'),('kay','j9krsbVh'),('keane','keane'),('kelle','wrIKOrOv'),('kelly','igljMfro'),('kenne','5VW0760Y'),('kenyo','OW5R7BDV'),('kevyn','cnWkcI6P'),('kiaya','JboLgvO3'),('kibo','CnNTrAU5'),('kiera','r3a2vRD5'),('kirk','sOetGtVg'),('kitra','itau9cYy'),('knox','whKJAsCy'),('kyla','hbnZUgbH'),('kyla34','4g4LEjbq'),('kylie','yb4gebTK'),('kylyn','EBrWfRE1'),('kyra','dwABzsvO'),('kyra38','7HdiQykJ'),('kyra82','iAKi88U5'),('lacot','dFfFJYvl'),('lacy','NmHXKZtD'),('lamar','sa2QKybQ'),('lance','PqBv39S0'),('larei','hTP0RWLr'),('lavin','g1sXJqOA'),('lawre','xi9WECj7'),('leand','kF2RKCWW'),('leila','SYuw6D4s'),('len','tyQ38aZt'),('leona','leonard'),('leroy','Vr0klvfU'),('lesle','2j44VOet'),('liber','yW6vJqiS'),('lila','bLxHyIyG'),('lilli','6k6bM8PY'),('lilli96','LDbvfI1s'),('linda','zJgAnoWt'),('linus','3phcRRz6'),('lisan','7l63QfjG'),('logan','gROsYNlC'),('lois','GNnC0AFq'),('lucy','DzMvJTya'),('lydia','WtucwmQT'),('macey','HdjFJETo'),('macey40','uc66FTix'),('macon','9bswyZaO'),('madel','xBqxaVH8'),('magee','FzGl2ZA0'),('maggi','e6W6PM54'),('maggi58','AOrhgGp4'),('maggi81','pfN9Bc0i'),('maggy','pzwidWlb'),('maggy41','gpxuOsEP'),('mallo','x5MBujRe'),('mari','8Y8nkmkD'),('marik','ImD5sRY3'),('mark','j6vWzxV3'),('mason','JxQuVW9r'),('maxin','sxgOafPo'),('may','iYwIYhBr'),('melod','uY6M4uJj'),('melys','P4q5u0si'),('melys4','luCVSetf'),('micha','bFL4Neau'),('mira','LLyDtmwx'),('mira23','iQzNXrw0'),('moana','r3iifwcA'),('molly','molly'),('natha','F8VkWd4U'),('nell','KPUmK4RK'),('neve','i15079re'),('nicol','5xxcDYPP'),('nigel','Iuz7KCRU'),('noel','Et6kvFsk'),('nolan','bT4BF5g8'),('ocean','pLpfj8Fx'),('octav','VqFqSFFO'),('octav17','m4q4kDT8'),('odyss','CVZDD7tj'),('odyss42','COqvJewg'),('oleg','6oVYC8Lf'),('olga','t9PazsAx'),('olive','buM76RA9'),('olymp','uPj1UCjj'),('orla','1oAK78Oi'),('pando','9uP2gTzS'),('pasca','VDAWyNS3'),('patri','txIr0Pwr'),('paula','7oYJaDxn'),('phela','YSRvV1Az'),('porte','TfwjYDvv'),('price','HPA6wQGc'),('quama','32Rsr2g8'),('quinl','2wyN5WgL'),('quon','XBllas2f'),('quon75','tjav9abl'),('rae','73La75Nr'),('raja','kN3dMhsI'),('ralph','RA5iWP9d'),('ramon','k7qRBRo7'),('rapha','AI8K5Nxw'),('rasha','k22yj5XG'),('rasha15','qlEYEnL0'),('ray','pSgxzVsB'),('raya','wUs2aS65'),('reece','EsCisTdJ'),('reece57','LMw4iujA'),('regan','yAorgqRC'),('regin','6snVJJJ4'),('reube','gMILMUGz'),('rhian','uaOvvnPA'),('rhona','FcalpAFR'),('rhond','gIMnVfHe'),('richa','cBdghQVF'),('rina','jsF503m7'),('risa','bpznqrQ6'),('roary','VBEh0L7o'),('rober','SD1xt9G5'),('rogan','V9EfLjbo'),('roone','FKPybdXl'),('root','root'),('rosal','5dBvrQ3z'),('rose','G9cSvsc0'),('roth','weVDxPUr'),('ruby','XU80sOh3'),('ruth','lC7iiYF0'),('rylee','JkPUMAwx'),('sacha','01w41ho6'),('sage','cfVZuxEK'),('sasha','ED6AhZB8'),('scarl','vXUE94ZF'),('scott','VC8vkyYc'),('sean','sean'),('sean82','wtCaOuDG'),('sebas','OO1CPQyL'),('selma','zDAtFdKX'),('seren','RPj0oaQW'),('shana','8eYCZj1E'),('shana53','fIBMx5Jq'),('shea','OiVwqbuH'),('shelb','bNDlguu1'),('signe','OlX1SY8v'),('siman','3i3aGZk7'),('simon','yNvGfGXT'),('skyle','Yv9tbv9E'),('slade','7YTigcKC'),('slade21','RDz9zmiq'),('stacy','W9m1XxRm'),('stacy96','uTd34LR2'),('steph','5etHFBf2'),('steph26','pG7P3B2A'),('summe','57yx5cfJ'),('sydne','ZGZCOhx4'),('sylve','PVz8h7nG'),('sylvi','i2FfXW4h'),('tad','HphAqGXE'),('tanne','cTiUMBdw'),('tara','JGDiCyBW'),('tashy','J429dZvG'),('tate','eg6qTGB2'),('tatia','Jqf00CdL'),('tatum','N40kmw41'),('tatya','rjzlNTOL'),('taylo','5FQvM7nB'),('thane','3vI2L8YZ'),('timot','YTexSIuQ'),('troy','rkNSqTWG'),('tyler','WhZJNPnF'),('tyler43','f1oWZPm2'),('ulla','pku1RNov'),('ulyss','mEl3M3rj'),('uma','DBq1qBKv'),('valen','AUWPycQn'),('vanna','tpwxwXLu'),('while','8gCIeoHc'),('willa','6WM95TOW'),('wylie','RVhdrSPk'),('wyomi','fqBGdDWZ'),('xande','EpvsI6a7'),('xavie','cIxPiCmk'),('yetta','7GUVqNEE'),('yetta36','yUxfAj2O'),('yetta41','C0rtxnbt'),('yolan','SK9N86Sy'),('yuli','jVWkFSnF'),('yvett','ZxgPrfWX'),('yvonn','wNHYdW3o'),('zache','tqthNM4b'),('zahir','LDk32UVq'),('zelda','wjpT8HpN'),('zelen','cB8NttGf'),('zenia','Lq1zroCz'),('zephr','cubjT6og'),('zia','dxY03oQY');
/*!40000 ALTER TABLE `logindetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lzc3794541`
--

DROP TABLE IF EXISTS `lzc3794541`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lzc3794541` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `LZC3794541studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `LZC3794541studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lzc3794541`
--

LOCK TABLES `lzc3794541` WRITE;
/*!40000 ALTER TABLE `lzc3794541` DISABLE KEYS */;
INSERT INTO `lzc3794541` VALUES (602,222,0.0,0.0,0.0),(719,218,0.0,0.0,0.0),(820,221,0.0,0.0,0.0),(871,220,0.0,0.0,0.0),(944,219,0.0,0.0,0.0);
/*!40000 ALTER TABLE `lzc3794541` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lzc3794541structure`
--

DROP TABLE IF EXISTS `lzc3794541structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lzc3794541structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lzc3794541structure`
--

LOCK TABLES `lzc3794541structure` WRITE;
/*!40000 ALTER TABLE `lzc3794541structure` DISABLE KEYS */;
INSERT INTO `lzc3794541structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `lzc3794541structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mec1014141`
--

DROP TABLE IF EXISTS `mec1014141`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mec1014141` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `exam1` decimal(4,1) DEFAULT '0.0',
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `MEC1014141studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MEC1014141studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mec1014141`
--

LOCK TABLES `mec1014141` WRITE;
/*!40000 ALTER TABLE `mec1014141` DISABLE KEYS */;
INSERT INTO `mec1014141` VALUES (600,5,0.0,0.0,0.0,0.0),(760,22,0.0,0.0,0.0,0.0),(804,21,0.0,0.0,0.0,0.0),(954,19,0.0,0.0,0.0,0.0),(991,20,0.0,0.0,0.0,0.0);
/*!40000 ALTER TABLE `mec1014141` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mec1014141structure`
--

DROP TABLE IF EXISTS `mec1014141structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mec1014141structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mec1014141structure`
--

LOCK TABLES `mec1014141structure` WRITE;
/*!40000 ALTER TABLE `mec1014141structure` DISABLE KEYS */;
INSERT INTO `mec1014141structure` VALUES ('assign1',30),('assign2',30),('assign3',30),('exam1',100);
/*!40000 ALTER TABLE `mec1014141structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mec1014191`
--

DROP TABLE IF EXISTS `mec1014191`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mec1014191` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `MEC1014191studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MEC1014191studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mec1014191`
--

LOCK TABLES `mec1014191` WRITE;
/*!40000 ALTER TABLE `mec1014191` DISABLE KEYS */;
INSERT INTO `mec1014191` VALUES (584,45,0.0,0.0,0.0),(599,44,0.0,0.0,0.0),(746,46,0.0,0.0,0.0),(957,47,0.0,0.0,0.0),(999,43,0.0,0.0,0.0);
/*!40000 ALTER TABLE `mec1014191` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mec1014191structure`
--

DROP TABLE IF EXISTS `mec1014191structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mec1014191structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mec1014191structure`
--

LOCK TABLES `mec1014191structure` WRITE;
/*!40000 ALTER TABLE `mec1014191structure` DISABLE KEYS */;
INSERT INTO `mec1014191structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `mec1014191structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mec1104181`
--

DROP TABLE IF EXISTS `mec1104181`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mec1104181` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `MEC1104181studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MEC1104181studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mec1104181`
--

LOCK TABLES `mec1104181` WRITE;
/*!40000 ALTER TABLE `mec1104181` DISABLE KEYS */;
INSERT INTO `mec1104181` VALUES (600,42,0.0,0.0,0.0),(823,40,0.0,0.0,0.0),(874,41,0.0,0.0,0.0),(940,38,0.0,0.0,0.0),(984,39,0.0,0.0,0.0);
/*!40000 ALTER TABLE `mec1104181` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mec1104181structure`
--

DROP TABLE IF EXISTS `mec1104181structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mec1104181structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mec1104181structure`
--

LOCK TABLES `mec1104181structure` WRITE;
/*!40000 ALTER TABLE `mec1104181structure` DISABLE KEYS */;
INSERT INTO `mec1104181structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `mec1104181structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msz3804501`
--

DROP TABLE IF EXISTS `msz3804501`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msz3804501` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `MSZ3804501studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MSZ3804501studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msz3804501`
--

LOCK TABLES `msz3804501` WRITE;
/*!40000 ALTER TABLE `msz3804501` DISABLE KEYS */;
INSERT INTO `msz3804501` VALUES (723,202,0.0,0.0,0.0),(771,199,0.0,0.0,0.0),(787,201,0.0,0.0,0.0),(839,200,0.0,0.0,0.0),(985,198,0.0,0.0,0.0);
/*!40000 ALTER TABLE `msz3804501` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msz3804501structure`
--

DROP TABLE IF EXISTS `msz3804501structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msz3804501structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msz3804501structure`
--

LOCK TABLES `msz3804501structure` WRITE;
/*!40000 ALTER TABLE `msz3804501structure` DISABLE KEYS */;
INSERT INTO `msz3804501structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `msz3804501structure` ENABLE KEYS */;
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
INSERT INTO `names1` VALUES ('ACN647'),('ADA612'),('ADC472'),('AIY403'),('AIY910'),('AML434'),('ATF941'),('AWG854'),('AWT688'),('AXX174'),('AYL565'),('AYW223'),('BAS355'),('BES361'),('BFE121'),('BGK574'),('BJN913'),('BJO897'),('BNL161'),('BYU517'),('BZJ525'),('CFH637'),('CJU378'),('COI967'),('CSZ973'),('CUO272'),('CUV308'),('CZD316'),('DCW219'),('DDC448'),('DEI726'),('DFK369'),('DFZ953'),('DIQ792'),('DLC863'),('DRL138'),('DTH282'),('DTW249'),('DUD354'),('DUI521'),('EAY297'),('EID585'),('EKU261'),('ETB561'),('EUH316'),('EVO724'),('EXN476'),('FDX848'),('FFC874'),('FFU695'),('FIA908'),('FMV316'),('FNV482'),('FUW253'),('FVA985'),('FVX421'),('GEN323'),('GEY256'),('GQA430'),('GRA957'),('GSQ119'),('GST781'),('GWE488'),('GYV483'),('HKF596'),('HNR864'),('HNY179'),('HPY579'),('HQA204'),('HSB226'),('HSU797'),('HTF453'),('ICN499'),('IHC888'),('IHQ168'),('INM716'),('IOG144'),('IPQ551'),('IUT840'),('IUV276'),('IWA519'),('IXI832'),('JAS224'),('JFG544'),('JJI785'),('JKG247'),('JLC841'),('JNI477'),('JSF796'),('JXD982'),('KBV581'),('KNF981'),('KRE544'),('KTY725'),('LCQ179'),('LFC440'),('LFF407'),('LGL587'),('LJW398'),('LMI869'),('LWX305'),('LXJ479'),('LXY445'),('LZC379'),('MFY237'),('MIV243'),('MJL110'),('MKP842'),('MPE415'),('MSZ380'),('NKD236'),('NKF371'),('NOQ721'),('NQS618'),('NRC941'),('NZK993'),('OCD327'),('OGH935'),('OIM993'),('ONB921'),('OQA728'),('OQS850'),('ORN925'),('PBI352'),('PER336'),('PIJ316'),('PMA979'),('PQQ865'),('PRZ482'),('PXD739'),('PZI766'),('QAB396'),('QAC981'),('QEK316'),('QFC158'),('QGZ526'),('QHR384'),('QKK858'),('QKX886'),('QRN954'),('QSX315'),('QVQ983'),('QXS922'),('RAG356'),('RDR752'),('RIT276'),('RRQ942'),('SAP913'),('SEC333'),('SGN569'),('SYV320'),('TCM760'),('TEE732'),('TIQ275'),('TNI947'),('TQU338'),('TUE852'),('TVG979'),('UCL957'),('UIV136'),('UJU965'),('UMN352'),('UPM889'),('USN389'),('USX346'),('VBW291'),('VFO268'),('VKW781'),('VSF146'),('VXX409'),('WDN111'),('WEZ124'),('WHM164'),('WHQ226'),('WOF376'),('WPA767'),('WQB553'),('WRM334'),('WSS172'),('WVH955'),('WWH561'),('WYM851'),('WYN174'),('WZT983'),('XBW196'),('XWP374'),('YBR584'),('YGC819'),('YJG561'),('YNP380'),('YQN172'),('YQR985'),('YTH757'),('YVH757'),('YYN687'),('ZBR597'),('ZHR424'),('ZKV518'),('ZSL643'),('ZYX261');
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
INSERT INTO `names2` VALUES ('Aaron'),('Abel'),('Abigail'),('Addison'),('Adrian'),('Akeem'),('Alexa'),('Alexis'),('Alma'),('Ariana'),('Benjamin'),('Brady'),('Branden'),('Cailin'),('Cameran'),('Caryn'),('Casey'),('Cassady'),('Chanda'),('Chelsea'),('Colleen'),('Colton'),('Courtney'),('Curran'),('Dacey'),('Dakota'),('Damian'),('Damon'),('Daphne'),('Doris'),('Duncan'),('Erasmus'),('Ezekiel'),('Florence'),('Gavin'),('George'),('Grady'),('Hakeem'),('Haley'),('Hammett'),('Hanna'),('Harper'),('Hunter'),('Idola'),('India'),('Indigo'),('Isaiah'),('Ivor'),('Ivory'),('Jason'),('Jescie'),('Joan'),('Kieran'),('Kitra'),('Knox'),('Kyra'),('Lamar'),('Lavinia'),('Leila'),('Liberty'),('Lila'),('Logan'),('Lydia'),('Macey'),('Macon'),('Maggy'),('Mariko'),('Mark'),('Melyssa'),('Michael'),('Mira'),('Moana'),('Nicole'),('Nigel'),('Odysseus'),('Oliver'),('Pandora'),('Price'),('Quon'),('Raphael'),('Rashad'),('Reece'),('Regina'),('Roary'),('Robert'),('Sean'),('Selma'),('Shelby'),('Slade'),('Summer'),('TaShya'),('Tatum'),('Uma'),('Whilemina'),('Yetta'),('Yvonne');
/*!40000 ALTER TABLE `names2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `namesdept`
--

DROP TABLE IF EXISTS `namesdept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `namesdept` (
  `names` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `namesdept`
--

LOCK TABLES `namesdept` WRITE;
/*!40000 ALTER TABLE `namesdept` DISABLE KEYS */;
INSERT INTO `namesdept` VALUES ('Computer Science'),('Indutrial Engineering'),('Electrical Engineering'),('Mechanical Engineering'),('Chemical Engineering'),('Sociology'),('Psychology'),('Geology'),('MIS'),('Business Management');
/*!40000 ALTER TABLE `namesdept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nqs6184311`
--

DROP TABLE IF EXISTS `nqs6184311`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nqs6184311` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `NQS6184311studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `NQS6184311studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nqs6184311`
--

LOCK TABLES `nqs6184311` WRITE;
/*!40000 ALTER TABLE `nqs6184311` DISABLE KEYS */;
INSERT INTO `nqs6184311` VALUES (715,105,0.0,0.0,0.0),(720,104,0.0,0.0,0.0),(808,107,0.0,0.0,0.0),(832,103,0.0,0.0,0.0),(867,106,0.0,0.0,0.0);
/*!40000 ALTER TABLE `nqs6184311` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nqs6184311structure`
--

DROP TABLE IF EXISTS `nqs6184311structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nqs6184311structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nqs6184311structure`
--

LOCK TABLES `nqs6184311structure` WRITE;
/*!40000 ALTER TABLE `nqs6184311structure` DISABLE KEYS */;
INSERT INTO `nqs6184311structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `nqs6184311structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocd3274401`
--

DROP TABLE IF EXISTS `ocd3274401`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocd3274401` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `OCD3274401studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `OCD3274401studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocd3274401`
--

LOCK TABLES `ocd3274401` WRITE;
/*!40000 ALTER TABLE `ocd3274401` DISABLE KEYS */;
INSERT INTO `ocd3274401` VALUES (761,151,0.0,0.0,0.0),(918,150,0.0,0.0,0.0),(930,149,0.0,0.0,0.0),(938,152,0.0,0.0,0.0),(969,148,0.0,0.0,0.0);
/*!40000 ALTER TABLE `ocd3274401` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocd3274401structure`
--

DROP TABLE IF EXISTS `ocd3274401structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocd3274401structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocd3274401structure`
--

LOCK TABLES `ocd3274401structure` WRITE;
/*!40000 ALTER TABLE `ocd3274401structure` DISABLE KEYS */;
INSERT INTO `ocd3274401structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `ocd3274401structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ogh9354411`
--

DROP TABLE IF EXISTS `ogh9354411`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ogh9354411` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `OGH9354411studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `OGH9354411studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ogh9354411`
--

LOCK TABLES `ogh9354411` WRITE;
/*!40000 ALTER TABLE `ogh9354411` DISABLE KEYS */;
INSERT INTO `ogh9354411` VALUES (718,156,0.0,0.0,0.0),(734,155,0.0,0.0,0.0),(836,154,0.0,0.0,0.0),(924,157,0.0,0.0,0.0),(964,153,0.0,0.0,0.0);
/*!40000 ALTER TABLE `ogh9354411` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ogh9354411structure`
--

DROP TABLE IF EXISTS `ogh9354411structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ogh9354411structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ogh9354411structure`
--

LOCK TABLES `ogh9354411structure` WRITE;
/*!40000 ALTER TABLE `ogh9354411structure` DISABLE KEYS */;
INSERT INTO `ogh9354411structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `ogh9354411structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oqa7284301`
--

DROP TABLE IF EXISTS `oqa7284301`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oqa7284301` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `OQA7284301studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `OQA7284301studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oqa7284301`
--

LOCK TABLES `oqa7284301` WRITE;
/*!40000 ALTER TABLE `oqa7284301` DISABLE KEYS */;
INSERT INTO `oqa7284301` VALUES (716,101,0.0,0.0,0.0),(812,100,0.0,0.0,0.0),(894,102,0.0,0.0,0.0),(923,99,0.0,0.0,0.0),(988,98,0.0,0.0,0.0);
/*!40000 ALTER TABLE `oqa7284301` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oqa7284301structure`
--

DROP TABLE IF EXISTS `oqa7284301structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oqa7284301structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oqa7284301structure`
--

LOCK TABLES `oqa7284301structure` WRITE;
/*!40000 ALTER TABLE `oqa7284301structure` DISABLE KEYS */;
INSERT INTO `oqa7284301structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `oqa7284301structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pbi3524481`
--

DROP TABLE IF EXISTS `pbi3524481`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pbi3524481` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `PBI3524481studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PBI3524481studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pbi3524481`
--

LOCK TABLES `pbi3524481` WRITE;
/*!40000 ALTER TABLE `pbi3524481` DISABLE KEYS */;
INSERT INTO `pbi3524481` VALUES (731,189,0.0,0.0,0.0),(778,192,0.0,0.0,0.0),(971,190,0.0,0.0,0.0),(973,191,0.0,0.0,0.0),(992,188,0.0,0.0,0.0);
/*!40000 ALTER TABLE `pbi3524481` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pbi3524481structure`
--

DROP TABLE IF EXISTS `pbi3524481structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pbi3524481structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pbi3524481structure`
--

LOCK TABLES `pbi3524481structure` WRITE;
/*!40000 ALTER TABLE `pbi3524481structure` DISABLE KEYS */;
INSERT INTO `pbi3524481structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `pbi3524481structure` ENABLE KEYS */;
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
  CONSTRAINT `Username` FOREIGN KEY (`Username`) REFERENCES `logindetails` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (1,'root','root',1,5),(581,'leonard','leona',1,1),(582,'Cameron','camer',1,2),(583,'Chadwick','chadw',1,2),(584,'kamal','kamal',1,3),(585,'Griffin','griff',1,4),(586,'Issac','issac',18,1),(587,'Keane','keane',1,3),(588,'Molly','molly',1,3),(589,'Ella','ella',1,3),(590,'Hiram','hiram',1,2),(591,'Jared','jared',1,2),(592,'borri','borri',1,2),(593,'Patrick','patri',18,2),(594,'Timothy','timot',18,2),(595,'Chase','chase',18,2),(596,'Autumn','autum',18,2),(597,'Gregory','grego',18,2),(598,'Ingrid','ingri',18,3),(599,'Kelley','kelle',18,3),(600,'Sean','sean',18,3),(601,'Zachery','zache',18,3),(602,'Jade','jade',18,3),(603,'Addison','addis',19,1),(604,'Arden','arden',26,1),(605,'Ashely','ashel',25,1),(606,'Austin','austi',24,1),(607,'Belle','belle',22,1),(608,'Brent','brent',23,1),(609,'Camilla','camil',20,1),(610,'Cara','cara',21,1),(611,'Addison','addis72',18,2),(612,'Akshay','aksha',18,2),(613,'Alfonso','alfon',20,2),(614,'Arden','arden13',24,2),(615,'Ashely','ashel41',25,2),(616,'Athena','athen',25,2),(617,'Austin','austi96',18,2),(618,'Belle','belle72',20,2),(619,'Brenda','brend',24,2),(620,'Brent','brent69',24,2),(621,'Camilla','camil26',19,2),(622,'Cara','cara18',26,2),(623,'Carson','carso',21,2),(624,'Celeste','celes',22,2),(625,'Chancellor','chanc',22,2),(626,'Channing','chann',25,2),(627,'Cheyenne','cheye',19,2),(628,'Colorado','color',26,2),(629,'Cullen','culle',24,2),(630,'Dahlia','dahli',25,2),(631,'Elizabeth','eliza',19,2),(632,'Elmo','elmo',24,2),(633,'Ferdinand','ferdi',22,2),(634,'Ferris','ferri',18,2),(635,'Finn','finn',24,2),(636,'Fiona','fiona',19,2),(637,'Flynn','flynn',24,2),(638,'Garrison','garri',21,2),(639,'Grace','grace',19,2),(640,'Griffin','griff29',19,2),(641,'Hadley','hadle',19,2),(642,'Hamilton','hamil',24,2),(643,'Hammett','hamme',22,2),(644,'Hillary','hilla',25,2),(645,'Hiram','hiram0',22,2),(646,'Hu','hu',23,2),(647,'Illiana','illia',23,2),(648,'Jamalia','jamal',26,2),(649,'Jeanette','jeane',21,2),(650,'Jenette','jenet',21,2),(651,'Jenna','jenna',18,2),(652,'Jessica','jessi',18,2),(653,'John','john',22,2),(654,'Jordan','jorda',26,2),(655,'Justina','justi',22,2),(656,'Kato','kato',24,2),(657,'Kay','kay',19,2),(658,'Kenneth','kenne',26,2),(659,'Kyla','kyla',25,2),(660,'Lacota','lacot',18,2),(661,'Lance','lance',25,2),(662,'Leandra','leand',23,2),(663,'Lesley','lesle',24,2),(664,'Lillian','lilli',22,2),(665,'Lisandra','lisan',25,2),(666,'Magee','magee',19,2),(667,'Maggie','maggi',20,2),(668,'Mason','mason',18,2),(669,'Melyssa','melys',21,2),(670,'Nathan','natha',18,2),(671,'Neve','neve',23,2),(672,'Ocean','ocean',21,2),(673,'Olga','olga',24,2),(674,'Olympia','olymp',26,2),(675,'Paula','paula',19,2),(676,'Phelan','phela',18,2),(677,'Quamar','quama',24,2),(678,'Quinlan','quinl',19,2),(679,'Ralph','ralph',24,2),(680,'Ramona','ramon',26,2),(681,'Rashad','rasha',25,2),(682,'Raya','raya',26,2),(683,'Rina','rina',25,2),(684,'Rogan','rogan',21,2),(685,'Rooney','roone',23,2),(686,'Rosalyn','rosal',22,2),(687,'Rose','rose',22,2),(688,'Ruby','ruby',25,2),(689,'Rylee','rylee',19,2),(690,'Sasha','sasha',20,2),(691,'Shana','shana',18,2),(692,'Simant','siman',24,2),(693,'Stacy','stacy',23,2),(694,'Stephen','steph',19,2),(695,'Sylvia','sylvi',23,2),(696,'Tate','tate',18,2),(697,'Troy','troy',19,2),(698,'Tyler','tyler',25,2),(699,'Valentine','valen',25,2),(700,'Vanna','vanna',22,2),(701,'Wylie','wylie',26,2),(702,'Xander','xande',20,2),(703,'Yetta','yetta',25,2),(704,'Zelenia','zelen',26,2),(705,'Zephr','zephr',21,2),(706,'Alana','alana',24,3),(707,'Alfonso','alfon3',19,3),(708,'Allen','allen',21,3),(709,'Amber','amber',18,3),(710,'Andrew','andre',21,3),(711,'Anjolie','anjol',18,3),(712,'Arden','arden3',19,3),(713,'Audra','audra',19,3),(714,'August','augus',18,4),(715,'Autumn','autum72',19,3),(716,'Baxter','baxte',24,3),(717,'Benedict','bened',18,3),(718,'Brody','brody',18,3),(719,'Burton','burto',23,3),(720,'Callie','calli',19,3),(721,'Carla','carla',22,4),(722,'Charity','chari',24,3),(723,'Chelsea','chels',26,3),(724,'Clare','clare',19,3),(725,'Coby','coby',18,3),(726,'Conan','conan',23,4),(727,'Cruz','cruz',26,3),(728,'Cynthia','cynth',19,3),(729,'Dalton','dalto',21,3),(730,'Dieter','diete',18,3),(731,'Drake','drake',22,3),(732,'Emerson','emers',18,3),(733,'Faith','faith',22,3),(734,'Gavin','gavin',21,3),(735,'Graham','graha',20,4),(736,'Graiden','graid',18,3),(737,'Gray','gray',25,3),(738,'Guinevere','guine',23,3),(739,'Hakeem','hakee',18,3),(740,'Hanae','hanae',23,3),(741,'Hannah','hanna',21,4),(742,'Hermione','hermi',25,3),(743,'Hillary','hilla31',18,3),(744,'Illiana','illia93',21,3),(745,'Jana','jana',23,3),(746,'Jared','jared73',26,3),(747,'Jeremy','jerem',20,3),(748,'Joan','joan',26,3),(749,'Jordan','jorda44',23,3),(750,'Kalia','kalia',26,3),(751,'Kasimir','kasim',20,3),(752,'Katell','katel',25,3),(753,'Kathleen','kathl',19,3),(754,'Kenyon','kenyo',19,3),(755,'Kevyn','kevyn',26,3),(756,'Kiayada','kiaya',18,4),(757,'Kylie','kylie',23,3),(758,'Kylynn','kylyn',21,3),(759,'Kyra','kyra',19,3),(760,'Lacy','lacy',25,3),(761,'Lareina','larei',23,3),(762,'Len','len',23,3),(763,'Leroy','leroy',24,3),(764,'Lucy','lucy',22,3),(765,'Macey','macey',19,3),(766,'Maggie','maggi58',24,3),(767,'Maggy','maggy',23,3),(768,'Maxine','maxin',24,3),(769,'Melodie','melod',18,3),(770,'Nell','nell',26,4),(771,'Noel','noel',21,3),(772,'Nolan','nolan',23,3),(773,'Octavia','octav',21,3),(774,'Odysseus','odyss',18,3),(775,'Quon','quon',22,3),(776,'Rae','rae',22,3),(777,'Reuben','reube',26,3),(778,'Richard','richa',18,3),(779,'Roth','roth',20,3),(780,'Ruth','ruth',22,3),(781,'Sage','sage',18,3),(782,'Scarlett','scarl',26,3),(783,'Sebastian','sebas',20,3),(784,'Serena','seren',26,4),(785,'Shana','shana53',23,3),(786,'Shea','shea',21,3),(787,'Signe','signe',25,3),(788,'Slade','slade',23,3),(789,'Stacy','stacy96',20,3),(790,'Stephen','steph26',20,3),(791,'Sydney','sydne',25,3),(792,'Sylvester','sylve',23,4),(793,'Tad','tad',23,4),(794,'Tanner','tanne',23,3),(795,'Tatiana','tatia',26,3),(796,'Taylor','taylo',23,3),(797,'Thane','thane',18,3),(798,'Tyler','tyler43',25,3),(799,'Ulysses','ulyss',24,3),(800,'Wyoming','wyomi',21,3),(801,'Yolanda','yolan',25,4),(802,'Yvette','yvett',26,3),(803,'Zahir','zahir',21,3),(804,'Zelda','zelda',25,3),(805,'Zia','zia',23,3),(806,'Aaron','aaron',22,3),(807,'Adara','adara',19,3),(808,'Alec','alec',23,3),(809,'Alfonso','alfon88',25,3),(810,'Alfreda','alfre',23,4),(811,'Allistair','allis',25,3),(812,'Amelia','ameli',18,3),(813,'Anjolie','anjol80',20,3),(814,'Aphrodite','aphro',20,3),(815,'April','april',24,3),(816,'Aristotle','arist',24,3),(817,'Aurelia','aurel',20,3),(818,'Blair','blair',18,4),(819,'Blaze','blaze',20,3),(820,'Brian','brian',25,3),(821,'Britanni','brita',22,3),(822,'Callum','callu',20,3),(823,'Cameran','camer63',19,3),(824,'Castor','casto',22,3),(825,'Chaim','chaim',24,3),(826,'Charde','chard',19,3),(827,'Chase','chase1',19,4),(828,'Chiquita','chiqu',18,4),(829,'Christian','chris',18,4),(830,'Clarke','clark',19,3),(831,'Damon','damon',22,3),(832,'Daphne','daphn',18,3),(833,'David','david',21,3),(834,'Delilah','delil',25,4),(835,'Devin','devin',26,3),(836,'Dolan','dolan',25,3),(837,'Dylan','dylan',19,4),(838,'Ebony','ebony',22,3),(839,'Edward','edwar',19,3),(840,'Eleanor','elean',25,3),(841,'Elliott','ellio',19,3),(842,'Erich','erich',20,3),(843,'Felix','felix',26,3),(844,'Francis','franc',21,3),(845,'Gillian','gilli',23,3),(846,'Gisela','gisel',22,3),(847,'Gloria','glori',19,3),(848,'Grant','grant',25,3),(849,'Gray','gray78',22,3),(850,'Guinevere','guine12',22,3),(851,'Hakeem','hakee41',22,3),(852,'Halla','halla',21,3),(853,'Harper','harpe',25,4),(854,'Ila','ila',25,3),(855,'Indira','indir',24,3),(856,'Jada','jada',21,4),(857,'Jade','jade98',20,3),(858,'Jaden','jaden',26,3),(859,'Jakeem','jakee',26,3),(860,'Jamalia','jamal34',25,4),(861,'James','james',18,3),(862,'Jason','jason',24,3),(863,'Kelly','kelly',25,4),(864,'Kibo','kibo',20,3),(865,'Kirk','kirk',24,3),(866,'Kyla','kyla34',21,3),(867,'Kyra','kyra38',23,3),(868,'Lawrence','lawre',26,3),(869,'Lillith','lilli96',20,4),(870,'Linda','linda',20,3),(871,'Linus','linus',20,3),(872,'Lois','lois',19,3),(873,'Madeline','madel',20,3),(874,'Maggie','maggi81',20,3),(875,'Mallory','mallo',20,3),(876,'Mari','mari',19,3),(877,'May','may',23,3),(878,'Mira','mira',23,4),(879,'Octavius','octav17',21,3),(880,'Oleg','oleg',18,3),(881,'Orla','orla',23,4),(882,'Pascale','pasca',20,3),(883,'Porter','porte',24,4),(884,'Raja','raja',23,3),(885,'Ray','ray',21,3),(886,'Reece','reece',19,3),(887,'Regan','regan',22,4),(888,'Rhiannon','rhian',21,3),(889,'Rhona','rhona',22,3),(890,'Rhonda','rhond',18,3),(891,'Risa','risa',18,3),(892,'Sacha','sacha',25,3),(893,'Scott','scott',20,3),(894,'Simone','simon',18,3),(895,'Skyler','skyle',22,3),(896,'Tara','tara',25,3),(897,'Tatyana','tatya',21,3),(898,'Ulla','ulla',26,3),(899,'Willa','willa',22,4),(900,'Xavier','xavie',22,3),(901,'Yetta','yetta36',18,3),(902,'Yuli','yuli',21,3),(903,'Zenia','zenia',25,3),(904,'Aaron','aaron68',24,3),(905,'Abel','abel',23,3),(906,'Abigail','abiga',23,3),(907,'Addison','addis33',25,3),(908,'Adrian','adria',25,3),(909,'Akeem','akeem',18,3),(910,'Alexa','alexa',19,3),(911,'Alexis','alexi',26,3),(912,'Alma','alma',24,3),(913,'Ariana','arian',25,4),(914,'Benjamin','benja',23,3),(915,'Brady','brady',20,4),(916,'Branden','brand',18,3),(917,'Cailin','caili',25,4),(918,'Cameran','camer64',20,3),(919,'Caryn','caryn',26,3),(920,'Casey','casey',25,3),(921,'Cassady','cassa',21,3),(922,'Chanda','chand',18,4),(923,'Chelsea','chels76',21,3),(924,'Colleen','colle',19,3),(925,'Colton','colto',20,3),(926,'Courtney','court',25,3),(927,'Curran','curra',18,4),(928,'Dacey','dacey',26,3),(929,'Dakota','dakot',25,3),(930,'Damian','damia',20,3),(931,'Damon','damon81',24,3),(932,'Daphne','daphn46',26,3),(933,'Doris','doris',24,3),(934,'Duncan','dunca',24,3),(935,'Erasmus','erasm',23,3),(936,'Ezekiel','ezeki',19,3),(937,'Florence','flore',22,4),(938,'Gavin','gavin7',23,3),(939,'George','georg',19,3),(940,'Grady','grady',20,3),(941,'Hakeem','hakee92',26,3),(942,'Haley','haley',23,3),(943,'Hammett','hamme35',23,3),(944,'Hanna','hanna8',23,3),(945,'Harper','harpe65',20,3),(946,'Hunter','hunte',18,3),(947,'Idola','idola',24,3),(948,'India','india',18,3),(949,'Indigo','indig',19,3),(950,'Isaiah','isaia',21,3),(951,'Ivor','ivor',19,4),(952,'Ivory','ivory',24,3),(953,'Jason','jason38',18,3),(954,'Jescie','jesci',21,3),(955,'Joan','joan10',22,3),(956,'Kieran','kiera',24,3),(957,'Kitra','kitra',24,3),(958,'Knox','knox',21,3),(959,'Kyra','kyra82',19,3),(960,'Lamar','lamar',21,3),(961,'Lavinia','lavin',26,3),(962,'Leila','leila',25,3),(963,'Liberty','liber',18,3),(964,'Lila','lila',26,3),(965,'Logan','logan',23,3),(966,'Lydia','lydia',22,3),(967,'Macey','macey40',23,3),(968,'Macon','macon',18,3),(969,'Maggy','maggy41',26,3),(970,'Mariko','marik',20,3),(971,'Mark','mark',19,3),(972,'Melyssa','melys4',18,3),(973,'Michael','micha',26,3),(974,'Mira','mira23',25,3),(975,'Moana','moana',25,3),(976,'Nicole','nicol',20,3),(977,'Nigel','nigel',18,3),(978,'Odysseus','odyss42',25,4),(979,'Oliver','olive',22,3),(980,'Pandora','pando',23,3),(981,'Price','price',20,4),(982,'Quon','quon75',25,3),(983,'Raphael','rapha',24,3),(984,'Rashad','rasha15',24,3),(985,'Reece','reece57',20,3),(986,'Regina','regin',19,3),(987,'Roary','roary',19,4),(988,'Robert','rober',26,3),(989,'Sean','sean82',21,3),(990,'Selma','selma',19,3),(991,'Shelby','shelb',19,3),(992,'Slade','slade21',18,3),(993,'Summer','summe',21,3),(994,'TaShya','tashy',24,3),(995,'Tatum','tatum',23,4),(996,'Uma','uma',25,3),(997,'Whilemina','while',23,3),(998,'Yetta','yetta41',25,4),(999,'Yvonne','yvonn',20,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Admin','1'),(2,'Professor','2'),(3,'Student','3'),(4,'TA','4'),(5,'SuperAdmin','5');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qgz5264251`
--

DROP TABLE IF EXISTS `qgz5264251`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qgz5264251` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `QGZ5264251studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `QGZ5264251studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qgz5264251`
--

LOCK TABLES `qgz5264251` WRITE;
/*!40000 ALTER TABLE `qgz5264251` DISABLE KEYS */;
INSERT INTO `qgz5264251` VALUES (768,76,0.0,0.0,0.0),(800,75,0.0,0.0,0.0),(821,74,0.0,0.0,0.0),(868,73,0.0,0.0,0.0),(982,77,0.0,0.0,0.0);
/*!40000 ALTER TABLE `qgz5264251` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qgz5264251structure`
--

DROP TABLE IF EXISTS `qgz5264251structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qgz5264251structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qgz5264251structure`
--

LOCK TABLES `qgz5264251structure` WRITE;
/*!40000 ALTER TABLE `qgz5264251structure` DISABLE KEYS */;
INSERT INTO `qgz5264251structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `qgz5264251structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sap9134211`
--

DROP TABLE IF EXISTS `sap9134211`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sap9134211` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `SAP9134211studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `SAP9134211studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sap9134211`
--

LOCK TABLES `sap9134211` WRITE;
/*!40000 ALTER TABLE `sap9134211` DISABLE KEYS */;
INSERT INTO `sap9134211` VALUES (762,57,0.0,0.0,0.0),(845,55,0.0,0.0,0.0),(872,54,0.0,0.0,0.0),(889,53,0.0,0.0,0.0),(892,56,0.0,0.0,0.0);
/*!40000 ALTER TABLE `sap9134211` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sap9134211structure`
--

DROP TABLE IF EXISTS `sap9134211structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sap9134211structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sap9134211structure`
--

LOCK TABLES `sap9134211structure` WRITE;
/*!40000 ALTER TABLE `sap9134211structure` DISABLE KEYS */;
INSERT INTO `sap9134211structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `sap9134211structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester` (
  `SemesterID` int(12) NOT NULL AUTO_INCREMENT,
  `SemesterName` varchar(45) NOT NULL,
  `SemesterYear` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IsCurrent` int(12) NOT NULL,
  PRIMARY KEY (`SemesterID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,'FALL','2014-04-28 07:14:27',1);
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
INSERT INTO `student` VALUES (584,3.43,'1'),(585,4.00,'1'),(587,4.00,'1'),(588,4.00,'1'),(589,2.86,'1'),(598,4.00,'1'),(599,4.00,'1'),(600,3.71,'1'),(601,4.00,'1'),(602,2.86,'1'),(706,4.00,'2'),(707,4.00,'1'),(708,4.00,'1'),(709,4.00,'3'),(710,4.00,'2'),(711,4.00,'2'),(712,4.00,'1'),(713,4.00,'3'),(714,4.00,'2'),(715,4.00,'2'),(716,4.00,'1'),(717,4.00,'2'),(718,4.00,'1'),(719,4.00,'2'),(720,4.00,'2'),(721,4.00,'3'),(722,4.00,'2'),(723,4.00,'2'),(724,4.00,'1'),(725,4.00,'1'),(726,4.00,'1'),(727,4.00,'2'),(728,4.00,'2'),(729,4.00,'3'),(730,4.00,'2'),(731,4.00,'1'),(732,4.00,'1'),(733,4.00,'3'),(734,4.00,'3'),(735,4.00,'2'),(736,4.00,'1'),(737,4.00,'2'),(738,4.00,'1'),(739,4.00,'3'),(740,4.00,'3'),(741,4.00,'2'),(742,4.00,'1'),(743,4.00,'1'),(744,4.00,'2'),(745,4.00,'1'),(746,4.00,'2'),(747,4.00,'3'),(748,4.00,'3'),(749,4.00,'1'),(750,4.00,'1'),(751,4.00,'1'),(752,4.00,'2'),(753,4.00,'1'),(754,4.00,'1'),(755,4.00,'1'),(756,4.00,'3'),(757,4.00,'1'),(758,4.00,'2'),(759,4.00,'3'),(760,4.00,'2'),(761,4.00,'1'),(762,4.00,'3'),(763,4.00,'3'),(764,4.00,'3'),(765,4.00,'3'),(766,4.00,'2'),(767,4.00,'2'),(768,4.00,'1'),(769,4.00,'1'),(770,4.00,'3'),(771,4.00,'1'),(772,4.00,'1'),(773,4.00,'2'),(774,4.00,'1'),(775,4.00,'1'),(776,4.00,'1'),(777,4.00,'3'),(778,4.00,'3'),(779,4.00,'1'),(780,4.00,'3'),(781,4.00,'2'),(782,4.00,'1'),(783,4.00,'3'),(784,4.00,'1'),(785,4.00,'3'),(786,4.00,'2'),(787,4.00,'3'),(788,4.00,'2'),(789,4.00,'1'),(790,4.00,'3'),(791,4.00,'3'),(792,4.00,'2'),(793,4.00,'1'),(794,4.00,'2'),(795,4.00,'1'),(796,4.00,'1'),(797,4.00,'3'),(798,4.00,'1'),(799,4.00,'3'),(800,4.00,'3'),(801,4.00,'2'),(802,4.00,'2'),(803,4.00,'1'),(804,4.00,'2'),(805,4.00,'1'),(806,4.00,'2'),(807,4.00,'3'),(808,4.00,'1'),(809,4.00,'3'),(810,4.00,'2'),(811,4.00,'1'),(812,4.00,'3'),(813,4.00,'3'),(814,4.00,'2'),(815,4.00,'3'),(816,4.00,'3'),(817,4.00,'1'),(818,4.00,'3'),(819,4.00,'2'),(820,4.00,'2'),(821,4.00,'1'),(822,4.00,'2'),(823,4.00,'3'),(824,4.00,'1'),(825,4.00,'1'),(826,4.00,'1'),(827,4.00,'1'),(828,4.00,'2'),(829,4.00,'1'),(830,4.00,'1'),(831,4.00,'1'),(832,4.00,'2'),(833,4.00,'3'),(834,4.00,'3'),(835,4.00,'1'),(836,4.00,'3'),(837,4.00,'2'),(838,4.00,'3'),(839,4.00,'2'),(840,4.00,'1'),(841,4.00,'3'),(842,4.00,'1'),(843,4.00,'1'),(844,4.00,'2'),(845,4.00,'1'),(846,4.00,'2'),(847,4.00,'3'),(848,4.00,'3'),(849,4.00,'1'),(850,4.00,'2'),(851,4.00,'1'),(852,4.00,'3'),(853,4.00,'2'),(854,4.00,'2'),(855,4.00,'3'),(856,4.00,'2'),(857,4.00,'1'),(858,4.00,'3'),(859,4.00,'1'),(860,4.00,'2'),(861,4.00,'3'),(862,4.00,'1'),(863,4.00,'1'),(864,4.00,'3'),(865,4.00,'3'),(866,4.00,'1'),(867,4.00,'1'),(868,4.00,'3'),(869,4.00,'1'),(870,4.00,'2'),(871,4.00,'2'),(872,4.00,'2'),(873,4.00,'2'),(874,4.00,'2'),(875,4.00,'2'),(876,4.00,'1'),(877,4.00,'1'),(878,4.00,'1'),(879,4.00,'1'),(880,4.00,'1'),(881,4.00,'1'),(882,4.00,'1'),(883,4.00,'2'),(884,4.00,'3'),(885,4.00,'2'),(886,4.00,'2'),(887,4.00,'2'),(888,4.00,'2'),(889,4.00,'2'),(890,4.00,'1'),(891,4.00,'1'),(892,4.00,'3'),(893,4.00,'1'),(894,4.00,'3'),(895,4.00,'2'),(896,4.00,'2'),(897,4.00,'2'),(898,4.00,'1'),(899,4.00,'2'),(900,4.00,'2'),(901,4.00,'2'),(902,4.00,'3'),(903,4.00,'1'),(904,4.00,'2'),(905,4.00,'1'),(906,4.00,'2'),(907,4.00,'2'),(908,4.00,'2'),(909,4.00,'1'),(910,4.00,'3'),(911,4.00,'3'),(912,4.00,'3'),(913,4.00,'2'),(914,4.00,'1'),(915,4.00,'1'),(916,4.00,'3'),(917,4.00,'2'),(918,4.00,'3'),(919,4.00,'2'),(920,4.00,'3'),(921,4.00,'3'),(922,4.00,'2'),(923,4.00,'2'),(924,4.00,'2'),(925,4.00,'2'),(926,4.00,'3'),(927,4.00,'1'),(928,4.00,'1'),(929,4.00,'3'),(930,4.00,'2'),(931,4.00,'2'),(932,4.00,'3'),(933,4.00,'3'),(934,4.00,'3'),(935,4.00,'3'),(936,4.00,'1'),(937,4.00,'1'),(938,4.00,'3'),(939,4.00,'1'),(940,4.00,'2'),(941,4.00,'2'),(942,4.00,'1'),(943,4.00,'3'),(944,4.00,'2'),(945,4.00,'3'),(946,4.00,'1'),(947,4.00,'2'),(948,4.00,'2'),(949,4.00,'2'),(950,4.00,'3'),(951,4.00,'1'),(952,4.00,'2'),(953,4.00,'3'),(954,4.00,'3'),(955,4.00,'2'),(956,4.00,'2'),(957,4.00,'1'),(958,4.00,'1'),(959,4.00,'2'),(960,4.00,'3'),(961,4.00,'2'),(962,4.00,'1'),(963,4.00,'1'),(964,4.00,'3'),(965,4.00,'2'),(966,4.00,'3'),(967,4.00,'3'),(968,4.00,'3'),(969,4.00,'1'),(970,4.00,'1'),(971,4.00,'3'),(972,4.00,'3'),(973,4.00,'3'),(974,4.00,'2'),(975,4.00,'1'),(976,4.00,'1'),(977,4.00,'2'),(978,4.00,'1'),(979,4.00,'1'),(980,4.00,'2'),(981,4.00,'1'),(982,4.00,'3'),(983,4.00,'2'),(984,4.00,'1'),(985,4.00,'2'),(986,4.00,'2'),(987,4.00,'2'),(988,4.00,'2'),(989,4.00,'1'),(990,4.00,'3'),(991,4.00,'1'),(992,4.00,'3'),(993,4.00,'1'),(994,4.00,'2'),(995,4.00,'2'),(996,4.00,'3'),(997,4.00,'3'),(998,4.00,'2'),(999,4.00,'2');
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
  `Grade` varchar(2) NOT NULL,
  PRIMARY KEY (`EnrollmentID`),
  KEY `OfferID_idx` (`OfferID`),
  KEY `UIN_idx` (`UIN`),
  KEY `Grade_idx` (`Grade`),
  CONSTRAINT `Grade` FOREIGN KEY (`Grade`) REFERENCES `gradingsystem` (`Grade`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `OfferID` FOREIGN KEY (`OfferID`) REFERENCES `coursesoffered` (`OfferID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `StudentUIN` FOREIGN KEY (`UIN`) REFERENCES `people` (`UIN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=248 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentenrollment`
--

LOCK TABLES `studentenrollment` WRITE;
/*!40000 ALTER TABLE `studentenrollment` DISABLE KEYS */;
INSERT INTO `studentenrollment` VALUES (5,600,414,'A'),(6,600,410,'A-'),(7,584,410,'A-'),(10,589,410,'B+'),(12,598,410,'A'),(13,602,410,'B+'),(14,817,411,'A'),(15,890,411,'A'),(16,902,411,'A'),(17,835,411,'A'),(18,840,411,'A'),(19,954,414,'A'),(20,991,414,'A'),(21,804,414,'A'),(22,760,414,'A'),(23,807,415,'A'),(24,766,415,'A'),(25,929,415,'A'),(26,977,415,'A'),(27,956,415,'A'),(28,729,416,'A'),(29,959,416,'A'),(30,891,416,'A'),(31,980,416,'A'),(32,942,416,'A'),(33,888,417,'A'),(34,728,417,'A'),(35,782,417,'A'),(36,842,417,'A'),(37,757,417,'A'),(38,940,418,'A'),(39,984,418,'A'),(40,823,418,'A'),(41,874,418,'A'),(42,600,418,'A'),(43,999,419,'A'),(44,599,419,'A'),(45,584,419,'A'),(46,746,419,'A'),(47,957,419,'A'),(48,851,420,'A'),(49,788,420,'A'),(50,893,420,'A'),(51,841,420,'A'),(52,745,420,'A'),(53,889,421,'A'),(54,872,421,'A'),(55,845,421,'A'),(56,892,421,'A'),(57,762,421,'A'),(58,843,422,'A'),(59,953,422,'A'),(60,819,422,'A'),(61,710,422,'A'),(62,926,422,'A'),(63,838,423,'A'),(64,852,423,'A'),(65,772,423,'A'),(66,822,423,'A'),(67,968,423,'A'),(68,970,424,'A'),(69,763,424,'A'),(70,713,424,'A'),(71,886,424,'A'),(72,748,424,'A'),(73,868,425,'A'),(74,821,425,'A'),(75,800,425,'A'),(76,768,425,'A'),(77,982,425,'A'),(78,765,426,'A'),(79,866,426,'A'),(80,795,426,'A'),(81,850,426,'A'),(82,963,426,'A'),(83,798,427,'A'),(84,803,427,'A'),(85,759,427,'A'),(86,909,427,'A'),(87,589,427,'A'),(88,958,428,'A'),(89,877,428,'A'),(90,769,428,'A'),(91,598,428,'A'),(92,859,428,'A'),(93,811,429,'A'),(94,767,429,'A'),(95,753,429,'A'),(96,939,429,'A'),(97,900,429,'A'),(98,988,430,'A'),(99,923,430,'A'),(100,812,430,'A'),(101,716,430,'A'),(102,894,430,'A'),(103,832,431,'A'),(104,720,431,'A'),(105,715,431,'A'),(106,867,431,'A'),(107,808,431,'A'),(108,777,432,'A'),(109,742,432,'A'),(110,755,432,'A'),(111,790,432,'A'),(112,873,432,'A'),(113,749,433,'A'),(114,813,433,'A'),(115,990,433,'A'),(116,774,433,'A'),(117,876,433,'A'),(118,941,434,'A'),(119,947,434,'A'),(120,791,434,'A'),(121,979,434,'A'),(122,895,434,'A'),(123,884,435,'A'),(124,897,435,'A'),(125,949,435,'A'),(126,857,435,'A'),(127,904,435,'A'),(128,825,436,'A'),(129,730,436,'A'),(130,806,436,'A'),(131,961,436,'A'),(132,751,436,'A'),(133,747,437,'A'),(134,908,437,'A'),(135,865,437,'A'),(136,906,437,'A'),(137,733,437,'A'),(138,993,438,'A'),(139,799,438,'A'),(140,849,438,'A'),(141,901,438,'A'),(142,844,438,'A'),(143,780,439,'A'),(144,833,439,'A'),(145,960,439,'A'),(146,997,439,'A'),(147,740,439,'A'),(148,969,440,'A'),(149,930,440,'A'),(150,918,440,'A'),(151,761,440,'A'),(152,938,440,'A'),(153,964,441,'A'),(154,836,441,'A'),(155,734,441,'A'),(156,718,441,'A'),(157,924,441,'A'),(158,885,442,'A'),(159,796,442,'A'),(160,709,442,'A'),(161,831,442,'A'),(162,943,442,'A'),(163,846,443,'A'),(164,898,443,'A'),(165,972,443,'A'),(166,743,443,'A'),(167,896,443,'A'),(168,816,444,'A'),(169,848,444,'A'),(170,882,444,'A'),(171,776,444,'A'),(172,950,444,'A'),(173,764,445,'A'),(174,965,445,'A'),(175,905,445,'A'),(176,712,445,'A'),(177,588,445,'A'),(178,983,446,'A'),(179,727,446,'A'),(180,707,446,'A'),(181,789,446,'A'),(182,711,446,'A'),(183,966,447,'A'),(184,601,447,'A'),(185,955,447,'A'),(186,724,447,'A'),(187,928,447,'A'),(188,992,448,'A'),(189,731,448,'A'),(190,971,448,'A'),(191,973,448,'A'),(192,778,448,'A'),(193,786,449,'A'),(194,925,449,'A'),(195,875,449,'A'),(196,948,449,'A'),(197,864,449,'A'),(198,985,450,'A'),(199,771,450,'A'),(200,839,450,'A'),(201,787,450,'A'),(202,723,450,'A'),(203,855,451,'A'),(204,814,451,'A'),(205,996,451,'A'),(206,815,451,'A'),(207,914,451,'A'),(208,967,452,'A'),(209,934,452,'A'),(210,758,452,'A'),(211,824,452,'A'),(212,854,452,'A'),(213,989,453,'A'),(214,722,453,'A'),(215,986,453,'A'),(216,708,453,'A'),(217,962,453,'A'),(218,719,454,'A'),(219,944,454,'A'),(220,871,454,'A'),(221,820,454,'A'),(222,602,454,'A'),(223,862,455,'A'),(224,797,455,'A'),(225,912,455,'A'),(226,921,455,'A'),(227,717,455,'A'),(228,952,456,'A'),(229,794,456,'A'),(230,736,456,'A'),(231,785,456,'A'),(232,805,456,'A'),(233,907,457,'A'),(234,919,457,'A'),(235,879,457,'A'),(236,732,457,'A'),(237,903,457,'A'),(238,975,458,'A'),(239,750,458,'A'),(240,916,458,'A'),(241,911,458,'A'),(242,830,458,'A'),(243,920,459,'A'),(244,936,459,'A'),(245,935,459,'A'),(246,826,459,'A'),(247,976,459,'A');
/*!40000 ALTER TABLE `studentenrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachingassistant`
--

DROP TABLE IF EXISTS `teachingassistant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teachingassistant` (
  `TaUIN` int(12) NOT NULL,
  `OfferID` int(12) NOT NULL,
  `TaOfficeHours` varchar(45) NOT NULL DEFAULT 'TBD',
  `TaOfficeLocation` varchar(45) NOT NULL DEFAULT 'TBD',
  PRIMARY KEY (`TaUIN`,`OfferID`),
  KEY `TaUIN_idx` (`TaUIN`),
  KEY `TaOfferID_idx` (`OfferID`),
  CONSTRAINT `TaOfferID` FOREIGN KEY (`OfferID`) REFERENCES `coursesoffered` (`OfferID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TaUIN` FOREIGN KEY (`TaUIN`) REFERENCES `student` (`UIN`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachingassistant`
--

LOCK TABLES `teachingassistant` WRITE;
/*!40000 ALTER TABLE `teachingassistant` DISABLE KEYS */;
INSERT INTO `teachingassistant` VALUES (585,410,'TB decided','TBD'),(714,421,'TBD','TBD'),(721,444,'TBD','TBD'),(726,425,'TBD','TBD'),(735,455,'TBD','TBD'),(741,441,'TBD','TBD'),(756,434,'TBD','TBD'),(770,424,'TBD','TBD'),(784,436,'TBD','TBD'),(792,419,'TBD','TBD'),(793,423,'TBD','TBD'),(801,415,'TBD','TBD'),(810,449,'TBD','TBD'),(818,447,'TBD','TBD'),(827,438,'TBD','TBD'),(828,450,'TBD','TBD'),(829,445,'TBD','TBD'),(834,430,'TBD','TBD'),(837,452,'TBD','TBD'),(853,417,'TBD','TBD'),(856,439,'TBD','TBD'),(860,451,'TBD','TBD'),(863,442,'TBD','TBD'),(869,456,'TBD','TBD'),(878,448,'TBD','TBD'),(881,414,'TBD','TBD'),(883,420,'TBD','TBD'),(887,458,'TBD','TBD'),(899,435,'TBD','TBD'),(913,410,'TBD','TBD'),(915,443,'TBD','TBD'),(917,411,'TBD','TBD'),(922,440,'TBD','TBD'),(927,453,'TBD','TBD'),(937,432,'TBD','TBD'),(951,429,'TBD','TBD'),(978,437,'TBD','TBD'),(981,416,'TBD','TBD'),(987,427,'TBD','TBD'),(995,459,'TBD','TBD'),(998,426,'TBD','TBD');
/*!40000 ALTER TABLE `teachingassistant` ENABLE KEYS */;
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
-- Table structure for table `tiq2754371`
--

DROP TABLE IF EXISTS `tiq2754371`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiq2754371` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `TIQ2754371studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TIQ2754371studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiq2754371`
--

LOCK TABLES `tiq2754371` WRITE;
/*!40000 ALTER TABLE `tiq2754371` DISABLE KEYS */;
INSERT INTO `tiq2754371` VALUES (733,137,0.0,0.0,0.0),(747,133,0.0,0.0,0.0),(865,135,0.0,0.0,0.0),(906,136,0.0,0.0,0.0),(908,134,0.0,0.0,0.0);
/*!40000 ALTER TABLE `tiq2754371` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiq2754371structure`
--

DROP TABLE IF EXISTS `tiq2754371structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiq2754371structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiq2754371structure`
--

LOCK TABLES `tiq2754371structure` WRITE;
/*!40000 ALTER TABLE `tiq2754371structure` DISABLE KEYS */;
INSERT INTO `tiq2754371structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `tiq2754371structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vsf1464291`
--

DROP TABLE IF EXISTS `vsf1464291`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vsf1464291` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `VSF1464291studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `VSF1464291studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vsf1464291`
--

LOCK TABLES `vsf1464291` WRITE;
/*!40000 ALTER TABLE `vsf1464291` DISABLE KEYS */;
INSERT INTO `vsf1464291` VALUES (753,95,0.0,0.0,0.0),(767,94,0.0,0.0,0.0),(811,93,0.0,0.0,0.0),(900,97,0.0,0.0,0.0),(939,96,0.0,0.0,0.0);
/*!40000 ALTER TABLE `vsf1464291` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vsf1464291structure`
--

DROP TABLE IF EXISTS `vsf1464291structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vsf1464291structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vsf1464291structure`
--

LOCK TABLES `vsf1464291structure` WRITE;
/*!40000 ALTER TABLE `vsf1464291structure` DISABLE KEYS */;
INSERT INTO `vsf1464291structure` VALUES ('assign1',20),('assign2',20),('assign3',20);
/*!40000 ALTER TABLE `vsf1464291structure` ENABLE KEYS */;
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
INSERT INTO `waitlist` VALUES (585,410,1);
/*!40000 ALTER TABLE `waitlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `whm1644361`
--

DROP TABLE IF EXISTS `whm1644361`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `whm1644361` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `WHM1644361studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `WHM1644361studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `whm1644361`
--

LOCK TABLES `whm1644361` WRITE;
/*!40000 ALTER TABLE `whm1644361` DISABLE KEYS */;
INSERT INTO `whm1644361` VALUES (730,129,0.0,0.0,0.0),(751,132,0.0,0.0,0.0),(806,130,0.0,0.0,0.0),(825,128,0.0,0.0,0.0),(961,131,0.0,0.0,0.0);
/*!40000 ALTER TABLE `whm1644361` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `whm1644361structure`
--

DROP TABLE IF EXISTS `whm1644361structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `whm1644361structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `whm1644361structure`
--

LOCK TABLES `whm1644361structure` WRITE;
/*!40000 ALTER TABLE `whm1644361structure` DISABLE KEYS */;
INSERT INTO `whm1644361structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `whm1644361structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wym8514441`
--

DROP TABLE IF EXISTS `wym8514441`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wym8514441` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `WYM8514441studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `WYM8514441studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wym8514441`
--

LOCK TABLES `wym8514441` WRITE;
/*!40000 ALTER TABLE `wym8514441` DISABLE KEYS */;
INSERT INTO `wym8514441` VALUES (776,171,0.0,0.0,0.0),(816,168,0.0,0.0,0.0),(848,169,0.0,0.0,0.0),(882,170,0.0,0.0,0.0),(950,172,0.0,0.0,0.0);
/*!40000 ALTER TABLE `wym8514441` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wym8514441structure`
--

DROP TABLE IF EXISTS `wym8514441structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wym8514441structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wym8514441structure`
--

LOCK TABLES `wym8514441structure` WRITE;
/*!40000 ALTER TABLE `wym8514441structure` DISABLE KEYS */;
INSERT INTO `wym8514441structure` VALUES ('assign1',50),('assign2',50),('assign3',50);
/*!40000 ALTER TABLE `wym8514441structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yyn6874241`
--

DROP TABLE IF EXISTS `yyn6874241`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yyn6874241` (
  `StudentUIN` int(12) NOT NULL,
  `StudentEnrollmentID` int(12) NOT NULL,
  `assign1` decimal(4,1) DEFAULT '0.0',
  `assign2` decimal(4,1) DEFAULT '0.0',
  `assign3` decimal(4,1) DEFAULT '0.0',
  PRIMARY KEY (`StudentUIN`),
  KEY `StudentID_idx` (`StudentUIN`),
  KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`),
  CONSTRAINT `YYN6874241studentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `YYN6874241studentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yyn6874241`
--

LOCK TABLES `yyn6874241` WRITE;
/*!40000 ALTER TABLE `yyn6874241` DISABLE KEYS */;
INSERT INTO `yyn6874241` VALUES (713,70,0.0,0.0,0.0),(748,72,0.0,0.0,0.0),(763,69,0.0,0.0,0.0),(886,71,0.0,0.0,0.0),(970,68,0.0,0.0,0.0);
/*!40000 ALTER TABLE `yyn6874241` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yyn6874241structure`
--

DROP TABLE IF EXISTS `yyn6874241structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yyn6874241structure` (
  `ExamName` varchar(20) NOT NULL DEFAULT '',
  `TotalMarks` int(12) DEFAULT NULL,
  PRIMARY KEY (`ExamName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yyn6874241structure`
--

LOCK TABLES `yyn6874241structure` WRITE;
/*!40000 ALTER TABLE `yyn6874241structure` DISABLE KEYS */;
INSERT INTO `yyn6874241structure` VALUES ('assign1',40),('assign2',40),('assign3',40);
/*!40000 ALTER TABLE `yyn6874241structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'university'
--

--
-- Dumping routines for database 'university'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-30 17:53:57
