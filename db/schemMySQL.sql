drop table if exists review;
drop table if exists payment;
drop table if exists method;
drop table if exists solve;
drop table if exists task;
drop table if exists status;
drop table if exists subject;
drop table if exists users;
drop table if exists role;

create table role (
    id   bigint(20) primary key not null auto_increment,
    name varchar(50) not null
);

create table users (
    id       bigint(20) primary key not null auto_increment,
    login    varchar(50)      not null,
    email    varchar(50)      not null,
    password varchar(500)     not null,
    role_id  bigint(20),
    created  timestamp        not null,
    balance  real default 0.0 not null,
    constraint fk_users_role_id foreign key (role_id) references zmath.role (id)
);

create table subject (
    id   bigint(20) primary key not null auto_increment,
    name varchar(50) not null
);

create table status (
    id   bigint(20) primary key not null auto_increment,
    name varchar(50) not null
);

create table task (
    id         bigint(20) primary key not null auto_increment,
    user_id    bigint(20) not null,
    subject_id bigint(20) not null,
    file       varchar(200) not null,
    comment    varchar(500) not null,
    deadline   timestamp    not null,
    status_id  bigint(20) not null,
    cost       real         not null,
    created    timestamp    not null,
    contact    varchar(500),
    cause      varchar(500),
    hidden     boolean      not null,
    constraint fk_task_user_id foreign key (user_id) references zmath.users (id),
    constraint fk_task_subject_id foreign key (subject_id) references zmath.subject (id),
    constraint fk_task_status_id foreign key (status_id) references zmath.status (id)
);

create table solve (
    id      bigint(20) primary key not null auto_increment,
    task_id bigint(20) not null,
    user_id bigint(20) not null,
    file    varchar(200) not null,
    comment varchar(500),
    constraint fk_solve_task_id foreign key (task_id) references zmath.task (id),
    constraint fk_solve_user_id foreign key (user_id) references zmath.users (id)
);

create table method (
    id   bigint(20) primary key not null auto_increment,
    name varchar(50) not null
);

create table payment (
    id        bigint(20) primary key not null auto_increment,
    created   timestamp not null,
    realized  timestamp not null,
    user_id   bigint(20) not null,
    task_id   bigint(20) not null,
    method_id bigint(20) not null,
    amount    real      not null,
    comment   varchar(500),
    status    boolean   not null,
    constraint fk_payment_user_id foreign key (user_id) references zmath.users (id),
    constraint fk_payment_task_id foreign key (task_id) references zmath.task (id),
    constraint fk_payment_method_id foreign key (method_id) references zmath.method (id)
);

create table review (
    id      bigint(20) primary key not null auto_increment,
    user_id bigint(20) not null,
    created timestamp not null,
    text    varchar(1000),
    constraint fk_review_user_id foreign key (user_id) references zmath.users (id)
);

insert into role(name)
values ('ROLE_User'),
       ('ROLE_Employee'),
       ('ROLE_Admin');

insert into subject(name)
values ('Математический анализ'),
       ('Линейная алгебра'),
       ('Аналитическая геометрия'),
       ('Дифференциальные уравнения'),
       ('Теория вероятностей'),
       ('Численные методы'),
       ('Теоретическая механика'),
       ('Математическая физика'),
       ('Теория функций комплексного переменного'),
       ('Теория устойчивости'),
       ('Программирование на С#'),
       ('Остальное');

insert into status(name)
values ('Новый'),
       ('Просмотрен'),
       ('Ожидание оплаты'),
       ('Решается'),
       ('Готово');

insert into method(name)
values ('Карта Visa');
