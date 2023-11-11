create database electric_bill_system;
use electric_bill_system;

create table Sign_Up( meter_number varchar(20), username varchar(20), name varchar(30), password varchar(30), usertype varchar(20));
select * from Sign_Up;
create table new_customer(name varchar(30), meter_No varchar(20), address varchar(60), city varchar(25), state varchar(25), email varchar(35), phone_No varchar(13));
select * from new_customer;
create table meter_Info(meter_number varchar(30), meter_location varchar(20), meter_Type varchar(30), phase_code varchar(10), bill_Type varchar(20), days varchar(10));
select * from meter_Info;
create table tax(cost_per_unit varchar(20), meter_rent varchar(20), service_charge varchar(20), service_tax varchar(20), swacch_bharat varchar(20), fixed_tax varchar(20));
select * from tax;
insert into tax values('10','45','20','58','5','18');

create table bill(meter_no varchar(20), month varchar(20), unit varchar(20), total_bill varchar(20), status varchar(20));
select * from bill;