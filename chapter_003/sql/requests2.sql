create table departments(
	id serial primary key,
	name varchar(200)
);
create table employees(
	id serial primary key,
	name varchar(200),
	dep_id int references departments(id)
);

insert into departments(name) values
('dep1'),
('dep2'),
('dep3');

insert into employees(name, dep_id) values
('emp1', 1),
('emp2', 2),
('emp3', 3),
('emp4', null),
('emp5', null),
('emp6', 1);

select * from employees e left join
              departments d on e.dep_id=d.id where d.id is null;

select * from employees e left join
    departments d on e.dep_id=d.id;
select * from departments d right join
    employees e on e.dep_id=d.id;

create table teens(
	name varchar(200),
	gender boolean
);

insert into teens(name, gender)
values
    ('John', 'false'),
    ('Jenny', 'true'),
    ('Den', 'false'),
    ('Max', 'false'),
    ('Elly', 'true'),
    ('Alice', 'true'),
    ('Osy', 'false');

select 	t1.name,
		t2.name
from teens t1 cross join
	 teens t2
where t1.gender != t2.gender;