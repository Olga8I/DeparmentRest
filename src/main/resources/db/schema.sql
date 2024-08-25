DROP TABLE IF EXISTS phone_numbers CASCADE;
DROP TABLE IF EXISTS users_departments CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS departments CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE IF NOT EXISTS roles
(
    role_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS departments
(
    department_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    user_id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_firstName VARCHAR(255) NOT NULL,
    user_lastName  VARCHAR(255) NOT NULL,
    role_id        BIGINT REFERENCES roles (role_id)
);

CREATE TABLE IF NOT EXISTS users_departments
(
    users_departments_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id              BIGINT REFERENCES users (user_id),
    department_id        BIGINT REFERENCES departments (department_id),
    CONSTRAINT unique_link UNIQUE (user_id, department_id)
);

CREATE TABLE IF NOT EXISTS phone_numbers
(
    phonenumber_id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    phonenumber_number VARCHAR(255) NOT NULL UNIQUE,
    user_id            BIGINT REFERENCES users (user_id)
);

-- Вставка данных
INSERT INTO roles (role_name)
VALUES ('Администратор'),
       ('Технический директор'),
       ('Программист Java'),
       ('Программист React'),
       ('HR');

INSERT INTO departments (department_name)
VALUES ('Администрация'),
       ('BackEnd разработка'),
       ('Frontend разработка'),
       ('HR менеджмент');

INSERT INTO users (user_firstName, user_lastName, role_id)
VALUES ('Иван', 'Субботин', 1),
       ('Петр', 'Понедельников', 2),
       ('Игнат', 'Вторников', 3),
       ('Иван', 'Середец', 3),
       ('Максим', 'Четверкин', 3),
       ('Вера', 'Пятницкая', 4),
       ('Ольга', 'Воскресенская', 5);

INSERT INTO users_departments (user_id, department_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 1),
       (6, 3),
       (7, 4);

INSERT INTO phone_numbers (phonenumber_number, user_id)
VALUES ('+1(123)123 1111', 1),
       ('+1(123)123 2222', 1),
       ('+1(123)123 3333', 2),
       ('+1(123)123 4444', 2),
       ('+1(123)123 5555', 3),
       ('+1(123)123 6666', 4),
       ('+1(123)123 7777', 5),
       ('+1(123)123 8888', 6),
       ('+1(123)123 9995', 7);
