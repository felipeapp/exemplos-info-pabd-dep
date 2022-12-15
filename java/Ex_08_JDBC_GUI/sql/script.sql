drop database if exists mercado;
create database mercado;

use mercado;

create table produto (
    id int primary key not null auto_increment,
    nome varchar(200) not null unique,
    preco decimal(10,2) not null,
    quantidade int not null,
    medida varchar(20) not null,
    cadastro datetime not null default now()
);

select * from produto;
