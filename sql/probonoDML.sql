use playdata;

-- singer insert[가수 저장]
insert into singer (singer_name, detail) values('비틀즈', '역사상 최고의 롹 밴드로, 존 레논, 폴 매카트니, 조지해리슨, 링고스타로 구성' );
insert into singer (singer_name, detail) values('아이유', '천재 가수');
insert into singer (singer_name, detail) values('퀸' '록의 정석');
insert into singer (singer_name, detail) values('마이클잭슨', '팝의 황제');
insert into singer (singer_name, detail) values('아이유', '천재 가수');
insert into singer (singer_name, detail) values('윈터', '에스파 멤버');
insert into singer (singer_name, detail) values('카리나', '에스파 멤버2');
insert into singer (singer_name, detail) values('채연', '르세라핌 멤버');
insert into singer (singer_name, detail) values('Alfred Banks', '힙합 가수');
insert into singer (singer_name, detail) values('Hippie ', '힙합 가수');

SELECT * FROM singer;
 
-- concert insert[콘서트 저장]
insert into concert (concert_name, location, date, max_seats, contents) values('뉴욕 롹 콘서트', '뉴욕', '2022-07-04', 1000, '전설의 레전드, 비틀즈.');
insert into concert (concert_name, location, date, max_seats, contents) values('파리 팝 콘서트', '파리', '2022-11-12', 2000, '레전드의 전설, 잭슨을 만나다.');
insert into concert (concert_name, location, date, max_seats, contents) values('샌프란시스코 롹 앤 팝', '샌프란시스코', '2022-10-17', 3000, '왕의 귀환, queen과 그 외');
insert into concert (concert_name, location, date, max_seats, contents) values('워터밤', '서울', '2022-06-30', '500', '한국 물총놀이');
insert into concert (concert_name, location, date, max_seats, contents) values('싸이흠뻑쇼', '강남', '2022-07-15', '1000', '물놀이');
insert into concert (concert_name, location, date, max_seats, contents) values('Marcel p Black & Alfred Banks Live', 'LA', '2022-07-16', '100', 'The Freetown Boom Boom Room');
insert into concert (concert_name, location, date, max_seats, contents) values('Hippie Death Cult', 'Paris', '2022-07-26', '50', '300 McKinley St, Lafayette, LA 70501');


SELECT * FROM concert;

-- concert_singer insert[콘서트 - 싱어 맵핑 저장]
insert into concert_singer (concert_id, singer_id) values(1, 1);
insert into concert_singer (concert_id, singer_id) values(1, 3);
insert into concert_singer (concert_id, singer_id) values(2, 1);
insert into concert_singer (concert_id, singer_id) values(2, 2);
insert into concert_singer (concert_id, singer_id) values(3, 2);
insert into concert_singer (concert_id, singer_id) values(1, 2);
insert into concert_singer (concert_id, singer_id) values(1, 3);
insert into concert_singer (concert_id, singer_id) values(2, 1);
insert into concert_singer (concert_id, singer_id) values(2, 4);
insert into concert_singer (concert_id, singer_id) values(4, 6);


SELECT * FROM concert_singer;

-- Order insert[주문 저장]
insert into orders (customer_name, customer_email, amount, concert_id) values('유정원', 'yoo516@naver.com', 1, 2);
insert into orders (customer_name, customer_email, amount, concert_id) values('최영준', 'dkwk@naver.com', 3, 1);
insert into orders (customer_name, customer_email, amount, concert_id) values('임주완', 'pgeke235@naver.com', 6, 3);
insert into orders (customer_name, customer_email, amount, concert_id) values('신동혁', 'wgmeww66@naver.com', 6, 2);
insert into orders (customer_name, customer_email, amount, concert_id) values('핫산', 'opkefe566@naver.com', 2, 1);
insert into orders (customer_name, customer_email, amount, concert_id) values('강사님', 'qgnewk12@naver.com', 4, 1);
insert into orders (customer_name, customer_email, amount, concert_id) values('유재석', 'oegkww33@naver.com', 2, 3);
insert into orders (customer_name, customer_email, amount, concert_id) values('김연아', 'yak@frozen.co.kr', 5, 3);
insert into orders (customer_name, customer_email, amount, concert_id) values('신동엽', 'dy@naver.com', 3, 2);
insert into orders (customer_name, customer_email, amount, concert_id) values('최이슬', 'rain@daum.net', 1, 1);
insert into orders (customer_name, customer_email, amount, concert_id) values('황정민', 'paris@naver.com', 4, 3);

select * from orders


commit;