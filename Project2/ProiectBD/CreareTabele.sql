CREATE DATABASE IF NOT EXISTS proieeect;
use proieeect;

DROP TABLE IF EXISTS utilizator;
CREATE TABLE IF NOT EXISTS utilizator (
	id_user int not null primary key,
    CNP varchar(14) NOT NULL,
    nume varchar(30) NOT NULL,
    prenume varchar(30) NOT NULL,
    adresa varchar(50) NOT NULL,
    email varchar(30) NOT NULL,
    parola varchar(30) NOT NULL,
    nr_telefon varchar(30) NOT NULL,
    cont_iban varchar(30) NOT NULL,
    nr_cont varchar(30) NOT NULL,
    nr_contact varchar(30) NOT NULL,
    tip varchar(30) NOT NULL
);

DROP TABLE IF EXISTS profesor;
CREATE TABLE IF NOT EXISTS profesor (
	id_profesor int not null primary key,
    nr_min_ore int, 
    nr_maxim_ore int,
    cursuri varchar(30),
    departament varchar(30),
    FOREIGN KEY (id_profesor) REFERENCES utilizator(id_user)
);

DROP TABLE IF EXISTS materie;
CREATE TABLE IF NOT EXISTS materie (
	id_materie int not null primary key,
	profesor_id int,
    denumire_materie varchar(20),
    FOREIGN KEY (profesor_id) REFERENCES profesor(id_profesor),
    curs BOOLEAN,
    seminar BOOLEAN,
    laborator BOOLEAN
);

DROP TABLE IF EXISTS grup_studiu;
CREATE TABLE IF NOT EXISTS grup_studiu (
	id_grup int not null primary key,
    materie_id int,
    FOREIGN KEY (materie_id) REFERENCES materie(id_materie)
);

DROP TABLE IF EXISTS student;
CREATE TABLE IF NOT EXISTS student (
	id_student int not null primary key,
    an_studiu varchar(10) NOT NULL,
    grup_id int,
    FOREIGN KEY (id_student) REFERENCES utilizator(id_user),
    FOREIGN KEY (grup_id) REFERENCES grup_studiu(id_grup)
);

DROP TABLE IF EXISTS administrator;
CREATE TABLE IF NOT EXISTS administrator (
	id_administrator int not null primary key,
    tip varchar(20),
    FOREIGN KEY (id_administrator) REFERENCES utilizator(id_user)
);

DROP TABLE IF EXISTS inscriere;
CREATE TABLE IF NOT EXISTS inscriere (
	student_id int,
    FOREIGN KEY (student_id) REFERENCES student(id_student),
    materie_id int,
	FOREIGN KEY (materie_id) REFERENCES materie(id_materie)
);

DROP TABLE IF EXISTS activitate;
CREATE TABLE IF NOT EXISTS activitate (
	id_activitate int not null primary key,
	materie_id int,
	FOREIGN KEY (materie_id) REFERENCES materie(id_materie),
    tip_activitate varchar(20) NOT NULL,
    cologviu BOOLEAN,
    examen BOOLEAN,
    procent varchar(20) NOT NULL,
    nr_maxim_participanti int
);

DROP TABLE IF EXISTS inscriere_grup;
CREATE TABLE IF NOT EXISTS inscriere_grup (
	student_id int,
    grup_id int,
    FOREIGN KEY (student_id) REFERENCES student(id_student),
    FOREIGN KEY (grup_id) REFERENCES grup_studiu(id_grup)
);

DROP TABLE IF EXISTS mesaj;
CREATE TABLE IF NOT EXISTS mesaj (
	id_mesaj int not null primary key,
    continut varchar(50) NOT NULL,
	student_id int,
    grup_id int,
    FOREIGN KEY (student_id) REFERENCES student(id_student),
    FOREIGN KEY (grup_id) REFERENCES grup_studiu(id_grup)
);

DROP TABLE IF EXISTS activitate_grup;
CREATE TABLE IF NOT EXISTS activitate_grup (
	id_activitate_grup int not null primary key,
    descriere_activitate varchar(50) NOT NULL,
    data_activitate date,
    ora_inceput_activitate time,
    durata_activitate time,
    nr_min_participanti int,
    durata_exp_accept time,
	student_id int,
    grup_id int,
    FOREIGN KEY (student_id) REFERENCES student(id_student),
    FOREIGN KEY (grup_id) REFERENCES grup_studiu(id_grup)
);

DROP TABLE IF EXISTS note_activitate;
CREATE TABLE IF NOT EXISTS note_activitate(
	nota float,
	student_id int,
    activitate_id int,
    FOREIGN KEY (student_id) REFERENCES student(id_student),
    FOREIGN KEY (activitate_id) REFERENCES activitate(id_activitate)
);

DROP TABLE IF EXISTS note_finale;
CREATE TABLE IF NOT EXISTS note_finale(
	nota_finala float,
	student_id int,
    materie_id int,
    FOREIGN KEY (student_id) REFERENCES student(id_student),
    FOREIGN KEY (materie_id) REFERENCES materie(id_materie)
);