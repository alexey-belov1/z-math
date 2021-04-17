drop table review;
drop table payment;
drop table method;
drop table solve;
drop table task;
drop table status;
drop table subject;
drop table users;
drop table role;

create table role (
    id serial primary key,
    name varchar(50) not null
);

create table users (
    id serial primary key,
    login varchar(50) not null,
    email varchar(50) not null,
    password varchar(500) not null,
    role_id integer references role(id) not null,
    created timestamp not null,
    balance real default 0.0 not null
);

create table subject (
    id serial primary key,
    name varchar(50) not null
);

create table status (
    id serial primary key,
    name varchar(50) not null
);

create table task (
    id serial primary key,
    user_id integer references users(id) not null,
    subject_id integer references subject(id) not null,
    file varchar(200) not null,
    comment varchar(500) not null,
    deadline timestamp not null,
    status_id integer references status(id) not null,
    cost real not null,
    created timestamp not null,
    contact varchar(500),
    cause varchar(500),
    hidden boolean not null
);

create table solve (
    id serial primary key,
    task_id integer references task(id) not null,
    user_id integer references users(id) not null,
    file varchar(200) not null,
    comment varchar(500)
);

create table method (
    id serial primary key,
    name varchar(50) not null
);

create table payment (
    id serial primary key,
    created timestamp not null,
    realized timestamp not null,
    user_id integer references users(id) not null,
    task_id integer references task(id) not null,
    method_id integer references method(id) not null,
    amount real not null,
    comment varchar(500),
    status boolean not null
);

create table review (
    id serial primary key,
    user_id integer references users(id) not null,
    created timestamp not null,
    text varchar(1000)
);

insert into role(name) values
    ('ROLE_User'), ('ROLE_Employee'), ('ROLE_Admin');

insert into subject(name) values
    ('Математический анализ'), ('Линейная алгебра'), ('Аналитическая геометрия'),
    ('Дифференциальные уравнения'), ('Теория вероятностей'), ('Численные методы'),
    ('Теоретическая механика'), ('Математическая физика'), ('Теория функций комплексного переменного'),
    ('Теория устойчивости'), ('Программирование на С#'), ('Остальное');

insert into status(name) values
    ('Новый'), ('Просмотрен'), ('Ожидание оплаты'),
    ('Решается'), ('Готово');

insert into method(name) values
    ('Карта Visa');