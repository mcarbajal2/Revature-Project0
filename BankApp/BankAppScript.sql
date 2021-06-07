-- Users (Customer, Employee)

create table users (

id serial primary key, 
username varchar(20) not null,
password varchar(20) not null,
f_name varchar(20) not null,
l_name varchar (20) not null,
employee boolean
);

-- Accounts (Savings, Checking)

create table accounts (

id serial primary key,
account_num serial not null,
pending_account boolean,
account_bal int not null,
u_id int -- foreign key to users table
);

-- Create Accounts Foreign Key

alter table accounts drop constraint accounts_user_fk;

alter table accounts
add constraint accounts_user_fk
foreign key (u_id) references users(id) on delete set null;

-- Transactions Logs (username, transaction amount)

create table transactions (
id serial primary key,
username varchar(20) not null,
transaction int not null,
t_id int -- foreign key to accounts table
);

-- Create Transactions Foreign Key

alter table transactions drop constraint transactions_accounts_fk;

alter table transactions
add constraint transactions_accounts_fk
foreign key (t_id) references accounts(id) on delete set null;



