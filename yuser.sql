# Host: localhost  (Version: 5.5.53)
# Date: 2020-12-13 21:32:28
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

INSERT INTO `y_session` VALUES ('0c27451951893ddb1cbc35764556bd2c','--','1','computerlogin'),('5486b1f4d664bde817d4ad9f2b8cf6c0','--','5','computerlogin'),('57b1367bd8a3f2507aaf4d4f8d388974','--','3','computerlogin'),('8dbd74635278256b3329f51699e81d9b','--','2','computerlogin'),('c6512f2aca4d43e30c94b4e4c0ded62d','681a285b9455d7ffbea82367e4a97356','4','userlogin');

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

INSERT INTO `y_task` VALUES ('1','道岔故障（道岔挤岔报警）的应急处理','1,5,1,5,2,4,1'),('2','列车在站台着火的应急处理','1,2,3,4,5'),('3','应急指挥行车（出现红光带）','4,5,2,4,5,4,2,5,4'),('4','异物侵限的应急处理','1,2,3,4,5'),('5','列车在站台着火的应急处理','1,2,3,4,5'),('6','应急指挥行车（出现红光带）','1,2,3,4,5'),('7','异物侵限的应急处理','1,2,3,4,5'),('8','列车在站台出现故障的应急处理','3,5,4,2,3,5,4,2'),('9','车站大量旅客滞留（活动、会议散场导致突发大客流）的应急处理','1,2,3,4,5');

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

INSERT INTO `y_task_detail` VALUES ('041d6be5a5c541aa41bfff80b9bab055','c8063aebc4256a351379b4e1a75e7635',1,'5','--',4,1,0.00,'2020-12-13 21:28:42'),('35613130e1ccb93616908844e2978b3f','c8063aebc4256a351379b4e1a75e7635',0,'4','681a285b9455d7ffbea82367e4a97356',5,1,100.00,'2020-12-13 21:28:42'),('55f8882f97a0e71978d1acc0115905f1','c8063aebc4256a351379b4e1a75e7635',1,'5','--',7,1,0.00,'2020-12-13 21:28:42'),('62228fe61f85f1be62c1f5f5ef82f683','c8063aebc4256a351379b4e1a75e7635',0,'4','681a285b9455d7ffbea82367e4a97356',3,1,90.00,'2020-12-13 21:28:42'),('ad72f7af07f8124732e7193ac0777731','c8063aebc4256a351379b4e1a75e7635',0,'4','681a285b9455d7ffbea82367e4a97356',8,1,100.00,'2020-12-13 21:28:42'),('b33eb85eb9f23ddac3748fa191aa34e3','c8063aebc4256a351379b4e1a75e7635',1,'2','--',2,1,0.00,'2020-12-13 21:28:42'),('e64d0722ee2844c423c01683813b2c69','c8063aebc4256a351379b4e1a75e7635',1,'5','--',1,1,0.00,'2020-12-13 21:28:42'),('f658ab50453ecfdd8868278fd527d2db','c8063aebc4256a351379b4e1a75e7635',1,'2','--',6,1,0.00,'2020-12-13 21:28:42'),('fc65d7d1c17dfad54940ad3dfd006f72','c8063aebc4256a351379b4e1a75e7635',0,'4','681a285b9455d7ffbea82367e4a97356',0,1,100.00,'2020-12-13 21:28:42');

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

INSERT INTO `y_task_run` VALUES ('c8063aebc4256a351379b4e1a75e7635','3','应急指挥行车（出现红光带）',9,9,'已完成','实训','2020-12-13 21:28:42','2020-12-13 21:31:12');

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

INSERT INTO `y_user` VALUES ('31d59e0b332ae4bfd3d5a6667bfde96b','123123','kb','123123','科比','男','123123123','','2','2020-12-03 13:21:35'),('4a6e07cbf031f8e5edb51d5060068707','zt123','zt','123123','站台','男','123123','','3','2020-12-03 21:07:48'),('681a285b9455d7ffbea82367e4a97356','zt0001','zting','123123','站厅','男','123213123','','4','2020-12-11 11:55:59'),('743f511b3ac62e42381b4dd39b629f9b','123123','yx4','123123','啊啊啊a','女','123123','','1',NULL),('aaaaaa','','admin','123456','杨业',NULL,'','','admin',NULL),('c4bd5824c1a3bed22aa25ed0b0bab718','zbzz001','ym','123123','值班站长','男','123123123','','5','2020-12-10 16:38:38');
