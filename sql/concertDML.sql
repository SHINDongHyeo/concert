use playdata;

insert into singer (singer_name, detail) values('아이유', '천재 가수');
insert into singer (singer_name, detail) values('윈터', '에스파 멤버');
insert into singer (singer_name, detail) values('카리나', '에스파 멤버2');
insert into singer (singer_name, detail) values('채연', '르세라핌 멤버');
insert into singer (singer_name, detail) values('Alfred Banks', '힙합 가수');
insert into singer (singer_name, detail) values('Hippie ', '힙합 가수');

SELECT * FROM singer;
 
insert into concert (concert_name, location, date, max_seats, contents) values('워터밤', '서울', '2022-06-30', '500', '한국 물총놀이');
insert into concert (concert_name, location, date, max_seats, contents) values('싸이흠뻑쇼', '강남', '2022-07-15', '1000', '물놀이');
insert into concert (concert_name, location, date, max_seats, contents) values('Marcel p Black & Alfred Banks Live', 'LA', '2022-07-16', '100', 'The Freetown Boom Boom Room');
insert into concert (concert_name, location, date, max_seats, contents) values('Hippie Death Cult', 'Paris', '2022-07-26', '50', '300 McKinley St, Lafayette, LA 70501');

SELECT * FROM concert;

insert into orders (customer_name, customer_email, amount, concert_id) values('김연아', 'yak@frozen.co.kr', 5, 3);
insert into orders (customer_name, customer_email, amount, concert_id) values('신동엽', 'dy@naver.com', 3, 2);
insert into orders (customer_name, customer_email, amount, concert_id) values('최이슬', 'rain@daum.net', 1, 1);
insert into orders (customer_name, customer_email, amount, concert_id) values('황정민', 'paris@naver.com', 4, 3);

SELECT * FROM orders;

insert into concert_singer (concert_id, singer_id) values(1, 2);
insert into concert_singer (concert_id, singer_id) values(1, 3);
insert into concert_singer (concert_id, singer_id) values(2, 1);
insert into concert_singer (concert_id, singer_id) values(2, 4);
insert into concert_singer (concert_id, singer_id) values(4, 6);

SELECT * FROM concert;


commit;
