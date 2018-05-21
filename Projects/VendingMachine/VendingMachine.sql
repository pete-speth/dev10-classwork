
drop database if exists VendingMachine;
create database VendingMachine;
use VendingMachine;

create table Inventory(
	Id int auto_increment primary key,
    ItemName varchar(30) not null,
    Price fixed(5,2) not null,
    Quantity int not null
);

insert into Inventory (ItemName, Price, Quantity)
values ('Gatorade - Red',2.25,10), ('Gatorade - Blue',2.25,10), ('Gatorade - Yellow',2.25,10),
	('Arnold Palmer',1.35,10), ('Lemonade',1.25,10), ('Iced Tea',1.15,10),
	('Mountain Dew',1.45,10), ('Coke',1.25,10), ('Sprite',1.05,10),
	('Dr. Pepper',1.35,10), ('Root Beer',1.50,10), ('Bottled Water',0.85,10);
    
select * from Inventory;