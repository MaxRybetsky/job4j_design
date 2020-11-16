create database terminal_db;
\c terminal_db;
create table role(
    id serial primary key,
    name_of_role varchar(100)
);
create table rules(
    id serial primary key,
    edit_items boolean,
    delete_items boolean,
    create_items boolean
);
create table role_rules(
    id serial primary key,
    role_id int references role(id),
    rules_id int references rules(id)
);
create table users(
    id serial primary key,
    name varchar(200),
    role_id int references role(id)
);
create table category(
    id serial primary key,
    category_name varchar(200)
);
create table state(
    id serial primary key,
    state_of_item varchar(100)
);
create table item(
    id serial primary key,
    item_name varchar(200),
    user_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)
);
create table comments(
    id serial primary key,
    comment text,
    item_id int references item(id)
);
create table attachs(
    id serial primary key,
    attach text,
    item_id int references item(id)
);
insert into role(name_of_role)
    values
        ('admin'),
        ('user');
insert into rules(edit_items, delete_items, create_items)
    values
        ('true', 'true','true'),
        ('false', 'false','true');
insert into role_rules(role_id, rules_id)
    values
        (1, 1),
        (2, 2);
insert into users(name, role_id)
    values
        ('Max', 1),
        ('Ivan', 2),
        ('John', 2);
insert into category(category_name)
    values
        ('warning'),
        ('error'),
        ('note'),
        ('message');
insert into state(state_of_item)
    values
        ('created'),
        ('received'),
        ('process'),
        ('ready');
insert into item(item_name, user_id, category_id, state_id)
    values
        ('New soft is ready', 1, 4, 4),
        ('Error with SQL', 2, 2, 3),
        ('Suppress warning in project', 3, 1, 4);
insert into comments(comment, item_id)
    values
        ('Please, update your soft. Thanks!', 1),
        ('Some error with db server, need help', 2),
        ('Should I pay attention to it?', 3);
insert into attachs(attach, item_id)
    values
        ('https://dropbox.com/user/logSQL.txt', 2),
        ('https://picsstrg.com/sfUIv12.jpg', 3);