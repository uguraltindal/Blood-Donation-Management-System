# Blood Donation Management System

Designing a simple system for observing and managing blood types&amp;stocks through the database


## Assingment
It is desired to design a system using Java and include a user interface. The designed system should use all the information from the Postgre SQL database environment and save the necessary changes over the database as a result of the operations.

**Some of the required features are listed below**

- The database you will create must contain at least **4 tables**. Each table must contain at least **10 data**.
- You should use **primary key** and **foreign key** constraints in your tables.
- At least one **insert**, **update** and **delete** operation should be possible from the interface.
- You must create at least one **“sequence”** and ensure that the values in the relevant column are **automatically** assigned during the insertion to be made from the interface.
- You must define **2 triggers** and trigger them with the values to be entered from the interface. A notification message should be returned to the interface that the trigger is working.


## Getting Started

### PostgreSQL installation

PostgreSQL was determined as the desired database environment for the assignment. You can make the necessary installations for PostgreSQL, from [here](https://www.postgresql.org/). 

> **Note :** Please make a note of the password you set during installation.

### Creating data, constraints, tables in PostgreSQL

You can find SQL queries and generated sample data containing the features specified in the assignment above in the [database](https://github.com/uguraltindal/Blood-Donation-Management-System/tree/main/database) folder. In order to create the database environment correctly, first copy the entire query in [kizilay-schema](https://github.com/uguraltindal/Blood-Donation-Management-System/blob/main/database/kizilay-schema_1.sql) and run these queries in the database environment you created in PostgreSQL. Then apply the same process for the query in [kizilay-data.sql](https://github.com/uguraltindal/Blood-Donation-Management-System/blob/main/database/kizilay-data.sql). The order in which queries are run in the database environment is important here. Because while the SQL environment is being designed, schema queries and sample data queries are stored in separate documents so that the data can be better understood. The part you need to pay attention to here is to make sure that the schema is created before entering the data.

### Connection between Java and PostgreSQL

In order to provide the connection between Java and PostgreSQL, the PostgreSQL [jar](https://github.com/uguraltindal/Blood-Donation-Management-System/blob/main/postgresql-42.2.18.jar) file given above must be added to the code environment. [Here](https://www.cs.utexas.edu/~scottm/cs324e/Assignments/AddJarToEclipse.htm) you can see how to add the jar file to the code environment.

After that, you must enter your ID and password that you determined during PostgreSQL installations in java as follows.
