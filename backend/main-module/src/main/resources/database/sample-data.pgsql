begin;

drop table if exists users;
drop table if exists movies;
drop table if exists ordersmeals;

ALTER SEQUENCE if exists categories_id_seq RESTART WITH 1;
ALTER SEQUENCE if exists meals_id_seq RESTART WITH 1;
ALTER SEQUENCE if exists customers_id_seq RESTART WITH 1;
ALTER SEQUENCE if exists orders_id_seq RESTART WITH 1;
ALTER SEQUENCE if exists subscriptions_id_seq RESTART WITH 1;
ALTER SEQUENCE if exists locations_id_seq RESTART WITH 1;
ALTER SEQUENCE if exists restaurants_id_seq RESTART WITH 1;

insert into locations(name, address) values
('Sabre parter', 'ul. Lea 23, Kraków'),
('Sabre piwnica', 'ul. Lea 23, Kraków'),
('Sabre strych', 'ul. Lea 23, Kraków'),
('Schibsted Bawialnia', 'ul. AK 2, Kraków'),
('Schibsted Sala Reprezentacyjna', 'ul. AK 2, Kraków'),
('Nokia parter', 'ul. Conrada 2, Kraków'),
('Nokia Playstation', 'ul. Conrada 1, Kraków');

insert into restaurants(name, address) values
('Kebab Pasja', 'ul. Kawiory 1a, Kraków'),
('Luna', 'ul. Budryka 2, Kraków'),
('Babcia Malina', 'ul. Słowackiego 10, Kraków'),
('U Szwagra', 'ul. Wesele 12, Kraków'),
('Kwant', 'ul. AK 28, Kraków'),
('Hindus FoodTruck', 'ul. Conrada 21, Kraków'),
('Makarun', 'ul. Batorego 1, Kraków');

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
('ryby'),
('zupy');
select * from meals;
insert into meals(name, price, weight, category_id, restaurant_id) VALUES
('Wspaniały Kotlet', 12.99, 250, 1, 1),
('Mięso mielone', 30.99, 300, 1, 4),
('Pierogi z węża', 18.99, 450, 1, 1),
('Kotlet de Volaille', 20.99, 450, 1, 4),
('Potrawka z chrząszcza', 15.99, 350, 1, 3),
('Marchew z makaronem', 13.99, 450, 2, 2),
('Sałatka z bakłażanu', 9.99, 400, 3, 2),
('Sałatka z kurczakiem a la Cezar', 10.99, 350, 3, 6),
('Sałatka z jabłka i marchwi', 5.99, 200, 3, 7),
('Ryba po grecku', 15.99, 350, 4, 3),
('Filet z ryby smażony w panierce', 25.99, 200, 4, 1),
('Gulasz ognisty', 11.99, 250, 5, 5),
('Zupa tajska kokosowa z warzywami', 30.99, 250, 5, 6),
('Koperkowa z ryżem', 30.99, 150, 5, 6);
------------------------------------------------------------------------------
insert into orders(date, price, customer_id, state, location_id) VALUES
('2019-06-13', null, 1, 'ORDERED', 1),
('2019-06-13', null, 2, 'ORDERED', 6),
('2019-06-13', null, 3, 'ORDERED', 7),
('2019-06-13', null, 4, 'ORDERED', 3),
('2019-06-13', null, 1, 'CANCELLED', 3),
('2019-06-10', null, 1, 'CANCELLED', 6),
('2019-06-13', null, 2, 'CANCELLED', 3),
('2019-06-12', null, 3, 'CANCELLED', 1),
('2019-06-12', null, 4, 'CANCELLED', 2),
('2019-06-11', null, 5, 'CANCELLED', 3),
('2019-06-11', null, 5, 'CANCELLED', 4),
('2019-06-10', null, 1, 'DELIVERED', 7),
('2019-06-11', null, 2, 'DELIVERED', 1),
('2019-06-10', null, 2, 'DELIVERED', 1),
('2019-06-09', null, 3, 'DELIVERED', 2),
('2019-06-12', null, 4, 'DELIVERED', 2),
('2019-06-10', null, 5, 'DELIVERED', 3),
('2019-06-15', null, 1, 'SUBSCRIBED', 7),
('2019-06-16', null, 1, 'SUBSCRIBED', 7),
('2019-06-17', null, 1, 'SUBSCRIBED', 7),
('2019-06-16', null, 2, 'SUBSCRIBED', 1),
('2019-06-17', null, 2, 'SUBSCRIBED', 1),
('2019-06-18', null, 2, 'SUBSCRIBED', 1),
('2019-06-19', null, 2, 'SUBSCRIBED', 2),
('2019-06-20', null, 2, 'SUBSCRIBED', 2),
('2019-06-15', null, 3, 'SUBSCRIBED', 3),
('2019-06-15', null, 4, 'SUBSCRIBED', 4),
('2019-06-16', null, 4, 'SUBSCRIBED', 3),
('2019-06-16', null, 5, 'SUBSCRIBED', 4),
('2019-06-17', null, 5, 'SUBSCRIBED', 7);


select * from orders_meals;
insert into orders_meals(id_order, id_meal) VALUES
(1,14),(1,10),
(2,9),(2,11),
(3,8),(3,14),
(4,2),(4,4),
(5,12),(5,4),
(6,11),(6,5),
(7,6),(7,7),
(8,5),(8,14),
(9,2),(9,6),
(10,11),(10,3),
(11,12),(11,2),
(12,9),(12,5),
(13,10),(13,4),
(14,14),(14,11),
(15,7),(15,11),
(16,10),(16,4),
(17,10),(17,2),
(18,12),(18,6),
(19,12),(19,9),
(20,5),(20,1),
(21,2),(21,3),
(22,10),(22,1),
(23,5),(23,12),
(24,8),(24,7),
(25,5),(25,1),
(26,3),(26,13),
(27,8),(27,11),
(28,6),(28,1),
(29,7),(29,10),
(30,12),(30,1);


update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=1) where id=1;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=2) where id=2;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=3) where id=3;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=4) where id=4;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=5) where id=5;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=6) where id=6;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=7) where id=7;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=8) where id=8;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=9) where id=9;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=10) where id=10;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=11) where id=11;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=12) where id=12;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=13) where id=13;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=14) where id=14;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=15) where id=15;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=16) where id=16;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=17) where id=17;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=18) where id=18;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=19) where id=19;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=20) where id=20;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=21) where id=21;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=22) where id=22;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=23) where id=23;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=24) where id=24;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=25) where id=25;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=26) where id=26;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=27) where id=27;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=28) where id=28;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=29) where id=29;
update orders set price=(select sum(price) from orders_meals om join meals m on m.id=om.id_meal where id_order=30) where id=30;

commit;