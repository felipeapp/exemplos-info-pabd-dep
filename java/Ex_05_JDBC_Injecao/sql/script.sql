drop database if exists exemplo_05_jdbc;
create database exemplo_05_jdbc;

use exemplo_05_jdbc;

create table usuario (
    id int primary key auto_increment not null,
    nome varchar(200) not null,
    pin int not null,
    data_nascimento date not null,
    data_acesso datetime not null
);

select * from usuario;
