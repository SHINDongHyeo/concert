--show databases;

USE playdata;
-- 재능기부 프로젝트
DROP TABLE IF EXISTS concert;

-- 재능 기부자
DROP TABLE IF EXISTS singer;

-- 재능 수혜자
DROP TABLE IF EXISTS writer;
 
-- 재능기부 정보
DROP TABLE IF EXISTS song;

-- 이름, 데뷔, 
CREATE TABLE singer (
       singer_id          	VARCHAR(20)  PRIMARY KEY,
       name               	VARCHAR(20) NOT NULL
);

CREATE TABLE writer (
       writer_id        		VARCHAR(20) PRIMARY KEY,
       name                		VARCHAR(20) NOT NULL
);


CREATE TABLE song (
       song_id          	VARCHAR(50) PRIMARY KEY,
       song_name      VARCHAR(50) NOT NULL,
       genre  	VARCHAR(200) NOT NULL
);

CREATE TABLE concert (
	   concert_id     		INT AUTO_INCREMENT PRIMARY KEY,
	   location 		VARCHAR(50) NOT NULL,
       song_id           			VARCHAR(50) NOT NULL,       
       singer_id          				VARCHAR(20) NOT NULL,
       writer_id           				VARCHAR(20) NOT NULL, 
       concert_detail      			VARCHAR(100) NOT NULL
);

ALTER TABLE concert AUTO_INCREMENT = 10000;
ALTER TABLE concert  ADD FOREIGN KEY (song_id) REFERENCES song  (song_id);
ALTER TABLE concert ADD FOREIGN KEY (singer_id)  REFERENCES singer  (singer_id);
ALTER TABLE concert ADD FOREIGN KEY (writer_id) REFERENCES writer  (writer_id);
