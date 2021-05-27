-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: healthcare
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `citas`
--

DROP TABLE IF EXISTS `citas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `citas` (
  `IdCita` int NOT NULL AUTO_INCREMENT,
  `Fecha` datetime NOT NULL,
  `Causa` varchar(100) NOT NULL,
  `Urgente` bit(1) NOT NULL DEFAULT b'0',
  `IdUsuario` int NOT NULL,
  `IdMedico` int NOT NULL,
  PRIMARY KEY (`IdCita`),
  KEY `FK_IdUsuario_citas_idx` (`IdUsuario`),
  KEY `FK_IdMedico_citas` (`IdMedico`),
  CONSTRAINT `FK_IdMedico_citas` FOREIGN KEY (`IdMedico`) REFERENCES `usuarios` (`IdUsuario`),
  CONSTRAINT `FK_IdUsuario_citas` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citas`
--

LOCK TABLES `citas` WRITE;
/*!40000 ALTER TABLE `citas` DISABLE KEYS */;
/*!40000 ALTER TABLE `citas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial` (
  `IdHistorial` int NOT NULL AUTO_INCREMENT,
  `Sintomas` varchar(300) NOT NULL,
  `Diagnostico` varchar(300) NOT NULL,
  `Tratamiento` varchar(300) NOT NULL,
  `FechaModificacion` datetime NOT NULL,
  `IdUsuario` int NOT NULL,
  PRIMARY KEY (`IdHistorial`),
  KEY `FK_IdUsuario_historial_idx` (`IdUsuario`),
  CONSTRAINT `FK_IdUsuario_historial` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial`
--

LOCK TABLES `historial` WRITE;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
INSERT INTO `historial` VALUES (25,'Dolor de cabeza','Migrañas','Paracetamol','2021-05-18 15:40:12',26),(26,'Inflamación de garganta','Faringitis','Ibuprofeno y Antibiótico','2021-05-18 15:42:20',26),(27,'Dolor de garganta','Inflamación de las amígdalas','Ibuprofeno','2021-05-19 00:55:53',28),(28,'Dolor de garganta y fiebre','Amigdalitis','Antibiótico y reposo','2021-05-23 00:00:00',26),(30,'Tos persistente y pérdida de gusto y olfato','Posible caso de coronavirus','PCR','2021-05-23 00:00:00',26);
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `IdProducto` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(300) NOT NULL,
  `Unidades` int NOT NULL,
  `Descripcion` varchar(300) NOT NULL,
  `IdUsuario` int NOT NULL,
  PRIMARY KEY (`IdProducto`),
  KEY `FK_IdUsuario_inventario_idx` (`IdUsuario`),
  CONSTRAINT `FK_IdUsuario_inventario` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (27,'Paracetamol',2,'svd',31),(28,'Amoxicilina',5,'Antibiótico',25),(30,'jhk',7,'kjh',31),(31,'Anestesia',7,'lkj',31),(32,'fvedfv',4,'sdfv',31);
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personas` (
  `IdPersona` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(75) NOT NULL,
  `Apellido1` varchar(75) NOT NULL,
  `Apellido2` varchar(75) DEFAULT NULL,
  `Sexo` enum('M','F') NOT NULL,
  `FechaNacimiento` datetime NOT NULL,
  `Direccion` varchar(100) NOT NULL,
  `Telefono` varchar(75) NOT NULL,
  `NumeroSS` varchar(40) NOT NULL,
  PRIMARY KEY (`IdPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (25,'okjj','kh','jhkj','M','1999-05-03 00:00:00','jhkhjjjjjjj','sdc','cs'),(26,'sgkj','lkj','lkjl','M','2021-05-04 00:00:00','lkj','ljk','lkjl'),(27,'Carmen','Lopez','Rodriguez','F','1980-05-04 00:00:00','C/Reyes Catolicos 15','658978874','CYL255445886'),(28,'Pedro','Fernandez','Torres','M','1980-05-06 00:00:00','C/Cordoba 1','658898214','CYL255874587'),(29,'lkj','lkj','lk','F','2021-05-03 00:00:00','lkj','lkj','sgs'),(30,'alkcl','aldsk','alsk','M','1980-05-06 00:00:00','lññl','ñlk','ñl');
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recetas`
--

DROP TABLE IF EXISTS `recetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recetas` (
  `IdReceta` int NOT NULL AUTO_INCREMENT,
  `NombreMedicamento` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Fecha` datetime NOT NULL,
  `Dias` int NOT NULL,
  `VecesPorDia` int NOT NULL,
  `TiempoTomas` int NOT NULL,
  `IdUsuario` int NOT NULL,
  `IdHistorial` int NOT NULL,
  PRIMARY KEY (`IdReceta`),
  KEY `FK_IdUsuario_recetas_idx` (`IdUsuario`),
  KEY `FK_IdHistorial_recetas` (`IdHistorial`),
  CONSTRAINT `FK_IdHistorial_recetas` FOREIGN KEY (`IdHistorial`) REFERENCES `historial` (`IdHistorial`),
  CONSTRAINT `FK_IdUsuario_recetas` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recetas`
--

LOCK TABLES `recetas` WRITE;
/*!40000 ALTER TABLE `recetas` DISABLE KEYS */;
INSERT INTO `recetas` VALUES (25,'Ibuprofeno','2021-05-18 00:11:25',5,3,8,29,26),(26,'Paracetamol','2021-05-18 00:12:17',3,1,24,29,25),(27,'Paracetamol','2021-05-23 00:00:00',8,3,8,29,30);
/*!40000 ALTER TABLE `recetas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `IdUsuario` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tipo` int NOT NULL,
  `IdPersona` int NOT NULL,
  `Especialidad` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`),
  KEY `FK_IdPersona_usuarios_idx` (`IdPersona`),
  CONSTRAINT `FK_IdPersona_usuarios` FOREIGN KEY (`IdPersona`) REFERENCES `personas` (`IdPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (25,'admin','admin',3,25,NULL),(26,'us2','abc',2,26,NULL),(28,'us3','abc',2,27,NULL),(29,'us4','abc',0,28,'Médico de cabecera'),(30,'us5','abc',2,29,NULL),(31,'us6','abc',1,30,NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-27 13:47:56
