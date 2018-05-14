use Hotel;

insert into RoomType (TypeName)
values ('Single'),('Double'),('King');

insert into Ammenity (AmmenityName)
values ('Mini Fridge'),('Spa Bath'),('Microwave'),('Coffee Maker');

insert into Room (RoomTypeID, RoomNumber, Floor, Occupancy)
values (1,101,1,2), (2,102,1,4), (3,103,1,6),
	(1,201,2,2), (2,202,2,4), (3,203,2,6);
    
insert into RoomAmmenity (RoomID, AmmenityID) 
values (1,3),(1,4),
	(4,3),(4,4),
    (2,1),(2,3),(2,4),
    (5,1),(5,3),(5,4),
    (3,1),(3,2),(3,3),(3,4),
    (6,1),(6,2),(6,3),(6,4);

insert into Customer (CustomerName, Phone, Email)
values ('Ned Stark','(123) 456-7890', 'warden@thenorth.com'),
	('Tyrion Lannister','(987) 654-3210', 'dwarf@casterlyrock.org'),
	('Petyr Baelish','(246) 810-1214','littlefinger@harrenhal.net'),
	('Brienne of Tarth','(357) 911-1315','fightergirl@honorbound.com'),
	('Samuel Tarly','(112) 358-1321','wannabemaester@thecitadel.edu');

insert into Reservation (CustomerID, StartDate, EndDate)
values (1,'2018/06/01','2018/06/05'),
	(2,'2018/06/01','2018/06/03'),
	(3,'2018/06/02','2018/06/09'),
	(4,'2018/06/05','2018/06/06'),
	(5,'2018/06/01','2018/06/04'),
    (1,'2018/07/02','2018/07/04'),
	(2,'2018/07/01','2018/07/07'),
	(3,'2018/07/04','2018/07/08'),
	(4,'2018/07/07','2018/07/010'),
	(5,'2018/07/01','2018/07/06');

insert into RoomReservation (RoomID, ReservationID)
values (1,1), (3,2), (2,3), (4,3), (6,4), (5,5),
	(3,6),(2,7),(1,8),(5,9),(4,10);

insert into Guest (GuestName, GuestAge)
values ('Ned Stark',38), ('Brandon Stark',11), ('Rickon Stark',7),
	('Tyrion Lannister',24),('Jamie Lannister',27),
    ('Petyr Baelish',34),
	('Brienne of Tarth',26),
	('Samwell Tarly',18),('Jon Snow', 19), ('Gilly',14);

insert into ReservationGuest (ReservationID, GuestID)
values (1,1),(1,2),(1,3),
	(2,4),(2,5),
    (3,6),
    (4,7),
    (5,8),(5,9),(5,10);


insert into PromoCode (StartDate, EndDate, PercentDiscount, FlatDiscount)
values ('2018/06/01', '2018/06/05', 10, null), 
	('2018/06/01', '2018/06/05', null, 15),
    ('2018/07/01', '2018/07/31', 15, null),
	('2018/08/01', '2018/08/31', null, 25);
    
insert into ReservationPromoCode (ReservationID, PromoCodeID)
values (1,1),(2,1),(5,2);

   
insert into Bill (ReservationID, BillDate, Subtotal, Tax, Total)
values (1,'2018/06/05',312,31.20,343.20),
	(2,'2018/06/03',121,12.10,133.10),
	(3,'2018/06/09',516,51.60,557.60),
	(4,'2018/06/06',60,6,66),
	(5,'2018/06/04',334,33.40,367.20);


insert into BillDetail (BillID)
values (1), (2), (3), (4), (5);

insert into RoomRate (BillDetailID, RoomTypeID, StartDate, EndDate, Price)
values (1,2, '2018/06/01', '2018/06/05', 300),
	(1,1, '2018/06/01','2018/06/03', 121),
    (1,3, '2018/06/02','2018/06/09', 475),
    (1,1, '2018/06/05','2018/06/06', 60),
    (1,2, '2018/06/01','2018/06/04', 325);

insert into AddOn (AddOnType)
values ('Pay Per View Movie'), ('Room Service'), ('Massage'), ('Manicure'), ('Pedicure');

insert into AddOnCharge (BillDetailID, AddOnID, AddOnDate, UnitPrice, Quantity, Total)
values (1, 1, '2018/06/03', 5, 1, 5),
	(1, 2, '2018/06/04', 7, 1, 7),
    (3, 3, '2018/06/07', 40, 1, 40),
    (5, 1, '2018/06/02', 5, 2, 10);