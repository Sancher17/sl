
@echo off

c:\mysql\bin\mysql < CREATE DATABASE hlstats;
c:\mysql\bin\mysql < CREATE USER 'username_here'@'localhost' IDENTIFIED BY 'password_here';
c:\mysql\bin\mysql < GRANT ALL ON hlstats.* TO 'username_here'@'localhost';
c:\mysql\bin\mysql < install.sql