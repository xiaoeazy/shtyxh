-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.17-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema kindergarten_dev
--

CREATE DATABASE IF NOT EXISTS kindergarten_dev;
USE kindergarten_dev;

--
-- Definition of table `kg_allonetext`
--

DROP TABLE IF EXISTS `kg_allonetext`;
CREATE TABLE `kg_allonetext` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `typeCode` varchar(45) NOT NULL,
  `content` text,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_allonetext`
--

/*!40000 ALTER TABLE `kg_allonetext` DISABLE KEYS */;
INSERT INTO `kg_allonetext` (`id`,`title`,`typeCode`,`content`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'协会简介','introduction','1',2,-1,-1,-1,'2018-08-05 21:16:54',-1,'2018-08-05 21:21:19',-1,'ID',NULL,NULL,NULL),
 (2,'协会架构图','companyFramework','<p>3</p>',4,-1,-1,-1,'2018-08-05 21:16:54',-1,'2018-08-05 21:33:03',-1,'ID',NULL,NULL,NULL),
 (3,'历届理事','companyDirector','<p>4</p>',2,-1,-1,-1,'2018-08-05 21:16:54',-1,'2018-08-05 21:33:06',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_allonetext` ENABLE KEYS */;


--
-- Definition of table `kg_assessment_activity`
--

DROP TABLE IF EXISTS `kg_assessment_activity`;
CREATE TABLE `kg_assessment_activity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `finished` tinyint(1) DEFAULT '1',
  `assessment_type_id` bigint(20) unsigned NOT NULL,
  `attributeId` varchar(200) DEFAULT '-1',
  `assessment_activity_name` varchar(45) NOT NULL,
  `assessment_activity_content` text,
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_assessment_activity`
--

/*!40000 ALTER TABLE `kg_assessment_activity` DISABLE KEYS */;
INSERT INTO `kg_assessment_activity` (`id`,`finished`,`assessment_type_id`,`attributeId`,`assessment_activity_name`,`assessment_activity_content`,`createDate`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (31,1,18,'1','评估任务A','<p>11</p>','2018-08-04 15:03:31',2,-1,-1,-1,'2018-08-04 15:03:31',-1,'2018-08-04 15:03:36',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_assessment_activity` ENABLE KEYS */;


--
-- Definition of table `kg_assessment_activity_user_progress`
--

DROP TABLE IF EXISTS `kg_assessment_activity_user_progress`;
CREATE TABLE `kg_assessment_activity_user_progress` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `assessment_activity_id` bigint(20) unsigned NOT NULL,
  `upload_user_id` bigint(20) unsigned NOT NULL,
  `admin_suggestion` text,
  `expert_user_id` bigint(20) unsigned DEFAULT NULL,
  `expert_suggestion` text,
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `state` int(10) unsigned NOT NULL DEFAULT '10',
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `Index_2` (`assessment_activity_id`,`upload_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_assessment_activity_user_progress`
--

/*!40000 ALTER TABLE `kg_assessment_activity_user_progress` DISABLE KEYS */;
INSERT INTO `kg_assessment_activity_user_progress` (`id`,`assessment_activity_id`,`upload_user_id`,`admin_suggestion`,`expert_user_id`,`expert_suggestion`,`createDate`,`state`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (16,31,10013,'11',10032,NULL,'2018-08-04 15:04:02',40,3,-1,-1,-1,'2018-08-04 15:04:02',-1,'2018-08-04 15:09:17',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_assessment_activity_user_progress` ENABLE KEYS */;


--
-- Definition of table `kg_assessment_activity_user_upload`
--

DROP TABLE IF EXISTS `kg_assessment_activity_user_upload`;
CREATE TABLE `kg_assessment_activity_user_upload` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `upload_user_id` bigint(20) unsigned NOT NULL,
  `progress_id` bigint(20) unsigned NOT NULL,
  `file_name` varchar(240) DEFAULT NULL,
  `file_path` varchar(240) DEFAULT NULL,
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_assessment_activity_user_upload`
--

/*!40000 ALTER TABLE `kg_assessment_activity_user_upload` DISABLE KEYS */;
INSERT INTO `kg_assessment_activity_user_upload` (`id`,`upload_user_id`,`progress_id`,`file_name`,`file_path`,`createDate`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (28,10013,16,'wx.png','/uploadFile/assessment/31/10013/462eb08e-b546-441a-abc0-12987a1e5aa1.PNG','2018-08-04 15:04:02',1,-1,-1,-1,'2018-08-04 15:04:02',-1,'2018-08-04 15:04:02',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_assessment_activity_user_upload` ENABLE KEYS */;


--
-- Definition of table `kg_assessment_type`
--

DROP TABLE IF EXISTS `kg_assessment_type`;
CREATE TABLE `kg_assessment_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `assessment_type_name` varchar(45) NOT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_assessment_type`
--

/*!40000 ALTER TABLE `kg_assessment_type` DISABLE KEYS */;
INSERT INTO `kg_assessment_type` (`id`,`assessment_type_name`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (18,'学校评估',3,-1,-1,-1,'2018-06-18 12:08:49',-1,'2018-06-20 20:59:29',-1,'ID',NULL,NULL,NULL),
 (19,'教师评估',1,-1,-1,-1,'2018-06-18 12:09:15',-1,'2018-06-18 12:09:15',-1,'ID',NULL,NULL,NULL),
 (20,'aa',1,-1,-1,-1,'2018-08-04 15:10:09',-1,'2018-08-04 15:10:09',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_assessment_type` ENABLE KEYS */;


--
-- Definition of table `kg_canton`
--

DROP TABLE IF EXISTS `kg_canton`;
CREATE TABLE `kg_canton` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cantonName` varchar(45) NOT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_canton`
--

/*!40000 ALTER TABLE `kg_canton` DISABLE KEYS */;
INSERT INTO `kg_canton` (`id`,`cantonName`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'静安',1,-1,-1,-1,'2018-08-05 10:37:21',-1,'2018-08-05 10:37:21',-1,'ID',NULL,NULL,NULL),
 (2,'杨浦',1,-1,-1,-1,'2018-08-05 10:37:27',-1,'2018-08-05 10:37:27',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_canton` ENABLE KEYS */;


--
-- Definition of table `kg_carousel`
--

DROP TABLE IF EXISTS `kg_carousel`;
CREATE TABLE `kg_carousel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_path` varchar(200) NOT NULL,
  `sequence` bigint(20) unsigned DEFAULT '0',
  `urltype` bigint(20) unsigned DEFAULT '0' COMMENT '0:weburl , 1:activity ,2 :news',
  `web_url` varchar(200) DEFAULT NULL,
  `activity_id` bigint(20) DEFAULT '-1',
  `news_id` bigint(20) DEFAULT '-1',
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_carousel`
--

/*!40000 ALTER TABLE `kg_carousel` DISABLE KEYS */;
INSERT INTO `kg_carousel` (`id`,`file_path`,`sequence`,`urltype`,`web_url`,`activity_id`,`news_id`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (21,'/uploadFile/carousel/20180618184523_1000_475.jpg',0,1,'',-1,50,1,-1,-1,-1,'2018-06-18 18:45:29',-1,'2018-06-18 18:45:29',-1,'ID',NULL,NULL,NULL),
 (22,'/uploadFile/carousel/20180804151133_1000_475.jpg',0,0,'http://www.baidu.com',-1,-1,1,-1,-1,-1,'2018-08-04 15:11:44',-1,'2018-08-04 15:11:44',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_carousel` ENABLE KEYS */;


--
-- Definition of table `kg_config`
--

DROP TABLE IF EXISTS `kg_config`;
CREATE TABLE `kg_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sysName` varchar(40) NOT NULL,
  `sysKey` varchar(40) DEFAULT NULL,
  `sysValue` varchar(200) DEFAULT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_config`
--

/*!40000 ALTER TABLE `kg_config` DISABLE KEYS */;
INSERT INTO `kg_config` (`id`,`sysName`,`sysKey`,`sysValue`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'网站名称','webname','上海市托幼协会',19,-1,-1,-1,'2018-04-09 14:03:06',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (2,'图片上传默认路径','uploadpath','/uploadFile',19,-1,-1,-1,'2018-04-09 14:03:06',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (3,'网站版权信息','copyright','copyright &copy huanTed ',20,-1,-1,-1,'2018-04-09 14:03:06',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (4,'站点默认关键字','keyword','幼协会，上海幼协会，门户',18,-1,-1,-1,'2018-04-09 14:03:07',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (5,'站点描述','webdesc','幼协会站点',17,-1,-1,-1,'2018-04-09 14:03:07',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (6,'网站备案号','ICPlicense','沪ICP备01234567891',19,-1,-1,-1,'2018-04-09 14:03:08',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (7,'网站logo','webLogo','/resources/upload/logo/logo.png',10,-1,-1,-1,'2018-05-05 13:30:10',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (8,'网站ico','webIco','/favicon.ico',10,-1,-1,-1,'2018-05-05 13:44:02',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (9,'网站备案信息地址','ICPlicensePath','http://www.baidu.com',10,-1,-1,-1,'2018-06-18 11:25:37',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (10,'网站地址','webIp','http://localhost:8080/kindergarten',10,-1,-1,-1,'2018-06-18 11:36:11',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (11,'微信','wx','/uploadFile/wx/wx.png',10,-1,-1,-1,'2018-06-18 21:29:27',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL),
 (12,'微博','wb','/uploadFile/wb/wb.png',10,-1,-1,-1,'2018-06-18 21:29:41',-1,'2018-06-20 16:27:11',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_config` ENABLE KEYS */;


--
-- Definition of table `kg_contact`
--

DROP TABLE IF EXISTS `kg_contact`;
CREATE TABLE `kg_contact` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sysName` varchar(40) NOT NULL,
  `sysKey` varchar(40) DEFAULT NULL,
  `sysValue` varchar(200) DEFAULT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_contact`
--

/*!40000 ALTER TABLE `kg_contact` DISABLE KEYS */;
INSERT INTO `kg_contact` (`id`,`sysName`,`sysKey`,`sysValue`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'联系电话','tel','021-62671784',5,-1,-1,-1,'2018-05-05 12:01:55',-1,'2018-06-18 21:11:35',-1,'ID',NULL,NULL,NULL),
 (2,'图文传真','fax','021-321006601',2,-1,-1,-1,'2018-05-05 12:01:55',-1,'2018-05-05 12:20:59',-1,'ID',NULL,NULL,NULL),
 (3,'办公地址','address','石门二路 199弄 1号 404 ',1,-1,-1,-1,'2018-05-05 12:01:55',-1,'2018-05-05 12:01:55',-1,'ID',NULL,NULL,NULL),
 (4,'邮件编码','zipcode','200041',1,-1,-1,-1,'2018-05-05 12:01:55',-1,'2018-05-05 12:01:55',-1,'ID',NULL,NULL,NULL),
 (5,'联系邮箱','hremail','aaa@163.com',1,-1,-1,-1,'2018-05-05 12:01:55',-1,'2018-05-05 12:01:55',-1,'ID',NULL,NULL,NULL),
 (6,'地图标题','mapTitle','石门二路&nbsp;199弄&nbsp;1号&nbsp;404',1,-1,-1,-1,'2018-05-05 12:22:41',-1,'2018-05-05 12:22:41',-1,'ID',NULL,NULL,NULL),
 (7,'地图内容','mapContent','上海市托幼协会，&nbsp;石门二路&nbsp;199弄&nbsp;1号&nbsp;404&nbsp;，欢迎您！',1,-1,-1,-1,'2018-05-05 12:22:41',-1,'2018-05-05 12:22:41',-1,'ID',NULL,NULL,NULL),
 (8,'地图坐标','mapPoint','121.467142|31.239651',1,-1,-1,-1,'2018-05-05 12:22:41',-1,'2018-05-05 12:22:41',-1,'ID',NULL,NULL,NULL),
 (9,'标题','title','上海市托幼协会',1,-1,-1,-1,'2018-05-05 12:43:00',-1,'2018-05-05 12:43:00',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_contact` ENABLE KEYS */;


--
-- Definition of table `kg_demo`
--

DROP TABLE IF EXISTS `kg_demo`;
CREATE TABLE `kg_demo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_demo`
--

/*!40000 ALTER TABLE `kg_demo` DISABLE KEYS */;
/*!40000 ALTER TABLE `kg_demo` ENABLE KEYS */;


--
-- Definition of table `kg_download`
--

DROP TABLE IF EXISTS `kg_download`;
CREATE TABLE `kg_download` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_title` varchar(200) NOT NULL,
  `file_path` varchar(200) NOT NULL,
  `summary` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_download`
--

/*!40000 ALTER TABLE `kg_download` DISABLE KEYS */;
INSERT INTO `kg_download` (`id`,`file_title`,`file_path`,`summary`,`password`,`createDate`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (14,'rotate-bg.png','/uploadFile/download/rotate-bg.png','','','2018-08-04 15:10:40',1,-1,-1,-1,'2018-08-04 15:10:40',-1,'2018-08-04 15:10:40',-1,'ID',NULL,NULL,NULL),
 (15,'bg1.png','/uploadFile/download/bg1.png','','','2018-08-04 15:10:57',1,-1,-1,-1,'2018-08-04 15:10:57',-1,'2018-08-04 15:10:57',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_download` ENABLE KEYS */;


--
-- Definition of table `kg_history`
--

DROP TABLE IF EXISTS `kg_history`;
CREATE TABLE `kg_history` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(200) NOT NULL,
  `historyTime` varchar(45) NOT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_history`
--

/*!40000 ALTER TABLE `kg_history` DISABLE KEYS */;
INSERT INTO `kg_history` (`id`,`title`,`content`,`historyTime`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (12,'1','1','1',1,-1,-1,-1,'2018-08-05 19:35:41',-1,'2018-08-05 19:35:41',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_history` ENABLE KEYS */;


--
-- Definition of table `kg_introduction`
--

DROP TABLE IF EXISTS `kg_introduction`;
CREATE TABLE `kg_introduction` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `introduction` text,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_introduction`
--

/*!40000 ALTER TABLE `kg_introduction` DISABLE KEYS */;
INSERT INTO `kg_introduction` (`id`,`introduction`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'<p>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上海市托幼协会是经上海市教育委员会同意，上海市社会团体管理局核准登记，取得法人资格的行业性、自律性的非营利性社会组织。</p><p>　　上海市托幼协会是为适应政府职能转变，适应上海学前教育事业发展的需要，更好地为本市各级各类学前教育机构服务，进一步推动本市托幼事业的发展而建立的。它的宗旨是：坚持四项基本原则, 遵守宪法、法律、法规, 贯彻党和政府的方针、政策, 团结全市各级各类学前教育机构和广大学前教育工作者，开展有关学前教育的理论、政策和实际问题等方面的研究, 协助政府加强学前教育管理，保护会员的合法权益，提高学前教育队伍的整体素质，推进托幼事业的发展。协会是会员的服务机构，协会是会员的自律组织，协会是会员的代表，协会也是行业的协调组织。</p><p>　　上海市托幼协会自2002年成立以来，得到了市各有关部门及领导的关心与支持。原市人大教科文委主任夏秀蓉、市教委副主任张民生任名誉会长，原市人大常委会副主任胡正昌、华师大潘洁教授、市教委基教处何幼华副处长任顾问，市卫生局、市妇联委派相关领导担任协会副会长参与协会的领导工作，现市人大常委、市政府参事瞿钧任会长。</p><p>　　协会的业务范围是：信息咨询，内外协调，业务培训，交流研讨、资质审核等。</p><p>　　协会的具体工作职责如下：</p><p>　　（一）积极宣传、贯策党和国家有关学前教育的方针、政策，宣传学前教育的地位、作用，团结、依靠社会各方面的力量，关心、支持学前教育工作。</p><p>　　（二）开展学前教育业务培训、保教业务咨询、保教人才交流等服务工作。</p><p>　　（三）开展国内外学前教育的合作与交流，推广研究成果和经验。</p><p>　　（四）就学前教育事业发展过程中存在的问题开展调研，参与有关学前教育发展、改革以及与托、幼园所利益相关的政府决策论证，提出事业发展中有关政策和立法的建议，并为政府提供决策依据。</p><p>　　（五）推进依法办园办所，创导良好的行风。对参与不正当竞争，有损事业整体形象的会员，协会采取警告、批评、开除会员资格等惩戒措施，也可建议有关行政机构依法对非会员单位的违法活动进行处理。</p><p>　　（六）依据学前教育有关法规，制定本市托幼保教质量规范，服务标准。</p><p>　　（七）接受政府有关职能部门委托，依法开展与学前教育发展相关的工作，如统计、调查、发布信息、公信证明、收费价格协调、资质审核等。</p><p>　　（八）协调会员与会员，会员与非会员，会员与幼儿家长、保教人员，会员与其它单位及社会组织的关系并维护其合法权益。</p><p>　　协会建立后将首先接受上海市教育委员会的委托开展对一级幼儿园、托儿所的认定、发证工作，上海市教委与上海市卫生局还在研究有关其他工作的委托。同时协会将充分发挥本市教育资源及人才资源的优势，开展学前教育培训、保教业务咨询、保教人才交流等服务工作。协会还将创造条件开展国内外学前教育的合作与交流，还将就多元化办园体制等问题开展调查、研究，总结经验，建立必要的行业规范，并为政府提供决策依据。</p><p>　　协会由单位会员和个人会员组成，凡经本市教育行政部门批准从事学前教育的机构和研究单位及在学前教育研究上有显著成绩或对学前教育事业发展有较大贡献的个人，承认本团体的章程，有加入本团体的愿望，填写入会申请，经理事会讨论通过均能成为本协会的单位会员和个人会员，会员享有参加本团体的活动，优先获得本团体服务的权利，并享有选举权、被选举权和表决权，同时还有对本团体工作的批评建议权和监督权。会员也应履行执行本团体决议，完成团体交代的工作及按时交纳会费的义务。</p><p>　　协会愿与所有会员单位共同努力，为上海托幼事业的发展作出贡献。</p><p>　　</p><p>　　</p><p>　　</p><p>　　 上海市托幼协会</p><p>　　</p><p>　　</p><p>　　</p><p>　　 上海市托幼协会地址：石门二路199弄1号404室</p><p>　　&nbsp;</p><p>　　邮编：200041 电话：62671784</p><p><br/></p>',23,-1,-1,-1,'2018-04-07 09:18:47',-1,'2018-04-28 15:08:11',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_introduction` ENABLE KEYS */;


--
-- Definition of table `kg_link`
--

DROP TABLE IF EXISTS `kg_link`;
CREATE TABLE `kg_link` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `link_name` varchar(45) NOT NULL,
  `link_url` varchar(200) DEFAULT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_link`
--

/*!40000 ALTER TABLE `kg_link` DISABLE KEYS */;
INSERT INTO `kg_link` (`id`,`link_name`,`link_url`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (8,'百度','http://www.baidu.com',2,-1,-1,-1,'2018-04-06 19:06:57',-1,'2018-05-12 14:02:18',-1,'ID',NULL,NULL,NULL),
 (9,'新浪','https://www.sina.com.cn/',2,-1,-1,-1,'2018-04-06 19:07:00',-1,'2018-05-12 14:04:10',-1,'ID',NULL,NULL,NULL),
 (10,'新浪体育','http://sports.sina.com.cn/',2,-1,-1,-1,'2018-04-14 10:43:51',-1,'2018-05-12 14:04:30',-1,'ID',NULL,NULL,NULL),
 (11,'aa','aa',1,-1,-1,-1,'2018-08-04 15:13:04',-1,'2018-08-04 15:13:04',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_link` ENABLE KEYS */;


--
-- Definition of table `kg_member_units`
--

DROP TABLE IF EXISTS `kg_member_units`;
CREATE TABLE `kg_member_units` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `memberNo` varchar(45) NOT NULL,
  `cantonId` bigint(20) NOT NULL,
  `kindergartenName` varchar(45) NOT NULL,
  `kindergartenSite` varchar(45) NOT NULL,
  `zipCode` varchar(45) NOT NULL,
  `telphone` varchar(45) NOT NULL,
  `level` varchar(45) NOT NULL,
  `nature` varchar(45) NOT NULL COMMENT '性质',
  `admissionTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `Index_2` (`memberNo`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_member_units`
--

/*!40000 ALTER TABLE `kg_member_units` DISABLE KEYS */;
INSERT INTO `kg_member_units` (`id`,`memberNo`,`cantonId`,`kindergartenName`,`kindergartenSite`,`zipCode`,`telphone`,`level`,`nature`,`admissionTime`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'3',1,'中心幼儿园','长江西路共江路','123456','12345678901','1','民办','2018-08-05 10:16:08',2,-1,-1,-1,'2018-08-05 10:16:08',-1,'2018-08-05 11:36:41',-1,'ID',NULL,NULL,NULL),
 (24,'2',1,'2','2','2','2','2','2','2018-08-05 11:25:30',1,-1,-1,-1,'2018-08-05 11:25:30',-1,'2018-08-05 11:25:30',-1,'ID',NULL,NULL,NULL),
 (26,'1',1,'1','1','1','1','1','2','2018-08-05 15:15:39',2,-1,-1,-1,'2018-08-05 15:15:39',-1,'2018-08-05 15:15:45',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_member_units` ENABLE KEYS */;


--
-- Definition of table `kg_news`
--

DROP TABLE IF EXISTS `kg_news`;
CREATE TABLE `kg_news` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `typeId` bigint(20) unsigned NOT NULL,
  `sourceId` bigint(20) unsigned NOT NULL DEFAULT '1',
  `attributeId` varchar(200) DEFAULT '-1',
  `newsTitle` varchar(45) NOT NULL,
  `summary` varchar(200) NOT NULL,
  `thumbnail` varchar(200) DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `content` text,
  `sequence` bigint(20) unsigned DEFAULT '0',
  `indexshow` varchar(1) NOT NULL DEFAULT 'N' COMMENT '//是否首页显示缩略图',
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `viewsCount` int(10) DEFAULT '0',
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_2` (`newsTitle`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_news`
--

/*!40000 ALTER TABLE `kg_news` DISABLE KEYS */;
INSERT INTO `kg_news` (`id`,`typeId`,`sourceId`,`attributeId`,`newsTitle`,`summary`,`thumbnail`,`author`,`content`,`sequence`,`indexshow`,`createDate`,`viewsCount`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (44,1,1,'2','没作业、不考试的芬兰孩子，教育世界第一的10个秘密','没作业、不考试的芬兰孩子，教育世界第一的10个秘密','',NULL,'<p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">北欧国家芬兰，是个有着漫长、黑暗、寒冷冬天的弹丸小国。这里的老师和家长，都希望孩子能多玩而不是多写作业。</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">所以芬兰的孩子上学最晚，假期最长、作业最少。就这样一个看起来处处都输在起跑线的芬兰小国，却培养出了</span><span style=\"font-size: 15px; font-family: Helvetica, sans-serif; color: rgb(62, 62, 62);\">“</span><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">整体表现全球第一</span><span style=\"font-size: 15px; font-family: Helvetica, sans-serif; color: rgb(62, 62, 62);\">”</span><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">的中学生和高中生。</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">为什么芬兰教育能够领跑全世界？这是所有父母和教育工作者都想知道的秘密。</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><img src=\"http://118.31.36.160:8080/kindergarten/ueditor/jsp/upload/image/20180614/1528961166679031200.jpg\" title=\"1528961166679031200.jpg\" alt=\"6417-fyqzcxh1444093.jpg\"/></p><p style=\"white-space: normal; line-height: 26px; text-align: center; background: white;\"><img src=\"http://118.31.36.160:8080/kindergarten/ueditor/jsp/upload/image/20180614/1528961176174007672.jpg\" title=\"1528961176174007672.jpg\" alt=\"ee68-fyqzcxh1447948.jpg\"/></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">芬兰学生的成绩在全球范围遥遥领先</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">我们翻遍芬兰教育资料，找出了芬兰教育世界第一的</span><span style=\"font-family: Helvetica, sans-serif; color: rgb(62, 62, 62);\">10</span><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">个秘密。</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-family: Helvetica, sans-serif; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">1</span></strong></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">全世界落差最小的教育体制，实现了真正的教育公平</span></strong></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">在芬兰上学，根本不存在买学区房、提前几年排号入学的情况。因为The neighborhood school is the best school——家附近的那所学校，就是最适合孩子的好学校。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">“</span><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">我们这样的小国，不能容许社会上出现学习落差与失衡！”为了实现真正的教育公平，芬兰尽力做到所有的学校都是水准一致的高品质：学校不排名、好老师分散全国、教学质量城乡差距小。</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">2</span></strong></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">学生上学年龄普遍比其他国家</span></strong></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">“</span><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">我们相信7岁以下的孩子不需要为上学作准备，”芬兰一个日托中心的管理者说，“他们需要时间去玩，去做更多的体力活动。这才能产生创造力。”</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">所以芬兰的孩子，都是满七岁才入学。而且学生上学后学校没有制服、不标榜精英培养（基础教育不分“快慢班”和“重点班”）。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">所有的学生都是同等对待，有时候倒是会给跟不上进度的孩子单独辅导，而不是对优等生单独辅导。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">3</span></strong></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">教师是非常受尊重的专业人士</span></strong></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">在芬兰，要通过激烈的竞争和层层筛选，才能成为教师。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">所有的竞选者，都至少要拿到硕士学位，才有资格参与教师行业的竞争。这些措施保证了只有10%的顶尖优越者，才能成功进入教育行业。</span></p><p style=\"white-space: normal; text-align: center;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\"><img src=\"http://118.31.36.160:8080/kindergarten/ueditor/jsp/upload/image/20180614/1528961274468006291.jpg\" title=\"1528961274468006291.jpg\" alt=\"cadd-fyqzcxh1448339.jpg\"/></span></p><p style=\"white-space: normal;\"><span style=\"color: rgb(62, 62, 62); font-family: 宋体; font-size: 15px;\">所以教师是专业性非常高的一种职业，和律师和医生一样受尊重。</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">4</span></strong></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">最高机密：芬兰学生没有课后作业</span></strong></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">教师会致力于在课堂上就让学生们理解消化课堂知识，这样就不用让孩子在家写作业写到半夜，而是拿更多的时间来消化学到的知识。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">Finland allows their children to be children</span><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">。与其把孩子关在教室里死记硬背，这里的老师只想让孩子就做一个孩子，去好好地玩，在玩耍中探索和学习。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\"><img src=\"http://118.31.36.160:8080/kindergarten/ueditor/jsp/upload/image/20180614/1528961364370083067.jpg\" title=\"1528961364370083067.jpg\" alt=\"23d7-fyqzcxh1449024.jpg\"/>&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">在老师眼里，学生可以去做自己想做的事情，比如孩子逃课去爬树，老师也不介意，因为在他们看来爬树也能学到知识。</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">5</span></strong></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">阅读习惯：每日至少半小时阅读</span></strong></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">芬兰孩子们的每天家庭作业之一，就是“至少半小时的自我阅读”。不是孩子继续啃教科书，而是鼓励去读自己想看的书，用自我引发兴趣的方式，让他们沉浸在书籍的世界。</span></p><p style=\"white-space: normal;\"><img src=\"http://118.31.36.160:8080/kindergarten/ueditor/jsp/upload/image/20180614/1528961381534047817.jpg\" title=\"1528961381534047817.jpg\" alt=\"a489-fyqzcxh1449371.jpg\"/></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">芬兰教育体系把阅读与写作视为极重要的学习与生活技能。不仅教材和辅具的选择范围很广泛，教师更有绝对的自由去选择最适合孩子的阅读内容、范围与进阶程度，也会倾听并支持孩子的想法。</span><span style=\"color: rgb(62, 62, 62); font-family: 宋体; font-size: 15px;\">也会和图书馆一起举办不同的形式活动，让学生和亲子的阅读更加生活化。在芬兰，有80%的人有去图书馆的习惯，每年造访图书馆的人次多达4000万。</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">芬兰名言：Read even if you sink</span></strong></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><img src=\"http://118.31.36.160:8080/kindergarten/ueditor/jsp/upload/image/20180614/1528961391131070493.jpg\" title=\"1528961391131070493.jpg\" alt=\"cd4e-fyqzcxh1449739.jpg\"/></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: darkorange;\">《姆明一族》是每个芬兰人都读过的故事，代表芬兰精神和国家认同定位。</span></strong></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-family: Helvetica, sans-serif; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">6</span></strong></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">阅读环境：图书馆是芬兰的人文地标</span></strong></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">芬兰众多的公立图书馆，除了都有现代化的设备、丰实的藏书。其中儿童区，更是宽广、丰富、完善，而且童趣十足。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">不仅有各种儿童书籍期刊，各种语言的童书、音乐、DVD、舒适的沙发、随处可见的可爱玩偶，娃娃及装饰，营造出一种亲子阅读的自由氛围。</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">7</span></strong></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">每个学生都要掌握多种语言</span></strong></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">芬兰教育非常重视语言能力的培养。学生掌握的语言越多，眼界就越开阔，就有更多的机会接触到全世界的知识。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">因此语言的学习，不论是母语（芬兰文），还是外语（英文、法文、德文等），在芬兰基础教育的课程时数中，都占了相当大的比重。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">一般的芬兰学生，至少会有三至四种语言学习的机会，至少会熟练运用3种以上的语言。</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">8</span></strong></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">最重要的原则：要让孩子做快乐的人</span></strong></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">让学生快乐成长，这是学校和老师想让孩子做的第一件事。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">所以他们不仅支持孩子去玩，还会主动带着孩子去玩。在芬兰，每节课后都会有漫长的课外时间，教师会带着孩子冲向户外玩耍。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">因为芬兰的冬天太漫长太黑暗，所以他们更珍惜户外玩耍的机会。就连刚出生的小孩子，都会被家长带着出门，感受大自然的气息。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">所以芬兰学生的假期很长（2个半月，以前是3个月），这样孩子就可以随心所欲地在大自然中玩耍。</span></p><p style=\"margin-top: 10px; white-space: normal; background: white;\"><br/></p><p style=\"margin-top: 10px; white-space: normal; background: white;\"><br/></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">9</span></strong></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">烹饪、艺术、体育等，所有的都要学</span></strong></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">芬兰的音乐教育非常成功，民族乐派大师西贝流士和人们耳熟能详的《芬兰颂》，就是来自芬兰。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">除此之外，芬兰学生们要学的还有很多，比如游泳、花式溜冰、曲棍球、绘画、艺术、乐器、音乐、体育和阅读等。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">是不是很奇怪？明明芬兰学生上课时间最少、写作业时间最少，学的东西却一点也不少。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">这是因为，芬兰老师每天会花4个小时研究如何让课堂教学更有成效。因此，学生们大多能够很快掌握需要学会的知识。</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">10</span></strong></p><p style=\"white-space: normal;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">让孩子有时间玩耍社交</span></strong></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">拥有着欧洲最顶级的教育系统，芬兰的孩子在7岁以前却不学任何数学、阅读和写作，光让孩子去玩。那7岁之前，他们做了什么呢？</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">日托中心会教他们一些社交习惯。比如，学会交朋友，学会尊重他人，比如，自己穿衣服。芬兰官方也一直强调的是学前教育的“学习的乐趣”，语言的丰富性以及沟通能力。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">每天90分钟的户外活动必不可少。“芬兰的幼儿园不是为升学作准备的，”芬兰教育专家Pasi Sahlberg 说，“主要是保证孩子是快乐的，并成为一个有责任的人。”</span></p><p style=\"margin-top: 10px; white-space: normal; background: white;\"><br/></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">芬兰学生的整体表现都很出色。他们不只在阅读、解决问题能力项目上的评比排行高居不下，在数学、自然科学等科目，也和大家习以为常认定数理能力比较强的亚洲学生旗鼓相当。</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-family: Helvetica, sans-serif; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">芬兰学生优秀的一个很大因素是：玩才是最大的竞争力。</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-family: Helvetica, sans-serif; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">因为玩给了孩子关于如何学习的技能。一旦他们享受在玩中完成任务的过程，无论是演一个故事或者建造一个房子，孩子为了提升竞争力，都有动力去不断完善和提高自己。</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-family: Helvetica, sans-serif; color: rgb(62, 62, 62);\">&nbsp;</span></p><p style=\"white-space: normal; line-height: 26px; background: white;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">芬兰教育世界第一背后的</span><span style=\"font-size: 15px; font-family: Helvetica, sans-serif; color: rgb(62, 62, 62);\">10</span><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62);\">个秘密，你认同几个？</span></p><p><br/></p>',0,'N','2018-06-18 18:37:57',0,3,-1,-1,-1,'2018-06-18 18:37:57',-1,'2018-06-18 18:58:28',-1,'ID',NULL,NULL,NULL),
 (45,1,1,'','未来的课堂：从功利回归至育人','未来的课堂应该是怎么样的？大家一定会各抒己见，然而，不管大家对未来的课堂还会有各自的预测和理解，因为大家对未来的预测，对怎样设计面向未来的今日课堂，对课堂里“学什么、怎样学”与未来学生所需要的素养的关系还有不同的见解。','/uploadFile/news/20180618184340_390_285.png',NULL,'<p style=\"white-space: normal; text-align: center; line-height: 24px;\"><strong><span style=\"font-family: 宋体; color: rgb(99, 36, 35);\">未来的课堂：从功利回归至育人</span></strong></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; text-align: center; line-height: 24px;\"><strong><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">尹后庆（上海市教育学会会长、国家督学）</span></strong></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">未来的课堂应该是怎么样的？大家一定会各抒己见，然而，不管大家对未来的课堂还会有各自的预测和理解，因为大家对未来的预测，对怎样设计面向未来的今日课堂，对课堂里“学什么、怎样学”与未来学生所需要的素养的关系还有不同的见解。但不管怎样，有一点是肯定的：</span><strong><span style=\"font-size: 15px; font-family: 宋体; color: rgb(54, 96, 146); letter-spacing: 1px;\">未来已来，未来的课堂其实就是当下的课堂。当下的课堂里最重要的是：我们不能重复地复制以前的课堂，不能让将在未来生活的孩子今天仍然生活在过去的课堂里！</span></strong></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;&nbsp;</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal; text-align: center; line-height: 24px;\"><strong><span style=\"font-family: 宋体; color: rgb(23, 54, 93); letter-spacing: 1px;\">学校重要性没有削弱</span></strong></p><p style=\"white-space: normal; text-align: center; line-height: 24px;\"><strong><span style=\"font-family: 宋体; color: rgb(23, 54, 93); letter-spacing: 1px;\">老师职业不会消失</span></strong></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">联合国教科文组织有一本书——《反思教育：向“全球共同利益”的转变？》。书中提到，“目前的学校教育还是为了满足一个多世纪之前的生产需求而设计出来的。学习的模式在过去二十年里发生了巨大的变化，知识来源改变了，我们与知识之间的交流互动方式也改变了。然而正规教育系统变化缓慢，目前的状态与其过去二百多年间的情况依然非常相似。”</span></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; text-align: center; line-height: 24px;\"><br/></p><p style=\"white-space: normal; text-align: center; line-height: 24px;\"><strong><span style=\"font-family: 宋体; color: rgb(23, 54, 93); letter-spacing: 1px;\">课堂改革</span></strong></p><p style=\"white-space: normal; text-align: center; line-height: 24px;\"><strong><span style=\"font-family: 宋体; color: rgb(23, 54, 93); letter-spacing: 1px;\">重在解决学什么，怎么学</span></strong></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">今天我们观摩了芬兰教师的课堂，我们可以从中了解她们是怎样设计课堂的。</span><strong><span style=\"font-size: 15px; font-family: 宋体; color: rgb(54, 96, 146); letter-spacing: 1px;\">我个人认为最值得关注的是：把知识的学习放在情景中进行。</span></strong><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">这次芬兰两位老师的课，设计了冬天野外的雪地上，生活着很多动物。以冬天动物的生活作为主题和情景，并且把语言、数学、音乐、制作等学科的学习任务在场景中展开。场景与学习内容联系在一起，让知识与生活建立了联系。当学习用符号表征的知识、事实的知识与生活情景结合的时候，知识及其鲜活的价值往往会转化为学习者内心的真实的感受，转化为对外部世界的真切的理解，才能在学习中让知识形成能力，形成情感。</span></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; line-height: 24px;\"><br/></p><p style=\"white-space: normal; text-align: center; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span><strong><span style=\"font-family: 宋体; color: rgb(23, 54, 93); letter-spacing: 1px;\">让教育从追求外在的、功利目标</span></strong></p><p style=\"white-space: normal; text-align: center; line-height: 24px;\"><strong><span style=\"font-family: 宋体; color: rgb(23, 54, 93); letter-spacing: 1px;\">转向到真正直抵人心</span></strong></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><strong><span style=\"font-size: 15px; font-family: 宋体; color: rgb(54, 96, 146); letter-spacing: 1px;\">今天的教育和学习不仅在于实现显性的知识目标，教育和学习需要从单纯的功利性目标走向真正直抵人心，成为真正育人的教育，真正回归育人的本原。</span></strong><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">比如说，音乐课不仅是教孩子会唱歌，会弹琴，而是让孩子学会感受生活的美和表达这种美。上午的芬兰的老师有节音乐课——这样的音乐课和我们以往理解的唱歌课的确是不一样的。体现了音乐不仅仅是为了让孩子学会唱歌、弹琴，而是让他们学会在具体的情景下，充满参与艺术活动的热忱，感受生活之美，学会创意表达。感知音乐艺术和用音乐语言表达，仅仅用人声还不够，还得用各种乐器，用音乐的不同旋律和节奏来表达不同的情绪和感受。</span></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; line-height: 24px;\"><br/></p><p style=\"white-space: normal; text-align: center; line-height: 24px;\"><strong><span style=\"font-family: 宋体; color: rgb(23, 54, 93); letter-spacing: 1px;\">凸显学生学习的主体性</span></strong></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">课程改革要解决孩子怎样学的问题。时代的发展需要我们能在现有条件下尽最大努力去思考、去实践、去改变，不断寻找空间，力求有所改变。</span><strong><span style=\"font-size: 15px; font-family: 宋体; color: rgb(54, 96, 146); letter-spacing: 1px;\">重要的是把孩子作为主体，去思考能给他们提供什么，支持他们怎样发展。</span></strong><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">本次活动中，各个学校展示了积极探索的成果，但还有很多问题需要深化研究和持续实践总结。比如，遇到这样的学习方式，老师会说我原来会教学，你这么说，我现在反而不会教了；家长也会质疑这样的课堂是否浪费时间等等。这些都还需要更加深入的研究和实践才能更好地回答。</span></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal; text-align: center; line-height: 24px;\"><strong><span style=\"font-family: 宋体; color: rgb(23, 54, 93); letter-spacing: 1px;\">未来已来，</span></strong><strong><span style=\"font-family: 宋体; color: rgb(23, 54, 93); letter-spacing: 1px;\">未来的课堂就是当下的课堂</span></strong></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">正如吕华琼校长提到的，未来的课堂其实就是当下的课堂。这里面要解决的问题是怎么把理想的课堂变成孩子获得的课堂。</span></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">我们很多学校设计了很多课程。但我要问的是：这些课程是不是已经成为孩子获得的课程了?孩子从课堂中获得了什么？你有足够的证据去证明吗？显然一旦去研究这个问题时，我们会发现，教学本该达到的目标，我们还远远没有达到。关于怎么评价今天的课堂，马蒂娜和苏明娜很自信，她们说她们的老师和家长互相信任。而我们也许需要去寻找更实在、更科学的方法去证明我们的目的有否达到，从而让我们更加扎实，更加有效地把理想的课程成为学生获得的课程。</span></p><p style=\"white-space: normal; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">&nbsp;</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">最后，我想说：我们的课堂永远需要面向未来的。因为课堂里的孩子拥有未来。</span><strong><span style=\"font-size: 15px; font-family: 宋体; color: rgb(54, 96, 146); letter-spacing: 1px;\">我们的课堂需要立足当下，但是当下的课堂千万不能成为复制过去的课堂。要让孩子们置身于面向未来的课堂，这是在新时代我们送给孩子们的最好的礼物。</span></strong></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\"><br/><br/></span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-family: 宋体; color: rgb(54, 96, 146); letter-spacing: 1px;\">（本文选自</span><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">1</span><span style=\"font-family: 宋体; color: rgb(62, 62, 62); letter-spacing: 1px;\">月7日－8日在“面向未来的课堂”中芬课程教学改革实践交流活动中尹后庆的发言）</span></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\"><br/></p><p style=\"white-space: normal;\">&nbsp;</p><p><br/></p>',0,'N','2018-06-18 18:38:30',0,2,-1,-1,-1,'2018-06-18 18:38:30',-1,'2018-06-18 18:43:42',-1,'ID',NULL,NULL,NULL),
 (46,1,1,'','未来学校教育面临的三大挑战','未来学校教育面临的三大挑战','',NULL,'<p style=\"white-space: normal; text-align: center;\"><strong><span style=\"font-size: 20px; font-family: 宋体; color: rgb(0, 112, 192);\">未来学校教育面临的三大挑战</span></strong></p><hr style=\"white-space: normal;\"/><p style=\"white-space: normal; text-align: center;\"><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">挑战一：以学科教学为核心的课程体系正面临重大挑战</span></strong></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\"></span></p><hr style=\"white-space: normal;\"/><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">今天很多学科之间的内在联系越来越紧密，而传统分科的教学，基本是按单学科的框架进行传授，给出的也多是标准答案，其内容普遍与现实生活相割裂，缺乏系统性，解决今天社会发展的实际问题的能力比较弱。</span><br/></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">未来多学科、跨学科、甚至是超学科的学科思想会被越来越多地运用到基础教育的课堂。</span></strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">所谓的超学科，是一些以往并不存在的新学科，之所以有超学科，是因为原有的学科概念已经不足以概括我们今天的发展。比如，教育神经学。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">从另一个视角看，教育部《关于全面深化课程改革落实立德树人根本任务的意见》的文件中，<span style=\"color: rgb(0, 112, 192);\">学生发展核心素养首次被提出。</span>这一提法有现实的针对性，突出强调了个人修养、社会关爱、家国情怀，更加注重自主发展、合作参与、创新实践。<span style=\"color: rgb(0, 112, 192);\">这已经不是单纯地从学科的角度来考虑教育问题了，而是从人的素养这一更大的格局来考虑教育的发展和人的发展。</span></span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(192, 0, 0);\">但是今天以学科教学为核心的课程体系能否支撑起学生发展所需的核心素养？</span></strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">我认为，挑战很大。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">中国传统的学科教学体系，对于打好学科知识的基础是做的比较好的，但是从培养人的思维能力、自主发展、创新实践等方面来看，却是不足的。<span style=\"color: rgb(0, 112, 192);\">随着信息技术和科学技术的发展，今天很多学科知识的更新速度在加快，许多以往很难、很复杂的知识，可能在学生毕业踏上社会时，已经被淘汰、或者被更新。</span></span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">以前我们说的“学习”更多地是指，我们把不懂的学会了，这是学科性的思路。但现在“学习”则意味着，你还需要系统性、综合性的思维来做出选择、判断，需要学会借助各方力量来寻求解决问题的路径等等。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">面对着时代的飞速发展和人才培养的全新要求，传统学科教学中纯知识性学习的重要性正在减弱。而另一些基于学科基础，与实际、与社会发生联接的内容和学生发展核心素养的培育，其需求正在逐步增大。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">在今天，一个人能够掌握一个产品全过程的、所有环节的格局已经过去了。现在人们更多面对的是一种黑箱式的学习方式，很多产品都是由分工协作完成，一个人不需要什么都知道，但这其中学习能力、迁移能力、协同能力等变得很重要。打个比方，你不需要知道你的智能手机到底是怎么工作的，但你很需要知道它们该怎么用。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">但这些素养、能力和内容还缺乏清晰的体系和架构，因而即使目前有的已经进入课程体系，也仍是零碎的、不完整的。譬如，创新思维能力培养，学科教学是一方面，但却并不能承担起全部重任。因为创新思维并不局限于某一个学科领域的创新。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">因而以学科课程作为主要内容主要核心的课程架构其实已经受到了严峻的挑战。</span><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">未来虽然传统的分科教学仍然会存在，但比重会下降，更适合人的发展的、更适合智能社会的一种新的课程架构正在逐渐形成。</span></strong></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(62, 62, 62);\">而一些趋势已经显现，比如以往所有学生都必须学习的、基本相同的学科教学内容正在被打破。</span><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">从国际趋势看，特别在高中阶段，已经有很多学校开始让学生可以学习不同的学科，或者学习同一学科里面不同水平层次。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">上海的高考新政中一项重要的改革“学业水平考”的学科六选三以及合格考、等级考的区分，就是对此迈出的一大步。而在国际课程中，这种学科学习模式已经是常态，比如在IB课程中，实验科学包含了理化生三门学科，但它并不要求学生三门都学，你只要学习其中一门就可以了，但是这一门的学法和传统的不一样，它会从实验科学的角度对这门学科的学习提出更高的要求，这是基于学科群的思想。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">同时，多学科、跨学科、甚至是超学科的学科思想被越来越多地运用到基础教育的课堂。比较典型的是美国的STEM教育。STEM最鲜明的特质是，由科学、技术、工程和数学学科所组成，但又不是它们的简单组合，STEM教育是基于项目的学习，是跨学科的、体现合作与团队工作的、运用科学的探究过程和工程设计过程，同时也是关于实际问题解决的、连接抽象知识与学生生活的教育。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">在世界许多国家、包括中国，也有许多此类的探索，比如以活动课程、课题研究、探究式学习、主题式学习、项目等为抓手，进行多学科的共同学习、跨学科的共同学习。虽然已有很多途径，但仍然不系统、不完整，仍有待研究和突破。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\"><br/></span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\"></span></p><hr style=\"white-space: normal;\"/><p style=\"white-space: normal; text-indent: 32px; line-height: 24px; text-align: center;\"><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">挑战二：如何让学生学会借用外脑是教育面临的又一挑战</span></strong><br/></p><p style=\"white-space: normal;\"><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\"></span></strong></p><hr style=\"white-space: normal;\"/><p style=\"white-space: normal; text-indent: 32px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">阿尔法狗的出现，意味着<span style=\"color: rgb(0, 112, 192);\">智能社会、数字化社会已经向我们逼近，而且这个步伐可能比我们预想得还要快。</span>未来，如果你要这样的社会生存的话，靠自身去掌握事物的全部显然已经做不到了，虽然，你的学科背景、学科水平还是会起到一定的作用，但是这个作用所占的权重会大幅度下降。</span><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(192, 0, 0);\">人更多的要学会借用外脑，让内脑和外脑连接在一起，才能适应未来社会的挑战。</span></strong></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">其实今天已经有很多的技术成为了我们人体的衍生，比如汽车就是人脚的衍生。未来还会出现另外一种衍生，那就是脑的衍生，或者说是脑的部分功能的衍生。比如，计算机的记忆功能、存储功能，智能手机的应用等等这些设备其实就都是脑的衍生。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">内脑是用自己的大脑，外脑就是借别人的智慧。未来，人的外脑会越来越多，越来越强。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">但是人的大脑功能的衍生和人体其他功能的衍生一样，都是要通过学习才能够实现真正衍生的，不学习不会自动得到衍生。</span><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">许多人其实已经开始广泛地使用外脑了，但这更多的是一种自觉的行为，是人学习的自我选择的行为。</span><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(192, 0, 0);\">但是如何使用、并用好我们的外脑，这部分内容事实上还并没有真正地进入到我们的课程体系中去。</span></strong></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">而未来，你能否适应社会发展的需求，能否胜任工作岗位的需要，这跟你对这些衍生功能的学习掌握程度是紧密相关的。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(192, 0, 0);\">而这个过程中，人的选择能力、学习能力、判断能力就成为了人的核心能力。</span></strong></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">所以，今天对人价值的判断标准，已不仅仅局限在你自身的学历水平、知识水平，更多了个以往从未有过的维度，那就是你借助外脑扩展你能力的这种能力。</span></strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(62, 62, 62);\">这也就是说，</span><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">我们不只是要发展自身，还要不断学习运用外手、外脚、外耳和外脑，</span><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">当社会变得不确定性越来越强的时候，你的学习能力越强、使用工具的水平越高，你应对不确定性的层次就越高，综合实力就越强。</span></strong></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">对此，教育是否也做好了准备？</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\"><br/></span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\"></span></p><hr style=\"white-space: normal;\"/><p style=\"white-space: normal; text-indent: 32px; line-height: 24px; text-align: center;\"><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">挑战三：学校围墙进一步被打破，校园的概念在重新界定中</span></strong></p><hr style=\"white-space: normal;\"/><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-family: 微软雅黑, sans-serif; font-size: 15px;\">于是第三个问题就呼之欲出了，那就是：学校的围墙要进一步被打破。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">学校校园原来是有固定空间的，但是现在出现的很多新现象改变了原来的校园的概念。</span><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(192, 0, 0);\">就像人需要借助外脑一样，学校也需要借助外脑，来实现对未来学生的培养，来弥补智能社会下学校在资源和能力上的不足。</span></strong></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(192, 0, 0);\">现代形态的校园，应该是校园学习和社会学习深入结合。</span></strong></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">学生更多的创新能力的养成将来自于，学校与企业的结合，与社会的结合，中学与大学的结合。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">在德国，学校与企业的融合非常的紧密。我在德国职业教育学校看到，宝马、大众等公司都把最新的车型、最新的研究成果直接放到学校，学生接触到的都是最前沿的技术和产品。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(0, 112, 192);\">学生更多的成长与发展将来自于，学校能给学生找寻到的最大程度的资源与课程。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif;\">以前教室的配置只有黑板，后来开始有了电脑、屏幕、投影仪、电子白板。在iPad出现以后，其实电子白板就没用了，因为每个iPad就是电子白板。或许若干年后，iPad和智能手机将成为教室新的标准配置。而随着这些的改变，教与学的方式、学校内部的连接方式、人际交往方式都将发生质的改变。但是从另一个角度看，人可能就没有隐私了，变化总会引发新的问题出现。</span></p><p style=\"white-space: normal; text-indent: 32px; line-height: 24px;\"><strong><span style=\"font-size: 15px; font-family: 微软雅黑, sans-serif; color: rgb(192, 0, 0);\">但学校一定会面临这样的转型，学校打破围墙是必然的，只有从自身与外界的连接中重新审视学校教育，才能重塑学校教育。</span></strong></p><p style=\"white-space: normal;\">&nbsp;</p><p style=\"white-space: normal; text-align: right;\"><span style=\"font-family: 宋体; color: rgb(117, 117, 118);\">文 | 唐盛昌</span></p><p style=\"white-space: normal; text-align: right;\"><span style=\"font-size: 12px; font-family: 宋体;\">上海市基础教育国际课程比较研究所所长</span></p><p style=\"white-space: normal; text-align: right;\"><span style=\"font-size: 12px; font-family: 宋体;\">原上海中学校长，特级校长、特级教师</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p><span style=\"font-family: 宋体;\"><br/></span></p><p><br/></p>',0,'N','2018-06-18 18:38:55',0,1,-1,-1,-1,'2018-06-18 18:38:55',-1,'2018-06-18 18:38:55',-1,'ID',NULL,NULL,NULL),
 (47,1,1,'','2017中国学前教育热点事件盘点','２０１７年，是中国学前教育实现跨越式发展的一年，第六个全国学前教育宣传月广泛深入推进，第三期学前教育行动计划全面开启，全国学前教育改革发展实验区开展建设……站在辞旧迎新的岁末，我们愿与读者一起回顾２０１７年中国学前教育发展的热点事件','',NULL,'<p style=\"white-space: normal; text-align: center;\"><strong><span style=\"font-size: 19px;\">2017</span></strong><strong><span style=\"font-size: 19px; font-family: 宋体;\">中国学前教育热点事件盘点</span></strong></p><p style=\"white-space: normal;\"><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">２０１７年，是中国学前教育实现跨越式发展的一年，第六个全国学前教育宣传月广泛深入推进，第三期学前教育行动计划全面开启，全国学前教育改革发展实验区开展建设……站在辞旧迎新的岁末，我们愿与读者一起回顾２０１７年中国学前教育发展的热点事件，请学前教育界知名专家进行评析，期待能凝心聚力，共同为学前教育事业的大发展鼓与呼，为学前教育人做方向性引领、提供专业化服务。　</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">及时传递教育行业的深度信息、透视相关领域的专业实践，提供深入可行的专业引领，促进我国教育事业发展，是中国教育报发展的使命！学前周刊希望与各方力量携手，深度服务中国学前教育的明天。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"><br/></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">０１------------------------------------</span><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px; text-align: center;\"><strong><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">学前教育立法</span></strong></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【事件回溯】２０１７年，全国“两会”期间，推动学前教育立法，解学前教育事业根本性发展难题，引起社会和学前教育界前所未有的关注。３月５日、３月１５日，中国教育报先后刊发文章《庞丽娟呼吁：尽快研究出台〈学前教育法〉》，刊发整版深度报道《为中国学前教育发展而立法》，回应学前教育立法这一热点。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【专家评析】</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">全国人大常委会委员、中国教育政策研究院副院长、北京师范大学教授庞丽娟：</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">为中国学前教育发展而立法</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">发展学前教育，有效解决当前存在的突出问题，最根本的就是要尽快研究出台专门法——《学前教育法》，对我国学前教育公共服务建设与改革发展中的深层次难题、关键性体制机制问题作出明确规定。应以法律的形式，明确学前教育的性质、地位，明确学前教育是国家基础教育、是国民教育体系的重要组成部分，并且是一项重要的社会公益事业，对促进国民素质整体提高、社会公平、脱贫攻坚等均具有基础性、先导性作用。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">当前，在《教育法》所规定的四个独立学段中，唯独学前教育没有立法，不能有效地发挥规范和保障当前以及未来学前教育发展的作用。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">习近平总书记曾指出“教育公平是社会公平的重要基础，要不断促进教育发展成果更多更公平惠及全体人民，以教育公平促进社会公平正义”。《学前教育法》立法，能确保政府发展学前教育事业的职责落实到位，管理体制、各地政府投入有保障；确保扩大学前教育资源，特别是普惠性资源，尤其是在广大的中西部、革命老区、民族地区、边疆地区和贫困地区实现普及普惠；确保教师待遇落实和队伍建设与教育质量提高。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">当前，资源不足、特别是普惠性资源严重不足是当前我国学前教育事业发展的突出矛盾。同时，不少地方幼儿园运转困难，因为缺乏经费；有园开不了，因为缺乏教师；也有一些教师的专业素质不高，教育质量不好。导致这些问题的原因主要在于尚未建立起适应发展需要的学前教育管理体制、投入与运行保障机制、办园体制等，缺乏教师队伍建设、特别是身份地位与待遇保障政策；在一些地方政府职责落实不到位，或者说努力与认识不到位，对投入办园、教师待遇与准入资质等有规定却不落实。最根本的原因，在于我国没有《学前教育法》，对上述这些学前教育改革发展中的深层次难题、关键性体制机制问题，没有作出明确规定。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">我们的国家大、人口多、区域发展不均衡，这就使得立法变得非常复杂。要从根本上解决体制机制的问题，需要各方面齐心携手、开拓创新。为了孩子的健康成长，为了千家万户不再为入园揪心，安心地工作生活，更为国家当前和未来的发展，需要我们使出洪荒之力，同心协作，共同推进学前教育立法进程，加快学前教育立法步伐！</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"><br/></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">０<span style=\"line-height: 32px;\">２</span>------------------------------------</span><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px; text-align: center;\"><strong><span style=\"color: rgb(49, 49, 49); font-family: 宋体;\">学前教育纳入义务教育？</span></strong><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【事件回溯】在全国“两会”期间，某政协委员“将学前教育纳入义务教育”的提案，引起社会的广泛热议。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【专家评析】</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">全国政协委员、北京师范大学教授刘焱：</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">学前教育不宜纳入义务教育</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">上幼儿园难，上公办幼儿园更难，这已经成为很多家长的“心头病”。将学前教育纳入义务教育的提案，因抓住老百姓希望政府帮他们减轻教育成本的心情，引起社会的广泛热议。那么，这一建议是否可行？</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">３月５日，中国教育报刊出报道，《刘焱：“所谓‘１号提案’，我有不同意见”》就此回应热点。刘焱认为，现在我们国家的财力有限，还不适合这样提。从国家的财政能力、师资储备来看，也不具备可行性。义务教育带有强制性的特点，我们不能强迫３岁的孩子去上学。从世界的情况看，现在也只有很少的国家把学前三年教育纳入义务教育。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">回归根本，我们应该怎么解决入园难问题？出路应该是加快建设普惠性幼儿园的发展步伐，让每一个家长能够在家门口，交不多的费用，让孩子进入比较好的幼儿园。目前，我们学前教育的普惠性还是不够的，但是解决这个问题，并不是说把这三年都纳入到义务教育里。参考欧美国家基本的做法是学前一年免费，有的纳入义务教育主要目的是帮孩子做好入学前的准备。在当前我们国家的经济形势下，学前一年免费应该优先从老少边穷地区开始，正如习近平总书记一再强调的，别让贫困孩子输在起跑线上。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">０<span style=\"line-height: 32px;\">３</span>------------------------------------</span><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px; text-align: center;\"><strong><span style=\"color: rgb(49, 49, 49); font-family: 宋体;\">教育部印发《幼儿园办园行为督导评估办法》</span></strong></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【事件回溯】４月２６日，教育部印发了《幼儿园办园行为督导评估办法》（以下简称《办法》），对幼儿园办园行为督导评估的目的、原则、范围、周期、内容、组织实施和结果运用等作了具体规定。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【专家评析】</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">中国教育科学研究院副研究员史亚娟：</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">幼儿园督导评估制度为学前教育事业保驾护航</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">《办法》是我国第一部有关幼儿园评估的教育法规。《办法》对幼儿园办园行为督导评估的目的、原则、范围、周期、内容、组织实施和结果运用等作了明确规定，它的发布和实施必将对规范幼儿园办园行为、保障幼儿健康快乐成长、促进学前教育事业持续健康发展产生重要影响。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">“十二五”以来，我国学前教育事业取得了跨越式发展，学前教育规模迅速扩大，普及程度快速提高，人民群众对学前教育的需求也逐渐从“有学上”向“上好学”转变。但是，随着学前教育规模快速扩张，一些幼儿园存在的办园条件差，园舍场地紧张，玩教具材料匮乏，教职工队伍数量不足、素质不高、流动性大，教育活动“小学化”，财务管理不规范等问题逐渐暴露出来。携程亲子园等虐童事件的发生更是给我们敲响了警钟。预防和减少类似事件的发生，迫切要求相关部门的工作重点从抓普及向抓规范、提质量上转变。可以说，《办法》的出台是规范办园行为、提高保教质量的必然要求，是化解人民群众日益增长的对优质学前教育的需求与学前教育事业发展不平衡不充分之间矛盾的重要举措。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">我国学前教育量大面广，办园主体多样，办园水平参差不齐。除了在册的幼儿园外，各地还有大量的未注册幼儿园，这些幼儿园在规范办园方面问题更突出。加强学前教育管理，规范办园行为，任务十分艰巨。《国务院关于当前发展学前教育的若干意见》中明确要求各地“对目前存在的无证办园进行全面排查，加强指导，督促整改”，《办法》把未取得办园许可的幼儿园纳入督导评估范围，并强制要求所有幼儿园必须参与评估，既是贯彻落实国务院文件要求，也是回应人民群众的关切。对幼儿园的办园行为进行督导评估，可以全面、及时地把握学前教育发展的现状，了解幼儿园办园中存在的困难和问题，有助于相关部门合理制定或调整学前教育政策，是加强对幼儿园的分类管理和监督指导，实现依法治教的重要举措，也是实现第三期学前教育行动计划中提出的“到２０２０年幼儿园保教质量评估监管体系基本形成，办园行为普遍规范，‘小学化’现象基本消除”这一目标的重要保障。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">２０１２年教育部发布了《学前教育督导评估暂行办法》，从“督政”的角度，督促地方人民政府及相关部门切实履行发展学前教育的职责。《幼儿园办园行为督导评估办法》的发布，则是为督导部门对幼儿园办园行为的监测和督导评估提供了依据，从“督学”的角度监督和指导幼儿园加强内部管理，规范办园。两者的贯彻落实，将有助于构建督政、督学、监测三位一体的完整的学前教育督导评估体系，及时发现和解决学前教育事业发展中存在的突出困难和问题，为千千万万幼儿的健康、快乐成长保驾护航。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"><br/></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">０４</span><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px; text-align: center;\"><strong><span style=\"color: rgb(49, 49, 49); font-family: 宋体;\">“游戏——点亮快乐童年”</span></strong><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【事件回溯】５月２３日，“游戏——点亮快乐童年”２０１７年全国学前教育宣传月启动仪式在浙江省安吉县举行，历时一个月的宣传游戏对幼儿童年生活重要价值的活动在全国各地拉开序幕。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【专家评析】</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">华东师范大学教授华爱华：</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">游戏才是儿童的正业</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">对学龄前儿童来说，玩就是学，学就是玩，玩是孩子特有的学习方式。归根结底，让孩子玩好，是对孩子认知习惯、生活和学习方式的顺应，更是对孩子成长规律的尊重。幼儿园要“以游戏为基本活动”，这是我国学前教育改革中的一个重要命题，也是我国幼儿园课程改革的重要指导思想。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">２０１７年全国学前教育宣传月的主题是“游戏——点亮快乐童年”。这一提法，切中时弊。谁都知道，游戏是儿童的天性，但却有多少人无视儿童的这一天性，因为他们不知道，这一天性中蕴含了儿童发展的无限潜能和生长的内动力；有多少人在谴责儿童贪玩，因为他们不知道这正是游戏所体现的早期生命的活力；还有多少人告诫孩子，业会荒于嬉，玩物会丧志，可他们不知道，这一古老的规训并不适用于年幼的儿童，对幼儿来说，游戏才是他们的正当行为。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">游戏是幼儿发展的需要，是幼儿的“心理维生素”，更是幼儿学习的方式。当我们理解了游戏对幼儿成长的意义以后，就能理解“以游戏为基本活动”的幼儿园教育，就能认识幼儿园教师的专业地位。因为，如何创设游戏环境，提供怎样的游戏材料，在游戏中如何观察和引导幼儿的发展，大有学问，这学问远比直接教知识技能要大得多。社会各界应充分认识并积极宣传游戏对儿童学习与发展的独特价值，其价值的实现就体现在幼儿教师的专业性上，体现在全社会对学前教育的正确理解和对教师专业地位的尊重。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"><br/></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">０<span style=\"line-height: 32px;\">５</span></span><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px; text-align: center;\"><strong><span style=\"color: rgb(49, 49, 49); font-family: 宋体;\"><span style=\"line-height: 32px;\">实施第三期学前教育行动计划</span></span></strong></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【事件回溯】５月２４日，教育部在浙江省安吉县召开第三期学前教育行动计划部署会。会议总结交流了实施前两期学前教育三年行动计划的经验，对组织实施好第三期行动计划作出了全面部署。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【教育部声音】</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">学前教育改革发展的又一次重大部署</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">学前教育是人民群众普遍关心的重大民生问题。实施第一期、第二期行动计划６年来，我国学前教育改革发展取得显著成效，学前三年毛入园率和财政投入大幅提高，幼儿园教师队伍建设、幼儿园管理明显加强。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">实施第三期学前教育行动计划，是在我国基本实现教育现代化进入全面攻坚阶段对学前教育改革发展作出的又一次重大部署，是一项需要持续推进实施的重大教育工程、民心工程。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">第三期学前教育行动计划以普及为主题，普惠为主线，确定了到２０２０年全国学前三年毛入园率达到８５％、普惠性幼儿园覆盖率达到８０％左右的“双普”规划目标。各地要聚焦第三期行动计划的主要目标任务，抓住重点难点，集中解决好一些长期制约学前教育发展的瓶颈问题。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">一是把位置摆上去。各地要把第三期行动计划的实施列入政府工作的重要议事日程，相关部门特别是教育部门要把学前教育作为重要的年度任务抓好落实。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">二是把实施落下来。政策措施要实，要结合实际拿出真招、实招、硬招，真正解决学前教育深层次矛盾和实际问题。财政投入要实，要有效整合和使用好中央和地方各级财政资金，切实解决好幼儿园保运转、保工资、保发展、保安全等问题。工程项目要实，制定好项目规划，抓实过程管理，建立一套有效的激励机制。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">三是把督导顶起来。推进学前教育基本普及，要建立一套有效的评估、验收和督导机制，国家层面建立普及学前教育督导评估制度，地方层面建立专项督查机制，聚焦重点和薄弱环节，让政策真正落地、取得实效，确保第三期行动计划如期实现。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">０<span style=\"line-height: 32px;\">６</span></span><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px; text-align: center;\"><strong><span style=\"color: rgb(49, 49, 49); font-family: 宋体;\">《民办教育促进法》及其配套文件正式施行</span></strong><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【事件回溯】９月１日，新修订的《中华人民共和国民办教育促进法》及其配套文件正式施行。这一系列的国家动作，直接关系学前教育领域内的民办机构主体的切身利益与发展前景。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【专家评析】</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">南京师范大学教授王海英：</span></p><p style=\"margin-top: auto; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">将价值共识变成行动共识</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">综观中央及省的政策，积极鼓励与大力支持非营利性民办园成为价值共识，但接下来的关键是，各级政府如何将价值共识通过实际政策变成行动共识。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">根据《我国非营利性民办园制度建设研究》课题组对全国１３个省市３０００余名举办者的调查，全国范围内倾向于选择非营利性民办园的占比为４７％，选择营利性民办园的占比为５３％，贵州等地选择营利性民办园的比例高达５８％。２０１６年的教育部统计公报显示，民办园比例已经高达６５％，民办园在园幼儿数已经高达５３％，如果各项扶持措施不到位，公益普惠的学前教育公共服务体系将会面临巨大挑战。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">而伴随着举办者的营利性倾向，资本市场也开始在幼教领域跑马圈地。据统计，２０１１年一级市场幼教行业投资项目１６例。截至２０１７年６月，幼教领域投融资总额约６９．９７亿元，同比增长３３．８％。当一级市场纷纷投资民办园时，二级市场也开始跨界活动，掀起幼教资产的并购热潮，从２０１５年的７例扩展到２０１６年的１１例，交易金额达２６．４８亿元。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">举办者的营利性倾向、资本进军幼教的现实都严重挑战着非营利性民办园的生存空间，各级政府要切实采取措施，从“应当”“可以”两个层面将非营利性民办园的扶持性措施执行到位。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">第一，非营利性民办园应当按照与公办学校同等原则，享受税收优惠、土地划拨等强制性扶持措施。譬如，对非营利性民办园自用的房产、土地，免征房产税、城镇土地使用税。非营利性民办园按照税法规定进行免税资格认定后，免征非营利性收入的企业所得税。同时，对非营利性民办园要采取土地划拨的方式。在土地无法划拨的地区，各级政府要采取多种措施尽可能保障非营利性民办园与公办园同等性质，或相当于土地划拨的用地政策。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">第二，非营利性民办园可以采取政府补贴、基金奖励、捐资激励、购买服务等选择性扶持措施。因此，各级政府在财有余力的情况下要尽可能采取组合措施，而非单一措施，最大限度地扶持非营利性民办园发展。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">当然，在扶持非营利性民办园的同时，各级政府也要加强监管，在准入、收费、资产、财务、质量等方面进行全面监控，确保公共财政发挥其公共服务职能。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"><br/></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">０<span style=\"line-height: 32px;\">７</span></span><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px; text-align: center;\"><strong><span style=\"color: rgb(49, 49, 49); font-family: 宋体;\">深化教育体制机制改革</span></strong><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【事件回溯】９月２４日，中共中央办公厅、国务院办公厅印发施行《关于深化教育体制机制改革的意见》（以下简称《意见》）。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【专家评析】</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">中国教育科学研究院副研究员高丙成：</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">深化学前教育体制机制改革，加快推进学前教育现代化</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">近年来，党和国家高度重视学前教育发展，我国学前教育事业实现了跨越式发展。截至２０１６年，我国在园儿童４４１４万，学前三年毛入园率达到７７．４％，幼儿园教职工３８１万，学前教育经费总投入２８０２亿元，学前教育发展迈上新台阶。但是，由于量大、面广、线长，底子薄、欠账多，学前教育仍然是我国教育体系的薄弱环节，仍然面临着保教方式不科学、办园体制不健全、管理体制不明确、保障机制不完善等诸多问题、困难和挑战。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">《意见》的出台是为了深化教育体制机制改革，全面贯彻党的教育方针，坚持社会主义办学方向，全面落实立德树人根本任务。其中，《意见》对学前教育体制机制改革进行了顶层设计、系统谋划和全面部署，对于深化我国学前教育体制机制改革、办好学前教育、实现幼有所育目标具有重大意义。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">针对这些人民群众关心的热点难点问题，《意见》进行了系统性、针对性和科学性的设计与安排，提出了清晰的工作思路、改进办法、改革举措和组织实施等方面的部署和要求。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">坚持科学保教，确保学前教育质量提升。为了纠正“小学化”倾向，加强科学保教，提高学前教育质量，《意见》提出要遵循幼儿身心发展规律，坚持以游戏为基本活动，合理安排幼儿生活作息；加强幼儿园质量监管，规范办园行为。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">优化办园模式，确保学前教育公益普惠。为了有效推进解决“入园难”“入园贵”问题，《意见》提出要创新学前教育普惠健康发展的体制机制，以县域为单位制定幼儿园总体布局规划，大力发展公办幼儿园，新建、改扩建一批普惠性幼儿园。鼓励社会力量举办幼儿园，支持民办幼儿园提供面向大众、收费合理、质量合格的普惠性服务。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">健全管理体制，确保学前教育权责清晰。针对学前教育管理体制不明确、职责不清晰等问题，《意见》强调要建立健全“国务院领导、省市统筹、以县为主”的学前教育管理体制。省市两级政府要加强统筹，加大对贫困地区的支持力度。落实县级政府在园所布局、师资建设、经费投入、质量保障、规范办园等方面的主体责任，充分发挥乡镇政府的作用。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">完善保障机制，确保学前教育优先发展。为了解决学前教育投入长效机制不健全、幼儿教师素质不高、督导评估体系不完善等问题，《意见》强调要落实幼儿园教职工配备标准，切实提高教师待遇；要制定出台公办幼儿园生均拨款或生均公用经费标准，健全学前教育经费投入机制；要建立贯通大中小幼的教育质量监测评估制度，依法加强对地方各级政府的督导，确保各项改革举措有谋划、有部署、有落实、有成效。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"><br/></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">０<span style=\"line-height: 32px;\">８</span></span><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px; text-align: center;\"><strong><span style=\"color: rgb(49, 49, 49); font-family: 宋体;\">“幼有所育”</span></strong><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【事件回溯】党的十九大把教育放在优先发展的战略地位，明确提出要“办好学前教育”“幼有所育、学有所教”“努力让每个孩子都能享有公平而有质量的教育”。如何学习贯彻十九大精神，办好学前教育？</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【专家评析】</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">中国学前教育研究会理事长、南京师范大学教授虞永平：</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">“幼有所育”：民生工程国家大业</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">习近平总书记指出：“增进民生福祉是发展的根本目的。必须多谋民生之利、多解民生之忧，在发展中补齐民生短板、促进社会公平正义，在幼有所育、学有所教、劳有所得、病有所医、老有所养、住有所居、弱有所扶上不断取得新进展。”当前，我国社会主要矛盾已经转化为人民日益增长的美好生活需要和不平衡不充分的发展之间的矛盾。人民群众对美好生活需要的现实基础就是保障和改善民生，子女的养育和教育就是重大的民生工程。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">能否实现幼有所育，关系到儿童的身心健康、终身发展；关系到广大人民群众的切身利益；关系到广大人民群众对我国学前教育的满意度；关系到国家和民族的未来；特别是二孩政策实施以来，学前教育成为该政策能否得到有效落实、育龄妇女的生育意愿能否得到激发，进而关系到人口老龄化背景下１６岁以下人口比例能否得到提升等与未来社会的劳动力和安全稳定联系在一起的大事。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">幼有所育是一种态度，表明了党和政府对未来一代健康成长的关心和期盼；幼有所育是一种回应，表明了党和政府直面人民群众的需要和期待；幼有所育是一个承诺，体现了党和政府对发展０—６岁儿童教育的决心和努力；幼有所育是一个号令，要求各级政府加大对学前儿童保育和教育的投入，要求全社会都来关心儿童的发展和成长，都来关心儿童保育和教育事业，共同为儿童获得有质量的教育贡献智慧和力量。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">在我们这样一个人口大国，０—６岁学前儿童有近１．１３亿，实现幼有所育，就是构建世界上最庞大的保育和教育体系。实现幼有所育是一个举世挑战，是一个伟大的民生工程、民心工程和未来工程，也是习近平新时代中国特色社会主义思想在教育实践中的具体体现。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"><br/></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"></span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">０<span style=\"line-height: 32px;\">９</span></span><span style=\"color: rgb(49, 49, 49); font-family: 宋体; text-align: center;\">------------------------------------</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px; text-align: center;\"><strong><span style=\"color: rgb(49, 49, 49); font-family: 宋体;\">０—３岁儿童早期发展服务难题怎么解</span></strong><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【事件回溯】近年来，随着脑科学、心理学、教育学等学科的发展和一些新的研究成果的出现，人们逐渐意识到开展早期综合发展和服务，无论对孩子的健康成长，还是对社会的发展都具有十分重要的意义。然而，当前我国早期综合发展和服务还处于“都管都不管”管理机制较不成熟的发展阶段，如何做好儿童的早期综合发展和服务，随着携程亲子园事件的发生，更加引起社会和政府的重视。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">【专家评析】</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">联合国儿童基金会驻中国办事处教育与发展专家陈学锋：</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">拥有最佳人生开端是每一位儿童的权利</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">人生开端的头三年是一生中大脑发育最为迅速的、可塑性最强的阶段。科学的抚育对儿童大脑发育，乃至整个人的发展至关重要。许多国家将实施儿童早期发展公共服务看作是积累个人和社会“认知资本”、减贫、推动社会公平和繁荣的重要措施。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">中国在“幼有所育”的大目标下，需要进一步提升促进儿童早期发展的战略定位，也需在制定法律政策和配置公共资源方面，给予更优先的考虑。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">在公共服务的目标上，将制定家庭友好型政策作为国家优先议题。要注重家庭和社区的作用，尊重家庭育儿的主体地位和责任，因为加强亲子间的亲情沟通，对儿童具有多重、长远的效益。根据国际经验，有效的做法是建立以家庭和社区为基础的促进儿童早期发展体系，为家庭提供健康、营养的早期教育指导和服务。在国内开展的一些社区亲子活动、家访指导等服务试点工作，也证明在促进儿童发展、减低家庭育儿压力和促进社区和谐等方面有综合效应。公共服务首先应确保家长得到科学育儿的指导和帮助。家庭不应把机构托育作为第一选择，目前在管理力量和专业基础比较薄弱的情况下尤其需要慎重。如果选择托儿服务，以便捷的、儿童更多机会和父母在一起的形式为佳。家庭和社区需要积极参与，并和服务机构良性互动，才能共同保护儿童的安全和健康。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">我们需要构建教育、卫生、福利、社会保障等多部门合作机制，合理统筹多元化政策和目标。联合国儿童基金会呼吁投资并扩展家庭、学校、社区和卫生诊所的儿童早期发展服务，优先关注最弱势儿童；实施两年免费学前教育、婴儿出生头六个月的带薪哺乳假、母亲的六个月带薪产假和父亲的四周带薪陪产假；给予在职父母必需的时间和资源，来促进其幼儿的大脑发育。这些政策有助于家长更好地保护孩子，并为他们在生命最初的关键几年提供更好的营养、游戏和早期学习体验。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">中国在促进儿童早期发展中仍面临诸多问题和挑战。农村儿童在健康、营养和教育方面存在差距；城乡早期儿童发展服务资源不足，社区儿童服务基础薄弱。希望我们共同推动儿童早期发展事业尽快走上发展的快车道。</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\"></span></p><hr style=\"white-space: normal;\"/><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\">文章来源<span style=\"color: rgb(49, 49, 49); font-family: 宋体; line-height: 32px;\">《中国教育报》２０１７年１２月３１日第２、３版　</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"color: rgb(49, 49, 49); font-family: 宋体; line-height: 32px;\">版名：治园</span></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">本版文字由本报记者常晶整理，漫画由海小平绘。</span><br/></p><p style=\"margin-top: auto; margin-bottom: 15px; white-space: normal; text-indent: 32px; line-height: 32px;\"><span style=\"font-family: 宋体; color: rgb(49, 49, 49);\">中国教育科学研究院副研究员高丙成对本次话题策划提出了宝贵意见，山东省寿光市文正教育集团宣传中心主任杨玉娟、古城街道弥景苑幼儿园郑素荣对整理资料有贡献，一并致谢！</span><strong><span style=\"font-size: 19px;\">&nbsp;</span></strong></p><p><strong><span style=\"font-size: 19px;\"><br/></span></strong></p><p><br/></p>',0,'Y','2018-06-18 18:39:22',0,2,-1,-1,-1,'2018-06-18 18:39:22',-1,'2018-06-18 19:00:19',-1,'ID',NULL,NULL,NULL),
 (48,1,1,'','2018教育改革的十个关键词','教育改革的十个关键词','',NULL,'<p style=\"margin-bottom: 14px; white-space: normal; text-align: center; border: none; padding: 0px;\"><strong><span style=\"font-size: 20px; font-family: 宋体;\">全国教育工作会议：2018，教育改革的十个关键词</span></strong><span style=\"font-family: 宋体;\"><a href=\"https://mp.weixin.qq.com/s?__biz=MzAxMjAzODUzNw==&mid=2652211438&idx=1&sn=d01b0f415eb10818fdd0df981aff2ed2&chksm=8056d008b721591e103fc7a24c8a612824b54484f9ad9fc23a24f838161973f1b5e61cdd5681&mpshare=1&scene=1&srcid=0228q0pzVK1OGTsWk5dt6ty0&pass_ticket=wYe8i9wdWWRUVm85YpJzyljvfBwZaoqrqMqG2v9orXh4tHG%2FX1qvpr1UkKAOgWOV##\"></a></span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\"></span></p><hr style=\"white-space: normal;\"/><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">“当前我国教育正面临新的形势和任务，机遇前所未有，挑战前所未有，许多新情况新问题都需要我们去面对、去解决。”在23日召开的2018年全国教育工作会议上，教育部部长陈宝生在详尽列举十八大以来中国教育事业发展成就之后强调，“我国教育整体大踏步前进，但局部差距依然存在；人民群众总体受教育机会大幅提升，但个性化、多样化需求仍未有效满足；目前我们人才总供给能力显著增强，但结构性矛盾尚未解决……解决教育发展不平衡不充分的问题，将是我们长期要面对的工作主题”。</span><br/></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">2018</span><span style=\"color: rgb(62, 62, 62);\">年，我国教育事业将在哪些方面着力，突破哪些难点？在这次会议中，陈宝生给出了2018年中国教育改革发展索引。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p style=\"white-space: normal; text-align: center;\"><strong><span style=\"font-family: 宋体; color: rgb(62, 62, 62); background: white;\">关键词</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: center; line-height: 24px; background: white;\"><strong><span style=\"font-family: 宋体; color: rgb(0, 82, 255);\">党建</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">2018</span><span style=\"color: rgb(62, 62, 62);\">年，教育系统全面启动党建质量年。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">陈宝生强调：“要把党的政治建设摆在首位，用习近平新时代中国特色社会主义思想武装头脑、指导实践、推动工作”。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">教育系统将以提升组织力为重点，实施党建工作“对标争先”计划，把教育系统每一个基层党组织都建设成为坚强的战斗堡垒。要研究制订党建工作考核办法、基层党组织书记考核细则、党支部工作规程、高校党委工作条例等规章制度，不断完善党建工作制度体系。要推进中小学校党组织和党的工作全覆盖。建立健全民办高校党组织，全面推行党组织书记选派，加强中外合作办学党建工作。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: center; line-height: 24px; background: white;\"><strong><span style=\"font-family: 宋体; color: rgb(0, 82, 255);\">思政工作</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">今年，教育系统将全面加强马克思主义理论学科建设，深入研究习近平新时代中国特色社会主义思想和习近平教育思想，支持高校在教育学、马克思主义理论等一级学科设立习近平教育思想研究方向，编写《习近平教育思想讲义》。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">陈宝生强调，要深入实施高校思想政治工作质量提升工程，研究制订学科德育指导纲要。实施“高校思想政治教育名师支持计划”，开展“高校思政课教师队伍建设年”专项工作，持续提升思政课质量。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: center; line-height: 24px; background: white;\"><strong><span style=\"font-family: 宋体; color: rgb(0, 82, 255);\">扶贫</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">每一个“冰花男孩”的出现，都会触动社会的神经。对于教育扶贫这一重大任务，今年，教育部将出台深度贫困地区教育脱贫攻坚实施方案，重点攻克“三区三州”贫困堡垒。实施“中西部高等教育振兴计划升级版”，推进中西部高校综合实力提升工程、中西部高校基础能力建设工程和对口支援西部高校计划，加强省部共建、部省合作，签订部省合建中西部14所高校协议。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">“继续实施支援中西部地区招生协作计划、农村和贫困地区定向招生专项计划、职业教育东西协作行动计划。落实好各类内地民族班招生计划，开展教学质量监测评价，深入推进新疆、西藏和四省藏区教育发展。”陈宝生说，今年，还要组织开展加快中西部教育发展工作督导评估监测，各地应抓紧制定工作措施。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: center; line-height: 24px; background: white;\"><strong><span style=\"font-family: 宋体; color: rgb(0, 82, 255);\">双一流”建设</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">2017</span><span style=\"color: rgb(62, 62, 62);\">年“双一流”建设名单发布后，“双一流”高校如何建，成为高等教育内涵发展的关键问题。陈宝生表示，“双一流”建设要注重绩效管理，研究制定“双一流”建设绩效评价办法，推动建设高校从凝练学科方向、编制建设方案转到全面落实。同时，要探索建设一批新时代中国特色社会主义标杆大学，发挥其排头兵作用。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">推动高等教育内涵发展，今年还有哪些新举措？陈宝生列举道，实施高等教育“六卓越——拔尖计划”2.0版，建设一批“一流本科、一流专业、一流人才”示范引领基地；发布实施普通高校本科专业类教学质量国家标准，形成周期性评估和常态监测相结合的多方质量保障机制；推进科教融合，启动实施高等学校基础研究珠峰计划，加强协同创新平台建设。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: center; line-height: 24px; background: white;\"><strong><span style=\"font-family: 宋体; color: rgb(0, 82, 255);\">教材建设</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">课程教材是国家事权。陈宝生指出，要加强课程教材建设和管理，颁布实施大中小学教材建设五年规划，统筹设计未来五年大中小学教材建设目标、任务和措施。出台中小学、职业院校、高等院校教材以及引进教材管理办法，印发中职德育、语文、历史三科公共基础课程标准，形成全面覆盖大中小学教材基本管理制度体系。健全完善教材编写审查制度，推进国家统编教材统一使用。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: center; line-height: 24px; background: white;\"><strong><span style=\"font-family: 宋体; color: rgb(0, 82, 255);\">学前教育</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">目前，“入园难”问题基本解决，但仍面临普惠性资源不足、教师队伍素质不高、保障机制不健全、社会热点时有发生等问题。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">对此，陈宝生表示，要坚持公益普惠，在大力发展公办园的同时，积极引导和扶持民办园提供普惠性服务，大力支持农村地区、脱贫攻坚地区、城乡接合部和二孩政策新增人口集中地区新建、改扩建幼儿园。建立健全“国务院领导、省市统筹、以县为主”的学前教育管理体制。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">投入上，将建立生均拨款、收费、资助一体化机制，出台公办园生均拨款、普惠性民办园补助标准，健全资助标准。师资上，将完善编制管理办法和工资待遇保障机制，引导和监督依法配足配齐保教人员。要提高保教质量，完善质量评估体系，建立办园行为常态监测机制，确保依法依规办园。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: center; line-height: 24px; background: white;\"><strong><span style=\"font-family: 宋体; color: rgb(0, 82, 255);\">城乡义务教育一体化</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">陈宝生指出，要确保今年底校舍建设和设施设备采购任务“过九成”，基本消除66人以上超大班额。全面加强乡村小规模学校和乡镇寄宿制学校建设，大力推进两类学校建设底部攻坚，力争2019年秋季开学前办学条件达到省定标准，让乡村小规模学校小而优、小而美。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">陈宝生强调，九年义务教育制度是《教育法》和《义务教育法》的明确规定，对于少数地方自行延长义务教育年限的冲动，要严肃排查、坚决制止。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">“大力规范校外教育培训机构，这件事迟早要做，迟做不如早做，小修不如大修。”陈宝生透露，今年将出台促进校外教育培训机构规范有序发展的意见，探索建立负面清单制度和联合监管机制，使其成为学校教育的有益补充者，而不是教育秩序的干扰者。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: center; line-height: 24px; background: white;\"><strong><span style=\"font-family: 宋体; color: rgb(0, 82, 255);\">考招改革</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">今年，教育系统要总结上海、浙江高考改革试点经验，为后续启动改革的省份提供借鉴。同时，进一步扩大试点范围，增加北京、天津、山东、海南4个省份。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">陈宝生透露，推进“新高考”考试内容改革，将更加突出考查学生运用所学知识分析问题、解决问题的能力。加强和改进普通高中学生综合素质评价，各地应抓紧出台配套文件，转变以考试成绩为唯一标准评价学生的做法。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: center; line-height: 24px; background: white;\"><strong><span style=\"font-family: 宋体; color: rgb(0, 82, 255);\">教师队伍</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">实现高质量发展，关键还是靠教师。今年，教育部将实施师德师风建设工程，大力提升教师思想政治素质和师德涵养。推行师德考核负面清单制度，实行师德“一票否决”。严格师德惩处，建立师德失范曝光平台和定期通报制度。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">教育部将实施教师教育振兴行动计划，建设一批高水平教师教育基地，分级分类开展师范类专业认证。国培计划要继续向集中连片特困地区、民族地区、“三区三州”倾斜，2018年实现832个集中连片特困地区县和国家级贫困县乡村教师培训全员覆盖。落实中小学教职工编制标准，编制向乡村小规模学校倾斜。深化大中小学教师职称制度、考核评价制度改革与监管，优化岗位设置，激发教师教书育人的积极性、创造性。</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体;\">&nbsp;</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: center; line-height: 24px; background: white;\"><strong><span style=\"font-family: 宋体; color: rgb(0, 82, 255);\">教育保障</span></strong></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">办出高质量、现代化的教育，必须要有科学、规范、高效的保障机制。今年，继续保证国家财政性教育经费支出占国内生产总值比例不低于4%，确保一般公共预算教育支出只增不减，确保按在校学生人数平均的一般公共预算教育支出只增不减。</span></p><p style=\"margin-bottom: 0px; white-space: normal; text-align: justify; text-indent: 32px; line-height: 24px; background: white;\"><span style=\"color: rgb(62, 62, 62);\">同时，教育部将出台调整优化结构提高经费使用效益的意见，既要优先保障也要优化结构，既要精准投入也要精细管理。调整优化来源结构，在继续保证财政投入稳步增长同时，进一步扩大社会投入比重。调整优化支出结构，更多向农村、边远、贫困、民族地区倾斜，向学前教育、义务教育、职业教育倾斜，向基层教师和困难学生倾斜。</span></p><p style=\"white-space: normal; text-align: right;\"><span style=\"font-size: 14px; font-family: Calibri, sans-serif; color: rgb(62, 62, 62);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style=\"font-size: 14px; font-family: 宋体; color: rgb(62, 62, 62);\">摘自中国职业教育</span><span style=\"font-size: 14px; font-family: Calibri, sans-serif; color: rgb(62, 62, 62);\">&nbsp;2018-01-24</span></p><p><span style=\"font-size: 14px; font-family: Calibri, sans-serif; color: rgb(62, 62, 62);\"><br/></span></p><p><br/></p>',0,'N','2018-06-18 18:39:43',0,1,-1,-1,-1,'2018-06-18 18:39:43',-1,'2018-06-18 18:39:43',-1,'ID',NULL,NULL,NULL);
INSERT INTO `kg_news` (`id`,`typeId`,`sourceId`,`attributeId`,`newsTitle`,`summary`,`thumbnail`,`author`,`content`,`sequence`,`indexshow`,`createDate`,`viewsCount`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (49,1,1,'2','上海率先发布新“民促法”','12月27日，上海市政府出台了《上海市民办学校分类许可登记管理办法》','',NULL,'<p style=\"white-space: normal; text-align: center;\"><strong><span style=\"font-size: 24px; font-family: 宋体; color: rgb(34, 34, 34);\">上海率先发布新《民促法》实施细则</span></strong><strong>&nbsp;</strong><strong><span style=\"font-size: 24px; font-family: 宋体; color: rgb(34, 34, 34);\">民办学校将分类管理</span></strong></p><p style=\"white-space: normal; text-align: center;\"><span style=\"font-size: 12px; font-family: Arial, sans-serif;\"></span></p><hr style=\"white-space: normal;\"/><p style=\"white-space: normal; text-align: center;\"><br/></p><p style=\"white-space: normal;\"><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">12</span><span style=\"font-size: 12px; font-family: 宋体;\">月</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">27</span><span style=\"font-size: 12px; font-family: 宋体;\">日，上海市政府出台了《上海市民办学校分类许可登记管理办法》（以下简称《分类办法》）。《分类办法》明确了各级各类民办学校的许可登记部门和流程，现有民办学校的过渡期，以及对民办学校的奖励补偿方案。</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 12px; font-family: 宋体;\">上海市的《分类办法》是新《民促法》正式施行后，首个具体落地的地方民办教育促进法操作方案，或将拉开各地教育分类管理细则发布序幕。</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 12px; font-family: 宋体;\">新《民促法》于今年</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">9</span><span style=\"font-size: 12px; font-family: 宋体;\">月</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">1</span><span style=\"font-size: 12px; font-family: 宋体;\">日正式施行，首次提出民办学校分类管理改革，即民办学校不再默认为非营利属性，而是以是否获得</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">“</span><span style=\"font-size: 12px; font-family: 宋体;\">办学收益和办学结余</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">”</span><span style=\"font-size: 12px; font-family: 宋体;\">，分为营利性与非营利性两者类型，并进行分类管理。</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 12px; font-family: 宋体;\">在新《民促法》出台前，非营利民办学校被定义为</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">“</span><span style=\"font-size: 12px; font-family: 宋体;\">民办非企业</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">”</span><span style=\"font-size: 12px; font-family: 宋体;\">法人单位，允许获得合理回报。修订后的法律则不再出现</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">“</span><span style=\"font-size: 12px; font-family: 宋体;\">合理回报</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">”</span><span style=\"font-size: 12px; font-family: 宋体;\">的字眼，并规定</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">“</span><span style=\"font-size: 12px; font-family: 宋体;\">非营利民办学校</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">”</span><span style=\"font-size: 12px; font-family: 宋体;\">不能获得办学收益和办学结余。</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 12px; font-family: 宋体;\">然而，由于牵涉的利益主体复杂、改革幅度大，地方省市的新《民促法》配套落地方案迟迟未能出台，安徽、辽宁等少数省份发布的相关文件，也仅是框架性的指导意见。民办教育领域普遍认为，地方的政策跟进速度比预期要慢。民办学校的态度也以观望为主。</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">“</span><span style=\"font-size: 12px; font-family: 宋体;\">这是</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">9</span><span style=\"font-size: 12px; font-family: 宋体;\">月份民促法出台后，第一个出台细则管理办法的一线城市，</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">”</span><span style=\"font-size: 12px; font-family: 宋体;\">通江资本教育领域负责人侯凯告诉经济观察网。他表示，相比于之前出台实施意见的省份，上海的分类办法适用范围更广，不仅包含了民办幼儿园、中小学、高等学校，还包含了民办培训机构。</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 12px; font-family: 宋体;\">另外，《分类办法》内容详细，可操作性更强。上海细化了各类民办学校申请办学许可的机关与流程，规定民办幼儿园，中小学，培训机构到区教育行政部门审批，民办职业培训类机构到区人社部审批，然后由民政部门或工商部门进行登记备案。</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">“</span><span style=\"font-size: 12px; font-family: 宋体;\">在分管边界上较之前更为明确。</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">”</span><span style=\"font-size: 12px; font-family: 宋体;\">侯凯说。</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 12px; font-family: 宋体;\">对于分类管理后的补偿奖励机制，《分类办法》也提出了更为具体的计算方案。《分类办法》规定，现有民办学校如果选择非营利性办学，将来终止后能够获得一定的补偿奖励。根据举办者</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">2017</span><span style=\"font-size: 12px; font-family: 宋体;\">年</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">9</span><span style=\"font-size: 12px; font-family: 宋体;\">月</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">1</span><span style=\"font-size: 12px; font-family: 宋体;\">日前的出资和已经取得的合理回报进行补偿计算，奖励的金额则依据以后的学校年度检查结论。</span></p><p style=\"white-space: normal;\"><span style=\"font-size: 12px; font-family: 宋体;\">除此外，《分类办法》也首次明确了分类登记的过渡期，要求现有的民办学校举办者在</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">2018</span><span style=\"font-size: 12px; font-family: 宋体;\">年</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">12</span><span style=\"font-size: 12px; font-family: 宋体;\">月</span><span style=\"font-size: 12px; font-family: Arial, sans-serif;\">31</span><span style=\"font-size: 12px; font-family: 宋体;\">日之前，向许可机关提交关于学校办学属性选择的书面材料。一位业内人士向记者表示，《分类办法》的过渡期比预期的要短，但政策落地的效率会相应更高。</span></p><p style=\"white-space: normal;\">&nbsp;</p><p style=\"white-space: normal; text-align: right;\"><span style=\"font-family: 宋体;\">转载自 经济观察报</span>2017-12-28</p><p><br/></p>',0,'N','2018-06-18 18:40:11',0,1,-1,-1,-1,'2018-06-18 18:40:11',-1,'2018-06-18 18:40:11',-1,'ID',NULL,NULL,NULL),
 (50,4,1,'2','悦读慧心 上海市托幼协会赠书仪式','“六一”前夕，上海73所集办托幼园所获赠书香礼物，早期阅读助力成长','/uploadFile/news/20180618185720_390_285.jpg',NULL,'<p style=\"white-space: normal; text-align: center;\"><img src=\"http://118.31.36.160:8080/kindergarten/ueditor/jsp/upload/image/20180614/1528940968113063716.jpg\" title=\"1528940968113063716.jpg\" alt=\"微信图片_20180614095033.jpg\"/></p><p style=\"white-space: normal; text-align: center;\">阅读越早开始越好。</p><p style=\"white-space: normal; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-margin-before: 0.5em; -webkit-margin-after: 0.5em;\">“藏在蛋里的宝宝是谁呀？快点出来吧！”一声号召，小鸡、乌龟、小蛇、企鹅、恐龙、鳄鱼等动物从大大小小、形状各不相同的蛋里破壳而出，台下的孩子们欢呼声一片。5月22日，“悦读慧心——2018‘六一’上海市托幼协会赠书仪式”在杨浦实验托儿所举行。上海托幼协会向上海8区73所集办托幼园所赠书，包括少年儿童出版社出版的绘本《蛋宝宝》《条纹，条纹，发现啦！》，阅读对象为2至3岁的孩子，福建少儿出版社针对3至6岁孩子的《台湾儿童文学馆·林良童心绘本2》系列等。</p><p style=\"white-space: normal; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-margin-before: 0.5em; -webkit-margin-after: 0.5em;\"><img src=\"http://118.31.36.160:8080/kindergarten/ueditor/jsp/upload/image/20180614/1528941081024007031.jpg\" title=\"1528941081024007031.jpg\" alt=\"微信图片_20180614095043.jpg\"/></p><p style=\"white-space: normal; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-margin-before: 0.5em; -webkit-margin-after: 0.5em;\">最近几年，3岁以下婴幼儿的托育服务供给成为全社会关心的问题。今年4月底，上海率先出台托育服务“1+2”文件，鼓励多方参与，为3岁以下幼儿及其家长提供幼儿保育和科学育儿指导的服务，鼓励社会组织、企业、事业单位或个人举办，面向3岁以下幼儿，尤其是2至3岁幼儿实施保育为主、教养融合的幼儿照护的全日制、半日制或计时制机构。</p><p style=\"white-space: normal; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-margin-before: 0.5em; -webkit-margin-after: 0.5em;\">&nbsp;</p><p style=\"white-space: normal; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-margin-before: 0.5em; -webkit-margin-after: 0.5em;\">上海市托幼协会成立于2002年，前身为“上海市地区托幼协会”。上世纪80年代，“上海市地区托儿所协会”下属会员单位约有300多家，均属集体所有制性质的单位，集体办单位前身由各区街道，街镇办的生产组、加工厂等组建，归各区集体事业管理办公室统一管理，而集体办托幼园所是带有教育福利事业性质的机构，简称为“地区托幼园所”， 也称“集办托幼园所”。上海市托幼协会秘书长胡育告诉记者，集办托幼园所在上世纪70、80年代幼儿入托入园各高峰期均发挥了蓄水池作用，为缓解本市入托入园矛盾发挥过很大作用。但随着1999年上海实施托幼一体化管理体制后，部分集办托幼园所受限于体制问题，发展进度存在较大差异，活力相对不足。目前，全市16个区中拥有集办托幼园所的共8个区，分别为黄浦、徐汇、长宁、静安、虹口、杨浦、宝山及浦东新区，共有73家园所，约有14000多名2至6岁幼儿在园；其中，12家园所招收1.5至2岁的孩子，253名在托；73家园所均招收2至3岁孩子，4400多名在托。据最新统计数据，目前上海17840名2至3岁孩子入托在全市社会力量办园的园所中，集办托幼园占24.67%。集办园所的保教质量也不断提升，和公办、民办托幼机构形成区域学前教育三足鼎立的状态，有效缓解了入托入园难的问题。</p><p style=\"white-space: normal; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-margin-before: 0.5em; -webkit-margin-after: 0.5em;\">&nbsp;</p><p style=\"white-space: normal; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-margin-before: 0.5em; -webkit-margin-after: 0.5em;\">随着二胎政策开放，上海正值入园入托高峰，为推进多元化办园，提高集办托幼园所的办园积极性，2015年起，上海市托幼协会自筹经费，每年拨出12万元，购置玩具及图书，分发给8区集办托幼园所，鼓励园所进一步提高保教质量，满足百姓需求，促进儿童健康发展，为推进上海学前教育事业发展作出贡献。</p><p style=\"white-space: normal; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-margin-before: 0.5em; -webkit-margin-after: 0.5em;\"><img src=\"http://118.31.36.160:8080/kindergarten/ueditor/jsp/upload/image/20180614/1528941104110024529.jpg\" title=\"1528941104110024529.jpg\" alt=\"微信图片_20180614095054.jpg\"/></p><p style=\"white-space: normal; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-margin-before: 0.5em; -webkit-margin-after: 0.5em;\">在专家看来，开展早期阅读对孩子的成长十分关键，既是幼儿增长知识、开拓眼界和陶冶情操的有效途径，也是孩子们接受新事物的重要方式，更是孩子们在丰富语言，表达感受，与同伴交流必不可少的语言学习途径。今年，上海市托幼协会与上海图书有限公司合作，结合专家推荐，精心挑选适合托幼阶段儿童阅读的绘本。赠书仪式上，杨浦区学前教育管理服务中心的老师和少儿出版社编辑及绘本中文译者带领孩子们从不同角度围绕《蛋宝宝》绘本进行多元化阅读，为家庭及托幼园所的早期阅读作了示范。《蛋宝宝》绘本富有科普性质的自然原理讲述与具有音响韵律感的拟声词描绘相结合，让孩子们在与各种动物宝宝“交朋友”过程中获得知识，收获发现的愉悦，有利于发展孩子们自然观察智能和人际社会智能。在简洁的线条和鲜艳的色彩间还点缀着各种动物不同的神态和表情，趣味盎然。</p><p style=\"white-space: normal; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-margin-before: 0.5em; -webkit-margin-after: 0.5em;\"><img src=\"http://118.31.36.160:8080/kindergarten/ueditor/jsp/upload/image/20180614/1528941112240091152.jpg\" title=\"1528941112240091152.jpg\" alt=\"微信图片_20180614095104.jpg\"/></p><p><br/></p>',2,'Y','2018-06-18 18:41:06',0,5,-1,-1,-1,'2018-06-18 18:41:06',-1,'2018-06-18 19:10:42',-1,'ID',NULL,NULL,NULL),
 (53,1,1,'1','11','1','',NULL,'<p>111</p>',1,'Y','2018-08-04 14:59:54',0,2,-1,-1,-1,'2018-08-04 14:59:54',-1,'2018-08-04 15:00:01',-1,'ID',NULL,NULL,NULL),
 (54,1,1,'','1','1','',NULL,'<p>1</p>',0,'N','2018-08-05 14:28:20',0,1,-1,-1,-1,'2018-08-05 14:28:20',-1,'2018-08-05 14:28:20',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_news` ENABLE KEYS */;


--
-- Definition of table `kg_newsattribute`
--

DROP TABLE IF EXISTS `kg_newsattribute`;
CREATE TABLE `kg_newsattribute` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `attributeName` varchar(45) NOT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_newsattribute`
--

/*!40000 ALTER TABLE `kg_newsattribute` DISABLE KEYS */;
INSERT INTO `kg_newsattribute` (`id`,`attributeName`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'头条',1,-1,-1,-1,'2018-04-10 14:24:28',-1,'2018-04-10 14:24:28',-1,'ID',NULL,NULL,NULL),
 (2,'推荐',1,-1,-1,-1,'2018-04-10 14:24:58',-1,'2018-04-10 14:24:58',-1,'ID',NULL,NULL,NULL),
 (3,'热点',1,-1,-1,-1,'2018-04-15 22:19:59',-1,'2018-04-15 22:19:59',-1,'ID',NULL,NULL,NULL),
 (4,'咨讯中心重点',2,-1,-1,-1,'2018-05-22 15:56:23',-1,'2018-05-22 20:11:09',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_newsattribute` ENABLE KEYS */;


--
-- Definition of table `kg_newssource`
--

DROP TABLE IF EXISTS `kg_newssource`;
CREATE TABLE `kg_newssource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sourceName` varchar(45) NOT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_newssource`
--

/*!40000 ALTER TABLE `kg_newssource` DISABLE KEYS */;
INSERT INTO `kg_newssource` (`id`,`sourceName`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'原创',4,-1,-1,-1,'2018-04-10 14:09:01',-1,'2018-06-18 22:08:49',-1,'ID',NULL,NULL,NULL),
 (2,'网络',1,-1,-1,-1,'2018-06-18 22:08:54',-1,'2018-06-18 22:08:54',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_newssource` ENABLE KEYS */;


--
-- Definition of table `kg_newstype`
--

DROP TABLE IF EXISTS `kg_newstype`;
CREATE TABLE `kg_newstype` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `typeName` varchar(45) NOT NULL,
  `showIndex` tinyint(1) DEFAULT '0',
  `entranceImagePath` varchar(200) NOT NULL,
  `showEntrance` tinyint(1) DEFAULT '0',
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_newstype`
--

/*!40000 ALTER TABLE `kg_newstype` DISABLE KEYS */;
INSERT INTO `kg_newstype` (`id`,`typeName`,`showIndex`,`entranceImagePath`,`showEntrance`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'公告通知',0,'',0,13,-1,-1,-1,'2018-04-10 10:10:56',-1,'2018-08-04 15:03:09',-1,'ID',NULL,NULL,NULL),
 (2,'政策法规',0,'/uploadFile/entranceimage/entranceimage.png',0,14,-1,-1,-1,'2018-06-17 18:16:00',-1,'2018-08-04 15:03:09',-1,'ID',NULL,NULL,NULL),
 (3,'培训通知',0,'',0,13,-1,-1,-1,'2018-04-10 10:11:12',-1,'2018-08-04 15:03:09',-1,'ID',NULL,NULL,NULL),
 (4,'协会动态',0,'',0,11,-1,-1,-1,'2018-06-17 18:17:16',-1,'2018-08-04 15:03:09',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_newstype` ENABLE KEYS */;


--
-- Definition of table `kg_offers`
--

DROP TABLE IF EXISTS `kg_offers`;
CREATE TABLE `kg_offers` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `publishUnit` varchar(45) NOT NULL,
  `contactPerson` varchar(200) NOT NULL,
  `contactTel` varchar(20) DEFAULT NULL,
  `salary` varchar(20) DEFAULT NULL,
  `content` text,
  `publishDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_offers`
--

/*!40000 ALTER TABLE `kg_offers` DISABLE KEYS */;
INSERT INTO `kg_offers` (`id`,`publishUnit`,`contactPerson`,`contactTel`,`salary`,`content`,`publishDate`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (55,'2','2','2',NULL,'<p>2222</p>','2018-08-05 14:31:45',1,-1,-1,-1,'2018-08-05 14:31:45',-1,'2018-08-05 14:31:45',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kg_offers` ENABLE KEYS */;


--
-- Definition of table `kg_question_answer`
--

DROP TABLE IF EXISTS `kg_question_answer`;
CREATE TABLE `kg_question_answer` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `qid` bigint(20) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `value` varchar(200) DEFAULT NULL,
  `checkCurr` tinyint(1) DEFAULT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_question_answer`
--

/*!40000 ALTER TABLE `kg_question_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `kg_question_answer` ENABLE KEYS */;


--
-- Definition of table `kg_questionmainitem`
--

DROP TABLE IF EXISTS `kg_questionmainitem`;
CREATE TABLE `kg_questionmainitem` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sid` bigint(20) NOT NULL,
  `Q_ITEMS_TITLE` varchar(200) DEFAULT NULL,
  `analysis` varchar(200) DEFAULT NULL,
  `ITEM_TYPE` varchar(200) DEFAULT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_questionmainitem`
--

/*!40000 ALTER TABLE `kg_questionmainitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `kg_questionmainitem` ENABLE KEYS */;


--
-- Definition of table `kg_questionsurvey`
--

DROP TABLE IF EXISTS `kg_questionsurvey`;
CREATE TABLE `kg_questionsurvey` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `finished` tinyint(1) DEFAULT '1',
  `joinCount` bigint(20) DEFAULT '0',
  `survey_name` varchar(200) NOT NULL,
  `survey_desc` varchar(200) DEFAULT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_questionsurvey`
--

/*!40000 ALTER TABLE `kg_questionsurvey` DISABLE KEYS */;
/*!40000 ALTER TABLE `kg_questionsurvey` ENABLE KEYS */;


--
-- Definition of table `kg_user_q_answer`
--

DROP TABLE IF EXISTS `kg_user_q_answer`;
CREATE TABLE `kg_user_q_answer` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sid` bigint(20) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `aid` bigint(20) NOT NULL,
  `checkCurr` tinyint(1) DEFAULT NULL,
  `content` text,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kg_user_q_answer`
--

/*!40000 ALTER TABLE `kg_user_q_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `kg_user_q_answer` ENABLE KEYS */;


--
-- Definition of table `kt_demo`
--

DROP TABLE IF EXISTS `kt_demo`;
CREATE TABLE `kt_demo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kt_demo`
--

/*!40000 ALTER TABLE `kt_demo` DISABLE KEYS */;
/*!40000 ALTER TABLE `kt_demo` ENABLE KEYS */;


--
-- Definition of table `kt_link`
--

DROP TABLE IF EXISTS `kt_link`;
CREATE TABLE `kt_link` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `link_name` varchar(45) NOT NULL,
  `link_url` varchar(200) NOT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kt_link`
--

/*!40000 ALTER TABLE `kt_link` DISABLE KEYS */;
INSERT INTO `kt_link` (`id`,`link_name`,`link_url`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'1','2',1,-1,-1,-1,'2018-03-21 14:49:50',-1,'2018-03-21 14:49:51',-1,'ID',NULL,NULL,NULL),
 (2,'111','222',1,-1,-1,-1,'2018-03-21 14:49:50',-1,'2018-03-21 14:49:51',-1,'ID',NULL,NULL,NULL),
 (3,'111','222',1,-1,-1,-1,'2018-03-21 14:49:50',-1,'2018-03-21 14:49:51',-1,'ID',NULL,NULL,NULL),
 (4,'111','222',1,-1,-1,-1,'2018-03-21 14:49:50',-1,'2018-03-21 14:49:51',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `kt_link` ENABLE KEYS */;


--
-- Definition of table `sys_func`
--

DROP TABLE IF EXISTS `sys_func`;
CREATE TABLE `sys_func` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `func_name` varchar(45) NOT NULL,
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_func`
--

/*!40000 ALTER TABLE `sys_func` DISABLE KEYS */;
INSERT INTO `sys_func` (`id`,`func_name`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (1,'协会简介',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (2,'任务类别',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (3,'评估任务',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (4,'评估任务进度',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (5,'文档下载',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (6,'资讯中心',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (7,'咨询类别',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (8,'来源配置',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (9,'相关链接',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (10,'系统配置',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (11,'属性配置',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (12,'用户',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (13,'角色',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (14,'轮播图',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (15,'联系我们',1,-1,-1,-1,'2018-05-12 18:36:13',-1,'2018-05-12 18:36:13',-1,'ID',NULL,NULL,NULL),
 (16,'调查问卷',1,-1,-1,-1,'2018-05-22 15:00:23',-1,'2018-05-22 15:00:23',-1,'ID',NULL,NULL,NULL),
 (17,'专家审核',1,-1,-1,-1,'2018-05-22 15:00:23',-1,'2018-05-22 15:00:23',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_func` ENABLE KEYS */;


--
-- Definition of table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_CODE` varchar(40) NOT NULL COMMENT '角色编码',
  `ROLE_NAME` varchar(150) NOT NULL COMMENT '角色名称',
  `ROLE_DESCRIPTION` varchar(240) DEFAULT NULL COMMENT '角色描述',
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `SYS_ROLE_B_U1` (`ROLE_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_role`
--

/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`ROLE_ID`,`ROLE_CODE`,`ROLE_NAME`,`ROLE_DESCRIPTION`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (10003,'admin','后台管理员1','后台管理员1',9,-1,-1,-1,'2018-04-11 11:19:09',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10004,'wd','园长','园长',2,-1,-1,-1,'2018-04-12 10:45:44',-1,'2018-04-15 22:17:55',-1,'ID',NULL,NULL,NULL),
 (10006,'zj','专家','专家',1,-1,-1,-1,'2018-06-20 21:07:08',-1,'2018-06-20 21:07:08',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;


--
-- Definition of table `sys_role_func`
--

DROP TABLE IF EXISTS `sys_role_func`;
CREATE TABLE `sys_role_func` (
  `RF_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FUNC_ID` bigint(20) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`RF_ID`),
  UNIQUE KEY `SYS_USER_ROLE_U1` (`ROLE_ID`,`FUNC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10099 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_role_func`
--

/*!40000 ALTER TABLE `sys_role_func` DISABLE KEYS */;
INSERT INTO `sys_role_func` (`RF_ID`,`FUNC_ID`,`ROLE_ID`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (10081,1,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10082,2,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10083,3,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10084,4,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10085,5,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10086,6,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10087,7,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10088,8,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10089,9,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10090,10,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10091,11,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10092,12,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10093,13,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10094,14,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10095,15,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10096,16,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10097,17,10003,1,-1,-1,-1,'2018-05-22 15:04:57',-1,'2018-05-22 15:04:57',-1,'ID',NULL,NULL,NULL),
 (10098,17,10006,1,-1,-1,-1,'2018-05-22 15:09:08',-1,'2018-05-22 15:09:08',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_role_func` ENABLE KEYS */;


--
-- Definition of table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_TYPE` varchar(30) DEFAULT NULL COMMENT '用户类型',
  `USER_NAME` varchar(40) NOT NULL COMMENT '用户名',
  `REAL_NAME` varchar(40) NOT NULL,
  `PASSWORD_ENCRYPTED` varchar(80) DEFAULT NULL COMMENT '加密过的密码',
  `EMAIL` varchar(150) DEFAULT NULL COMMENT '邮箱地址',
  `PHONE` varchar(40) DEFAULT NULL COMMENT '电话号码',
  `START_ACTIVE_DATE` datetime DEFAULT NULL COMMENT '有效期从',
  `END_ACTIVE_DATE` datetime DEFAULT NULL COMMENT '有效期至',
  `STATUS` varchar(30) DEFAULT NULL COMMENT '状态',
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `SYS_USER_U1` (`USER_NAME`),
  KEY `SYS_USER_N1` (`STATUS`)
) ENGINE=InnoDB AUTO_INCREMENT=10034 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_user`
--

/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`USER_ID`,`USER_TYPE`,`USER_NAME`,`REAL_NAME`,`PASSWORD_ENCRYPTED`,`EMAIL`,`PHONE`,`START_ACTIVE_DATE`,`END_ACTIVE_DATE`,`STATUS`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (10013,NULL,'admin','管理员','202cb962ac59075b964b07152d234b70','','',NULL,NULL,NULL,8,-1,-1,-1,'2018-04-11 14:16:39',-1,'2018-06-18 13:23:26',-1,'ID',NULL,NULL,NULL),
 (10014,NULL,'teacher','老师一','202cb962ac59075b964b07152d234b70','','',NULL,NULL,NULL,52,-1,-1,-1,'2018-04-12 13:38:12',-1,'2018-06-21 13:42:02',-1,'ID',NULL,NULL,NULL),
 (10032,NULL,'expert','专家','202cb962ac59075b964b07152d234b70','123','',NULL,NULL,NULL,12,-1,-1,-1,'2018-04-13 14:32:16',-1,'2018-06-20 21:38:38',-1,'ID',NULL,NULL,NULL),
 (10033,NULL,'teacher2','老师二','202cb962ac59075b964b07152d234b70','','',NULL,NULL,NULL,4,-1,-1,-1,'2018-05-12 14:50:07',-1,'2018-06-20 20:28:53',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;


--
-- Definition of table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `SUR_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `OBJECT_VERSION_NUMBER` bigint(20) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  `CERTIFICATE_TYPE` varchar(240) DEFAULT 'ID' COMMENT '证件类型',
  `EFFECTIVE_START_DATE` date DEFAULT NULL COMMENT '有效日期从',
  `EFFECTIVE_END_DATE` date DEFAULT NULL COMMENT '有效日期至',
  `ATTRIBUTE_CATEGORY` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`SUR_ID`),
  UNIQUE KEY `SYS_USER_ROLE_U1` (`ROLE_ID`,`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10034 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_user_role`
--

/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`SUR_ID`,`USER_ID`,`ROLE_ID`,`OBJECT_VERSION_NUMBER`,`REQUEST_ID`,`PROGRAM_ID`,`CREATED_BY`,`CREATION_DATE`,`LAST_UPDATED_BY`,`LAST_UPDATE_DATE`,`LAST_UPDATE_LOGIN`,`CERTIFICATE_TYPE`,`EFFECTIVE_START_DATE`,`EFFECTIVE_END_DATE`,`ATTRIBUTE_CATEGORY`) VALUES 
 (10001,10013,10003,1,-1,-1,-1,'2018-05-11 21:36:00',-1,'2018-05-11 21:36:00',-1,'ID',NULL,NULL,NULL),
 (10024,10032,10006,1,-1,-1,-1,'2018-06-20 21:38:38',-1,'2018-06-20 21:38:38',-1,'ID',NULL,NULL,NULL),
 (10033,10014,10004,1,-1,-1,-1,'2018-06-21 13:42:02',-1,'2018-06-21 13:42:02',-1,'ID',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
