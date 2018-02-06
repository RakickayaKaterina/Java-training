CREATE DATABASE `Equipment shop` DEFAULT CHARACTER SET utf8 ;
USE `Equipment shop`;
CREATE TABLE `Product` (
	maker varchar(10) NOT NULL,
    model varchar(50),
    type varchar(50) CHECK ( type IN ('PC', 'Laptop', 'Printer') ),
	PRIMARY KEY (model)
	
);

CREATE TABLE `Laptop`(
	code int auto_increment,
    model varchar(50) NOT NULL,
    speed smallint NOT NULL,
    ram smallint NOT NULL,
    hd real NOT NULL,
    price int,
    screen tinyint NOT NULL,
	PRIMARY KEY (code),
    FOREIGN KEY (model) REFERENCES product(model)
);

CREATE TABLE `PC` (
	code int auto_increment,
    model varchar(50) NOT NULL,
    speed smallint NOT NULL,
    ram smallint NOT NULL,
    hd real NOT NULL,
    cd varchar(10) NOT NULL,
    price int,
    PRIMARY KEY (code),
    FOREIGN KEY (model) REFERENCES product(model)
);



CREATE TABLE Printer(
	code int auto_increment,
    model varchar(50) NOT NULL,
    color char(1) DEFAULT 'n' CHECK (color IN ('y', 'n')),
    type varchar(10) NOT NULL CHECK (type IN ('Laser', 'Jet', 'Matrix')),
    price int NOT NULL,
    PRIMARY KEY (code),
    FOREIGN KEY (model) REFERENCES product(model)
);
