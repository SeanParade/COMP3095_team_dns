# mySql version 5.7
DROP DATABASE IF EXISTS COMP3095;

CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
grant all on COMP3095.* to 'admin'@'localhost' identified by 'admin'; 

CREATE TABLE DEPARTMENT
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	departmentName varchar(255) NOT NULL UNIQUE,
	location varchar(255)
	);
    
INSERT INTO DEPARTMENT(departmentName, location)
VALUES
("Accounting", "Room 1B"),
("Administration", "Room 1A"),
("Marketing", "Room 2B"),
("IT", "Room B01"),
("Human Resources", "Room 1D");


CREATE TABLE EGROUP
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	groupName varchar(255) UNIQUE,
	departmentId int(11)
	
);

CREATE TABLE EMPLOYEE
(
	id int(11) PRIMARY KEY,
	firstName varchar(255) NOT NULL,
	lastName varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
    username varchar(255) UNIQUE,
	hireYear varchar(255),
	departmentId int(11),
	groupId int(11),
    role varchar(255),
    password varchar(30),
    token varchar(255),
    UNIQUE(firstName, lastname, email),
	
	
	INDEX(departmentId),

	FOREIGN KEY(departmentId)
		REFERENCES department(id)
		ON UPDATE CASCADE ON DELETE RESTRICT

);

INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, role, departmentId, username, password)
VALUES
(0000, "admin", "!", "admin@domain.ca", "2015", "Administrator", 4, "admin", "admin"),
#accounting
(1001,"Rich", "Gregory", "rich.gregory@gmail.com", "2017", "Accountant", 1, "rgregory", "password"),
(1002, "Nita", "Cochrane", "nita.cochrane@gmail.com", "2017", "Accountant", 1, "ncochrane", "password"),
#admin
(2001, "Simon", "Martin", "simon.martin@gmail.com", "2017", "Receptionist", 2, "smartin", "password"),
(2002, "Mindy", "Kelly", "mindy.kelly@gmail.com", "2017", "Office Administrator", 2, "mkelly", "password"),
#marketing
(3001, "Magaret", "Oneill", "magaret.oneill@gmail.com", "2017", "Sales", 3, "moneill", "password"),
(3002, "Donnie", "Zhang", "donnie.zhang@gmail.com", "2017", "Sales", 3, "dzhang",  "password"),
#it
(4001, "Hyon", "Barnhill", "hyon.barnhill@gmail.com", "2017", "Network Admin", 4, "hbarnhill", "password"),
(4002, "Jazmine", "Velasquez", "jazmine.velasquez@gmail.com", "2017", "Database Admin", 4, "jvelasquez", "password"),
(4003, "Deb", "Jude", "deb.jude@gmail.com", "2017", "Web Developer", 4, "djude", "password"),
#human resources
(5001, "Sui", "Layne", "sui.layne@gmail.com", "2017", "HR Manager", 5, "slayne", "password"),
(5002, "Leota", "Luckett", "leota.luckett@gmail.com", "2017", "HR Advisor", 5, "lluckett", "password");

CREATE TABLE REPORT
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	templateName varchar(255) NOT NULL,
	title varchar(255) NOT NULL UNIQUE,
	date DATE NOT NULL,
	departmentId int(11) NOT NULL,
	groupId int(11),
	employeeId int(11),
	totalEvaluation int(2),

		INDEX(departmentId), INDEX(groupId), INDEX(employeeId),

		FOREIGN KEY(departmentId)
			REFERENCES department(id)
			ON UPDATE CASCADE ON DELETE RESTRICT,

		FOREIGN KEY(groupId)
			REFERENCES egroup(id)
			ON UPDATE CASCADE ON DELETE RESTRICT,

		FOREIGN KEY(employeeId)
			REFERENCES employee(id)
			ON UPDATE CASCADE ON DELETE RESTRICT

	
);
CREATE TABLE REPORT_ITEM
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	subTitle varchar(255),
	evaluation int(1),
	description varchar(255),
	reportId int(11),

	INDEX(reportId),
	
	FOREIGN KEY(reportId)
		REFERENCES report(id)
		ON UPDATE CASCADE ON DELETE RESTRICT

);

CREATE TABLE EMPLOYEE_ATTENDANCE
(
	id int(11) AUTO_INCREMENT PRIMARY KEY,
	date DATE NOT NULL,
	present BOOLEAN NOT NULL,
	employeeId int(11) NOT NULL,

	INDEX(employeeId),
	
	FOREIGN KEY(employeeId)
		REFERENCES employee(id)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

