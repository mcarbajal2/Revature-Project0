
/*** CREATE users table ***/

create table users (
u_id serial primary key,
username varchar(20),
password varchar(20),
f_name varchar(20),
l_name varchar (20),
is_employee boolean
);

/*** CREATE accounts table ***/

create table accounts (
acc_num serial primary key, 
u_id int references users(u_id), -- fk to users, 
acc_num serial,
balance numeric(10),
acc_type varchar(10),
is_pending boolean
);

/*** CREATE transctions table ***/

create table transactions (
t_id serial primary key,
u_id int references accounts(u_id), -- fk to accounts
acc_num numeric(6),
t_amount numeric(10),
t_name varchar(10)
);

/*** PROCEDURE ***/

create procedure add_user(u_id serial, username varchar, password varchar, f_name varchar, l_name varchar, is_employee boolean)
language sql
as $$
insert into "BankApp".users values(default, username, password, f_name, l_name, is_employee);
$$;

-- call add_user();

/*** INSERT employee dummy account***/

insert into users values (default, 'admin', 'password', 'John', 'Smith', true);


DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

DROP OWNED BY mina CASCADE;

show search_path;
set search_path to public;

delete from users where u_id = 5;

