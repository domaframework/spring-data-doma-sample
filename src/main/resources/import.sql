create table person(id long identity primary key, first_name varchar(20), last_name varchar(20));
insert into person (id, first_name, last_name) values(1, 'hoge', 'foo');