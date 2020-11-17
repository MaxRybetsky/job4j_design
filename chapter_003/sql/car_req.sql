--Создать структуры данных в базе.
create table engine(
	id serial primary key,
	name varchar(200)
);

insert into engine(name) values
    ('Eng1'),
    ('Eng2'),
    ('Eng3');

create table body(
	id serial primary key,
	name varchar(200)
);

insert into body(name) values
    ('Body1'),
    ('Body2'),
    ('Body3');

create table transmission(
	id serial primary key,
	name varchar(200)
);

insert into transmission(name) values
    ('Trans1'),
    ('Trans2'),
    ('Trans3');

--Создать структуру Машина
create table vehicle(
	id serial primary key,
	name varchar(200),
	eng_id int references engine(id),
	trans_id int references transmission(id),
	body_id int references body(id)
);

insert into vehicle(name, eng_id, trans_id, body_id) values
    ('Car1', 1, 2, 3);

--Вывести список всех машин и все привязанные к ним детали.
select  v.name as Vehicle,
		e.name as Engine,
		b.name as Body,
		tr.name as Transmission
from vehicle v
	inner join engine e on v.eng_id=e.id
	inner join body b on v.body_id=b.id
	inner join transmission tr on v.trans_id=tr.id;

-- Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select  e.name from vehicle v
	right join engine e on v.eng_id=e.id
where 	v.eng_id is null;

select  b.name from vehicle v
	right join body b on v.body_id=b.id
where 	v.body_id is null;

select  tr.name from vehicle v
	right join transmission tr on v.trans_id=tr.id
where 	v.trans_id is null;