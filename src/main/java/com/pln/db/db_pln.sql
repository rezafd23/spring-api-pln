# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.30)
# Database: db_pln
# Generation Time: 2020-12-23 08:50:03 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table hibernate_sequence
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;

INSERT INTO `hibernate_sequence` (`next_val`)
VALUES
	(2),
	(2),
	(2),
	(2),
	(2);

/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_daya
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_daya`;

CREATE TABLE `tbl_daya` (
  `id_daya` int(11) NOT NULL AUTO_INCREMENT,
  `daya` varchar(10) DEFAULT NULL,
  `tarif` double DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` enum('1','0') DEFAULT '1',
  PRIMARY KEY (`id_daya`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tbl_daya` WRITE;
/*!40000 ALTER TABLE `tbl_daya` DISABLE KEYS */;

INSERT INTO `tbl_daya` (`id_daya`, `daya`, `tarif`, `created_at`, `status`)
VALUES
	(1,'900 VA',1352,'2020-12-11 23:32:36','1'),
	(2,'1300 VA',1444.7,'2020-12-11 23:33:02','1'),
	(3,'2200 VA',1444.7,'2020-12-11 23:33:45','1'),
	(21,'150 VA',12447.5,'2020-12-17 00:48:07','0');

/*!40000 ALTER TABLE `tbl_daya` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_pelanggan
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_pelanggan`;

CREATE TABLE `tbl_pelanggan` (
  `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT,
  `no_pelanggan` varchar(30) DEFAULT NULL,
  `id_daya` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` enum('1','0') DEFAULT '1',
  `id_user` int(11) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_pelanggan`),
  KEY `id_daya` (`id_daya`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `tbl_pelanggan_ibfk_1` FOREIGN KEY (`id_daya`) REFERENCES `tbl_daya` (`id_daya`) ON UPDATE CASCADE,
  CONSTRAINT `tbl_pelanggan_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `tbl_user` (`id_user`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tbl_pelanggan` WRITE;
/*!40000 ALTER TABLE `tbl_pelanggan` DISABLE KEYS */;

INSERT INTO `tbl_pelanggan` (`id_pelanggan`, `no_pelanggan`, `id_daya`, `created_at`, `status`, `id_user`, `alamat`)
VALUES
	(1,'12345678',2,'2020-12-17 03:24:41','1',1,'wisma asri 1 jalan Mampang prapatan'),
	(2,'56567678',1,'2020-12-17 03:28:38','1',2,'wisma asri 2');

/*!40000 ALTER TABLE `tbl_pelanggan` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_token
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_token`;

CREATE TABLE `tbl_token` (
  `id_token` int(11) NOT NULL AUTO_INCREMENT,
  `no_token` varchar(30) DEFAULT NULL,
  `id_pelanggan` int(11) DEFAULT NULL,
  `id_voucer` int(11) DEFAULT NULL,
  `portal_bayar` varchar(50) DEFAULT NULL,
  `status_bayar` enum('success','failed') DEFAULT NULL,
  `status_redeem` enum('redeem','non') DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` enum('1','0') DEFAULT '1',
  `kode_bayar` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_token`),
  KEY `id_pelanggan` (`id_pelanggan`),
  KEY `id_voucer` (`id_voucer`),
  CONSTRAINT `tbl_token_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `tbl_pelanggan` (`id_pelanggan`) ON UPDATE CASCADE,
  CONSTRAINT `tbl_token_ibfk_2` FOREIGN KEY (`id_voucer`) REFERENCES `tbl_voucer` (`id_voucer`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tbl_token` WRITE;
/*!40000 ALTER TABLE `tbl_token` DISABLE KEYS */;

INSERT INTO `tbl_token` (`id_token`, `no_token`, `id_pelanggan`, `id_voucer`, `portal_bayar`, `status_bayar`, `status_redeem`, `created_at`, `status`, `kode_bayar`)
VALUES
	(2,'20765320532778467475',1,5,'m-BTPS','success','redeem','2020-12-17 04:22:17','1','12345'),
	(3,'23508071507335432073',1,5,'m-BTPS','success','non','2020-12-17 14:00:38','1','12345'),
	(4,'95376269867243994029',2,5,'m-BTPS','success','redeem','2020-12-17 14:19:04','1','12345'),
	(5,'69153874603562996895',2,5,'m-BTPS','success','non','2020-12-17 14:19:58','1','12345'),
	(6,'33262320378708059301',1,5,'m-BTPS','success','redeem','2020-12-23 11:20:35','1','12345'),
	(7,'21564244501206469748',1,5,'m-BTPS','success','redeem','2020-12-23 11:25:53','1','12345'),
	(8,'75441128087055357827',1,5,'m-BTPS','success','non','2020-12-23 11:28:37','1','12345');

/*!40000 ALTER TABLE `tbl_token` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `no_ktp` varchar(20) DEFAULT NULL,
  `nama_user` varchar(50) DEFAULT NULL,
  `no_hp` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` enum('1','0') DEFAULT '1',
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;

INSERT INTO `tbl_user` (`id_user`, `no_ktp`, `nama_user`, `no_hp`, `created_at`, `status`)
VALUES
	(1,'3278062707970013','Reza Fadilah','085353444497','2020-12-11 23:34:46','1'),
	(2,'3278062707970013','Ahmad','081222811120','2020-12-11 23:35:03','1');

/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_voucer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_voucer`;

CREATE TABLE `tbl_voucer` (
  `id_voucer` int(11) NOT NULL AUTO_INCREMENT,
  `voucer` varchar(10) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` enum('1','0') DEFAULT '1',
  PRIMARY KEY (`id_voucer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tbl_voucer` WRITE;
/*!40000 ALTER TABLE `tbl_voucer` DISABLE KEYS */;

INSERT INTO `tbl_voucer` (`id_voucer`, `voucer`, `harga`, `created_at`, `status`)
VALUES
	(2,'10.000',10000,'2020-12-11 22:36:23','1'),
	(3,'20.000',20000,'2020-12-11 22:36:41','1'),
	(4,'50.000',50000,'2020-12-11 22:37:05','1'),
	(5,'100.000',100000,'2020-12-11 22:37:11','1'),
	(6,'200.000',200000,'2020-12-11 22:37:21','1'),
	(7,'500.000',500000,'2020-12-11 22:37:40','1');

/*!40000 ALTER TABLE `tbl_voucer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table view_pelanggan
# ------------------------------------------------------------

DROP VIEW IF EXISTS `view_pelanggan`;

CREATE TABLE `view_pelanggan` (
   `nama` VARCHAR(50) NULL DEFAULT NULL,
   `no_pelanggan` VARCHAR(30) NULL DEFAULT NULL,
   `daya` VARCHAR(10) NULL DEFAULT NULL,
   `tarif` DOUBLE NULL DEFAULT NULL
) ENGINE=MyISAM;



# Dump of table view_token
# ------------------------------------------------------------

DROP VIEW IF EXISTS `view_token`;

CREATE TABLE `view_token` (
   `no_pelanggan` VARCHAR(30) NULL DEFAULT NULL,
   `token` DOUBLE(19) NULL DEFAULT NULL
) ENGINE=MyISAM;





# Replace placeholder table for view_pelanggan with correct view syntax
# ------------------------------------------------------------

DROP TABLE `view_pelanggan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_pelanggan`
AS SELECT
   `a`.`nama_user` AS `nama`,
   `b`.`no_pelanggan` AS `no_pelanggan`,
   `c`.`daya` AS `daya`,
   `c`.`tarif` AS `tarif`
FROM ((`tbl_user` `a` join `tbl_pelanggan` `b` on((`a`.`id_user` = `b`.`id_user`))) join `tbl_daya` `c` on((`b`.`id_daya` = `c`.`id_daya`))) where ((`a`.`status` = '1') and (`b`.`status` = '1') and (`c`.`status` = '1'));


# Replace placeholder table for view_token with correct view syntax
# ------------------------------------------------------------

DROP TABLE `view_token`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_token`
AS SELECT
   `c`.`no_pelanggan` AS `no_pelanggan`,sum(round((`b`.`harga` / `d`.`tarif`),2)) AS `token`
FROM (((`tbl_token` `a` left join `tbl_voucer` `b` on((`a`.`id_voucer` = `b`.`id_voucer`))) left join `tbl_pelanggan` `c` on((`a`.`id_pelanggan` = `c`.`id_pelanggan`))) left join `tbl_daya` `d` on((`c`.`id_daya` = `d`.`id_daya`))) where ((`a`.`status_redeem` = 'redeem') and (`a`.`status` = 1)) group by `c`.`no_pelanggan`;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
