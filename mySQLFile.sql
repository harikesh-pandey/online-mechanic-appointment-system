CREATE DATABASE IF NOT EXISTS AMDOCS_PROJECT;
USE AMDOCS_PROJECT;
CREATE TABLE IF NOT EXISTS CUSTOMER
(
CUST_ID VARCHAR(20) PRIMARY KEY,
CUST_NAME VARCHAR(30) NOT NULL,
VEHICLE_NO VARCHAR(20) NOT NULL,
CONTACT CHAR(20) NOT NULL,
DESCRIPTION VARCHAR(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS MECHANIC
(
MECH_ID VARCHAR(20) PRIMARY KEY,
MECH_NAME VARCHAR(30) NOT NULL,
EXPERIENCE VARCHAR(20) NOT NULL,
CONTACT CHAR(20) NOT NULL,
TIME_SLOT VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS APPOINTMENT
(
APP_ID VARCHAR(20) PRIMARY KEY,
CUST_ID VARCHAR(20) NOT NULL,
MECH_ID VARCHAR(20) NOT NULL,
APP_TIME VARCHAR(30) NOT NULL,
APP_STATUS CHAR(20) NOT NULL,
ISSUE VARCHAR(300) NOT NULL
);