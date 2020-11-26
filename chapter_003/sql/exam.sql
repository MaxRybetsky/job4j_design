-- Names of all persons that are NOT in the company with id = 5
select name
from person
where company_id!=5;

-- Company name for each person
select p.name, c.name
from person p inner join company c on p.company_id=c.id;

-- Select the name of the company with the maximum number of persons + number of persons in this company
select c.name, count(p.id) as num
from company c left join person p on c.id=p.company_id
group by c.id
order by num desc
limit 1;