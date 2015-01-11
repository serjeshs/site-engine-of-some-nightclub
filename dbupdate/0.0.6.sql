use afisha;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uriName` varchar(45) NOT NULL,
  `title` varchar(255) NOT NULL,
  `text` text NOT NULL,
  `Creator_AppUser_id` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifed` timestamp NOT NULL DEFAULT '2014-12-31 22:01:01',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
INSERT INTO `page` VALUES (1,'main','Главная страница','Добрый день.<br>\r\nПриветствую Вас на сайте, посвященному афишам.',2,'2015-01-08 08:56:21','2015-01-08 14:35:15'),(2,'about','О сайте','asdasd',2,'2015-01-11 16:41:20','2015-01-11 16:41:20');
/*!40000 ALTER TABLE `page` ENABLE KEYS */;
UNLOCK TABLES;
  
  CREATE TABLE `afisha`.`blog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `text` TEXT NOT NULL,
  `Creator_AppUser_id` INT NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `modifed` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));
	
