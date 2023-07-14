show tables;

rename table test_table1 to test_table3;
show tables;

desc test_table3;
alter table test_table3 modify data1 int(100);
desc test_table3;

alter table test_table3 change data1 data10 int(200);
desc test_table3;

alter table test_table3 change data10 data5 int(200);
desc test_table3;

alter table test_table3 add data4 int(20);
desc test_table3;

alter table test_table3 drop data4;
desc test_table3;

show tables;
drop table test_table3;
show tables;






