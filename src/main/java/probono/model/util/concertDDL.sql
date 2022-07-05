--show databases;

USE playdata;
-- 재능기부 프로젝트
DROP TABLE IF EXISTS concert_singer;

-- 재능 기부자
DROP TABLE IF EXISTS singer;
 
-- 재능기부 정보
DROP TABLE IF EXISTS orders;

-- 재능 수혜자
DROP TABLE IF EXISTS concert;
 
CREATE TABLE singer (
       singer_id          	int AUTO_INCREMENT PRIMARY KEY,
       singer_name               	VARCHAR(20) NOT NULL,
       detail                		VARCHAR(50) NOT NULL
);

CREATE TABLE concert (
       concert_id        		int AUTO_INCREMENT PRIMARY KEY,
       concert_name                		VARCHAR(50) NOT NULL,
       location                		VARCHAR(20) NOT NULL,
       date                		date NOT NULL,
       max_seats                		int NOT NULL,
       contents                		VARCHAR(50) NOT NULL
);


CREATE TABLE orders (
       order_id          	int AUTO_INCREMENT PRIMARY KEY,
       customer_name                		VARCHAR(20) NOT NULL,
       customer_email                		VARCHAR(20) NOT NULL,
       amount                		int,
       concert_id                		int
);

CREATE TABLE concert_singer (
	   concert_singer_id     		int AUTO_INCREMENT PRIMARY KEY,
	   concert_id 					int,     
       singer_id          			int
);

ALTER TABLE orders  ADD FOREIGN KEY (concert_id) REFERENCES concert  (concert_id);
ALTER TABLE concert_singer ADD FOREIGN KEY (concert_id) REFERENCES concert  (concert_id);
ALTER TABLE concert_singer ADD FOREIGN KEY (singer_id) REFERENCES singer  (singer_id);
