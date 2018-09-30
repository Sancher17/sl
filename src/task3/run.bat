@echo off
sqlcmd -E -S localhost -i D:\Java\Senla10_1\src\task3\create_db_tables.sql

@echo off
set root=C:\Documents and Settings\Administrator
cd %root%
mysql -u root  -e "CREATE DATABASE IF NOT EXISTS bookshop4";
mysql -u root  bookshop4 < create_db_tables.sql