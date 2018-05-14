use Hotel;

-- Write a query that returns a list of reservations that end tomorrow.
select *
from Reservation
having EndDate = adddate(curdate(), interval 1 day);

-- Write a query that returns all the rooms reserved for a particular customer.
select r.RoomNumber, c.CustomerName, res.StartDate, res.EndDate
from Room r
	inner join RoomReservation rr on r.RoomID = rr.RoomID
    inner join Reservation res on rr.ReservationID = res.ReservationID
    inner join Customer c on res.CustomerID = c.CustomerID
where c.CustomerName = 'Tyrion Lannister';

-- Write a query that returns rooms that do not contain a specific amenity.
select RoomNumber
from Room
where RoomID not in 
	(select r.RoomID
	from Room r
		inner join RoomAmmenity ra on r.RoomID = ra.RoomID
		inner join Ammenity a on a.AmmenityID = ra.AmmenityID
	where AmmenityName = 'Spa Bath');

-- Write a query that returns all the rooms available for a date range.
select RoomNumber
from Room
where RoomID not in
	(select r.RoomID
	from Room r
		inner join RoomReservation rr on r.RoomID = rr.RoomID
		inner join Reservation res on rr.ReservationID = res.ReservationID
		inner join Customer c on c.CustomerID = res.CustomerID
	where res.StartDate >= '2018/06/01' and res.EndDate <= '2018/06/05');
    
-- Write a query that returns the 3 most expensive bills upcoming in the next month.
select CustomerName, Total, BillDate
from Bill b
	inner join  Reservation res on b.ReservationID =res.ReservationID
    inner join Customer c on c.CustomerID = res.CustomerID
order by Total desc
limit 0,3;
