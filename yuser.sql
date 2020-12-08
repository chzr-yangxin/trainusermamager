-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: localhost    Database: yuser
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `y_permission`
--

DROP TABLE IF EXISTS `y_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `y_permission` (
  `id` varchar(40) NOT NULL,
  `roleid` varchar(40) NOT NULL,
  `perm` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_permission`
--

LOCK TABLES `y_permission` WRITE;
/*!40000 ALTER TABLE `y_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `y_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_role`
--

DROP TABLE IF EXISTS `y_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `y_role` (
  `id` varchar(40) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_role`
--

LOCK TABLES `y_role` WRITE;
/*!40000 ALTER TABLE `y_role` DISABLE KEYS */;
INSERT INTO `y_role` VALUES ('1','xczby','行车值班员'),('2','kyzby','客运值班员'),('3','zwy_ztaig','站务员（站台岗）'),('4','zwy_ztingg','站务员（站厅岗）'),('5','zbzz','值班站长');
/*!40000 ALTER TABLE `y_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_session`
--

DROP TABLE IF EXISTS `y_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `y_session` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `userid` varchar(40) DEFAULT '',
  `roleid` varchar(40) DEFAULT '',
  `logintype` varchar(40) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_session`
--

LOCK TABLES `y_session` WRITE;
/*!40000 ALTER TABLE `y_session` DISABLE KEYS */;
INSERT INTO `y_session` VALUES ('d558f40484f0af20344a305a78952e85','--','1','computerlogin');
/*!40000 ALTER TABLE `y_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_task`
--

DROP TABLE IF EXISTS `y_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `y_task` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `title` varchar(500) DEFAULT '',
  `orders` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_task`
--

LOCK TABLES `y_task` WRITE;
/*!40000 ALTER TABLE `y_task` DISABLE KEYS */;
INSERT INTO `y_task` VALUES ('1','道岔故障（道岔挤岔报警）的应急处理','1,5,1,5,2,4,1'),('2','列车在站台着火的应急处理',''),('3','应急指挥行车（出现红光带）',''),('4','异物侵限的应急处理',''),('5','列车在站台着火的应急处理',''),('6','应急指挥行车（出现红光带）',''),('7','异物侵限的应急处理',''),('8','列车在站台出现故障的应急处理',''),('9','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','');
/*!40000 ALTER TABLE `y_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_task_detail`
--

DROP TABLE IF EXISTS `y_task_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `y_task_detail` (
  `id` varchar(40) NOT NULL,
  `runid` varchar(40) NOT NULL,
  `iscomputer` int(1) NOT NULL,
  `roleid` varchar(40) NOT NULL,
  `userid` varchar(40) NOT NULL,
  `taskstep` int(10) NOT NULL,
  `status` int(1) NOT NULL,
  `score` float(5,2) DEFAULT '0.00',
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_task_detail`
--

LOCK TABLES `y_task_detail` WRITE;
/*!40000 ALTER TABLE `y_task_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `y_task_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_task_finish`
--

DROP TABLE IF EXISTS `y_task_finish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `y_task_finish` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `checkid` varchar(40) DEFAULT '',
  `taskid` varchar(40) DEFAULT '',
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_task_finish`
--

LOCK TABLES `y_task_finish` WRITE;
/*!40000 ALTER TABLE `y_task_finish` DISABLE KEYS */;
INSERT INTO `y_task_finish` VALUES ('1ed2665803b1d2c5fcfab3c4d89d6faa','bab98349-d057-4f77-ad6c-f775a5d50a60','9','2020-12-03 21:02:14'),('407c8fd83799c67e96eff9f27ba10218','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','3','2020-12-03 19:08:28');
/*!40000 ALTER TABLE `y_task_finish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_task_result`
--

DROP TABLE IF EXISTS `y_task_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `y_task_result` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `checkid` varchar(40) DEFAULT '',
  `checkname` varchar(500) DEFAULT '',
  `taskid` varchar(40) DEFAULT '',
  `userid` varchar(40) DEFAULT '',
  `roleid` varchar(40) DEFAULT '',
  `status` tinyint(1) DEFAULT '0',
  `score` float(5,2) DEFAULT '0.00',
  `ctime` datetime DEFAULT NULL,
  `tasktype` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_task_result`
--

LOCK TABLES `y_task_result` WRITE;
/*!40000 ALTER TABLE `y_task_result` DISABLE KEYS */;
INSERT INTO `y_task_result` VALUES ('0305ce93a936274e343d50f0180dfe30','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','应急指挥行车（出现红光带）','3','--','',1,99.00,'2020-12-03 11:54:35',''),('168448e090ff62471c8069c5b360fe47','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','应急指挥行车（出现红光带）','3','743f511b3ac62e42381b4dd39b629f9b','',1,98.00,'2020-12-03 11:54:35',''),('28883cfd4d4a40c303d36354a02095b5','bab98349-d057-4f77-ad6c-f775a5d50a60','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','9','--','4',1,0.00,'2020-12-03 19:17:07',''),('44b0efa421eb66bcc06d3f1e0090e776','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','应急指挥行车（出现红光带）','3','--','',1,0.00,'2020-12-03 11:54:35',''),('46c018f569ae63575b8f4f6f7dbd8ad3','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','应急指挥行车（出现红光带）','3','--','',1,0.00,'2020-12-03 11:54:35',''),('8fc48ce7ab17b95c67012910b4bd8b4c','bab98349-d057-4f77-ad6c-f775a5d50a60','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','9','743f511b3ac62e42381b4dd39b629f9b','1',1,99.00,'2020-12-03 19:17:07',''),('b8e68e1d10d875f16e3f3b5a3fd97cd3','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','应急指挥行车（出现红光带）','3','--','',1,0.00,'2020-12-03 11:54:35',''),('c1f17bc50d509953e419e119c5bdd0c2','bab98349-d057-4f77-ad6c-f775a5d50a60','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','9','--','5',1,0.00,'2020-12-03 19:17:07',''),('d65265aaf153cd2073e6f4a289b8563a','bab98349-d057-4f77-ad6c-f775a5d50a60','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','9','--','3',1,0.00,'2020-12-03 19:17:07',''),('e60fb3d1ce5a080653f9c3691202f4e2','bab98349-d057-4f77-ad6c-f775a5d50a60','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','9','31d59e0b332ae4bfd3d5a6667bfde96b','2',1,77.00,'2020-12-03 19:17:07','');
/*!40000 ALTER TABLE `y_task_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_task_run`
--

DROP TABLE IF EXISTS `y_task_run`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `y_task_run` (
  `id` varchar(40) NOT NULL,
  `taskid` varchar(40) NOT NULL,
  `taskname` varchar(40) NOT NULL,
  `tasksteps` int(10) NOT NULL,
  `curstep` int(10) NOT NULL,
  `taskstatus` varchar(10) NOT NULL,
  `tasktype` varchar(40) NOT NULL,
  `ctime` datetime DEFAULT NULL,
  `ftime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_task_run`
--

LOCK TABLES `y_task_run` WRITE;
/*!40000 ALTER TABLE `y_task_run` DISABLE KEYS */;
/*!40000 ALTER TABLE `y_task_run` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `y_user`
--

DROP TABLE IF EXISTS `y_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `y_user` (
  `id` varchar(40) NOT NULL,
  `usercode` varchar(40) DEFAULT '',
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL DEFAULT '',
  `nickname` varchar(100) NOT NULL DEFAULT '',
  `sex` varchar(10) DEFAULT NULL,
  `phone` varchar(40) DEFAULT '',
  `salt` varchar(40) NOT NULL DEFAULT '',
  `roleid` varchar(40) NOT NULL DEFAULT '',
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `y_user`
--

LOCK TABLES `y_user` WRITE;
/*!40000 ALTER TABLE `y_user` DISABLE KEYS */;
INSERT INTO `y_user` VALUES ('31d59e0b332ae4bfd3d5a6667bfde96b','123123','kb','123123','科比','男','123123123','','2','2020-12-03 13:21:35'),('4a6e07cbf031f8e5edb51d5060068707','zt123','zt','123123','站台','男','123123','','3','2020-12-03 21:07:48'),('743f511b3ac62e42381b4dd39b629f9b','123123','yx4','123123','啊啊啊a','女','123123','','1',NULL),('aaaaaa','','admin','123456','杨业',NULL,'','','admin',NULL);
/*!40000 ALTER TABLE `y_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-08 14:59:40
