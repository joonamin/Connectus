-- --------------------------------------------------------
-- Host:                         mariadb.connectus.social
-- Server version:               11.3.2-MariaDB-1:11.3.2+maria~ubu2204 - mariadb.org binary distribution
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for gather
CREATE DATABASE IF NOT EXISTS `gather` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `gather`;

-- Dumping structure for table gather.candidate
CREATE TABLE IF NOT EXISTS `candidate` (
  `candidate_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `spot_id` bigint(20) NOT NULL,
  PRIMARY KEY (`candidate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table gather.candidate: ~0 rows (approximately)

-- Dumping structure for table gather.gather
CREATE TABLE IF NOT EXISTS `gather` (
  `gather_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `end_time` varchar(255) NOT NULL,
  `host_id` bigint(20) NOT NULL,
  `is_closed` tinyint(1) NOT NULL DEFAULT 0,
  `max_joiner` int(11) NOT NULL,
  `spot_id` bigint(20) NOT NULL,
  PRIMARY KEY (`gather_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table gather.gather: ~35 rows (approximately)
INSERT INTO `gather` (`gather_id`, `created_at`, `updated_at`, `content`, `end_time`, `host_id`, `is_closed`, `max_joiner`, `spot_id`) VALUES
	(2, '2024-05-17 12:07:17.460973', '2024-05-17 12:07:17.748974', '안녕하세요', '2024-04-29 18:00', 1, 0, 3, 449616917989183022),
	(3, '2024-05-17 12:09:43.746966', '2024-05-17 12:09:43.803067', '안녕하세요', '2024-04-29 18:00', 1, 0, 3, 449616917989183027),
	(4, '2024-05-17 12:17:41.920285', '2024-05-17 12:17:42.022570', '안녕하세요', '2024-04-29 18:00', 1, 0, 3, 449616917989183029),
	(5, '2024-05-17 15:11:06.415479', '2024-05-17 15:11:06.759482', '로빈 뮤지엄으로 모여라!!', '2024-06-01 18:00', 9, 0, 3, 449616917989183074),
	(6, '2024-05-17 11:42:45.095080', '2024-05-17 11:42:45.284121', '희망 공원으로 모여볼까요?', '2024-06-03 18:00', 9, 0, 3, 449616917989183083),
	(7, '2024-05-17 11:44:27.374298', '2024-05-17 11:44:27.422291', '희망 공원으로 모여볼까요?', '2024-06-03 18:00', 9, 0, 3, 449616917989183085),
	(8, '2024-05-17 11:44:56.911614', '2024-05-17 11:44:56.934711', '희망 공원으로 모여보자!!!', '2024-06-10 18:00', 9, 0, 5, 449616917989183087),
	(9, '2024-05-18 02:08:39.307194', '2024-05-18 02:08:39.460687', '꾸웡꾸웡', '2024-06-01 23:00', 9, 0, 3, 449616917989183120),
	(10, '2024-05-18 02:11:09.862485', '2024-05-18 02:11:09.884437', '꾸웡꾸웡!!!!!!!!!!!!', '2024-06-01 23:00', 9, 0, 3, 449616917989183122),
	(11, '2024-05-18 06:15:58.544560', '2024-05-18 15:47:42.732574', 'test', '2024-05-18 15:30', 7, 1, 3, 0),
	(12, '2024-05-18 15:50:15.866432', '2024-05-18 15:51:28.473784', '이거것은 테스트', '2024-05-18 16:20', 7, 1, 2, 0),
	(13, '2024-05-18 15:51:55.269265', '2024-05-18 15:52:18.686093', '몇번이게', '2024-05-18 16:06', 7, 1, 2, 0),
	(14, '2024-05-18 15:54:31.792053', '2024-05-18 15:56:30.407748', '151515', '2024-05-18 16:09', 7, 1, 2, 0),
	(15, '2024-05-18 16:14:06.927148', '2024-05-18 16:14:07.190460', 'ddddqfqfqwfwq', '2024-05-18 16:34', 7, 0, 2, 449616917989183186),
	(16, '2024-05-19 12:39:06.955288', '2024-05-19 12:39:07.111665', '모여라 여기에', '2024-05-19 13:09', 7, 0, 6, 449616917989183744),
	(17, '2024-05-19 13:58:50.459697', '2024-05-19 13:58:50.723007', '밥먹자', '2024-05-19 14:13', 7, 0, 4, 449616917989184657),
	(18, '2024-05-19 14:34:40.097703', '2024-05-19 14:34:40.118900', 'ttttt', '2024-05-19 14:49', 7, 0, 3, 449616917989184880),
	(19, '2024-05-19 15:52:04.460454', '2024-05-19 15:52:04.477090', 'ㅂㅂㅂㅂ', '2024-05-19 16:22', 21, 0, 2, 449616917989185342),
	(20, '2024-05-19 17:41:02.911961', '2024-05-19 17:41:03.156224', '1234', '2024-05-19 17:56', 21, 0, 4, 449616917989186338),
	(21, '2024-05-19 18:00:28.952937', '2024-05-19 18:00:28.984890', 'ㅂㅂㅂㅂㅂㅂ', '2024-05-19 18:15', 7, 0, 5, 449616917989186409),
	(22, '2024-05-19 22:49:02.931904', '2024-05-19 22:49:03.156827', '우리팀 파이팅', '2024-05-19 23:04', 7, 0, 4, 449616917989188342),
	(23, '2024-05-19 23:40:09.165273', '2024-05-19 23:40:09.470768', '모여라ㅜ테스트\n', '2024-05-19 23:55', 7, 0, 4, 449616917989188527),
	(24, '2024-05-20 01:09:48.553892', '2024-05-20 01:09:48.569174', 'ttttt', '2024-05-20 01:29', 7, 0, 6, 449616917989190135),
	(25, '2024-05-20 01:33:57.266276', '2024-05-20 01:33:57.283374', '222222', '2024-05-20 02:03', 21, 0, 6, 449616917989190184),
	(26, '2024-05-20 02:03:53.538802', '2024-05-20 02:03:53.554443', 'qqq', '2024-05-20 02:33', 7, 0, 4, 449616917989190210),
	(27, '2024-05-20 02:36:35.250418', '2024-05-20 02:36:35.540603', '모여라 확인', '2024-05-20 03:06', 7, 0, 2, 449616917989190569),
	(28, '2024-05-20 02:45:06.416414', '2024-05-20 02:45:30.021473', '카츠동먹으실분', '2024-05-20 03:05', 7, 1, 4, 449616917989190804),
	(29, '2024-05-20 03:01:29.475142', '2024-05-20 03:01:29.493755', '같이 버스킹 해봐요!', '2024-05-20 03:21', 12, 0, 2, 449616917989191204),
	(30, '2024-05-20 03:05:06.629131', '2024-05-20 03:05:06.648432', 'ㅎㅇㅎㅇ', '2024-05-20 03:15', 21, 0, 2, 449616917989191395),
	(31, '2024-05-20 03:05:47.185010', '2024-05-20 03:05:47.201365', 'ㅂㅂㅂㅂ', '2024-05-20 03:20', 21, 0, 3, 449616917989191442),
	(32, '2024-05-20 03:14:02.158435', '2024-05-20 03:14:02.175794', '카츠탄탄으로 모여라', '2024-05-20 03:29', 7, 0, 5, 449616917989191613),
	(33, '2024-05-20 03:33:11.284307', '2024-05-20 03:33:11.301290', '모여라', '2024-05-20 03:48', 12, 0, 2, 449616917989191813),
	(34, '2024-05-20 04:07:12.211755', '2024-05-20 04:07:12.226109', '치킨주세요 ㅜㅜㅜ', '2024-05-20 04:22', 21, 0, 4, 449616917989191955),
	(35, '2024-05-20 09:14:06.406109', '2024-05-20 09:15:31.826859', '가즈아', '2024-05-20 09:29', 10, 1, 4, 449616917989192522),
	(36, '2024-05-20 09:46:31.066515', '2024-05-20 09:48:06.024566', '예쁜 카페로 개발하러 가요!', '2024-05-20 10:06', 10, 1, 3, 449616917989193321);

-- Dumping structure for table gather.gather_candidate_list
CREATE TABLE IF NOT EXISTS `gather_candidate_list` (
  `gather_gather_id` bigint(20) NOT NULL,
  `candidate_list` bigint(20) DEFAULT NULL,
  KEY `FK3pegpxc2wufdst6k70moloh2s` (`gather_gather_id`),
  CONSTRAINT `FK3pegpxc2wufdst6k70moloh2s` FOREIGN KEY (`gather_gather_id`) REFERENCES `gather` (`gather_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table gather.gather_candidate_list: ~10 rows (approximately)
INSERT INTO `gather_candidate_list` (`gather_gather_id`, `candidate_list`) VALUES
	(8, 7),
	(4, 7),
	(10, 7),
	(17, 10),
	(17, 2),
	(18, 10),
	(18, 2),
	(18, 12),
	(26, 21),
	(36, 7);

-- Dumping structure for table gather.gather_joiner_list
CREATE TABLE IF NOT EXISTS `gather_joiner_list` (
  `gather_gather_id` bigint(20) NOT NULL,
  `joiner_list` bigint(20) DEFAULT NULL,
  KEY `FKffdcgqwdki9neflll83cba2hg` (`gather_gather_id`),
  CONSTRAINT `FKffdcgqwdki9neflll83cba2hg` FOREIGN KEY (`gather_gather_id`) REFERENCES `gather` (`gather_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table gather.gather_joiner_list: ~11 rows (approximately)
INSERT INTO `gather_joiner_list` (`gather_gather_id`, `joiner_list`) VALUES
	(16, 21),
	(23, 12),
	(24, 21),
	(25, 7),
	(27, 21),
	(29, 21),
	(31, 12),
	(32, 21),
	(32, 12),
	(33, 7),
	(35, 7);

-- Dumping structure for table gather.joiner
CREATE TABLE IF NOT EXISTS `joiner` (
  `joiner_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `spot_id` bigint(20) NOT NULL,
  PRIMARY KEY (`joiner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table gather.joiner: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
