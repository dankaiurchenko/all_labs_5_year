CREATE SEQUENCE books_ids START 1;
DROP SEQUENCE IF EXISTS serial;
SELECT nextval('books_ids');
select *
from pg_sequence;

CREATE TABLE books
(
    id             SERIAL PRIMARY KEY,
    title          VARCHAR(100) NOT NULL,
    primary_author VARCHAR(100) NULL,
    date_published DATE,
    price          numeric,
    numberOfPages  integer
);


insert into books (title, primary_author, date_published, price, numberOfPages)
values ('pride and prejudice', 'jane austen', to_date('10-1999', 'mm-yyyy'), 56.5, 578),
       ('pride and prejudice2', 'jane austen', to_date('12-2021', 'mm-yyyy'), 56.5, 578);


select *
from books;


delete
from books
where id = 2;

CREATE SEQUENCE books_ids START 1;
DROP SEQUENCE IF EXISTS serial;
SELECT nextval('books_ids');
select *
from pg_sequence;

CREATE TABLE books
(
    id             SERIAL PRIMARY KEY,
    title          VARCHAR(100) NOT NULL,
    primary_author VARCHAR(100) NULL,
    date_published DATE,
    price          numeric,
    numberOfPages  integer
);


insert into books (title, primary_author, date_published, price, numberOfPages)
values ('pride and prejudice', 'jane austen', to_date('10-1999', 'mm-yyyy'), 56.5, 578),
       ('pride and prejudice2', 'jane austen', to_date('12-2021', 'mm-yyyy'), 56.5, 578);


select *
from books;


delete
from books
where id = 2;

CREATE SEQUENCE books_ids START 1;
DROP SEQUENCE IF EXISTS serial;
SELECT nextval('books_ids');
select *
from pg_sequence;

CREATE TABLE books
(
    id             SERIAL PRIMARY KEY,
    title          VARCHAR(100) NOT NULL,
    primary_author VARCHAR(100) NULL,
    date_published DATE,
    price          numeric,
    numberOfPages  integer
);


insert into books (title, primary_author, date_published, price, numberOfPages)
values ('pride and prejudice', 'jane austen', to_date('10-1999', 'mm-yyyy'), 56.5, 578),
       ('pride and prejudice2', 'jane austen', to_date('12-2021', 'mm-yyyy'), 56.5, 578);


select *
from books;
delete
from books
where id = 2;



CREATE SEQUENCE books_ids START 1;
DROP SEQUENCE IF EXISTS serial;
SELECT nextval('books_ids');
select *
from pg_sequence;

CREATE TABLE internet_novels
(
    id             SERIAL PRIMARY KEY,
    title          VARCHAR(100) NOT NULL,
    primary_author VARCHAR(100) NULL,
    date_published DATE,
    numberOfPages  integer,
    url            VARCHAR(500)
);


insert into internet_novels (title, primary_author, date_published, numberOfPages, url)
values ('pride and prejudice', 'jane austen', to_date('10-1999', 'mm-yyyy'), 578, 'https://www.postgresql.org/docs/8.0/sql-createuser.html'),
       ('pride and prejudice2', 'jane austen', to_date('12-2021', 'mm-yyyy'), 578, 'https://github.blog/2018-09-18-towards-natural-language-semantic-code-search/');


select *
from internet_novels;


delete
from internet_novels
where id = 2;

