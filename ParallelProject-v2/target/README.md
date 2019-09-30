##Please run the below DDL query before you execute this application

Create table Bank
(
	u_name varchar2(25),
	u_salary Number(10,2),
	phone_no varchar2,
	Email Varchar2(25),
	password Varchar2(25)
);


Create table Transaction
(
	Transaction_type varchar2(20),
	Amount Number(10,2),
	Balance Number(10,2)
);
