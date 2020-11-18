select p.* from type t
      left join product p
      on t.id=p.type_id
where t.name='СЫР';

select * from product where name like '%мороженное%';

select * from product where expired_date<current_date+interval '1 month';

select * from product where price = any
    (select max(price) from product);

select t.name, sum(p.pcs)
from product p inner join
     type t on t.id=p.type_id
group by t.id;

select p.* from type t
      left join product p
      on t.id=p.type_id
where t.name='СЫР' or t.name='МОЛОКО';

select * from
	(select t.name, sum(p.pcs) as nums
	 from product p inner join
		  type t on t.id=p.type_id
	 group by t.id) as res
where nums < 100;

select p.name, t.name
from product p left join type t
               on p.type_id=t.id;