
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
balance numeric(10),
acc_type varchar(10),
is_pending boolean
);

drop table accounts;
drop table transactions;
insert into accounts values (default, 3, 0.00, 'Checking', true);
alter table accounts alter column balance type numeric(10,2);

/*** STORED PROCEDURE ***/

create procedure add_acct(u_id int, balance numeric, acc_type varchar, is_pending boolean)
language sql
as $$
insert into accounts values (default, u_id, balance, acc_type, is_pending);
$$;

-- call add_acct();
drop procedure add_acct(u_id int, balance numeric, acc_type varchar, is_pending boolean);

/*** CREATE transctions table ***/

create table transactions (
t_id serial primary key,
u_id int,
acc_num int references accounts(acc_num), -- fk to accounts
t_amount numeric(10),
t_name varchar(10)
);

alter table transactions alter column acc_num type int;
alter table transactions alter column t_amount type numeric(10,2);


/*** INSERT employee dummy account***/

insert into users values (default, 'admin', 'password', 'John', 'Smith', true);


DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

DROP OWNED BY mina CASCADE;

show search_path;
set search_path to public;

delete from accounts where acc_num = 2;
update accounts set balance = 1000.00 where acc_num = 1;
update accounts set balance = 100.00 balance = 300.00 where acc_num = 1 acc_num = 3;
insert into transactions values (default, 2, 8, 900.0, 'deposit');


