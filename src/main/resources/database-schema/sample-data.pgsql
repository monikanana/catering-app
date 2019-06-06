begin;

drop table if exists users;
drop table if exists movies;
insert into customers(name, surname)values
('imie1', 'nazwisko1'),
('imie2', 'nazwisko2'),
('imie3', 'nazwisko3'),
('imie4', 'nazwisko4'),
('imie5', 'nazwisko5');
insert into categories(name) VALUES
('obiad mięsny'),
('wegetariańskie'),
('sałatki'),
('ryby');
insert into meals(is_top_meal, name, price, weight, category_id) VALUES
(true, 'name1', 12.99, 250, 1),
(true, 'name2', 9.99, 400, 3),
(true, 'name3', 15.99, 350, 1),
(true, 'name4', 30.99, 300, 1),
(true, 'name5', 8.99, 250, 1),
(false, 'name6', 30.99, 450, 2),
(false, 'name7', 10.99, 350, 3);
insert into orders(date, price, customer_id, state) VALUES
('2019-06-05', 56.97, 1, 1),
('2019-06-05', 22.98, 2, 1),
('2019-06-05', 12.99, 3, 1),
('2019-06-05', 8.99, 4, 2),
('2019-06-05', 22.98, 5, 2),
('2019-06-05', 39.98, 5, 3);
insert into orders_meals(id_order, id_meal) VALUES
(1,2),(1,3),(1,4),
(2,1),(2,2),
(3,5),(3,6),
(4,5),
(5,1),(5,2),
(6,1);

commit;
-- ceny w 'orders' są takie wpisane bo:
-- select sum(price), id_order from orders_meals om join meals m on om.id_meal=m.id group by id_order;

--SELECT * from meals;
--SELECT * from categories;
--SELECT * from customers;
--SELECT * from orders_meals;
--SELECT * from orders;