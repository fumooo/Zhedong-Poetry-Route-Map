-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (arm64)
--
-- Host: localhost    Database: ZheDong
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_UN` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'111','111'),(5,'fumo','fumo'),(6,'123','123'),(12,'1234','1234');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `pictureC` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'宁波',121.55500030517578,29.9,'浙江',NULL),(2,'绍兴',120.58599853515625,30.05,'浙江',NULL),(3,'台州',121.43000030517578,28.68,'浙江',NULL),(4,'温州',120.69999694824219,28.01,'浙江',NULL),(5,'金华',119.649506,29.089524,'浙江',NULL),(6,'丽水',119.933,28.48,'浙江',NULL),(7,'衢州',118.87263,28.99,'浙江',NULL),(8,'义乌',120.07468,29.30558,'浙江',NULL),(9,'杭州',120.21201,30.2084,'浙江',NULL),(10,'丰城',115.77121,28.1592,'江西',NULL),(60,'兖州',116.826546,35.552305,'山东',NULL),(61,'临海',121.13885,28.85603,'浙江',NULL),(62,'萧山',120.26452,30.18505,'浙江',NULL),(63,'桐庐',119.68853,29.79779,'浙江',NULL);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egroup`
--

DROP TABLE IF EXISTS `egroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `egroup` (
  `id` int NOT NULL AUTO_INCREMENT,
  `beginYear` int DEFAULT NULL,
  `endYear` int DEFAULT NULL,
  `peopleId` int DEFAULT NULL,
  `cityId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `group_FK` (`cityId`),
  KEY `group_FK_1` (`peopleId`),
  CONSTRAINT `group_FK` FOREIGN KEY (`cityId`) REFERENCES `city` (`id`),
  CONSTRAINT `group_FK_1` FOREIGN KEY (`peopleId`) REFERENCES `people` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egroup`
--

LOCK TABLES `egroup` WRITE;
/*!40000 ALTER TABLE `egroup` DISABLE KEYS */;
INSERT INTO `egroup` VALUES (1,635,643,4,8),(2,655,NULL,4,8),(3,680,NULL,4,8),(4,676,NULL,4,2),(5,615,NULL,2,9),(6,615,NULL,2,10),(7,652,653,4,60),(8,656,NULL,4,60),(9,665,NULL,4,60),(10,680,683,4,61),(11,680,NULL,4,62),(12,683,NULL,4,63),(13,580,581,1,1),(15,582,NULL,1,1);
/*!40000 ALTER TABLE `egroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experience`
--

DROP TABLE IF EXISTS `experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experience` (
  `groupId` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `beginYear` int DEFAULT NULL,
  `endYear` int DEFAULT NULL,
  `document` text,
  PRIMARY KEY (`id`),
  KEY `experience_FK_2` (`groupId`),
  CONSTRAINT `experience_FK_2` FOREIGN KEY (`groupId`) REFERENCES `egroup` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experience`
--

LOCK TABLES `experience` WRITE;
/*!40000 ALTER TABLE `experience` DISABLE KEYS */;
INSERT INTO `experience` VALUES (1,1,635,NULL,'游会稽，题诗山寺。'),(1,2,635,NULL,'居于家乡义乌。宾王生年难确考，本表所列年岁为虚拟数。'),(1,3,636,640,'居于家乡义乌。'),(1,4,641,NULL,'居乡.作咏鹅诗。'),(1,5,642,643,'居于家乡义乌。'),(2,6,655,NULL,'应举落第,南归义乌.将至,有《望乡夕泛》诗。'),(2,7,655,NULL,'居义乌时,与高四缔交。'),(2,8,655,NULL,'居故乡义乌.有《赋得白云抱幽石》诗。'),(2,9,655,NULL,'居故乡义乌.有《赋得春云处处生》诗。'),(3,10,680,NULL,'680年7月20日　七月中旬,至故乡.有《再与亲情书》.廿日,葬母于义乌故山。作品：《再与亲情书》'),(4,11,676,NULL,'夏秋之际,奉使江南.此行经淮南至吴会,数月后由洛返京.沿途有诗。'),(7,12,652,NULL,'奉母居瑕丘,与瑕丘县令韦某有文字交游。'),(7,13,653,NULL,'居瑕丘。'),(8,14,656,NULL,'居瑕丘'),(9,15,665,NULL,'遍谒兖州地方官');
/*!40000 ALTER TABLE `experience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `people` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `birth_date` int DEFAULT NULL,
  `death_date` int DEFAULT NULL,
  `introduction` text,
  `pictureP` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (1,'魏征',580,643,'魏征，字玄成，相州内黄县人，祖籍巨鹿郡下曲阳县，隋唐政治家，思想家，文学家和史学家，曾任谏议大夫、左光禄大夫，封郑国公，谥文贞，以直谏敢言著称，是中国史上最负盛名的谏臣，辅佐唐太宗创建「贞观之治」的大业，被后人称为「一代名相」。着有《隋书》序论，《梁书》、《陈书》、《齐书》的总论等。其言论多见《贞观政要》。','1684074169146.jpeg'),(2,'王绩',589,644,'王绩，字无功，号东皋子，隋末唐初绛州龙门县人，初唐诗人。 初唐的三十余年，诗坛上仍旧弥漫着梁陈余风，只有王绩在追踪晋宋间独来独往因而不免寂寞的陶渊明。他以此为后世称赞','1684074208643.jpeg'),(3,'卢照邻',634,682,'卢照邻，字升之，号幽忧子，幽州范阳人，唐朝诗人，后因久病厌世，投水死。他与王勃、杨炯和骆宾王一起被称为“初唐四杰”','1684074233473.jpeg'),(4,'骆冰王',635,684,'骆冰王，字观光，义乌人。骆宾王出身寒门，七岁能诗，号称神童。据说《咏鹅诗》就是此时所作。 唐朝初期的著名诗人，与王勃、杨炯、卢照邻合称初唐四杰。684年徐敬业起兵讨伐武则天，骆宾王起草了著名的《为徐敬业讨武曌檄》，敬业败后，宾王不知所终，一说被斩，一说剃发出家，终身隐居','1684074274854.jpeg'),(5,'王勃',650,676,'王勃，字子安，绛州龙门人。初唐时期的诗人，擅长骈文，与杨炯、卢照邻、骆宾王合称“初唐四杰”。','1684074365307.jpeg'),(6,'杨炯',650,693,NULL,''),(7,'沈佺期',656,716,NULL,''),(8,'宋之问',656,712,NULL,''),(9,'孟浩然',689,740,NULL,''),(10,'王昌龄',698,757,NULL,''),(11,'高适',700,765,NULL,''),(12,'王维',700,761,NULL,''),(13,'李白',701,762,NULL,''),(14,'杜甫',712,770,NULL,''),(15,'岑参',717,769,NULL,''),(16,'萧颖士',717,759,NULL,''),(17,'钱起',718,783,NULL,''),(18,'刘长卿',718,790,NULL,''),(19,'韦应物',737,792,NULL,''),(20,'孟郊',751,814,NULL,''),(21,'权德舆 ',759,818,NULL,''),(22,'令狐楚',766,837,NULL,''),(23,'韩愈',768,825,NULL,''),(24,'白居易',772,846,NULL,''),(25,'柳宗元',773,819,NULL,''),(26,'姚合',777,842,NULL,''),(27,'张彻',777,821,NULL,''),(28,'周贺',777,840,NULL,''),(29,'元稹',779,831,NULL,''),(30,'贾岛',779,831,NULL,''),(31,'牛僧孺',780,848,NULL,''),(32,'李德裕',787,849,NULL,''),(33,'皇甫湜 ',787,835,NULL,''),(34,'李贺',790,816,NULL,''),(35,'令狐绹',802,879,NULL,''),(36,'段成式',803,863,NULL,''),(37,'方干',809,886,NULL,''),(38,'喻凫',810,850,NULL,''),(39,'郑巢',815,855,NULL,''),(40,'李频',815,876,NULL,''),(41,'陆龟蒙',830,881,NULL,''),(42,'贯休',832,912,NULL,''),(43,'罗隐',833,909,NULL,''),(44,'皮日休',834,902,NULL,''),(45,'韦庄',836,910,NULL,''),(46,'司空图',837,908,NULL,''),(47,'鱼玄机',844,868,NULL,''),(48,'杜荀鹤 ',846,904,NULL,''),(49,'郑谷',848,909,NULL,''),(50,'崔涂',854,896,NULL,'');
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poem`
--

DROP TABLE IF EXISTS `poem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `experienceId` int DEFAULT NULL,
  `poemTitle` varchar(30) DEFAULT NULL,
  `content` text,
  `dynasty` varchar(10) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `source` text,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `poem_FK` (`experienceId`),
  CONSTRAINT `poem_FK` FOREIGN KEY (`experienceId`) REFERENCES `experience` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poem`
--

LOCK TABLES `poem` WRITE;
/*!40000 ALTER TABLE `poem` DISABLE KEYS */;
INSERT INTO `poem` VALUES (1,4,'咏鹅','鹅，鹅，鹅，曲项向天歌。\n白毛浮绿水，红掌拨清波。','初唐',641,'（陈熙晋《骆临海集笺注》上海古籍出版社1985年版卷3第108页。）','五言绝句'),(2,8,'赋得白云抱幽石','重岩抱危石，幽涧曳轻云。\n绕镇仙衣动，飘蓬羽盖分。\n锦色连花静，苔光带叶熏。\n讵知吴会影，长抱谷城文。','初唐',655,'（陈熙晋《骆临海集笺注》上海古籍出版社1985年版卷3第78页。）','五言律诗'),(7,6,'望乡夕泛','归怀剩不安，促榜犯风澜。\n落宿含楼近，浮月带江寒。\n喜逐行前至，忧从望里宽。\n今夜南枝鹊，应无绕树难。','初唐 ',655,'（陈熙晋《骆临海集笺注》上海古籍出版社1985年版卷2第59页。）','五言律诗'),(8,9,'赋得春云处处生','千里年光静，四望春云生。\n椠（一作暂）日祥光举，疏云瑞叶轻。\n盖阴笼迥树，阵影抱危城。\n非将吴会远，飘荡帝乡情。','初唐',655,'（陈熙晋《骆临海集笺注》上海古籍出版社1985年版卷3第78页。）','五言律诗'),(9,11,'在江南赠宋五之问','井络双源浚，浔阳九派长。\n沦（一作轮）波通地穴，输委下归塘。\n别岛笼朝蜃，连洲拥夕涨（一作阳）。\n韫珠澄（一作成）积润，让璧动浮光。\n浮光凝折水，积润疏圆沚。\n玉轮涵地开，剑阁（一作匣，又作匝）连星起。\n风烟标迥秀，英灵信多美。\n怀德践遗芳，端操惭谋己。\n谋己谬观光，牵迹强悽惶。\n揆拙迷三省，劳生昧（一作暗）两忘。\n弹随（一作冠）空被笑，献楚自多伤。\n一朝殊默语，千里易（一作异）炎凉。\n炎凉几迁贸，川（一作水）陆疲臻凑。\n积水架吴涛，连山横楚岫。\n风月虽殊昔，星河犹是旧。\n姑苏望南浦，邯郸通北走。\n北走平生亲，南浦别离津。\n潇湘一超忽，洞庭多苦辛。\n秋江无绿芷，寒汀有白蘋。\n采之（一作采采）将何遗，故人漳水滨。\n漳滨已辽远，江潭未旋返。\n为听短歌行，当想（一作忆）长洲苑。\n露金熏菊岸，风佩摇兰坂。\n蝉鸣稻叶秋，雁起芦花晚。\n晚秋（一作秋天）云日明，亭皋风雾（一作露）清。\n独负平生气，重（一作空）牵摇落情。\n占星非聚德，梦月讵悬名。\n寂寥伤楚奏，凄断泣秦声。\n秦声怀旧里，楚奏悲无已。\n郢路少知音，丛台富奇士。\n温辉凌爱日，壮气惊寒水。\n一顾重风云，三冬足文史。\n文史盛纷纶，京洛多风尘。\n犹轻（一作由来）五车富，未重一囊贫。\n李仙非（一作悲）易托，苏鬼尚（一作曲）难因。\n不惜劳歌尽，谁为听阳春。','初唐',676,'（陈熙晋《骆临海集笺注》上海古籍出版社1985年版卷3第78页。）',NULL),(10,11,'望月有所思','九秋凉风（一作气）肃，千里月华开。\n圆光随露湛，碎影逐波来。\n似霜明玉砌，如镜写珠胎。\n晚色依关近，边声杂吹哀。\n离居分照耀，怨绪共裴徊。\n自绕南飞羽，空忝北堂才。','初唐',676,'（陈熙晋《骆临海集笺注》上海古籍出版社1985年版卷3第78页。）','五言排律'),(11,14,'夏日游德州赠高四并序','夫在心为志，发言为诗，诗有不得尽言，言有不得尽意。仆少负不羁，长逾虚诞，读书颇存涉猎，学剑不待穷工，进不能矫翰龙云，退不能栖神豹雾，抚循诸己，深觉劳生。而太夫人在堂，义须捧檄，因仰长安而就日，赴帝乡以望云。虽文阙三冬，而书劳十上。嗟乎，入门自媚，谁相谓言，致使君门隔于九重，中堂远于千里，既而交非得兔，路是亡羊，敬止弊庐，朅来初服，遂得载披玉叶，款洽金兰，倾意气于一言，缔风期于千祀。虽交因气合，资得意以敦交，道契言忘，少寄言而筌道，是以轻投木李，以代疏麻，章句繁芜，心神愧恧，庶瞻雅韵，伫辱报章，则紫耀运星，开龙文于剑匣，素辉亏月，领骊颔于珠胎云尔。','初唐',656,'（陈熙晋《骆临海集笺注》上海古籍出版社1985年版卷3第78页。）',NULL),(12,15,'上兖州刺史启',NULL,'初唐',665,'（陈熙晋《骆临海集笺注》上海古籍出版社1985年版卷3第78页。）',NULL),(13,15,'上兖州张司马启',NULL,'初唐',665,'（陈熙晋《骆临海集笺注》上海古籍出版社1985年版卷3第78页。）',NULL);
/*!40000 ALTER TABLE `poem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scenery`
--

DROP TABLE IF EXISTS `scenery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scenery` (
  `id` int NOT NULL AUTO_INCREMENT,
  `peopleId` int DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `introduction` text,
  `picture` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `scenery_FK` (`peopleId`),
  CONSTRAINT `scenery_FK` FOREIGN KEY (`peopleId`) REFERENCES `people` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scenery`
--

LOCK TABLES `scenery` WRITE;
/*!40000 ALTER TABLE `scenery` DISABLE KEYS */;
INSERT INTO `scenery` VALUES (1,4,'大松溪峡谷','大松溪峡谷 以“雄、奇、险、幽”著称，多急流、飞瀑，多彩多姿、形态各异的溪坑巨石，星罗棋布于溪涧之中，使人使人驻足沉思、流连忘返。峡谷两岸，险峰和奇岩高耸环立，千年古树在悬崖峭壁之上横空而出，如苍剑挥舞。清粼粼的山溪千流百折，滩声哗哗，如在倾诉峡谷的千年柔情。\n','1.png'),(2,4,'双峰国家森林公园','浙江双峰国家森林公园位于浙江省宁波市宁海县，是一处集生态旅游、科普教育和休闲度假于一体的国家森林公园。\n浙江双峰国家森林公园地处天台山脉，属亚热带季风湿润气候区，植被以天然常绿阔叶林为主，森林覆盖率高达92.6%，最高峰坪头山海拔735.2米，总面积2281.41公顷。\n2003年12月，浙江双峰森林公园获批为国家级森林公园。','2.png'),(30,4,'灵隐寺','灵隐寺，又称云林禅寺，位于杭州西湖西北面，在飞来峰与北高峰之间灵隐山山麓中，是杭州历史悠久、景色宜人的游览胜地，中国最早的佛教寺院和中国十大古刹之一，亦是南宋高僧济公的修行地。\n','1683288357842.jpeg'),(31,1,'西湖','西湖，位于中华人民共和国浙江省杭州旧城西侧，是中国大陆首批国家重点风景名胜区和中国十大风景名胜之一。中国大陆主要的观赏性淡水湖泊之一。西湖东靠杭州市区，其余三面环山，面积约6.39平方公里，南北长约3.2公里，东西宽约2.8公里，绕湖一周近15公里。西湖平均水深2.27公尺，水体容量约为1429万立方公尺。','1683952008440.jpg'),(32,2,'雁荡山','雁荡山，简称雁山，坐落于浙江省温州市，分为北雁荡山、中雁荡山、南雁荡山。北雁荡山最为有名，一般雁荡山特指北雁荡山。北雁荡山、中雁荡山均位于温州市北部乐清境内。','1683962508968.jpeg'),(34,3,'盐官古城','海宁盐官镇距杭州五十公里，自古以来，每年农历八月十八日的钱塘江大潮都是天下奇观，每到这一天观潮的人人山人海，人潮也似海潮般汹涌澎湃。当大潮即将到来之际，只见远方天边似有一条银线汹涌而至，其声似万马奔腾，其势如排山倒海，雪浪凌空，洪流滚滚，摧枯拉朽，气象万千。它与山西壶口瀑布，贵州黄果树瀑布并列，是中国观潮赏景的三大胜地。','1684067212276.jpeg');
/*!40000 ALTER TABLE `scenery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ZheDong'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-21 21:56:59
