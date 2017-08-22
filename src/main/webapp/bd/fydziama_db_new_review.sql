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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id_review` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL DEFAULT '1',
  `review` varchar(255) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_review`),
  UNIQUE KEY `id_review_UNIQUE` (`id_review`),
  KEY `fk_user_index` (`id_user`),
  CONSTRAINT `fk_user_review` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (14,1,'Велике дякую дуже смачни суши...повару дякую теж','Юлія Сич'),(15,1,'Дуже смачні суши))) Велике дякую)','Юлька Лєбєдєва'),(16,1,'Віталя))) вкушняшкааа)) смачнючі роли!','Елена Кацюк'),(17,1,'Насправді смачно і швидко готують... і дуже привітний персонал!','Елена Апарович'),(18,1,'дуже смачно!','Олександр Килюшик'),(19,1,'Круто, смачно, дуже сподобалося)','Андрій Міхеєв'),(20,1,'Дякую) Дуже смачні суши)','Влад Антонюк'),(21,1,'очень понравилось рекомендую заглянуть,спасибо)','Виктор Мелика'),(22,1,'Спасибо большое суши супер очень очень вкусные!!!Всем Рекомендую,самые вкусные суши что я ела! ОБЪЕДЕНИЕ !!!','Дашуня Дашкина'),(23,1,'І мені дуже смачні суші були..обов\'язково буду у Вас ще замовляти..Молодці що відкрились..Побільше Вам клієнтів..)','Тетяна Мордас'),(24,1,'Дякую. смачніших суш я ще не куштувала...Дуже дякую що покормили. Тепер обідаємо лише з вами.','Юлія Скібчик'),(25,1,'Дякую дуже смачні суші)','Тошка Закревский'),(26,1,'Спасибо за суши.....очень вкусные!!Молодцы!!!!Побольше вам клиентов!!! ;)','Катенька Фильченко'),(27,1,'Суши дійсно дуже дуже дуже смачні, гарне упакування , ціни вражаючи, не дорого, тай персонал дуже приємний, ) нарешті в нас в місті є професійний суші-майстер)','Мария Буханько'),(28,1,'Дякую за суші. Повечеряла вкусненько. Дуже смачні','Наташа Луцюк'),(29,1,'Скажіть будь ласка до якого часу у вас дійсна знижка 20%','Анна Мохнар-Шумык'),(30,1,'Свята вечеря від Віталіка. Спасибі велике вони нереально смачнючі','Ірена Хондока'),(31,1,'смачні нам сподобалися!!!! дякую','Юлия Забияка'),(32,1,'Як смачно було.дякую','Юлія Сич'),(33,1,'Ваши суши просто великолепные, спасибо огромное за профессионализм, отдачу любимому делу, Вы огромные молодцы! Хочу еще!','Настя Адожина'),(34,1,'Ааа я стала 666 учасником спільноти 0_0. Та лад. Щойно повечеряла ролами. Це просто бомба!!! В нашому місті я точно таких не іла ;). Дякую!!!','Галчёнок Огребчук'),(35,1,'Рібята в Фудзияма реально чьоткі суші не то шо в остальних местах в кузні де їх роблять.Респект а ще затишно в закладі.','Влад Ковальчук'),(36,1,'В мене скоро будуть плюси в моіх кілограмах від ваших абалдєнно смачнючих суш!:) Надіюсь привіт від мене передали','Ірена Хондока'),(38,1,'Good job.','nina'),(40,1,'Hello to Fudgiyama personal!I newer tried your sushi,\r\nbut i\'m sure your stuff is awesome, even in US everyone talking about you guys. God bless you all.','Alex Shkinder'),(41,1,'Роллы вкусные, заставляют приходить еще и еще). Спасибо!','Татьяна'),(42,1,'ДУЖЕ СМАЧНО!ДЯКУЮ!!!ТАК ТРИМАТИ...','АЛЛА'),(43,1,'Посетила вчера первый раз \"Фудзияму\",все понравилось,роллы по вкусовым качествам и по внешнему виду ну просто превосходны,спасибо было очень вкусно','Ленка Дембовская'),(44,1,'Спасибо Вам!Всё было на высшем уровне! Так держать!!!Молодцы!!!','Julia Kupchishina'),(45,1,'Дякую,дуже смачно.','Артур Цюсьмак'),(46,1,'Спасибо.Всё было очень вкусно.','Боря Николаев'),(47,1,'Дуже вам дякуємо,суші просто неперевершені.','Viktoriya Kaminska'),(48,1,'Суши дуже смачні.надзвичайні.хто ще небув рекомендую відвідати.','Swetka Susenkowa'),(49,1,'І знову дякую','Артур Цюсьмак'),(50,1,'Знову дякую','Viktoriya Kaminska'),(51,1,'Благодаря суши-Бар \"ФудзиямА\", день Рождения любимой подружки прошел ярко, празднично и очень вкусно! ;)! Огромное СПАСИБО!','Таня Гриневич'),(52,1,'Нам очень понравилось Витасик;)спасибо большое,приятно за внимание','Ленка Дембовская'),(53,1,'Смакота!!!Дякую!!)))','Ира Хандучка'),(54,1,'Виталій, вельми вдячна тобі за добре,якісно й смачно приготовані суші....Дякую Вам з мамою за привітність й хороше обслуговування))))','Софія Прісакарь'),(55,1,'Віталік, дякую за гарячі роли, вони були неперевершені...втім як і всі інші...смакота=))','Маша Бабецька'),(56,1,'Дякуюмо, Віталя! Дуже приємно та як завжди смачно!','Юлія Никитюк'),(57,1,'Файні','Александр Сергеевич'),(58,1,'СПОДОБАЛОСЪ...!!!','Олександр Шемедюк'),(59,1,'Эх, хочу ещё ... зачётные... ???? Спасибо !!!','Сашок Малько'),(60,1,'Дякую Вітальос за смачні новинки','Мирослава Дембовська'),(61,1,'Просто омномном було,дякуемо','Людмила Христенко'),(62,1,'Дуже дякуєм за суші!!! Смачніше просто не буває!!!','Indi Gou'),(63,1,'Як завжди смачно','Ярослава Викторова'),(64,1,'Приємна атмосфера, хороший настрій і неймовірно смачні суші!!!','Наталья Калюх'),(65,1,'Смакотулька))) Дякуємо!','Людмила Фасішевська'),(66,1,'Дуже смачні суші,вже не вперше відвідую заклад.Хочеться ще і ще.Продовжуйте в тому ж дусі.','Дарина'),(67,1,'Дуже вкусні луче чим в Маєтку','Міша'),(68,1,'БОЛЬШОЕ СПАСИБО,РЕАЛЬНО ВКУСНО..','Марина Волкова'),(69,1,'Зачётный краб ...','Сашок Малько'),(70,1,'спасибо. как всегда очень вкусно)) Суши-Бар \"ФудзиямА\" КУЗНЕЦОВСК №1','Юлія Никитюк'),(71,1,'Спасибо за вкусняхи!!!)))','Марго Деревянчук'),(73,1,'Найкращі суші в світі!','Анна Королёва'),(74,1,'Дякую за смакоту як завжди непеоревершенні суші','Юлія Сич'),(75,1,'бесподобные)','Тая Кошмак'),(76,1,'нямняхі)','Юлія Миронюк'),(77,1,'Найкращі','Анастасія Салівоник'),(78,1,'палички проковтнеш, так смачно!) найкращі суші у нашому місті!!!!','Вика Матерьян'),(79,1,'Чудово провели вечір ) Дуже вам вдячні','Андрей Ефремов'),(80,1,'Дякую за суші...як завжди неперевершені...','Viktoriya Kaminska'),(81,1,'Дякую за суші.просто best!!!','Bogdan Chirva'),(82,1,'Дякую за суші.  Дуже смачні!!!','Лєра Турик'),(83,1,'Суші були неперевершені,дякую велике)','Катя Скібчик'),(84,1,'Все було дуже смачно)))) Лапша просто супер. Ням-ням','Мирослава Дембовська'),(85,1,'Дуже дякую за суші! Було дуже смачно, не встигла сфотографувати як все з\'їла)))','Діана Ляшик'),(86,1,'Було дуже смачно! Я рекоминдую цей суши-бар Фудзіяма!!!','Назар Гуцман'),(87,1,'Дякую за смакоту','Іра Ковальчук'),(88,1,'Дякую!! Було дуже смачно)','Дарина Ящук'),(89,1,'Ви найкращі))))))','Настя Дячишин'),(90,1,'Дякую)Було дуже смачно','Даша Москвич'),(91,1,'Ну дуже дякую)))','Юлія Скібчик'),(92,1,'Как всегда без слов)#самые#бомбезные','Анна Королёва'),(93,1,'Виграла в конкурсі. Як завжди дужее смачно, просто бомба Дякуюю))','Ксения Чирук'),(94,1,'Смакота)','Ирина Ляшко'),(95,1,'Ух...','Сашок Малько'),(96,1,'Конкурс виграла) дякую , було дуже смачно:)','Элеонора Андреева'),(97,1,'дуже класні, рекомендую','марія'),(98,1,'Як завжди суперррові)))','Аліна Скіба'),(99,1,'Було дуже смачно!','Karina Skiba'),(100,1,'СПАСИБКИ ЗА ОБЕД! ОЧЕНЬ ВКУСНО! КАК ВСЕГДА ВЫ ЛУЧШИЕ!','Елена Савицкая'),(101,1,'Було дуже смачно)) Дякую.','Людмила Яковчук'),(102,1,'як завжди вони смачнющі!!!  подивилася фото і знову захотілося)','Вика Матерьян'),(105,1,'Тёплая и почти домашняя обстановка. И как всегда, мои любимые вкусности. Спасибо вам за приятные моменты, проведенные в вашем заведении. С наступающим Новым годом. Процветания и по больше клиентов!)','Елена Савицкая'),(107,1,'Найсмачніші. Спробуйте! Ви не пошкодуєте.','Виктория Рудник'),(108,1,'Найкращі і найсмачніші.','Альона Костюк'),(109,1,'Дякую ,наїлися','Дарина Боцула'),(110,1,'Найкращі суші в місті) Місо суп ульот!!!','Сергей Оноприйчук'),(111,1,'Приємна атмосфера закладу, чудовий колектив та найсмачніші суші!)','Анна Случик'),(112,1,'Файний сет і головне ситний дуже…','Сашок Малько'),(114,1,'Дуже смачний рол))','Олічка Kanfetochka'),(115,1,'Було дуже смачно, дякуєм за теплий прийом.','Люда Вейна'),(116,1,'Все сподобалось як працівники такий суші. Буду всім знайомим рекомендувати.','Калушечка'),(117,1,'Сьогодні заходив забирати роли, потрібно було ще зачекати, - запропонували каву. Такого смачного Амерікано з молоком я ще не пив!!! І роли ДУЖЕ смачні!!!','Esfil');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-22 11:30:36
