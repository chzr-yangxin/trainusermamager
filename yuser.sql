# Host: localhost  (Version: 5.5.53)
# Date: 2020-12-08 21:31:59
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "y_permission"
#

DROP TABLE IF EXISTS `y_permission`;
CREATE TABLE `y_permission` (
  `id` varchar(40) NOT NULL,
  `roleid` varchar(40) NOT NULL,
  `perm` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "y_permission"
#


#
# Structure for table "y_role"
#

DROP TABLE IF EXISTS `y_role`;
CREATE TABLE `y_role` (
  `id` varchar(40) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "y_role"
#

INSERT INTO `y_role` VALUES ('1','xczby','行车值班员'),('2','kyzby','客运值班员'),('3','zwy_ztaig','站务员（站台岗）'),('4','zwy_ztingg','站务员（站厅岗）'),('5','zbzz','值班站长');

#
# Structure for table "y_session"
#

DROP TABLE IF EXISTS `y_session`;
CREATE TABLE `y_session` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `userid` varchar(40) DEFAULT '',
  `roleid` varchar(40) DEFAULT '',
  `logintype` varchar(40) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "y_session"
#

INSERT INTO `y_session` VALUES ('07a1b2a52365c7411bc5d76578e5df60','--','5','computerlogin'),('1f87e7e8f851d32eff46504c9574f0d6','--','4','computerlogin'),('2435012e133ea96b30b902ad3d52b48f','4a6e07cbf031f8e5edb51d5060068707','3','userlogin'),('4640b5b314622e2ffb25d3456a36caa4','--','2','computerlogin'),('6b9ceee9415cc644a6c19a8cad1523d6','--','1','computerlogin');

#
# Structure for table "y_task"
#

DROP TABLE IF EXISTS `y_task`;
CREATE TABLE `y_task` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `title` varchar(500) DEFAULT '',
  `orders` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "y_task"
#

INSERT INTO `y_task` VALUES ('1','道岔故障（道岔挤岔报警）的应急处理','1,5,1,5,2,4,1'),('2','列车在站台着火的应急处理','1,2,3,4,5'),('3','应急指挥行车（出现红光带）','1,2,3,4,5'),('4','异物侵限的应急处理','1,2,3,4,5'),('5','列车在站台着火的应急处理','1,2,3,4,5'),('6','应急指挥行车（出现红光带）','1,2,3,4,5'),('7','异物侵限的应急处理','1,2,3,4,5'),('8','列车在站台出现故障的应急处理','1,2,3,4,5'),('9','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','1,2,3,4,5');

#
# Structure for table "y_task_detail"
#

DROP TABLE IF EXISTS `y_task_detail`;
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

#
# Data for table "y_task_detail"
#

INSERT INTO `y_task_detail` VALUES ('0fcd0d7065d0f18c2fd0fd8f2df4e68e','389da4f64f9d2e7dcc2e16ebe219b118',1,'4','--',5,1,22.00,'2020-12-08 16:54:49'),('47673766b4402a50c04afbae394bc794','389da4f64f9d2e7dcc2e16ebe219b118',1,'5','--',1,1,66.00,'2020-12-08 16:54:49'),('58cd1cff0d5745b92aa7fac479627fb6','389da4f64f9d2e7dcc2e16ebe219b118',1,'1','--',0,1,77.00,'2020-12-08 16:54:49'),('6ae2fe88c358db6a8fdd25c4b9a85dc1','389da4f64f9d2e7dcc2e16ebe219b118',1,'5','--',3,1,44.00,'2020-12-08 16:54:49'),('86d1b8da668b7b26630907fec9f18608','389da4f64f9d2e7dcc2e16ebe219b118',1,'1','--',6,1,11.00,'2020-12-08 16:54:49'),('948cde3339e9df756cb952f82a5d0e53','389da4f64f9d2e7dcc2e16ebe219b118',1,'1','--',2,1,55.00,'2020-12-08 16:54:49'),('c120584563446f7dfd79be6d630a3572','389da4f64f9d2e7dcc2e16ebe219b118',0,'2','31d59e0b332ae4bfd3d5a6667bfde96b',4,1,33.00,'2020-12-08 16:54:49');

#
# Structure for table "y_task_finish"
#

DROP TABLE IF EXISTS `y_task_finish`;
CREATE TABLE `y_task_finish` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `checkid` varchar(40) DEFAULT '',
  `taskid` varchar(40) DEFAULT '',
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "y_task_finish"
#

INSERT INTO `y_task_finish` VALUES ('1ed2665803b1d2c5fcfab3c4d89d6faa','bab98349-d057-4f77-ad6c-f775a5d50a60','9','2020-12-03 21:02:14'),('407c8fd83799c67e96eff9f27ba10218','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','3','2020-12-03 19:08:28');

#
# Structure for table "y_task_result"
#

DROP TABLE IF EXISTS `y_task_result`;
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

#
# Data for table "y_task_result"
#

INSERT INTO `y_task_result` VALUES ('0305ce93a936274e343d50f0180dfe30','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','应急指挥行车（出现红光带）','3','--','',1,99.00,'2020-12-03 11:54:35',''),('168448e090ff62471c8069c5b360fe47','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','应急指挥行车（出现红光带）','3','743f511b3ac62e42381b4dd39b629f9b','',1,98.00,'2020-12-03 11:54:35',''),('28883cfd4d4a40c303d36354a02095b5','bab98349-d057-4f77-ad6c-f775a5d50a60','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','9','--','4',1,0.00,'2020-12-03 19:17:07',''),('44b0efa421eb66bcc06d3f1e0090e776','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','应急指挥行车（出现红光带）','3','--','',1,0.00,'2020-12-03 11:54:35',''),('46c018f569ae63575b8f4f6f7dbd8ad3','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','应急指挥行车（出现红光带）','3','--','',1,0.00,'2020-12-03 11:54:35',''),('8fc48ce7ab17b95c67012910b4bd8b4c','bab98349-d057-4f77-ad6c-f775a5d50a60','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','9','743f511b3ac62e42381b4dd39b629f9b','1',1,99.00,'2020-12-03 19:17:07',''),('b8e68e1d10d875f16e3f3b5a3fd97cd3','0b2665dc-4a27-4e6f-90f2-8af80c8260d4','应急指挥行车（出现红光带）','3','--','',1,0.00,'2020-12-03 11:54:35',''),('c1f17bc50d509953e419e119c5bdd0c2','bab98349-d057-4f77-ad6c-f775a5d50a60','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','9','--','5',1,0.00,'2020-12-03 19:17:07',''),('d65265aaf153cd2073e6f4a289b8563a','bab98349-d057-4f77-ad6c-f775a5d50a60','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','9','--','3',1,0.00,'2020-12-03 19:17:07',''),('e60fb3d1ce5a080653f9c3691202f4e2','bab98349-d057-4f77-ad6c-f775a5d50a60','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','9','31d59e0b332ae4bfd3d5a6667bfde96b','2',1,77.00,'2020-12-03 19:17:07','');

#
# Structure for table "y_task_run"
#

DROP TABLE IF EXISTS `y_task_run`;
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

#
# Data for table "y_task_run"
#

INSERT INTO `y_task_run` VALUES ('389da4f64f9d2e7dcc2e16ebe219b118','1','道岔故障（道岔挤岔报警）的应急处理',7,7,'已完成','实训','2020-12-08 16:54:49','2020-12-08 16:54:49');

#
# Structure for table "y_user"
#

DROP TABLE IF EXISTS `y_user`;
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

#
# Data for table "y_user"
#

INSERT INTO `y_user` VALUES ('31d59e0b332ae4bfd3d5a6667bfde96b','123123','kb','123123','科比','男','123123123','','2','2020-12-03 13:21:35'),('4a6e07cbf031f8e5edb51d5060068707','zt123','zt','123123','站台','男','123123','','3','2020-12-03 21:07:48'),('743f511b3ac62e42381b4dd39b629f9b','123123','yx4','123123','啊啊啊a','女','123123','','1',NULL),('aaaaaa','','admin','123456','杨业',NULL,'','','admin',NULL);
