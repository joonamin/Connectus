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


-- Dumping database structure for like
CREATE DATABASE IF NOT EXISTS `like` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `like`;

-- Dumping structure for table like.likes
CREATE TABLE IF NOT EXISTS `likes` (
  `type` tinyint(4) DEFAULT NULL CHECK (`type` between 0 and 1),
  `domain_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table like.likes: ~33 rows (approximately)
INSERT INTO `likes` (`type`, `domain_id`, `id`, `user_id`) VALUES
	(0, 221, 1, 7),
	(0, 201, 2, 7),
	(0, 202, 3, 7),
	(0, 221, 6, 12),
	(0, 221, 7, 9),
	(0, 221, 8, 2),
	(0, 268, 10, 7),
	(0, 200, 11, 7),
	(0, 197, 13, 7),
	(0, 195, 15, 7),
	(0, 269, 16, 7),
	(0, 270, 17, 7),
	(0, 273, 18, 7),
	(0, 272, 19, 7),
	(0, 177, 20, 7),
	(0, 171, 21, 7),
	(0, 172, 23, 7),
	(0, 173, 24, 7),
	(0, 275, 25, 7),
	(0, 284, 28, 7),
	(0, 282, 29, 7),
	(0, 281, 30, 21),
	(0, 276, 31, 21),
	(0, 282, 32, 21),
	(0, 293, 33, 7),
	(0, 266, 34, 11),
	(0, 294, 35, 7),
	(0, 298, 36, 12),
	(0, 295, 37, 12),
	(0, 294, 38, 12),
	(0, 297, 39, 7),
	(0, 317, 40, 7),
	(0, 315, 41, 21);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
