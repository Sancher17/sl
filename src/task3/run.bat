@echo off
mysql -uroot -p1512 < create_db_tables.sql
mysql -uroot -p1512 < data.sql