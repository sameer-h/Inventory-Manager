# Inventory Manager Application 
## Overview
This application is a project that I've created using Java and SQL through the [Apache Derby](https://db.apache.org/derby/manuals/) , a relational database management system that allows for connection between these two languages on client-side Java applications.

Additionally this application utilizes a GUI using [AWT](https://en.wikipedia.org/wiki/Abstract_Window_Toolkit) and [Swing](https://docs.oracle.com/javase/tutorial/uiswing/) which are both toolkits.

## How to Run
1. Download Product.zip and extract contents into a folder

2. Now, with this Product folder, there is already a database that has been in use by my Client. You can run the .jar file which will use the current database of items.

## Design
Here I will document the original design plan for this project

### Primary Functions - Menu Bar
<img width="519" alt="Screen Shot 2021-06-30 at 3 13 31 PM" src="https://user-images.githubusercontent.com/72811430/124025243-d32a3a00-d9b5-11eb-9cc8-1ebeb3507183.png">

### Sub-functions for each menu
<img width="719" alt="Screen Shot 2021-06-30 at 3 13 34 PM" src="https://user-images.githubusercontent.com/72811430/124025258-d6bdc100-d9b5-11eb-814d-f3cffefa0467.png">

### UML
<img width="1070" alt="Screen Shot 2021-06-30 at 3 13 45 PM" src="https://user-images.githubusercontent.com/72811430/124025285-de7d6580-d9b5-11eb-88c1-355a4da8a595.png">

#### Common Imports for my files
``` java
// hussainiaproject;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

```

### Object-Oriented Programming
* One of the key aspects of this program was the usage of *Object Oriented Programming* (OOP) was crucial to this program’s success and extensibility in terms of methods and accessing information. Through OOP use, I found it useful that I could deal with multiple classes with methods, making my program modular and easier to manage. This also meant that extensibility and maintenance of my program were easier and allowed for use of complex methods for data setting and retrieval in different situations.
* JavaDB is Oracle's supported distribution of the *Apache Derby* open source database. It supports standard *ANSI/ISO SQL* through the JDBC and Java EE APIs. Java DB is included in the JDK and Apache Derby is a relational database management system that can be embedded in Java programs (and is implemented into this one).
* Install.java serves as a class that pre-creates SQL Tables into the Database used in this IA which is named “IaDb”.
* Database Tables:
The tables: Inventory, LabList, and LabNeeds are ones that hold values for the lab stock, list of labs, and each lab’s requirements, respectively.
These tables are normalized to the third normal form (3NF) to reduce the duplication of data​ and ensure referential integrity by having 2NF and all the attributes in a table are determined only by the primary keys of that relationship and not by any non-prime structures.

### Constructors and Encapsulation
``` java
public class JavaDb {
  
  private String dbName;
  private Object[][] data;
  private Connection dbConn;
  
  public JavaDb(String dbName)
  {
    this.dbName = dbName;
    this.data = null;
    setDbConn();
  }
  
  public JavaDb()
  {
    this.dbName = "";
    this.data = null;
    this.dbConn = null;
  }
  
  ...

```

### SQL Commands for Insert, Update, Deleting Data
 #### Insert
 ``` SQL
 INSERT INTO Inventory (itemName, itemCount)
 VALUES (?,?)
 ```
  #### Update
``` SQL
  UPDATE Inventory
  SET itemCount=?
  WHERE itemName=?
 ```
### Delete
``` SQL
DELETE FROM Inventory WHERE itemName=?
```
 


