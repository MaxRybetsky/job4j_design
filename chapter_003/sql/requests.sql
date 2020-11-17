select * from product where type_id=any
    (select id from type where name='СЫР');

select * from product where name like '%мороженное%';

select * from product where expired_date<current_date+interval '1 month';

select * from product where price = any
    (select max(price) from product);

select
    (select name from type where id=type_id),
    sum(pcs)
from product
group by type_id;

select * from product where type_id=any
    (select id from type where name='СЫР'
	                        or name='МОЛОКО');

select name, nums
from
    (select
        (select name from type where id=type_id) as name,
        sum(pcs) as nums
    from product
    group by type_id) as res
where nums<10;

select
	name,
	(select name from type where id=type_id)
from  product;