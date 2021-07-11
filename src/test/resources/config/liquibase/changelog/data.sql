-- liquibase formatted sql

-- changeset abelov:11--insert_into_role
insert into role(name)
values ('ROLE_USER'),
       ('ROLE_EMPLOYEE'),
       ('ROLE_ADMIN');

-- changeset abelov:12--insert_into_users
insert into users(id, login, email, password, role_id, created)
values (1, 'admin', 'email', 'password', 3,
        '2021-01-01 00:00:00'),
       (2, 'user', 'email', 'password', 1,
        '2021-01-01 00:00:00');

-- changeset abelov:13--insert_into_subject
insert into subject(name)
values ('TestSubject1'),
       ('TestSubject2'),
       ('TestSubject3');

-- changeset abelov:14--insert_into_status
insert into status(name)
values ('TestStatus1'),
       ('TestStatus2'),
       ('TestStatus3');

-- changeset abelov:15--insert_into_method
insert into method(name, description)
values ('TestMethod1', 'Desc1'),
       ('TestMethod2', null);

-- changeset abelov:16--insert_into_review
insert into review(id, user_id, created, text)
values (1, 2, '2021-01-01 00:00:00', 'text1'),
       (2, 2, '2021-01-01 00:00:00', 'text2');

-- changeset abelov:17--insert_into_task
insert into task(id, user_id, subject_id, comment, deadline,
                 status_id, prepared_cost, payment_id, created, contact, cause, archived)
values (1, 2, 1, 'comment', '2021-01-01 00:00:00', 1, 100, null, '2021-01-01 00:00:00', 'contact', 'cause', false),
       (2, 2, 1, 'comment', '2021-01-01 00:00:00', 1, 100, null, '2021-01-01 00:00:00', 'contact', 'cause', false );
