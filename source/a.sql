-- --------------------------------------------------------
-- ホスト:                          127.0.0.1
-- サーバーのバージョン:                   8.0.26 - MySQL Community Server - GPL
-- サーバー OS:                      Win64
-- HeidiSQL バージョン:               12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- e5 のデータベース構造をダンプしています
CREATE DATABASE IF NOT EXISTS `e5` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `e5`;

--  テーブル e5.check_results の構造をダンプしています
CREATE TABLE IF NOT EXISTS `check_results` (
  `userid` int NOT NULL,
  `check_results_id` int NOT NULL AUTO_INCREMENT,
  `stress_score` int NOT NULL,
  `env_score` int NOT NULL,
  `body_score` int NOT NULL,
  `life_score` int NOT NULL,
  `question1` int NOT NULL,
  `question2` int NOT NULL,
  `question3` int NOT NULL,
  `question4` int NOT NULL,
  `question5` int NOT NULL,
  `question6` int NOT NULL,
  `question7` int NOT NULL,
  `question8` int NOT NULL,
  `question9` int NOT NULL,
  `question10` int NOT NULL,
  `created_at` datetime NOT NULL,
  `stress_factor` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`check_results_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- エクスポートするデータが選択されていません

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
