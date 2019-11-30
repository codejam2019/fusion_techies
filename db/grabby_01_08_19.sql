-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: localhost    Database: grabby
-- ------------------------------------------------------
-- Server version	5.7.26

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
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,'Grabby'),(2,'Codepuran'),(3,'Tech Sanhita'),(4,'Gully Boy'),(5,'Aksh');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'English'),(2,'Gujarati'),(3,'Hindi'),(4,'Marathi');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) DEFAULT NULL,
  `content_type` int(11) NOT NULL,
  `text` mediumtext,
  `rich_text` mediumtext,
  `url` varchar(2000) DEFAULT NULL,
  `language_id` int(11) DEFAULT NULL,
  `likes` int(11) DEFAULT '0',
  `dis_like` int(11) DEFAULT '0',
  `download` int(11) DEFAULT '0',
  `shares` int(11) DEFAULT '0',
  `is_visible` tinyint(1) DEFAULT '1',
  `min_age` int(11) DEFAULT '0',
  `max_age` int(11) DEFAULT '150',
  `creation_date` datetime DEFAULT NULL,
  `views` int(11) DEFAULT '0',
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_post_language` (`language_id`),
  KEY `fk_post_owner` (`owner_id`),
  CONSTRAINT `fk_post_language` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_owner` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'Good Morning Message',0,'The morning is like a blank page. You need to fill the colours on it. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0,'2019-07-26 12:37:16',NULL,NULL),(2,'gm message for gf',0,'The morning has arrived and bring with it new hope and optimism. Good morning!','','https://www.143greetings.com/goodmorning/messages.html',NULL,NULL,NULL,NULL,NULL,1,0,0,'2019-07-26 12:39:19',NULL,NULL),(3,NULL,0,'It is a pleasant morning. May all your dreams come true. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0,'2019-07-26 12:40:39',NULL,NULL),(4,NULL,0,'Keep your eyes open and grab every opportunity that comes your way. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0,'2019-07-26 12:40:56',NULL,NULL),(5,'',0,'A new day has begun. So, enjoy each and every moment of the day. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:41:55',NULL,NULL),(6,'',0,'Always be happy. It will increase your lifespan. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:42:13',NULL,NULL),(7,'',0,'Time to get out of bed and welcome the new day with a smile on your face. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:42:34',NULL,NULL),(8,'',0,'A day has just 24 hours and time is flying fast. Make the most of your time by doing something productive. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:42:50',NULL,NULL),(9,NULL,0,'May this morning be as wonderful as your lovely smile. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:44:11',NULL,NULL),(10,NULL,0,'Just believe in yourself and you would be able to accomplish anything in life. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:44:25',NULL,NULL),(11,NULL,0,'It is a wonderful morning. Make the best use of it. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:44:42',NULL,NULL),(12,NULL,0,'Wake up with a smile on your face. The day would surely go well. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:44:55',NULL,NULL),(13,NULL,0,'May the new day bring you new opportunities. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:45:20',NULL,NULL),(14,NULL,0,'It is such a wonderful morning. So wake up and enjoy the day. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:45:42',NULL,NULL),(15,NULL,0,'The day has begun. So, wake up and do something extraordinary today. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:46:05',NULL,NULL),(16,NULL,0,'It is time to rise and shine. Good morning everyone.','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,35,'2019-07-26 12:46:17',NULL,NULL),(17,NULL,0,'May the day bring you joy and happiness. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,30,65,'2019-07-26 12:46:50',NULL,NULL),(18,NULL,0,'Start you day with a broad smile. It would surely bring a change. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,30,65,'2019-07-26 12:47:11',NULL,NULL),(19,NULL,0,'A morning walk is the best exercise to start your day. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,30,65,'2019-07-26 12:47:23',NULL,NULL),(20,NULL,0,'No matter how long and dark the night is, it will always be followed by sunshine. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,30,65,'2019-07-26 12:47:36',NULL,NULL),(21,NULL,0,'The sun has risen, so wake up and get to work. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,30,65,'2019-07-26 12:47:47',NULL,NULL),(22,NULL,0,'It is a beautiful day. Let us make it extra special by doing something extraordinary today. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,30,65,'2019-07-26 12:48:12',NULL,NULL),(23,NULL,0,'Life is short, so enjoy it to the fullest. good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,30,65,'2019-07-26 12:48:23',NULL,NULL),(24,NULL,0,'May each and every moment of the day bring you joy and happiness. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,30,65,'2019-07-26 12:48:33',NULL,NULL),(25,NULL,0,'May the day bring you new adventures and opportunities. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,30,65,'2019-07-26 12:48:46',NULL,NULL),(26,NULL,0,'The morning is like a blank page. It is up to you how colourful you make it. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:49:16',NULL,NULL),(27,NULL,0,'You have got immense talent but it is waiting to be discovered. So, keep up the good work and someday you will be noticed. Good morning','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:49:33',NULL,NULL),(28,NULL,0,'Begin every day with the thought that you can achieve the impossible and surely you would achieve your target. Good morning.','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:49:43',NULL,NULL),(29,NULL,0,'Do not brood over what you do not have but be thankful for what you have got. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:49:51',NULL,NULL),(30,NULL,0,'Every morning brings with it new challenges and opportunities. How you grab the opportunities and overcome the challenges is up to you. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:50:02',NULL,NULL),(31,NULL,0,'When I open my eyes and see your smiling face I know that the new day has begun. Love you sweetheart and a very good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:50:19',NULL,NULL),(32,NULL,0,'Hope you slept well and are feeling refreshed. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:50:32',NULL,NULL),(33,NULL,0,'Time is precious. Cherish it and use it wisely by doing something productive. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:50:46',NULL,NULL),(34,NULL,0,'To be successful in life all you need is hard work, determination, and perseverance. so, imbibe there three qualities in your life and you would definitely be successful. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:50:56',NULL,NULL),(35,NULL,0,'To be successful in life all you need is hard work, determination, and perseverance. so, imbibe there three qualities in your life and you would definitely be successful. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:51:15',NULL,NULL),(36,NULL,0,'My mornings have become so much better since you have come into my life. Thanks for being there, sweetheart!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:51:26',NULL,NULL),(37,NULL,0,'Waking up in the morning and seeing your smiling face is what really makes my day. Good morning, honey!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:51:43',NULL,NULL),(38,NULL,0,'Make the most of the day and use it wisely because life does not give a second chance. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:52:00',NULL,NULL),(39,NULL,0,'Before I found you my life was incomplete. You have filled my life with love and happiness, sweetheart. Good mornin!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:52:28',NULL,NULL),(40,NULL,0,'Give your day a meaning by setting a goal. Then work to achieve that goal. Wishing you a very good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:52:37',NULL,NULL),(41,NULL,0,'Live your life to the fullest. Work hard and party hard. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:52:52',NULL,NULL),(42,NULL,0,'May the pleasant morning bring you lots of happiness and joy that will last throughout the day. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:53:07',NULL,NULL),(43,NULL,0,'Do not be afraid of sunset because sunrise would soon follow and would bring along new hope and optimism. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:53:19',NULL,NULL),(44,NULL,0,'Get up early in the morning and strive to make your dream come true. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:53:30',NULL,NULL),(45,NULL,0,'Waking up early and sharing our coffee together is what really makes my day.\nGood morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:53:39',NULL,NULL),(46,NULL,0,'When I wake up in the morning and see your lovely face, I can feel the positive vibes rushing through my veins. Lucky to have you in my life sweetheart. \nGood morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:53:47',NULL,NULL),(47,NULL,0,'Yesterday is a thing of the past, so forget about the fight we had yesterday and start today on a positive note. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:54:05',NULL,NULL),(48,NULL,0,'Life is short and each second is precious, so make the most of it. Begin each day with a positive mind and a smile on your face. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:54:22',NULL,NULL),(49,NULL,0,'Every morning I pray to God for giving me such a wonderful friend like you. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:54:31',NULL,NULL),(50,NULL,0,'Every morning is beautiful. It is our attitude that makes our day good or bad. So, have a positive attitude and your day would surely be a good one. Good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:54:41',NULL,NULL),(51,NULL,0,'Sweetheart, I want to wake up with you beside me, for you are my lucky charm. Missing you badly, come back soon. Good Morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:55:06',NULL,NULL),(52,NULL,0,'A brand new day has arrived and brought with it brand new hopes. Keep a positive mindset and strive to turn your dreams into reality. \nGood morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:55:30',NULL,NULL),(53,NULL,0,'I only want to see your face when I open my eyes in the morning. You are my lucky charm dear; the one wh always keeps me motivated. \nGood morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:55:50',NULL,NULL),(54,NULL,0,'Wake up to a wonderful morning with a hot cup of coffee and fresh air. A very good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:56:02',NULL,NULL),(55,NULL,0,'I always want to wake up with your lovely face right in front of my eyes; it surely makes my day much better. Wishing you a very good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:56:13',NULL,NULL),(56,NULL,0,'The biggest treasure of our lives are our friends and loved ones. Always cherish them. Wishing you a very good morning!','',NULL,NULL,NULL,NULL,NULL,NULL,1,18,0,'2019-07-26 12:56:23',NULL,NULL),(57,'Kissi',0,'Kissi','',NULL,NULL,NULL,NULL,NULL,NULL,1,20,65,'2019-07-28 10:23:04',NULL,NULL),(58,'Kissi',0,'Kissi','',NULL,NULL,NULL,NULL,NULL,NULL,1,20,65,'2019-07-28 10:23:04',NULL,NULL),(59,'Kissi',0,'Kissi','',NULL,NULL,NULL,NULL,NULL,NULL,1,20,65,'2019-07-28 10:23:04',NULL,NULL),(60,'Kissi',0,'Kissi','',NULL,NULL,NULL,NULL,NULL,NULL,1,20,65,'2019-07-28 10:23:28',NULL,NULL);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_application`
--

DROP TABLE IF EXISTS `post_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_application` (
  `post_id` int(11) NOT NULL,
  `app_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`,`app_id`),
  KEY `app_id` (`app_id`),
  CONSTRAINT `post_application_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `application` (`id`),
  CONSTRAINT `post_application_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_application`
--

LOCK TABLES `post_application` WRITE;
/*!40000 ALTER TABLE `post_application` DISABLE KEYS */;
INSERT INTO `post_application` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(41,1),(42,1),(43,1),(44,1),(45,1),(46,1),(47,1),(48,1),(49,1),(50,1),(51,1),(52,1),(53,1),(54,1),(55,1),(56,1),(57,1),(58,1),(59,1),(60,1);
/*!40000 ALTER TABLE `post_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_tag`
--

DROP TABLE IF EXISTS `post_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_tag` (
  `post_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`,`tag_id`),
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `post_tag_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `post_tag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_tag`
--

LOCK TABLES `post_tag` WRITE;
/*!40000 ALTER TABLE `post_tag` DISABLE KEYS */;
INSERT INTO `post_tag` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,2),(28,2),(29,2),(30,2),(57,2),(58,2),(59,2),(60,2),(22,3),(23,3),(24,3),(25,3),(26,3),(14,7),(15,7),(16,7),(17,7),(35,8),(36,8),(37,9),(38,9),(18,10),(19,10),(20,10),(21,10),(39,11),(40,11),(41,12),(42,12),(43,12),(44,12),(45,12),(46,12),(47,13),(48,13),(49,13),(50,13),(51,13),(31,14),(32,14),(33,14),(34,14),(35,14),(36,14),(37,14),(38,14),(52,15),(53,15),(54,15),(55,15),(56,15),(2,16),(3,16),(4,16),(5,16),(6,16);
/*!40000 ALTER TABLE `post_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `is_visible` tinyint(1) NOT NULL,
  `parent_tag_id` int(11) DEFAULT NULL,
  `app_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tag_ibfk_1` (`parent_tag_id`),
  CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`parent_tag_id`) REFERENCES `tag` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'Morning',1,NULL,0),(2,'Kiss',1,NULL,0),(3,'Diwali',1,NULL,0),(4,'Dhanteras',1,3,0),(5,'Vagh Baras',1,3,0),(6,'Kali Chaudas',1,3,0),(7,'Valentine Week',1,NULL,0),(8,'Rose Day',1,7,0),(9,'Propose Day',1,7,0),(10,'Chocolate Day',1,7,0),(11,'Teddy Day',1,7,0),(12,'Promise Day',1,7,0),(13,'Hug Day',1,7,0),(14,'Kiss Day',1,7,0),(15,'Valentine\'s Day',1,7,0),(16,'Love',1,NULL,0);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `password` varchar(1000) DEFAULT NULL,
  `user_type` int(2) NOT NULL,
  `age` int(2) DEFAULT NULL,
  `gender` int(2) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Akshay','pethaniakshay','patelaksh412@gmail.com','7695525','123',1,25,1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_application`
--

DROP TABLE IF EXISTS `user_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_application` (
  `user_id` int(11) NOT NULL,
  `application_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`application_id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `user_application_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `user_application_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_application`
--

LOCK TABLES `user_application` WRITE;
/*!40000 ALTER TABLE `user_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_blocked_posts`
--

DROP TABLE IF EXISTS `user_blocked_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_blocked_posts` (
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`post_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `user_blocked_posts_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `user_blocked_posts_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_blocked_posts`
--

LOCK TABLES `user_blocked_posts` WRITE;
/*!40000 ALTER TABLE `user_blocked_posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_blocked_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_blocked_tag`
--

DROP TABLE IF EXISTS `user_blocked_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_blocked_tag` (
  `user_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`tag_id`),
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `user_blocked_tag_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_blocked_tag_ibfk_3` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_blocked_tag`
--

LOCK TABLES `user_blocked_tag` WRITE;
/*!40000 ALTER TABLE `user_blocked_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_blocked_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_disliked_posts`
--

DROP TABLE IF EXISTS `user_disliked_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_disliked_posts` (
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`post_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `user_disliked_posts_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `user_disliked_posts_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_disliked_posts`
--

LOCK TABLES `user_disliked_posts` WRITE;
/*!40000 ALTER TABLE `user_disliked_posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_disliked_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_language`
--

DROP TABLE IF EXISTS `user_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_language` (
  `user_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`language_id`),
  KEY `language_id` (`language_id`),
  CONSTRAINT `user_language_ibfk_1` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`),
  CONSTRAINT `user_language_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `language` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_language`
--

LOCK TABLES `user_language` WRITE;
/*!40000 ALTER TABLE `user_language` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_liked_posts`
--

DROP TABLE IF EXISTS `user_liked_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_liked_posts` (
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`post_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `user_liked_posts_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `user_liked_posts_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_liked_posts`
--

LOCK TABLES `user_liked_posts` WRITE;
/*!40000 ALTER TABLE `user_liked_posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_liked_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_preferred_tag`
--

DROP TABLE IF EXISTS `user_preferred_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_preferred_tag` (
  `user_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`tag_id`),
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `user_preferred_tag_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_preferred_tag_ibfk_3` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_preferred_tag`
--

LOCK TABLES `user_preferred_tag` WRITE;
/*!40000 ALTER TABLE `user_preferred_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_preferred_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-01 22:49:48
