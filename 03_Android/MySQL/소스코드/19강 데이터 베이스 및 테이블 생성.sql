create database test_db
character set = 'utf8'
collate = 'utf8_general_ci';

use test_db;

create table test_table1(
data1 int(10),
data2 varchar(10),
data3 float(10, 2)
);

desc test_table1;

select * from test_table1;

use employees;

select * from departments;

create table dept1
as
select * from departments;

desc dept1;
select * from dept1;

create table dept2
as
select * from departments where 1=0;

desc dept2;
select * from dept2;

create table dept3
as
select dept_no from departments;

select * from dept3;













