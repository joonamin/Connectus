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


-- Dumping database structure for event
CREATE DATABASE IF NOT EXISTS `event` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `event`;

-- Dumping structure for table event.event
CREATE TABLE IF NOT EXISTS `event` (
  `is_finished` bit(1) DEFAULT NULL,
  `reward` int(11) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `host_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table event.event: ~1 rows (approximately)
INSERT INTO `event` (`is_finished`, `reward`, `created_at`, `host_id`, `id`, `updated_at`, `description`, `image_url`, `title`) VALUES
	(b'1', 100, '2024-05-19 12:10:28.567834', 12, 1, '2024-05-19 12:14:41.623782', '설명입니다', 'https://e106-connectus.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%EA%B6%8C.jpg', '포스트맨 이벤트입니다');

-- Dumping structure for table event.event_achievement
CREATE TABLE IF NOT EXISTS `event_achievement` (
  `event_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ping_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table event.event_achievement: ~0 rows (approximately)

-- Dumping structure for table event.event_on_clear_user_id_list
CREATE TABLE IF NOT EXISTS `event_on_clear_user_id_list` (
  `event_id` bigint(20) NOT NULL,
  `on_clear_user_id_list` bigint(20) DEFAULT NULL,
  KEY `FK5tspvi8s0sg3ijfw4oehrv94r` (`event_id`),
  CONSTRAINT `FK5tspvi8s0sg3ijfw4oehrv94r` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table event.event_on_clear_user_id_list: ~0 rows (approximately)

-- Dumping structure for table event.event_spot_id_list
CREATE TABLE IF NOT EXISTS `event_spot_id_list` (
  `event_id` bigint(20) NOT NULL,
  `spot_id_list` bigint(20) DEFAULT NULL,
  KEY `FK3d0rfsd5rbowncxr3dhh47fgf` (`event_id`),
  CONSTRAINT `FK3d0rfsd5rbowncxr3dhh47fgf` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table event.event_spot_id_list: ~1 rows (approximately)
INSERT INTO `event_spot_id_list` (`event_id`, `spot_id_list`) VALUES
	(1, 449616917989183742);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
