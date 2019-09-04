connect 'jdbc:derby:Finances;create=true';
drop table Accounts;
create table Accounts(acctNum int primary key, surname 
varchar(15), firstNames varchar(25), balance real);
insert into Accounts values(123456, 'Black', 'James 
Michael', 123.45);
INSERT INTO Accounts  VALUES (234567,'Smith','John James',752.85);
INSERT INTO Accounts  VALUES (333333,'Jones','Sally',5000);
INSERT INTO Accounts  VALUES (444444,'White','Peter',45);
select * from Accounts;
SELECT acctNum, balance FROM Accounts;
SELECT * FROM Accounts WHERE balance >= 0 AND balance <= 1000 order by balance desc;
SELECT * FROM Accounts WHERE surname < 'Jones';
UPDATE Accounts SET surname = 'Bloggs',firstNames = 'Fred Joseph' WHERE acctNum = 123456;
update Accounts set balance=999.99 where acctNum=123456; 
DELETE FROM Accounts WHERE balance < 100;
delete from Accounts where acctNum = 234567;
disconnect; 
connect 'jdbc:derby:Finances';
select * from Accounts;
exit;
