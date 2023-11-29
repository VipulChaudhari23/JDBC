create database bank;
use bank;
create table customer(id int auto_increment primary key, login varchar(255), passhash int, name varchar(255), phone varchar(255), email varchar(255), registrationDate date);
desc customer;
select * from customer;

alter table customer 