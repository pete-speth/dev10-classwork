drop database if exists Hotel;
create database Hotel;

use Hotel;

-- CREATE SCHEMA

create table RoomType (
	RoomTypeID int auto_increment primary key,
    TypeName varchar(15) not null
);

create table Ammenity (
	AmmenityID int auto_increment primary key,
    AmmenityName varchar(30)
);

create table Room (
	RoomID int auto_increment primary key,
    RoomTypeID int not null,
    RoomNumber int not null,
    Floor int not null,
    Occupancy int not null
);

alter table Room
add constraint fk_Room_RoomType
foreign key (RoomTypeID) references RoomType(RoomTypeID);

create table RoomAmmenity (
    RoomID int not null,
    AmmenityID int not null,
    
    primary key (RoomID, AmmenityID)
);

alter table RoomAmmenity
add constraint fk_RoomAmmenity_Room
foreign key (RoomID) references Room(RoomID);

alter table RoomAmmenity
add constraint fk_RoomAmmenity_Ammenity
foreign key (AmmenityID) references Ammenity(AmmenityID);

create table Customer (
	CustomerID int auto_increment primary key,
    CustomerName varchar(75) not null,
    Phone varchar(20) null,
    Email varchar(50) null
);

create table Guest (
	GuestID int auto_increment primary key,
    GuestName varchar(75) not null,
    GuestAge int null
);

create table PromoCode (
	PromoCodeID int auto_increment primary key,
    StartDate date not null,
    EndDate date not null,
    PercentDiscount fixed(10,4) null,
	FlatDiscount fixed(10,4) null
);

create table Reservation (
	ReservationID int auto_increment primary key,
    CustomerID int not null,
    StartDate date not null,
    EndDate date not null
);

alter table Reservation
add constraint fk_Reservation_Customer
foreign key (CustomerID) references Customer(CustomerID);

create table RoomReservation (
	RoomID int not null,
    ReservationID int not null,
    
    primary key (RoomID, ReservationID)
);

alter table RoomReservation
add constraint fk_RoomReservation_Room
foreign key (RoomID) references Room(RoomID);

alter table RoomReservation
add constraint fk_RoomReservation_Reservation
foreign key (ReservationID) references Reservation(ReservationID);

create table ReservationGuest (
	ReservationID int not null,
    GuestID int not null,
    
    primary key (ReservationID, GuestID)
);

alter table ReservationGuest
add constraint fk_ReservationGuest_Reservation
foreign key (ReservationID) references Reservation(ReservationID);

alter table ReservationGuest
add constraint fk_ReservationGuest_Guest
foreign key (GuestID) references Guest(GuestID);

create table ReservationPromoCode (
	ReservationID int not null,
    PromoCodeID int not null,
    
    primary key (ReservationID, PromoCodeID)
);

alter table ReservationPromoCode
add constraint fk_ReservationPromoCode_Reservation
foreign key (ReservationID) references Reservation(ReservationID);

alter table ReservationPromoCode
add constraint fk_ReservationPromoCode_PromoCode
foreign key (PromoCodeID) references PromoCode(PromoCodeID);

create table Bill (
	ReservationID int not null primary key,
    BillDate date not null,
    Subtotal fixed(10,4) not null,
    Tax fixed(10,4) not null,
    Total fixed(10,4) not null
);

alter table Bill
add constraint fk_Bill_Reservation
foreign key (ReservationID) references Reservation(ReservationID);

create table BillDetail (
	BillDetailID int auto_increment primary key,
    BillID int not null
);

alter table BillDetail
add constraint fk_BillDetail_Bill
foreign key (BillID) references Bill(ReservationID);

create table RoomRate (
	RoomRateID int auto_increment primary key,
    BillDetailID int not null,
    RoomTypeID int not null,
    StartDate date not null,
    EndDate date not null,
    Price fixed(10,4) not null
);

alter table RoomRate
add constraint fk_RoomRate_BillDetail
foreign key (BillDetailID) references BillDetail(BillDetailID);

alter table RoomRate
add constraint fk_RoomRate_RoomType
foreign key (RoomTypeID) references RoomType(RoomTypeID);

create table AddOn (
	AddOnID int auto_increment primary key,
    AddOnType varchar(40)
);

create table AddOnCharge (
	AddOnChargeID int auto_increment primary key,
    BillDetailID int not null,
    AddOnID int not null,
    AddOnDate date not null,
    UnitPrice fixed(10,4) not null,
    Quantity int not null,
    Total fixed(10,4) not null
);

alter table AddOnCharge
add constraint fk_AddOnCharge_BillDetailID
foreign key (BillDetailID) references BillDetail(BillDetailID);

alter table AddOnCharge
add constraint fk_AddOnCharge_AddOn
foreign key (AddOnID) references AddOn(AddOnID);


