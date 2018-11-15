INSERT INTO roles VALUES(default, 'administrator');
INSERT INTO roles VALUES(default, 'manager');
INSERT INTO roles VALUES(default, 'user');

INSERT INTO brand VALUES(default,'BMW');
INSERT INTO brand VALUES(default,'Audi');
INSERT INTO brand VALUES(default,'Chevrolet');
INSERT INTO brand VALUES(default,'Toyota');
INSERT INTO brand VALUES(default,'Volkswagen');
INSERT INTO brand VALUES(default,'Hyundai');
INSERT INTO brand VALUES(default,'Suzuki');
INSERT INTO brand VALUES(default,'Bugatti');
INSERT INTO brand VALUES(default,'Aston Martin');
INSERT INTO brand VALUES(default,'Bentley');
INSERT INTO brand VALUES(default,'Cadillac');
INSERT INTO brand VALUES(default,'Ferrari');
INSERT INTO brand VALUES(default,'Ford');


INSERT INTO classes VALUES(default,'Econom', 1.5);
INSERT INTO classes VALUES(default,'Middle', 2);
INSERT INTO classes VALUES(default,'Premium', 2.5);
INSERT INTO classes VALUES(default,'Business', 3);
INSERT INTO classes VALUES(default,'Lux', 4);

INSERT INTO bill VALUES(default,true, 'rent', 700, '2018-09-30 16:30:00',1);
INSERT INTO bill VALUES(default,true, 'crash', 500, '2018-10-01 16:30:00',2);

INSERT INTO users VALUES(default, 'alice777', 'qwerty12345', 'Alice', 'Koshova', 'Alexandrovna', 'BK345456', 'Gorlovka 2011.06.12', false, 1);
INSERT INTO users VALUES(default, 'alexKing', 'king1998', 'Alex', 'Smith', 'Sanders', 'BK777888', 'Kiev 2001.09.11', false, 2);
INSERT INTO users VALUES(default, 'brianLux', 'catcat66', 'Brian', 'Lux', 'Carlton', 'BK347496', 'Kharkiv 2000.09.05', false, 3);
INSERT INTO users VALUES(default, 'user', 'Users777', 'Brian', 'Lux', 'Carlton', 'BK346696', 'Kharkiv 2000.09.05', false, 3);
INSERT INTO users VALUES(default, 'manager', 'Manager777', 'Alex', 'Smith', 'Sanders', 'BK117888', 'Kiev 2001.09.11', false, 2);
INSERT INTO users VALUES(default, 'administrator', 'Admin777', 'Martin', 'Abrams', 'Carlton', 'BK997496', 'Kharkiv 2000.09.05', false, 1);

INSERT INTO cars VALUES(default, 'Spark', '600', 'XA6787CH', 2, 3, 2);
INSERT INTO cars VALUES(default, 'Vitara III', '700', 'XA0004CH', 1, 7, 1);
INSERT INTO cars VALUES(default, 'Passat B8', '900', 'XA7778CH', 3, 5, 3);
INSERT INTO cars VALUES(default, 'Passat B8', '900', 'XA7132CМ', 3, 5, 3);
INSERT INTO cars VALUES(default, 'CHIRON SPORT', '1500', 'XA7777CH', 2, 8, 4);
INSERT INTO cars VALUES(default, 'X6', '1100', 'XA9688CH', 2, 1, 4);
INSERT INTO cars VALUES(default, 'X5', '1000', 'XA1880ЕH', 2, 1, 4);
INSERT INTO cars VALUES(default, 'Q3', '1000', 'XA9604CH', 2, 2, 3);
INSERT INTO cars VALUES(default, 'Golf GTI', '900', 'XA6574CH', 2, 5, 3);
INSERT INTO cars VALUES(default, 'Highlander', '800', 'XA9609CH', 2, 4, 2);
INSERT INTO cars VALUES(default, 'New Tucson', '1200', 'XA0704CH', 2, 6, 3);
INSERT INTO cars VALUES(default, 'DB11', '1300', 'XA0781CH', 1, 9, 4);
INSERT INTO cars VALUES(default, 'Mulsanne', '1200', 'XA1181CH', 1, 10, 4);
INSERT INTO cars VALUES(default, 'KUGA', '600', 'XA0687CH', 1, 13, 1);

INSERT INTO orders VALUES(default, true, 1, '2018-09-02 12:30:00', '2018-09-02 16:30:00', NULL, 1, 1);
INSERT INTO orders VALUES(default, false, 2, '2018-10-22 12:00:00', '2018-10-12 18:00:00', NULL, 2, 2);

SELECT * from users;
SELECT * from bill;
SELECT * from classes;
SELECT * from brand;
SELECT * from users;
SELECT * from cars;
SELECT * from orders;
