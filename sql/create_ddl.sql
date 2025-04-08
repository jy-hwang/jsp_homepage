
DROP TABLE IF EXISTS notice;

CREATE TABLE `notice` (
  `no` bigint(20) NOT NULL AUTO_INCREMENT,
  `writer_id` varchar(20) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(2048) DEFAULT NULL,
  `hit` bigint(20) DEFAULT 0,
  `files` varchar(100) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NULL DEFAULT NULL,
  `deleted_date` timestamp NULL DEFAULT NULL,
  `is_disclose` tinyint(4) DEFAULT 0,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS comment;

CREATE TABLE `comment` (
  `no` bigint(20) NOT NULL AUTO_INCREMENT,
  `writer_id` varchar(20) DEFAULT NULL,
  `notice_no` bigint(20) DEFAULT NULL, 
  `content` varchar(512) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE OR REPLACE
VIEW notice_view
AS
SELECT n.no
     , count(c.NO) AS commentCount
     , n.writer_id AS writerId
     , n.title
     , hit
     , files
     , n.created_date AS createdDate
     , n.updated_date AS updatedDate
     , n.deleted_date AS deletedDate
     , is_disclose AS isDisclose
  FROM notice n
  LEFT OUTER JOIN comment c 
    ON n.NO = c.notice_no
 GROUP BY n.NO, n.writer_id, n.title, hit, files, is_disclose
 