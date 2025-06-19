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

--  テーブル e5.check_comments の構造をダンプしています
CREATE TABLE IF NOT EXISTS `check_comments` (
  `comments_advice_id` int NOT NULL AUTO_INCREMENT,
  `comments` varchar(30) NOT NULL,
  `advice` varchar(30) NOT NULL,
  `pet_check_comments` varchar(50) NOT NULL,
  `trends` varchar(50) NOT NULL,
  `min_score` int NOT NULL,
  `max_score` int NOT NULL,
  PRIMARY KEY (`comments_advice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- テーブル e5.check_comments: ~0 rows (約) のデータをダンプしています

--  テーブル e5.check_questions の構造をダンプしています
CREATE TABLE IF NOT EXISTS `check_questions` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `questions1` varchar(50) NOT NULL,
  `questions_category` varchar(50) NOT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- テーブル e5.check_questions: ~0 rows (約) のデータをダンプしています

--  テーブル e5.check_results の構造をダンプしています
CREATE TABLE IF NOT EXISTS `check_results` (
  `userid` int NOT NULL,
  `check_results_id` int unsigned NOT NULL AUTO_INCREMENT,
  `stress_score` int NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- テーブル e5.check_results: ~2 rows (約) のデータをダンプしています
REPLACE INTO `check_results` (`userid`, `check_results_id`, `stress_score`, `question1`, `question2`, `question3`, `question4`, `question5`, `question6`, `question7`, `question8`, `question9`, `question10`, `created_at`, `stress_factor`) VALUES
	(1, 2, 47, 3, 4, 2, 5, 1, 4, 2, 5, 3, 1, '2025-06-16 15:28:42', 'lack_of_sleep'),
	(1, 3, 48, 2, 1, 3, 5, 1, 4, 5, 3, 2, 1, '2025-06-17 16:44:42', 'lack_of_sleep');

--  テーブル e5.login_bonus_history の構造をダンプしています
CREATE TABLE IF NOT EXISTS `login_bonus_history` (
  `user_id` int NOT NULL,
  `bonus_date` datetime NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `bonus_date` (`bonus_date`),
  CONSTRAINT `fk_login_bonus_history_userid` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- テーブル e5.login_bonus_history: ~0 rows (約) のデータをダンプしています

--  テーブル e5.login_rewards の構造をダンプしています
CREATE TABLE IF NOT EXISTS `login_rewards` (
  `userid` int NOT NULL,
  `login_date` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- テーブル e5.login_rewards: ~0 rows (約) のデータをダンプしています

--  テーブル e5.one_month_trends の構造をダンプしています
CREATE TABLE IF NOT EXISTS `one_month_trends` (
  `omt_id` int NOT NULL AUTO_INCREMENT,
  `omt` varchar(50) NOT NULL,
  PRIMARY KEY (`omt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- テーブル e5.one_month_trends: ~0 rows (約) のデータをダンプしています

--  テーブル e5.one_week_trends の構造をダンプしています
CREATE TABLE IF NOT EXISTS `one_week_trends` (
  `owt_id` int NOT NULL AUTO_INCREMENT,
  `owt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `owt_comments` varchar(50) NOT NULL,
  `owt_stress_factor` varchar(50) NOT NULL,
  PRIMARY KEY (`owt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- テーブル e5.one_week_trends: ~0 rows (約) のデータをダンプしています

--  テーブル e5.pet_comments の構造をダンプしています
CREATE TABLE IF NOT EXISTS `pet_comments` (
  `pet_comments_id` int NOT NULL AUTO_INCREMENT,
  `pet_comments` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pet_comments_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- テーブル e5.pet_comments: ~0 rows (約) のデータをダンプしています

--  テーブル e5.useritems の構造をダンプしています
CREATE TABLE IF NOT EXISTS `useritems` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `petitems1` int NOT NULL DEFAULT '0',
  `petitems2` int NOT NULL DEFAULT '0',
  `petitems3` int NOT NULL DEFAULT '0',
  `petitems4` int NOT NULL DEFAULT '0',
  `petitems5` int NOT NULL DEFAULT '0',
  `petitems6` int NOT NULL DEFAULT '0',
  `petitems7` int NOT NULL DEFAULT '0',
  `petitems8` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- テーブル e5.useritems: ~0 rows (約) のデータをダンプしています

--  テーブル e5.users の構造をダンプしています
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ハッシュ化して利用する',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- テーブル e5.users: ~0 rows (約) のデータをダンプしています

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
