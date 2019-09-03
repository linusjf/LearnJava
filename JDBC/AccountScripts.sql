connect 'jdbc:derby:Finances;create=true';
create table Accounts(acctNum int primary key, surname 
varchar(15), firstNames varchar(25), balance real);
insert into Accounts values(123456, 'Black', 'James 
Michael', 123.45);
select * from Accounts; 
update Accounts set balance=999.99 where acctNum=123456; 
delete from Accounts where acctNum = 234567;
disconnect; 
connect 'jdbc:derby:Finances';
exit;
