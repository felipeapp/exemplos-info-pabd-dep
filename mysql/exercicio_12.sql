drop database if exists exercicio_resolvido;
create database exercicio_resolvido;

use exercicio_resolvido;

# A #
create table paciente (
    id int primary key not null auto_increment,
    nome varchar(150) not null,
    cpf char(11) unique not null,
    doenca varchar(50) not null,
    horario_registro datetime not null default now(),
    index(nome)
);

create table medico (
    id int primary key not null auto_increment,
    nome varchar(150) not null,
    matricula int unique not null,
    especialidade varchar(50) not null,
    salario decimal(6,2) not null,
    horario_registro datetime not null default now(),
    index(nome)
);

create table consulta (
    id_medico int not null,
    id_paciente int not null,
    horario datetime not null,
    valor decimal(5,2) not null,
    horario_registro datetime not null default now(),
    primary key(id_medico, id_paciente, horario),
    foreign key(id_medico) references medico(id) on update cascade on delete restrict,
    foreign key(id_paciente) references paciente(id) on update cascade on delete restrict,
    unique index(id_medico, horario),
    unique index(id_paciente, horario)
);

# B #
# Inserindo médicos
insert into medico (nome, matricula, especialidade, salario) values
    ('Anna Souza', 7777, 'Dermatologia', 7000),
    ('Arthur Barbosa', 2222, 'Cardiologia', 8000),
    ('Davi Leite', 3333, 'Cardiologia', 4000),
    ('Eduardo Henrique', 8888, 'Neurologia', 3000),
    ('Gabriela Souza', 4444, 'Anestegiologia', 3000),
    ('Maria Eduarda', 5555, 'Neurologia', 3000),
    ('Maria Paula', 9999, 'Oftalmologia', 2000),
    ('Miguel Leite', 1111, 'Infectologia', 2000),
    ('Nicolas Alves', 6666, 'Mastologia', 5000);

# Inserindo pacientes
insert into paciente (nome, cpf, doenca) values
    ('Anna Gabriela', '55555555555', 'Catarata'),
    ('Davi Leite', '33333333333', 'Arritmia'),
    ('Eduardo Luiz', '77777777777', 'Conjuntivite'),
    ('João Paulo', '44444444444', 'Doença de Chagas'),
    ('leonardo Alves', '11111111111', 'Epilepsia'),
    ('nicolas Alves', '66666666666', 'Glaucoma'),
    ('otávio Breno', '88888888888', 'espasmo das Pálpebras'),
    ('Sara Souza', '99999999999', 'Pericardite'),
    ('Thiago Lucas', '22222222222', 'Doença de Huntington');

# Inserindo consultas
insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 2222),
    (select id from paciente where cpf = '22222222222'),
    '2021-10-25 20:00', 200);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 2222),
    (select id from paciente where cpf = '44444444444'),
    '2021-10-24 20:00', 200);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 5555),
    (select id from paciente where cpf = '11111111111'),
    '2021-10-22 09:00', 300);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 9999),
    (select id from paciente where cpf = '33333333333'),
    '2021-10-21 08:00', 150);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 1111),
    (select id from paciente where cpf = '88888888888'),
    '2021-10-20 16:30', 200);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 4444),
    (select id from paciente where cpf = '44444444444'),
    '2021-10-16 20:00', 300);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 6666),
    (select id from paciente where cpf = '77777777777'),
    '2021-10-16 20:00', 250);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 6666),
    (select id from paciente where cpf = '11111111111'),
    '2021-10-16 19:00', 150);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 1111),
    (select id from paciente where cpf = '77777777777'),
    '2021-10-16 17:00', 300);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 3333),
    (select id from paciente where cpf = '99999999999'),
    '2021-10-13 13:00', 300);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 9999),
    (select id from paciente where cpf = '66666666666'),
    '2021-10-12 14:30', 200);

insert into consulta (id_medico, id_paciente, horario, valor) values
    ((select id from medico where matricula = 4444),
    (select id from paciente where cpf = '55555555555'),
    '2021-10-12 14:00', 200);

# C #
update consulta
    set id_medico = (select id from medico where matricula = 9999)
    where id_paciente = (select id from paciente where cpf = '11111111111');

# D #
/*
Note que não é possível remover o médico sem antes remover suas consultas.
O SGBD impedirá a remoção direta do médico para garantir a integridade referencial entre as tabelas.
*/

# Remove as consultas do médico
delete from consulta where id_medico = (select id from medico where matricula = 3333);

# Remove o médico
delete from medico where matricula = 3333;

# E #
select m.id, m.nome, m.matricula, m.especialidade, m.salario, c.horario, c.valor
    from medico m left join consulta c on m.id = c.id_medico
    order by m.matricula asc, c.horario desc;

# F #
select p.id, p.nome, p.cpf, c.horario, c.valor
    from paciente p left join consulta c on p.id = c.id_paciente
    order by p.cpf asc, c.horario desc;

# G #
# Função para formatar o CPF
# Não precisam se preocupar com isso, por enquanto, fica como curiosidade
DELIMITER //
create function formatar_cpf(cpf char(11))
returns char(14)
deterministic
begin
    return concat(substring(cpf, 1, 3), '.',
            substring(cpf, 4, 3), '.',
            substring(cpf, 7, 3), '-',
            substring(cpf, 10, 2));
end; //
DELIMITER ;

# Médicos
select nome `Nome`, matricula `Matrícula`, especialidade `Especialidade`,
    concat('r$ ', salario) `Salário` from medico order by nome;

# Pacientes
select nome `Nome`, formatar_cpf(cpf) `CPF`, doenca `Doença` from paciente order by nome;

# Consultas
select
    m.nome `Nome do Médico`, m.matricula `Matrícula do Médico`,
    p.nome `Nome do Paciente`, formatar_cpf(p.cpf) `CPF do Paciente`,
    date_format(c.horario, '%d/%m/%Y %H:%i') `Horário da Consulta`,
    concat('R$ ', c.valor) `Valor da Consulta`
from
    medico m inner join consulta c on m.id = c.id_medico
    inner join paciente p on p.id = c.id_paciente
order by
    c.horario desc, m.matricula asc;

# H #
# Médicos sem consultas
select m.* from medico m left join consulta c on m.id = c.id_medico where id_medico is null;

# Pacientes sem consultas
select p.* from paciente p left join consulta c on p.id = c.id_paciente where id_paciente is null;
