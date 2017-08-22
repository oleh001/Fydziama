-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: fydziama_db_new
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `good`
--

DROP TABLE IF EXISTS `good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `good` (
  `id_good` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL DEFAULT 'no_image.jpg',
  `id_brand` int(11) NOT NULL,
  `gram` smallint(11) NOT NULL DEFAULT '0',
  `anons` text NOT NULL,
  `content` text NOT NULL,
  `visible` enum('TRUE','FALSE') NOT NULL DEFAULT 'TRUE',
  `hits` enum('TRUE','FALSE') NOT NULL DEFAULT 'FALSE',
  `new` enum('TRUE','FALSE') NOT NULL DEFAULT 'FALSE',
  `sale` enum('TRUE','FALSE') NOT NULL DEFAULT 'FALSE',
  `price` float NOT NULL DEFAULT '0',
  `date` datetime NOT NULL,
  `img_slide` varchar(255) DEFAULT NULL,
  `avg_rating` int(5) DEFAULT '0',
  `total_vote_count` bigint(20) DEFAULT '0',
  `total_rating` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id_good`),
  UNIQUE KEY `id_good_UNIQUE` (`id_good`),
  KEY `fk_brand_index` (`id_brand`),
  CONSTRAINT `fk_brand_good` FOREIGN KEY (`id_brand`) REFERENCES `brand` (`id_brand`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `good`
--

LOCK TABLES `good` WRITE;
/*!40000 ALTER TABLE `good` DISABLE KEYS */;
INSERT INTO `good` VALUES (2,'good1','no_image.jpg',2,0,'fghj','cfgvjhj','TRUE','FALSE','TRUE','FALSE',0,'2017-07-13 00:00:00',NULL,0,0,0),(3,'dxfcgvh','no_image.jpg',1,0,'fcghjk','cghjmb','TRUE','TRUE','FALSE','TRUE',0,'2017-07-11 00:00:00',NULL,0,0,0),(4,'xfghj','no_image.jpg',2,0,'fgvhj','fhgfhj','TRUE','FALSE','TRUE','FALSE',0,'2017-07-04 00:00:00',NULL,0,0,0),(5,'dftyujk','no_image.jpg',1,2,'fghjkj','tgyhj','TRUE','TRUE','FALSE','TRUE',25,'1993-01-01 00:00:00','nm',0,0,0);
/*!40000 ALTER TABLE `good` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-22 11:30:35
