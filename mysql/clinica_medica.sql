drop database if exists clinica_medica;
create database clinica_medica;

use clinica_medica;

create table pessoa (
    cpf char(11) primary key not null,
    nome varchar(150) not null
);

create table especialidade (
    nome varchar(50) primary key not null
);

create table sala (
    codigo int primary key not null,
    endereco varchar(200) not null
);

create table medico (
    cpf char(11) primary key not null,
    crm int unique not null,
    codigo_sala int not null,
    foreign key(cpf) references pessoa(cpf)
        on update cascade on delete restrict,
    foreign key(codigo_sala) references sala(codigo)
        on update cascade on delete restrict
);

create table motorista (
    cpf char(11) primary key not null,
    cnh int unique not null,
    foreign key(cpf) references pessoa(cpf)
        on update cascade on delete restrict
);

create table paciente (
    cpf char(11) primary key not null,
    endereco varchar(200) not null,
    foreign key(cpf) references pessoa(cpf)
        on update cascade on delete restrict
);

create table doenca (
    nome varchar(100) primary key not null,
    causador varchar(100) not null
);

create table diagnosticado (
    cpf_paciente char(11) not null,
    nome_doenca varchar(100) not null,
    data_diagnostico date not null,
    primary key(cpf_paciente, nome_doenca),
    foreign key(cpf_paciente) references paciente(cpf)
        on update cascade on delete restrict,
    foreign key(nome_doenca) references doenca(nome)
        on update cascade on delete restrict
);

create table consulta (
    cpf_medico char(11) not null,
    cpf_paciente char(11) not null,
    horario datetime not null,
    primary key(cpf_medico, cpf_paciente, horario),
    foreign key(cpf_medico) references medico(cpf)
        on update cascade on delete restrict,
    foreign key(cpf_paciente) references paciente(cpf)
        on update cascade on delete restrict
);

create table domina (
    nome_especialidade varchar(50) not null,
    cpf_medico char(11) not null,
    anos_experiencia int not null,
    primary key(nome_especialidade, cpf_medico),
    foreign key(nome_especialidade) references especialidade(nome)
        on update cascade on delete restrict,
    foreign key(cpf_medico) references medico(cpf)
        on update cascade on delete restrict
);
