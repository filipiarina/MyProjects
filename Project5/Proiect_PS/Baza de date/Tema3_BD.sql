CREATE DATABASE IF NOT EXISTS BDtema3;
use BDtema3;

DROP TABLE IF EXISTS administrator;
CREATE TABLE IF NOT EXISTS administrator(
	admin_id int AUTO_INCREMENT PRIMARY KEY,
    nume varchar(30) NOT NULL,
    email varchar(30) NOT NULL,
    parola varchar(30) NOT NULL,
    telefon varchar(11) NOT NULL
);

DROP TABLE IF EXISTS coordonator;
CREATE TABLE IF NOT EXISTS coordonator(
	coordonator_id int AUTO_INCREMENT PRIMARY KEY,
    nume varchar(30) NOT NULL,
    email varchar(30) NOT NULL,
    parola varchar(30) NOT NULL
);

DROP TABLE IF EXISTS angajat;
CREATE TABLE IF NOT EXISTS angajat(
	angajat_id int AUTO_INCREMENT PRIMARY KEY,
    nume varchar(30) NOT NULL,
    email varchar(30) NOT NULL,
    parola varchar(30) NOT NULL,
    departament varchar(30) NOT NULL,
	aniExperienta int NOT NULL
);

DROP TABLE IF EXISTS locatieDeseu;
CREATE TABLE IF NOT EXISTS locatieDeseu(
	idLocatie int AUTO_INCREMENT PRIMARY KEY,
    adresaLocatie varchar(30) NOT NULL,
	tipDeseu varchar(30) NOT NULL,
    stadiu varchar(30) NOT NULL
);

DROP TABLE IF EXISTS locatieAngajat;
CREATE TABLE IF NOT EXISTS locatieAngajat(
	idLocatieAngajat int AUTO_INCREMENT PRIMARY KEY,
    idLocatie int NOT NULL,
    angajat_id int NOT NULL,
    FOREIGN KEY (idLocatie) REFERENCES locatieDeseu(idLocatie),
    FOREIGN KEY (angajat_id) REFERENCES angajat(angajat_id)
);

INSERT INTO `angajat` VALUES (1,'Pop Ana','popana@yahoo.com','1212','Departamentul de finante',6),
(2,'Antonese Matei','antonesematei@yahooo.com','2345','Manager',10),
(3,'Mateiescu Andrei','mateiescuandrei@yahoo.com','3456','Departamentul de colectare',3);

INSERT INTO `administrator` VALUES (1,'Filip Iarina','filipiarina@yahoo.com','1234','0756732598');

INSERT INTO `coordonator` VALUES (1,'Popescu Natan','popescunatan@yahoo.com','4567'),
 (2,'Filip Iulia','filipiulia@yahoo.com','5678');
 
  INSERT INTO `locatieDeseu` VALUES (1,'Marasti','Sticla','colectare'),
(2,'Manastur','Plastic si metal','colectare'),
(3,'Floresti','Hartie si carton','adunare'),
(4,'Marasti','Reziduale','colectare');

 INSERT INTO `locatieDeseu` VALUES (5,'Marasti','Plastic si metal','colectare'),
(6,'Manastur','Hartie si carton','colectare'),
(7,'Manastur','Reziduale','adunare');

 INSERT INTO `locatieDeseu` VALUES (8,'Floresti','Sticla','colectare'),
(9,'Floresti','Plastic si metal','colectare'),
(10,'Floresti','Hartie si carton','adunare'),
(11,'Marasti','Reziduale','colectare');

INSERT INTO `locatieAngajat` VALUES (1,2,3);
 