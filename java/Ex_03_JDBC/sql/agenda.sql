drop database if exists agenda;
create database agenda;

use agenda;

create table contato (
	id int primary key auto_increment not null,
	nome varchar(255) not null,
	email varchar(255) unique not null,
	idade int not null
);

insert into contato (nome, email, idade) values
	('Felipe', 'felipe@yahoo.com.br', 21),
	('Gabriel', 'gabriel@gmail.com', 30),
	('Laura', 'laura@gmail.com', 28),
	('Alberto', 'alberto@hotmail.com', 10),
	('Lucas', 'lucas@yahoo.com.br', 50);

select * from contato;
