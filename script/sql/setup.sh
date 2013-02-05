#!/bin/sh
echo "Create databases"
mysql -u root -pStudent2012 < create_dbs.sql

echo "Import data"
mysql -u root -pStudent2012 Charity_DB_Test_Model < Charity_DB_Test_Model_Script_v0.96_includingData.sql 

mysql -u root -pStudent2012 System_DB_Test_Model < System_DB_Test_Model_Script_v0.7_IncludingData.sql 