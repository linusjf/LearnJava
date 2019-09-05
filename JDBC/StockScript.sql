connect 'jdbc:derby:Inventory;create=true';
drop table Stock;
CREATE TABLE Stock(stockCode INTEGER, 
 description VARCHAR(20), 
 unitPrice REAL, 
 currentLevel INTEGER, 
 reorderLevel INTEGER);
ALTER TABLE Stock ADD supplier VARCHAR(30);
INSERT INTO Stock VALUES(222222,'Rubber',0.57,315,200,'MRF');
INSERT INTO Stock VALUES(111111,'Pencil',0.32,1517,1200,'Nataraj');
INSERT INTO Stock VALUES(333333,'A4 pad narrow feint',1.45,121,150,'Deluxe');
INSERT INTO Stock VALUES(444444,'A4 pad wide feint',1.45,123,120,'Deluxe');
INSERT INTO Stock VALUES(555555,'Ruler',0.69,80,80,'Camlin');
INSERT INTO Stock VALUES(666666,'Stapler',2.65,72,60,'Kangaro');
SELECT * FROM Stock;
SELECT stockCode,currentLevel,reorderLevel FROM Stock;
SELECT * FROM Stock ORDER BY unitPrice DESC;
SELECT stockCode, unitPrice FROM Stock 
 WHERE unitPrice> 1 AND unitPrice <1.5;
DELETE FROM Stock WHERE stockCode = 222222;
UPDATE Stock SET unitPrice = 1 WHERE unitPrice < 1;
disconnect; 
connect 'jdbc:derby:Inventory';
select * from Stock;
exit;
