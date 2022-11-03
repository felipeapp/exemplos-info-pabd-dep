drop database if exists exemplo_04_jdbc;
create database exemplo_04_jdbc;

use exemplo_04_jdbc;

create table usuario (
    id int primary key auto_increment not null,
    nome varchar(200) not null,
    pin int not null,
    data_nascimento date not null,
    data_acesso datetime not null
);

select * from usuario;
