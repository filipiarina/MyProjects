CREATE DATABASE IF NOT EXISTS TemaTP3;
use TemaTP3;

DROP TABLE IF EXISTS clients;

CREATE TABLE IF NOT EXISTS clients(
	id int not null primary key,
    nume varchar(15) NOT NULL,
    address varchar(40) NOT NULL
);

DROP TABLE IF EXISTS orders;

CREATE TABLE IF NOT EXISTS orders(
	id int not null primary key,
    id_product int NOT NULL,
    id_client int NOT NULL,
    num_products varchar(15) NOT NULL
);

DROP TABLE IF EXISTS products;

CREATE TABLE IF NOT EXISTS products(
	id int not null primary key,
    price float NOT NULL,
    nume varchar(15) NOT NULL,
    details varchar(30) NOT NULL,
    quantity int NOT NULL
);



INSERT INTO clients (id, nume, address) VALUES 
(1,  'Pop Andreea','Dej, Str.Secundara'),
(2,  'Ciubotari Alex', 'Cluj-Napoca, Str.AlexandruCelBun'),
(3,  'Morosan Maria','Oradea, Str.Primaverii'),
(4,  'Popescu Ion', 'Cluj-Napoca, Str.Baritiu'),
(5,  'Neagu Carmen','Alba Iulia, Str.Lalelelor');

INSERT INTO orders (id, id_product, id_client, num_products) VALUES 
(1, 2, 1, 'Canapea'),
(2, 3, 3, 'Dulap'),
(3, 4, 4, 'Masa'),
(4, 1, 5,'Fotoliu'),
(5, 5, 2,'Covor');

INSERT INTO products (id, price, nume, details, quantity) VALUES 
(1, 2000.0, 'Canapea','Canapea de piele', 90),
(2, 700.0, 'Dulap', 'Dulap din stejar', 20),
(3, 950.0, 'Masa','Masa de lemn', 30),
(4, 1000.0,'Fotoliu','Fotoliu de piele', 15),
(5, 500.0,'Covor','Covor minimalist', 60);

