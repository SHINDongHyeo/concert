use playdata;

-- activist insert[재능 기부자 저장]
insert into singer values('singer1', '나의사');
insert into singer values('singer2', '오드리');
insert into singer values('singer3', '키다리');

SELECT * FROM singer;
 
-- recipient insert[재능 수혜자 저장]
insert into writer values('writer1', '나아토피');
insert into writer values('writer2', '나문화');
insert into writer values('writer3', '나멘토');

SELECT * FROM writer;

-- probono insert[재능기부 정보 저장]
insert into song values('schweitzer', '슈바이처 프로젝트', '의사, ');
insert into song values('audreyHepbun', '오드레햅번 프로젝트', '예술가');
insert into song values('daddyLongLegs', '키다리아저씨 프로젝트', '결연');

SELECT * FROM song;

-- probono_project insert[재능 기부 프로젝트 저장]
insert into concert (location, song_id, singer_id, writer_id, concert_detail) values('슈바이처 프로젝트', 'schweitzer', 'giver1', 'receivePeople1', '아토피 무상 치료');
insert into concert (location, song_id, singer_id, writer_id, concert_detail) values('오드리햅번 프로젝트', 'audreyHepbun', 'giver2', 'receivePeople2', '무료컷팅');
insert into concert (location, song_id, singer_id, writer_id, concert_detail) values('키다리아저씨 프로젝트', 'schweitzer', 'giver3', 'receivePeople3', '장학금지원');

SELECT * FROM concert;


commit;
