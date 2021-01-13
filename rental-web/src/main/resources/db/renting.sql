/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.3-m13-log : Database - renting
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`renting` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `renting`;

/*Table structure for table `house` */

DROP TABLE IF EXISTS `house`;

CREATE TABLE `house` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'house唯一标识',
  `title` varchar(32) NOT NULL,
  `price` int(11) unsigned NOT NULL COMMENT '价格',
  `area` int(11) unsigned NOT NULL COMMENT '面积',
  `room` int(11) unsigned NOT NULL COMMENT '卧室数量',
  `floor` int(11) unsigned NOT NULL COMMENT '楼层',
  `total_floor` int(11) unsigned NOT NULL COMMENT '总楼层',
  `watch_times` int(11) unsigned DEFAULT '0' COMMENT '被看次数',
  `build_year` int(4) NOT NULL COMMENT '建立年限',
  `status` int(4) unsigned NOT NULL DEFAULT '0' COMMENT '房屋状态 0-未审核 1-审核通过 2-已出租 3-逻辑删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近数据更新时间',
  `city_en_name` varchar(32) NOT NULL COMMENT '城市标记缩写 如 北京bj',
  `region_en_name` varchar(255) NOT NULL COMMENT '地区英文简写 如昌平区 cpq',
  `cover` varchar(32) DEFAULT NULL COMMENT '封面',
  `direction` int(11) NOT NULL COMMENT '房屋朝向',
  `distance_to_subway` int(11) NOT NULL DEFAULT '-1' COMMENT '距地铁距离 默认-1 附近无地铁',
  `parlour` int(11) NOT NULL DEFAULT '0' COMMENT '客厅数量',
  `district` varchar(32) NOT NULL COMMENT '所在小区',
  `admin_id` bigint(20) NOT NULL COMMENT '所属管理员id',
  `bathroom` int(11) NOT NULL DEFAULT '0',
  `street` varchar(32) NOT NULL COMMENT '街道',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='房屋信息表';

/*Data for the table `house` */

insert  into `house`(`id`,`title`,`price`,`area`,`room`,`floor`,`total_floor`,`watch_times`,`build_year`,`status`,`create_time`,`last_update_time`,`city_en_name`,`region_en_name`,`cover`,`direction`,`distance_to_subway`,`parlour`,`district`,`admin_id`,`bathroom`,`street`) values (15,'富力城 国贸CBD 时尚休闲 商务办公',6200,70,2,10,20,2,2005,1,'2017-09-06 18:56:14','2019-12-02 00:59:10','bj','hdq','Fq1WqpIeF7_bNxfMhrYUxD7EWGLD',2,10,1,'融泽嘉园',2,0,'龙域西二路'),(16,'富力城 国贸CBD 时尚休闲 商务办公',6300,70,2,10,20,0,2012,1,'2017-09-06 19:53:35','2019-12-02 00:58:29','bj','hdq','FiFckiIXCpXswETQQ8RFvAy2lrJk',1,-1,1,'融泽嘉园',2,0,'龙域西二路'),(17,'二环东直门地铁站附近、王府井、天安门、国贸、三里屯、南锣鼓巷',3000,35,1,5,10,2,2013,1,'2017-09-06 20:45:35','2019-12-02 00:58:03','bj','hdq','FigxgBBuwXTFNP0BBa_f33WWW4qb',1,200,0,'融泽嘉园',2,0,'龙域西二路'),(18,'华贸城 东向一居挑空loft 干净温馨 随时可以签约',5700,52,1,7,20,0,2012,1,'2017-09-06 21:01:02','2019-12-02 00:57:30','bj','hdq','FnGvOoXFzfUtnN_jiCqUhNOF7uns',2,1085,1,'融泽嘉园',2,0,'龙域西二路'),(19,'望春园板楼三居室 自住精装 南北通透 采光好视野棒！',9200,132,3,6,14,0,2005,1,'2017-09-06 22:44:25','2019-12-02 00:56:57','bj','hdq','FjOQt1q8G1RwpUm76TmU5DEwjtGG',2,1108,2,'融泽嘉园',2,0,'龙域西二路'),(20,'高大上的整租两居室 业主诚意出租',5400,56,2,12,20,0,2012,1,'2017-09-06 23:39:50','2019-12-02 00:54:07','bj','hdq','FnjpDmGz3FmaSM0yWTzEYzugALuA',2,-1,1,'融泽嘉园',2,0,'龙域西二路'),(21,'新康园 正规三居室 精装修 家电家具齐全',1900,18,1,13,25,0,2012,1,'2017-09-07 00:52:47','2019-12-02 00:53:39','bj','hdq','FnsRyOQN77dg21Ev9ltUZ6XCKkgO',3,1302,0,'融泽嘉园',2,0,'龙域西二路'),(24,'湖光壹号望京华府183-387㎡阔景大宅',50000,288,5,1,1,0,2015,1,'2017-09-07 11:42:20','2019-12-14 10:20:15','bj','hdq','Fq1WqpIeF7_bNxfMhrYUxD7EWGLD',5,200,3,'融泽嘉园',2,0,'龙域西三路'),(25,'测试房源-编辑',3000,59,2,10,20,0,2010,3,'2017-10-28 22:34:48','2017-11-11 12:22:50','bj','cpq','FtbxR2LY98lnnX_TPOgOPzti3k7G',2,1000,1,'融泽嘉园',2,0,'龙域中街'),(26,'三房两厅 阳光房',9000,140,3,5,10,1,2017,1,'2019-12-02 00:50:01','2019-12-14 19:17:19','bj','dcq','FuW1F-2G-BYJBpUMpuCPhBp-_4pW',2,500,2,'紫禁城',2,0,'宣德门大街'),(27,'大三房',5000,80,1,2,6,0,2017,1,'2019-12-14 16:05:42','2019-12-16 14:59:05','bj','hdq','FpInl9BT5BURO34AxokT_toC9Ph7',2,-1,2,'大三房',2,0,'大三房'),(28,'北三环 3房两厅',8000,120,3,2,5,0,2018,0,'2019-12-16 06:58:09','2019-12-16 06:58:09','bj','xcq','FuW1F-2G-BYJBpUMpuCPhBp-_4pW',2,800,2,'世家花苑',2,0,'北三环西通街');

/*Table structure for table `house_detail` */

DROP TABLE IF EXISTS `house_detail`;

CREATE TABLE `house_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL COMMENT '详细描述',
  `layout_desc` varchar(255) DEFAULT NULL COMMENT '户型介绍',
  `traffic` varchar(255) DEFAULT NULL COMMENT '交通出行',
  `round_service` varchar(255) DEFAULT NULL COMMENT '周边配套',
  `rent_way` int(2) NOT NULL COMMENT '租赁方式',
  `address` varchar(32) NOT NULL COMMENT '详细地址 ',
  `subway_line_id` int(11) DEFAULT NULL COMMENT '附近地铁线id',
  `subway_line_name` varchar(32) DEFAULT NULL COMMENT '附近地铁线名称',
  `subway_station_id` int(11) DEFAULT NULL COMMENT '地铁站id',
  `subway_station_name` varchar(32) DEFAULT NULL COMMENT '地铁站名',
  `house_id` bigint(20) NOT NULL COMMENT '对应house的id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_on_house_id` (`house_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

/*Data for the table `house_detail` */

insert  into `house_detail`(`id`,`description`,`layout_desc`,`traffic`,`round_service`,`rent_way`,`address`,`subway_line_id`,`subway_line_name`,`subway_station_id`,`subway_station_name`,`house_id`) values (21,'国贸CBD商务区,近SOHO现代城,富顿中心,富力城商业街区,乐成中心,潘家园古玩城,八王坟长途客运站,北京游乐园,经由三环路可直达首都机场。附近有双井桥南,双井桥北,双井桥东双井桥西等30多条公交站牌!\n《天安门,故宫,王府井,三里屯,前门,天坛,北海,颐和园,雍和宫,奥林匹克公园,水立方,西单,欢乐谷,燕莎商城等》知名购物区及旅游名胜古迹,是您休闲旅游及商务下榻的理想选择','房间采光良好,落地窗外景色宜人','房子处于北京的CBD商务中心区国贸双井!紧邻双井地铁站,步行5分钟即到!这离国贸、中央电视台、潘家园、三里屯、团结湖、日坛使馆区、儿研所、大郊亭都很近','房子闹中取静,地理位置优越,交通方便,紧邻呼家楼地铁站和东大桥地铁站;去机场可乘坐东直门机场快轨,非常方便｡购物中心有双井购物中心、国贸购物中心和侨福芳草地购物中心、三里屯购物中心等,远道而来的朋友可尽览都市璀璨!',0,'二号院7号楼',4,'10号线',58,'双井',15),(22,'国贸CBD商务区,近SOHO现代城,富顿中心,富力城商业街区,乐成中心,潘家园古玩城,八王坟长途客运站,北京游乐园,经由三环路可直达首都机场。附近有双井桥南,双井桥北,双井桥东双井桥西等30多条公交站牌!\n《天安门,故宫,王府井,三里屯,前门,天坛,北海,颐和园,雍和宫,奥林匹克公园,水立方,西单,欢乐谷,燕莎商城等》知名购物区及旅游名胜古迹,是您休闲旅游及商务下榻的理想选择!','房间采光良好,落地窗外景色宜人','房子处于北京的CBD商务中心区国贸双井!紧邻双井地铁站,步行5分钟即到','这离国贸、中央电视台、潘家园、三里屯、团结湖、日坛使馆区、儿研所、大郊亭都很近。房子闹中取静,地理位置优越,交通方便,紧邻呼家楼地铁站和东大桥地铁站;去机场可乘坐东直门机场快轨,非常方便｡购物中心有双井购物中心、国贸购物中心和侨福芳草地购物中心、三里屯购物中心等,远道而来的朋友可尽览都市璀璨！',0,'1号院1号楼',1,'13号线',5,'上地',16),(24,'我和我女盆友当房东已经一年了,也是超赞房东,希望能为大家提供舒适的住所~ 房间的大门和房门都是密码门,小区有保安24小时值班,非常安全方便。 通常入住时间是下午三点,提前来的同学可以先寄存行李和洗澡哦~\n\n','房間非常漂亮,空間很大,鵝黃色的牆壁看起來非常舒服','位置距離地鐵站不遠','距故宫、天安门、王府井、三里屯、簋街、南锣鼓巷等景点均可地铁半小时内到达,交通便利~',0,'1号院2号楼',1,'13号线',16,'东直门',17),(25,'这个经纪人很懒，没写核心卖点','此房是一居室的格局，上下两层，面宽，房间亮堂，进门右手厨房，正前方是25平米的客厅，楼上是卧室，带洗手间！ 喧闹和安静隔开，适合居住','小区距离地铁13号线北苑站500米的距离，交通出行便利....','小区楼下就是华贸天地娱乐街，保利电影院，眉州东坡，中信银行，麦当劳等娱乐休闲设施齐全',0,'1号院3号楼',1,'13号线',11,'北苑',18),(26,'这个经纪人很懒，没写核心卖点','此房为望春园小区板楼南北通透户型，主卧客厅朝南，次卧朝北，两个客厅双卫，居住很舒适。','距离地铁5号线立水桥南站630米，有464,465,966,081，621等多条公交线路，交通出行四通八达。','小区旁有大型购物商场易事达，物美超市，丰宁蔬菜基地，航空总医院、安贞医院北苑分院，中国银行、中国农业银行、中国工商银行、中国交通银行、中国建设银行、招商银行分布。小区旁有天奥健身房，还有立水桥公园..',0,'6号院1号楼',1,'13号线',10,'立水桥',19),(27,'高大上的整租两居室 业主诚意出租\n1、客厅挑高、宽敞舒适、阳光充足 2、卧室搭配的很新颖，使用之高 3、厨房带阳台，让您和家人有足够的空间展现私家厨艺','客厅挑高、宽敞舒适、阳光充足 2、卧室搭配的很新颖，使用之高 3、厨房带阳台，让您和家人有足够的空间展现私家厨艺','近地铁13号线东直门站','社区环境好，环境优美，适宜居住，人文素质高，物业管理完善； 2、属于低密度社区 ，适宜居住 3、小区的林密树多，让您感受花园一样的家',0,'1号院5号楼',1,'13号线',16,'东直门',20),(28,'房子是正规三室一厅一厨一卫，装修保持的不错，家电家具都齐全。\n','房子客厅朝北面积比较大，主卧西南朝向，次卧朝北，另一个次卧朝西，两个次卧面积差不多大。','小区出南门到8号线育新地铁站614米，交通便利，小区500米范围内有物美，三旗百汇，龙旗广场等几个比较大的商场，生活购物便利，出小区北门朝东952米是地铁霍营站，是8号线和 13号线的换乘站，同时还有个S2线，通往怀来。（数据来源百度地图）','小区西边300米就是物美超市和三旗百汇市场（日常百货、粮油米面、瓜果蔬菜、生鲜海货等等，日常生活很便利，消费成本低），北边200米是龙旗购物广场和永辉超市（保利影院，KFC，麦当劳等，轻松满足娱乐消费）。小区里还有商店，饭店，家政等。',0,'2号院1号楼',1,'13号线',9,'霍营',21),(31,'懒死了 不谢','户型介绍','交通出行','周边配套',0,'3号院1号楼',1,'13号线',12,'望京西',24),(32,'房屋描述-编辑','户型介绍','交通出行','周边配套-编辑',0,'3号院2单元1003',1,'13号线',8,'回龙观',25),(33,'此房源大面积三居室，空房出租，业主重新做的保洁，很干净，租户无固定要求，均可谈。','此房源南北通都三居室，客厅主卧朝南，双次卧厨房朝北，北面也有阳台，卫生间东向有窗。','地铁、公交相当方便','此房源临近京开高速，黄马路附近，公交方便，可以直达北京站，国贸，亦庄',0,'乾清宫',1,'13号线',15,'柳芳',26),(34,'','','','',0,'大三房',NULL,NULL,NULL,NULL,27),(35,'','3房2厅','地铁、公交','',0,'西通街25号',1,'13号线',13,'芍药居',28);

/*Table structure for table `house_picture` */

DROP TABLE IF EXISTS `house_picture`;

CREATE TABLE `house_picture` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `house_id` bigint(20) NOT NULL COMMENT '所属房屋id',
  `cdn_prefix` varchar(255) NOT NULL COMMENT '图片路径',
  `width` int(11) DEFAULT NULL COMMENT '宽',
  `height` int(11) DEFAULT NULL COMMENT '高',
  `location` varchar(32) DEFAULT NULL COMMENT '所属房屋位置',
  `path` varchar(255) NOT NULL COMMENT '文件名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COMMENT='房屋图片信息';

/*Data for the table `house_picture` */

insert  into `house_picture`(`id`,`house_id`,`cdn_prefix`,`width`,`height`,`location`,`path`) values (86,26,'http://q1ucvq5bt.bkt.clouddn.com/',400,300,NULL,'FgZ9ynnoKRBoyPCBiqsbXRO6TBx5'),(87,26,'http://q1ucvq5bt.bkt.clouddn.com/',500,375,NULL,'FjwB7jxv8Mkpc3ZHxNI4JKHJpaJy'),(88,26,'http://q1ucvq5bt.bkt.clouddn.com/',300,199,NULL,'FuW1F-2G-BYJBpUMpuCPhBp-_4pW'),(89,26,'http://q1ucvq5bt.bkt.clouddn.com/',350,250,NULL,'FofUMuY_JkxyBui13Qo2AAbxOUA4'),(90,26,'http://q1ucvq5bt.bkt.clouddn.com/',500,353,NULL,'FnjpDmGz3FmaSM0yWTzEYzugALuA'),(91,24,'http://q1ucvq5bt.bkt.clouddn.com/',300,201,NULL,'Fne_uP7xhnQ66TNjmIBzaeNwaLe3'),(92,24,'http://q1ucvq5bt.bkt.clouddn.com/',500,333,NULL,'Fq1WqpIeF7_bNxfMhrYUxD7EWGLD'),(93,21,'http://q1ucvq5bt.bkt.clouddn.com/',400,300,NULL,'FgZ9ynnoKRBoyPCBiqsbXRO6TBx5'),(94,21,'http://q1ucvq5bt.bkt.clouddn.com/',509,592,NULL,'FnsRyOQN77dg21Ev9ltUZ6XCKkgO'),(95,20,'http://q1ucvq5bt.bkt.clouddn.com/',300,199,NULL,'FuW1F-2G-BYJBpUMpuCPhBp-_4pW'),(96,20,'http://q1ucvq5bt.bkt.clouddn.com/',500,353,NULL,'FnjpDmGz3FmaSM0yWTzEYzugALuA'),(97,19,'http://q1ucvq5bt.bkt.clouddn.com/',500,500,NULL,'FhsFH28EdECHEPGj-E2kMF00dqyz'),(98,19,'http://q1ucvq5bt.bkt.clouddn.com/',500,332,NULL,'FjOQt1q8G1RwpUm76TmU5DEwjtGG'),(99,18,'http://q1ucvq5bt.bkt.clouddn.com/',432,324,NULL,'FnGvOoXFzfUtnN_jiCqUhNOF7uns'),(100,18,'http://q1ucvq5bt.bkt.clouddn.com/',500,375,NULL,'FruFJKa06jomNHwYEC5wFZaa16y1'),(101,17,'http://q1ucvq5bt.bkt.clouddn.com/',500,500,NULL,'FjYhdz7lTs3Y5OFWS39kVubp9GV2'),(102,17,'http://q1ucvq5bt.bkt.clouddn.com/',534,300,NULL,'FiVpsJe0PQ08yjupw0nuqqNOgxl8'),(103,17,'http://q1ucvq5bt.bkt.clouddn.com/',566,300,NULL,'FigxgBBuwXTFNP0BBa_f33WWW4qb'),(104,16,'http://q1ucvq5bt.bkt.clouddn.com/',509,592,NULL,'FnsRyOQN77dg21Ev9ltUZ6XCKkgO'),(105,16,'http://q1ucvq5bt.bkt.clouddn.com/',500,335,NULL,'FiFckiIXCpXswETQQ8RFvAy2lrJk'),(106,15,'http://q1ucvq5bt.bkt.clouddn.com/',500,333,NULL,'FpInl9BT5BURO34AxokT_toC9Ph7'),(107,15,'http://q1ucvq5bt.bkt.clouddn.com/',500,333,NULL,'Fq1WqpIeF7_bNxfMhrYUxD7EWGLD'),(108,27,'http://q1ucvq5bt.bkt.clouddn.com/',500,333,NULL,'FpInl9BT5BURO34AxokT_toC9Ph7'),(109,28,'http://q1ucvq5bt.bkt.clouddn.com/',500,332,NULL,'FjOQt1q8G1RwpUm76TmU5DEwjtGG'),(110,28,'http://q1ucvq5bt.bkt.clouddn.com/',500,333,NULL,'FpInl9BT5BURO34AxokT_toC9Ph7'),(111,28,'http://q1ucvq5bt.bkt.clouddn.com/',300,199,NULL,'FuW1F-2G-BYJBpUMpuCPhBp-_4pW'),(112,28,'http://q1ucvq5bt.bkt.clouddn.com/',432,324,NULL,'FnGvOoXFzfUtnN_jiCqUhNOF7uns'),(113,28,'http://q1ucvq5bt.bkt.clouddn.com/',500,375,NULL,'FruFJKa06jomNHwYEC5wFZaa16y1');

/*Table structure for table `house_subscribe` */

DROP TABLE IF EXISTS `house_subscribe`;

CREATE TABLE `house_subscribe` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `house_id` bigint(20) NOT NULL COMMENT '房源id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `desc` varchar(255) DEFAULT NULL COMMENT '用户描述',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '预约状态 1-加入待看清单 2-已预约看房时间 3-看房完成',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `last_update_time` datetime NOT NULL COMMENT '记录更新时间',
  `order_time` datetime DEFAULT NULL COMMENT '预约时间',
  `telephone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `admin_id` bigint(20) NOT NULL COMMENT '房源发布者id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_on_user_and_house` (`house_id`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='预约看房信息表';

/*Data for the table `house_subscribe` */

insert  into `house_subscribe`(`id`,`house_id`,`user_id`,`desc`,`status`,`create_time`,`last_update_time`,`order_time`,`telephone`,`admin_id`) values (9,17,1,NULL,3,'2017-11-26 11:06:23','2017-12-02 09:21:01','2017-12-03 00:00:00','13888888888',2),(10,26,9,NULL,3,'2019-12-02 16:34:39','2019-12-02 22:25:08','2019-12-06 00:00:00','13262956975',2),(11,26,2,NULL,1,'2019-12-02 17:31:23','2019-12-02 17:31:23',NULL,NULL,2),(12,16,9,NULL,1,'2019-12-02 22:26:21','2019-12-02 22:26:21',NULL,NULL,2),(13,18,9,NULL,1,'2019-12-02 22:26:30','2019-12-02 22:26:30',NULL,NULL,2),(16,26,1,NULL,1,'2019-12-14 09:32:39','2019-12-14 09:32:39',NULL,NULL,2);

/*Table structure for table `house_tag` */

DROP TABLE IF EXISTS `house_tag`;

CREATE TABLE `house_tag` (
  `house_id` bigint(20) NOT NULL COMMENT '房源id',
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_on_house_id_and_name` (`house_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='房屋标签映射关系表';

/*Data for the table `house_tag` */

insert  into `house_tag`(`house_id`,`id`,`name`) values (15,18,'独立阳台'),(15,17,'空调'),(16,16,'光照充足'),(17,15,'随时看房'),(17,14,'集体供暖'),(18,13,'精装修'),(19,12,'独立卫生间'),(19,11,'独立阳台'),(21,19,'光照充足'),(21,20,'独立卫生间'),(24,10,'光照充足'),(24,3,'精装修'),(24,8,'集体供暖'),(25,21,'独立阳台'),(26,26,'光照充足'),(26,25,'独立卫生间'),(26,27,'独立阳台'),(26,24,'空调'),(26,23,'精装修'),(26,22,'随时看房'),(26,28,'集体供暖'),(28,29,'光照充足'),(28,30,'独立阳台');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `name` varchar(32) NOT NULL COMMENT '用户角色名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_and_name` (`user_id`,`name`) USING BTREE,
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

/*Data for the table `role` */

insert  into `role`(`id`,`user_id`,`name`) values (1,1,'USER'),(2,2,'ADMIN'),(3,3,'USER'),(4,4,'USER'),(5,5,'USER'),(6,6,'USER'),(7,7,'USER'),(8,8,'USER'),(9,9,'USER');

/*Table structure for table `subway` */

DROP TABLE IF EXISTS `subway`;

CREATE TABLE `subway` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '线路名',
  `city_en_name` varchar(32) NOT NULL COMMENT '所属城市英文名缩写',
  PRIMARY KEY (`id`),
  KEY `index_on_city` (`city_en_name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `subway` */

insert  into `subway`(`id`,`name`,`city_en_name`) values (1,'13号线','bj'),(2,'1号线','bj'),(3,'2号线','bj'),(4,'10号线','bj'),(5,'8号线','bj'),(6,'9号线','bj'),(7,'7号线','bj'),(8,'1号线','sh'),(9,'2号线','sh'),(10,'10号线','sh'),(11,'8号线','sh'),(12,'9号线','sh'),(13,'7号线','sh'),(14,'3号线','sh'),(15,'4号线','sh');

/*Table structure for table `subway_station` */

DROP TABLE IF EXISTS `subway_station`;

CREATE TABLE `subway_station` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `subway_id` bigint(20) NOT NULL COMMENT '所属地铁线id',
  `name` varchar(32) NOT NULL COMMENT '站点名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_station` (`subway_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4;

/*Data for the table `subway_station` */

insert  into `subway_station`(`id`,`subway_id`,`name`) values (5,1,'上地'),(16,1,'东直门'),(4,1,'五道口'),(14,1,'光熙门'),(11,1,'北苑'),(8,1,'回龙观'),(2,1,'大钟寺'),(12,1,'望京西'),(15,1,'柳芳'),(3,1,'知春路'),(10,1,'立水桥'),(13,1,'芍药居'),(6,1,'西二旗'),(1,1,'西直门'),(9,1,'霍营'),(7,1,'龙泽'),(33,4,'三元家庄'),(51,4,'三元桥'),(41,4,'丰台站'),(52,4,'亮马桥'),(27,4,'健德门'),(46,4,'公主坟'),(44,4,'六里桥'),(53,4,'农业展览馆'),(62,4,'分钟寺'),(59,4,'劲松'),(28,4,'北土城'),(61,4,'十里河'),(58,4,'双井'),(55,4,'呼家楼'),(54,4,'团结湖'),(57,4,'国贸'),(35,4,'大红门'),(32,4,'太阳宫'),(29,4,'安贞门'),(64,4,'宋家庄'),(20,4,'巴沟'),(30,4,'惠新西街南口'),(48,4,'慈寿寺'),(63,4,'成寿寺'),(42,4,'泥洼'),(22,4,'海淀黄庄'),(60,4,'潘家园'),(19,4,'火器营'),(26,4,'牡丹园'),(24,4,'知春路'),(23,4,'知春里'),(34,4,'石榴庄'),(39,4,'纪家庙'),(31,4,'芍药居'),(21,4,'苏州街'),(38,4,'草桥'),(45,4,'莲花桥'),(25,4,'西土城'),(43,4,'西局'),(47,4,'西钓鱼台'),(36,4,'角门东'),(37,4,'角门西'),(17,4,'车道沟'),(56,4,'金台夕照'),(18,4,'长春桥'),(40,4,'首经贸');

/*Table structure for table `support_address` */

DROP TABLE IF EXISTS `support_address`;

CREATE TABLE `support_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `belong_to` varchar(32) NOT NULL DEFAULT '0' COMMENT '上一级行政单位名',
  `en_name` varchar(32) NOT NULL COMMENT '行政单位英文名缩写',
  `cn_name` varchar(32) NOT NULL COMMENT '行政单位中文名',
  `level` varchar(16) NOT NULL COMMENT '行政级别 市-city 地区-region',
  `baidu_map_lng` double NOT NULL COMMENT '百度地图经度',
  `baidu_map_lat` double NOT NULL COMMENT '百度地图纬度',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_en_name_and_belong_to` (`en_name`,`level`,`belong_to`) USING BTREE COMMENT '每个城市的英文名都是独一无二的'
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

/*Data for the table `support_address` */

insert  into `support_address`(`id`,`belong_to`,`en_name`,`cn_name`,`level`,`baidu_map_lng`,`baidu_map_lat`) values (4,'bj','bj','北京','city',116.395645,39.929986),(5,'sh','sh','上海','city',121.487899,31.249162),(6,'hb','sjz','石家庄','city',114.522082,38.048958),(7,'hb','ts','唐山','city',118.183451,39.650531),(8,'hb','hd','邯郸','city',114.482694,36.609308),(9,'bj','dcq','东城区','region',116.42188470126446,39.93857401298612),(10,'bj','xcq','西城区','region',116.37319010401802,39.93428014370851),(12,'bj','hdq','海淀区','region',116.23967780102151,40.03316204507791),(13,'bj','cpq','昌平区','region',116.21645635689414,40.2217235498323),(14,'sh','ptq','普陀区','region',121.39844294374956,31.263742929075534),(15,'sjz','caq','长安区','region',114.59262155387033,38.07687479578663),(16,'sjz','qdq','桥东区','region',114.51078430496142,38.06338975380927),(17,'sjz','qxq','桥西区','region',114.43813995531943,38.033364550068136),(18,'sjz','xhq','新华区','region',114.4535014286928,38.117218640478164),(19,'bj','cyq','朝阳区','region',116.52169489108084,39.95895316640668);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `phone_number` varchar(15) NOT NULL COMMENT '电话号码',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `status` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '用户状态 0-正常 1-封禁',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户账号创建时间',
  `last_login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次更新记录时间',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_on_phone` (`phone_number`) USING BTREE COMMENT '用户手机号',
  UNIQUE KEY `index_on_username` (`name`) USING BTREE COMMENT '用户名索引',
  UNIQUE KEY `index_on_email` (`email`) USING BTREE COMMENT '电子邮箱索引'
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='用户基本信息表';

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`email`,`phone_number`,`password`,`status`,`create_time`,`last_login_time`,`last_update_time`,`avatar`) values (1,'hash','hash@dongnao.com','13812472893','$2a$10$IhUTbjXfkeyuBQdY7wSauOl/iT9TewkXskKeonZSlxljLjiqVbdY6',1,'2019-08-27 09:07:05','2019-08-27 09:07:07','2019-12-14 16:18:11','http://q1ucvq5bt.bkt.clouddn.com/admin.jpg'),(2,'admin','admin@dongnao.com','13912472893','$2a$10$/2XOqRQgvIZ//mNq3xPATOT1MpEuNKH3scssmNrKQ.85Nrxz/Z88q',1,'2019-08-27 09:07:05','2019-08-27 09:07:07','2019-12-14 16:14:00','http://q1ucvq5bt.bkt.clouddn.com/admin.jpg'),(9,'疯狂小租',NULL,'13262956975','$2a$10$ywWBXkdSCaVq4Mje6GQaY.RTzSVRGeAj81n2kTJm9RClc8MLpL02i',0,'2019-12-02 16:33:35','2019-12-02 16:33:35','2019-12-14 16:18:22',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
