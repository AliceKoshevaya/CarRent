INSERT INTO roles VALUES(default, 'administrator');
INSERT INTO roles VALUES(default, 'manager');
INSERT INTO roles VALUES(default, 'user');

INSERT INTO brand VALUES(default,'BMV');
INSERT INTO brand VALUES(default,'Audi');
INSERT INTO brand VALUES(default,'Chevrolet');
INSERT INTO brand VALUES(default,'Toyota');
INSERT INTO brand VALUES(default,'Volkswagen');
INSERT INTO brand VALUES(default,'Hyundai');
INSERT INTO brand VALUES(default,'Suzuki');

INSERT INTO classes VALUES(default,'Econom', 1.5);
INSERT INTO classes VALUES(default,'Middle', 2);
INSERT INTO classes VALUES(default,'Premium', 2.5);
INSERT INTO classes VALUES(default,'Business', 3);
INSERT INTO classes VALUES(default,'Lux', 4);

INSERT INTO bill VALUES(default,true, 'rent', 700, '2018.09.30');
INSERT INTO bill VALUES(default,true, 'crash', 500, '2018.10.01');
INSERT INTO bill VALUES(default,false, 'rent', 900, '2018.10.05');

INSERT INTO users VALUES(default, 'alice777', 'qwerty12345', 'Alice', 'Koshova', 'Alexandrovna', 'BK345456', 'Gorlovka', '2011.06.12', 1);
INSERT INTO users VALUES(default, 'alexKing', 'king1998', 'Alex', 'Smith', 'Sanders', 'BK777888', 'Kiev', '2001.09.11', 2);
INSERT INTO users VALUES(default, 'brianLux', 'catcat66', 'Brian', 'Lux', 'Carlton', 'BK347496', 'Kharkiv', '2000.09.05', 3);

INSERT INTO cars VALUES(default, 'Chevrolet Spark', '600', 'XA6787CH', 'taken', 3, 2);
INSERT INTO cars VALUES(default, 'Suzuki Vitara III', '700', 'XA0004CH', 'repairs', 7, 1);
INSERT INTO cars VALUES(default, 'Volkswagen Passat B8', '900', 'XA7778CH', 'stock', 5, 3);
INSERT INTO cars VALUES(default, 'Hyundai Santa Fe Grand', '800', 'XA9604CH', 'taken', 6, 4);

INSERT INTO orders VALUES(default, true, 'rent', '2018.09.02 12:30:00', '2018.09.02 16:30:00', 1, 1, 1);
INSERT INTO orders VALUES(default, false, 'crash', '2018.10.22 12:00:00', '2018.10.22 18:00:00', 2, 2, 2);

SELECT * from users;
SELECT * from bill;
SELECT * from classes;
SELECT * from brand;
SELECT * from users;
SELECT * from cars;