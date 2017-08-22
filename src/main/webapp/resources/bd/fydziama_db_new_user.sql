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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT 'Null',
  `email` varchar(255) DEFAULT 'Null',
  `phone` varchar(255) DEFAULT 'Null',
  `address` varchar(255) DEFAULT 'Null',
  `login` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `id_role` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  KEY `fk_role_index` (`id_role`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Костюк Олег Васильович','olehkostiuk001@ukr.net','0977164146','м. Івано-Франківськ вул. Шевченка 8','klass','fcf1d8d2f36c0cde8eca4b86a8fe1df8',1),(2,'Адміністратор','','Null','Null','admin','fcf1d8d2f36c0cde8eca4b86a8fe1df8',2),(3,'Савчишин Сергей Васильевич','semart3@gmail.com','+380680603399','г.Кузнецовск ул.Энергетиков,17 - 130','semart3','5127c215c07e5f1377391615c9be988a',1),(4,'Шиков Денис','panmartun@gmail.com','+380961128925','м-н Вараш','PanMartun','88dab1e7011c73d69c0b10de27afe80f',1),(5,'nina shkinder','ninashkinder@yahoo.com','503 780 0852','portland','nina','de13f7eeea023bda961057e7ffa37d13',1),(6,'Костюк Ігор Васильович','ihor_kos@bigmir.net','','NULL','igariok_kos777','bbb86e541390afb48d01f7f77c6bd75a',2),(7,'Dimon Krol Vasilevich','dktol12@gmail.com','+380687676759','м. Кузнецовськ, м-н Вараш 45а, кв.37.','dktol12','9838103c368f5f67a077525f3e9b555e',1),(8,'Столяр Валерій Віталійович','Dyed_from_STOC@ukr.net','0978903095','м. Кузнецовськ, м-н Будівельників, буд. 26/2','gusigusigagaga','2a78c45ad14546371b19dd1e4367abd9',1),(9,'Лалак Віталій Михайлович','granada7609@gmail.com','0971108097','Будівельників 25/1 кв.67','granada76','b64043125bd57608d5d9023bd003c56c',1),(10,'Огородник В.В.','jonikkotik@yandex.ru','0978458681','Вараш 4','jonik','9a85db6a0e0003fe1293737c39acc824',1),(11,'Клімук Люда','liuda-yuikjh@ukr.net','+380967342860','м-н Вараш 28А кв.26','liuda-yuikjh','7989800d1c61ed92466ec9b8f8e70e7c',1),(12,'Майданюк Андрій Юрійович','may02@ukr.net','0979455003','м.Кузнецовськ, м-н Будывельникыв, 27/2-27','SkyMay','76419c58730d9f35de7ac538c2fd6737',1),(13,'апрап','рап','рапр','апра','ввап','5d06c618a8469e7ac8aecb5e2de16274',1),(14,'Москвич Наталія Яківна','yamoskvichka@ mail.ru','0673097090','м.Вараш, м-н Перемоги, 21','yanataliya','5a8c90cc138a5a9b178579264c6fbc44',1),(15,'Постоева Валентина Юрьевна','','0979900399','самовывоз','Vally','7044f70755f972a2de3a6a879f34df68',1),(16,'Радчук Анна Василівна','ann.fly13@gmail.com','0971310680','Вараш 3','ann.vol4etska@yandex','0ce41b63800b64de6e1b731cb4ae2440',1),(17,'Churilovich Julia','juliachurilovich@gmail.com','0671583431','budivelnikiv 33/1','yulia','b0baee9d279d34fa1dfd71aadb908c3f',1),(18,'N.A.M.','anna23121977@gmail.com','066-972-96-23','Вараш','Anna','5537de1651b482080fa1dd927f7df290',1),(19,'Денис Завертаний Вячиславович','den.zavertaniy@mail.ru','+380969006617','вул.Енергетиків,буд 20\\2, кв 15','denis','c3d478fab2971a869fca1edd62b4730a',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-22 11:30:37
