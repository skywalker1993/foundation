insert into user (city, province, created_date, last_modified_date, password, phone, username, version) values ('guangzhou', 'guangdong', '1993-06-08 06:00:00', '1993-06-08 06:00:00', 'password1', '13288888888', 'huang', 0);
insert into user (city, province, created_date, last_modified_date, password, phone, username, version) values ('beijing', 'beijing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'password2', '13366666666', 'deng', 0);
insert into user (city, province, created_date, last_modified_date, password, phone, username, version) values ('chengdu', 'sichuan', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'password3', '13900000000', 'chen', 0);
insert into user (city, province, created_date, last_modified_date, password, phone, username, version) values ('xian', 'shanxi', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'password4', '18177777777', 'luo', 0);
insert into user (city, province, created_date, last_modified_date, password, phone, username, version) values ('xian', 'shanxi', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'password5', '15311111111', 'he', 0);
insert into user (city, province, created_date, last_modified_date, password, phone, username, version) values ('shenzhen', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'password6', '15711111111', 'zhang', 0);
insert into user (city, province, created_date, last_modified_date, password, phone, username, version) values (null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'password7', '18711111111', 'wu', 0);

insert into clan (name) values ('huang');

insert into person (clan_id, name) values (1, 'he');
insert into person (clan_id, name) values (1, 'tu');
