-- liquibase formatted sql

-- changeset abelov:2--create_table_role
create table role
(
    id   int primary key not null auto_increment,
    name varchar(50)     not null
);
-- rollback drop table role;

-- changeset abelov:3--create_table_users
create table users
(
    id       int primary key  not null auto_increment,
    login    varchar(50)      not null,
    email    varchar(50)      not null,
    password varchar(500)     not null,
    role_id  int,
    created  timestamp        not null,
    constraint fk_users_role_id foreign key (role_id) references zmath.role (id)
);
-- rollback drop table users;

-- changeset abelov:4--create_table_subject
create table subject
(
    id   int primary key not null auto_increment,
    name varchar(50)     not null
);
-- rollback drop table subject;

-- changeset abelov:5--create_table_status
create table status
(
    id   int primary key not null auto_increment,
    name varchar(50)     not null
);
-- rollback drop table status;

-- changeset abelov:6--create_table_method
create table method
(
    id          int primary key not null auto_increment,
    name        varchar(50)     not null,
    description varchar(300)
);
-- rollback drop table method;

-- changeset abelov:7--create_table_payment
create table payment
(
    id        int primary key not null auto_increment,
    created   timestamp       not null,
    method_id int             not null,
    amount    real            not null,
    constraint fk_payment_method_id foreign key (method_id) references zmath.method (id)
);
-- rollback drop table payment;

-- changeset abelov:8--create_table_task
create table task
(
    id            int primary key not null auto_increment,
    user_id       int             not null,
    subject_id    int             not null,
    comment       varchar(500),
    deadline      timestamp       not null,
    status_id     int             not null,
    cost          real,
    prepared_cost real,
    payment_id    int,
    created       timestamp       not null,
    contact       varchar(500),
    cause         varchar(500),
    archived      boolean         not null,
    constraint fk_task_user_id foreign key (user_id) references zmath.users (id),
    constraint fk_task_subject_id foreign key (subject_id) references zmath.subject (id),
    constraint fk_task_status_id foreign key (status_id) references zmath.status (id),
    constraint fk_task_payment_id foreign key (payment_id) references zmath.payment (id)
);
-- rollback drop table task;

-- changeset abelov:9--create_table_review
create table review
(
    id      int primary key not null auto_increment,
    user_id int             not null,
    created timestamp       not null,
    text    varchar(1000),
    constraint fk_review_user_id foreign key (user_id) references zmath.users (id)
);
-- rollback drop table review;

-- changeset abelov:10--create_table_attached_file
create table attached_file
(
    id        int primary key not null auto_increment,
    name      varchar(300)    not null,
    size      varchar(300)    not null,
    extension varchar(300)    not null,
    path      varchar(300)    not null,
    type      varchar(300)    not null,
    task_id   int             not null,
    constraint fk_attached_file_task_id foreign key (task_id) references zmath.task (id)
);
-- rollback drop table attached_file;


-- changeset abelov:11--insert_into_role
insert into role(name)
values ('ROLE_USER'),
       ('ROLE_EMPLOYEE'),
       ('ROLE_ADMIN');

-- changeset abelov:12--insert_into_users
insert into users(id, login, email, password, role_id, created, balance)
values (1, 'admin', 'admin@admin.ru', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 3,
        '2021-04-20 00:00:00', 0);

-- changeset abelov:13--insert_into_subject
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

-- changeset abelov:14--insert_into_status
insert into status(name)
values ('Новый'),
       ('Просмотрен'),
       ('Оплачен'),
       ('Выполнен'),
       ('Отказано');

-- changeset abelov:15--insert_into_method
insert into method(name, description)
values ('Банковская карта', 'VISA, Mastercard, Мир и другие'),
       ('Мобильный платёж', 'МТС, Билайн, Мегафон, Теле2'),
       ('Кошелёк WebMoney', null),
       ('Кошелёк QIWI', null);
