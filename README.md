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




For app deployment:

In Android Studio, you need to create an instance for a virtual device (we use a Pixel 2) running API of 28 or greater.
You need to import the project from the file system after cloning the the project.

After import, you need to build the project, launch the instance of the virtual device, and run the project.

The login page should pop up on your virtual device.
