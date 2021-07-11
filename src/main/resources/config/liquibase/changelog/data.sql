-- liquibase formatted sql

-- changeset abelov:11--insert_into_role
insert into role(name)
values ('ROLE_USER'),
       ('ROLE_EMPLOYEE'),
       ('ROLE_ADMIN');

-- changeset abelov:12--insert_into_users
insert into users(id, login, email, password, role_id, created)
values (1, 'admin', 'admin@admin.ru', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 3,
        '2021-04-20 00:00:00'),
       (2, 'user', 'user@user.ru', '$2a$10$rMFTxSBhjif2x/EsX7XmbeXyd.eDIvyNI4B9mByftYRlSUtjUkIeK', 1,
        '2021-04-20 00:00:00'),
       (3, 'employee', 'employee@employee.ru', '$2a$10$bBwXA8paW.9R0XQf5uVBV.eGGxZemnb6/5xXJqqauOYT2hqup3ppK', 2,
        '2021-04-20 00:00:00');

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
