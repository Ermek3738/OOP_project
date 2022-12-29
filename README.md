# Library Management System - OOP


A Library Management System made using the concepts of Object Oriented Programming. Minimal Code is written in the GUI and the entities are decoupled as well. The interface is console based.

## Interface
<p align="middle">
   <img src="../main/images/interface.PNG" width="400"/>
   <img src="../main/images/interface2.PNG" width="400"/>
</p>   

## Actors:
The actors include the following: 
* Librarian
* Assistant
* Borrower
* Administrator

## Use Cases:
After determining the actors, the second step in use case analysis is to determine the tasks that each actor will need to do with the system. Each task is called a use case because it represents one particular way the system will be used.

### Borrower:
*  Search for items by title.
*  Search for items by author.
*  Search for items by subject.
*  Place a book on hold if it is on loan to somebody else.
*  Check  the  borrower’s  personal  information  and  list  of  books  currently
borrowed.

### Assistant:
*  All the Borrower use cases
*  Check out an item for a borrower.
*  Check in an item that has been returned.
*  Renew an item.
*  Record that a fine has been paid.
*  Add a new borrower.
*  Update a borrower’s personal information (address, telephone number etc.).

### Librarian:
*  All the Borrower and Checkout Clerk use cases
*  Add a new item to the collection.
*  Delete an item from the collection.
*  Change the information the system has recorded about an item.

### Administrator:
*  Add Assistant.
*  Add Librarian.
*  View Issued Books History.
*  View All Books in Library.


## How to Run
1 - Install these:
 * [Java SE Development Kit 8 (JDK 8)]
 * After installing JDK 8, install [NetBeans IDE]

2 - Open NetBeans IDE. Click on File -> Open Project and browse to the downloaded folder named "Project" and select it. It will load the NetBeans project.

3 - Now everything is set up except the Java DB (Derby) Database of NetBeans. So, follow these steps to set up the database:

**Step 1:** In the Netbeans Window, there is a tab named "Services" on the left. Select it. Then right click on JavaDB > Properties and    change database location to "Database" folder downloaded with this repository (its placed besides the "Project" folder).

   
**Step 2:** After that a database named LMS will show up under JavaDB tab. Now Right Click Databases > New Connection and select Java DB Network and click Next. 

   
**Step 3:** Provide the following database credentials in the next popup and click Next.
  ```
  Host: localhost
  Port: 1527
  Database: LMS
  User Name: ermek
  Password: 321
  ``` 

