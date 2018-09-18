# DataSecurity

To create database, open sqlite3 shell.
Then type ".open [name of database]"

Ex. If your database name is testbase, you type .open testbase

This will create a new instance of the database if it does not already exist.

You then enter the following command:

.import MOCK_DATA.csv [table name];

Ex.
.import MOCK_DATA.csv student_info;

This creates a new table named student info and then creates rows with whatver data was in your .csv file.

You will need to make sure that the .csv file is in whatever directory you have your sqlite shell and .db file in.
