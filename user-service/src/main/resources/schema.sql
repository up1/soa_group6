DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS
(
    id int(11) NOT NULL AUTO_INCREMENT,
    firstname varchar(100) NOT NULL,
    lastname varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO demo.users values(1,'Suchaj','Jongprasit');
INSERT INTO demo.users values(2,'f2','l2');
INSERT INTO demo.users values(3,'f3','l3');
INSERT INTO demo.users values(4,'f4','l4');
INSERT INTO demo.users values(5,'f5','l5');
INSERT INTO demo.users values(6,'f6','l6');
INSERT INTO demo.users values(7,'f7','l7');
INSERT INTO demo.users values(8,'f8','l8');

