-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: desafio_sicredi
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `pauta`
--

DROP TABLE IF EXISTS `pauta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pauta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dutwo9we9e8lilucma56w4y05` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pauta`
--

LOCK TABLES `pauta` WRITE;
/*!40000 ALTER TABLE `pauta` DISABLE KEYS */;
INSERT INTO `pauta` VALUES (4,'Pauta 3475'),(1,'Pauta 4650sf'),(2,'Pauta 465mb0sf'),(5,'Pauta adloaknsd '),(6,'Pautaaa '),(3,'teste swagger');
/*!40000 ALTER TABLE `pauta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessao`
--

DROP TABLE IF EXISTS `sessao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sessao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_fim` datetime(6) DEFAULT NULL,
  `data_inicio` datetime(6) DEFAULT NULL,
  `pauta_id` bigint NOT NULL,
  `enviado_para_topico` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i4y3uuovrr67jtijklx0ccpxu` (`pauta_id`),
  CONSTRAINT `FKbc3ehywka7s7yk4j1bb51hgnf` FOREIGN KEY (`pauta_id`) REFERENCES `pauta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessao`
--

LOCK TABLES `sessao` WRITE;
/*!40000 ALTER TABLE `sessao` DISABLE KEYS */;
INSERT INTO `sessao` VALUES (1,'2022-04-12 23:10:02.541000','2022-04-12 23:09:02.541000',1,NULL),(2,'2022-04-12 23:15:31.160000','2022-04-12 23:14:31.160000',2,NULL),(3,'2022-04-12 23:37:20.033000','2022-04-12 23:36:20.033000',3,NULL),(4,'2022-04-13 07:37:10.429000','2022-04-13 07:35:10.429000',4,_binary ''),(5,'2022-04-13 10:27:11.223000','2022-04-13 10:22:11.223000',5,_binary ''),(6,'2022-04-13 10:42:24.255000','2022-04-13 10:37:24.255000',6,_binary '');
/*!40000 ALTER TABLE `sessao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voto`
--

DROP TABLE IF EXISTS `voto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `escolha` varchar(255) DEFAULT NULL,
  `id_associado` varchar(255) DEFAULT NULL,
  `pauta_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfgj7pqu54afvx0dpuf0ecxox5` (`pauta_id`),
  CONSTRAINT `FKfgj7pqu54afvx0dpuf0ecxox5` FOREIGN KEY (`pauta_id`) REFERENCES `pauta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voto`
--

LOCK TABLES `voto` WRITE;
/*!40000 ALTER TABLE `voto` DISABLE KEYS */;
INSERT INTO `voto` VALUES (1,'NÃO','82379511071',1),(2,'SIM','05547413500',1),(3,'NÃO','71886631069',4),(4,'SIM','05547413500',4),(5,'NÃO','65750193071',5),(6,'NÃO','34624475054',5),(7,'NÃO','03975404000',5),(8,'SIM','84397195072',5),(9,'SIM','15521546022',5),(10,'SIM','71351079000',5),(11,'SIM','52190056004',5);
/*!40000 ALTER TABLE `voto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-13 11:19:37
