# Java-Technologies
 Lab implementations for the Java Technologies course

### Laboratory 1
 Server can be found at https://java-technologies-lab1.herokuapp.com/lab1-1
- [x] Create the servlet according to description
- [x] Invoke the service via a desktop app
- [x] Analyze performance

### Laboratory 2
- [X] Created a Web Application with the mentioned components
- [X] Created the aforementioned web filters
- [X] Created the web listener that reads a context init parameter to set a default category
- [X] Created a "hand-made" cookie to store and restore the client's selected category
- [X] Added a captcha to the input form

### Laboratory 3
- [X] Created Web Page for defining exams
- [X] Created Web Page for defining students
- [X] Used relational database and JDBC in order to store and retrieve data
- [X] Used at least one non trivial JSF component
- [X] Internationalized the user interface
- [X] Solved the problem
- [X] Created a page for displaying the results
- [X] Created a random generator that produces problem instances of various sizes

### Laboratory 4
- [X] Created the pages using templates
- [X] Created at least one composite component
- [X] Used the components ajax and poll in order to continuously display information
- [X] Implemented an efficient way for obtaining connections to the database

  SQL command to see database connections: 
  SELECT pid FROM pg_stat_activity WHERE datname='lab3' AND application_name<>'psql';;
  
  SQL command to kill all connections:
  SELECT 
    pg_terminate_backend(pid) 
  FROM 
    pg_stat_activity 
  WHERE
    pid <> pg_backend_pid()
    AND datname = 'lab3';
	
  After closing all connection we get the following outcome: 
  org.postgresql.util.PSQLException: This connection has been closed.
  
  If we're not closing the connection, thus not returning it to the pool, we get the following outcome:
  javax.resource.spi.ResourceAllocationException: Error in allocating a connection. Cause: In-use connections equal max-pool-size and expired max-wait-time. Cannot allocate more connections.
  
### Laboratory 5
- [X] Rewrote the persistence layer of the application according to the JPA specifications
- [X] Created two new type of Exams: Written Test and Project Manager, each having a list of distinct attributes
- [X] Implemented an exam search using JPA Criteria API

### Laboratory 6
- [X] Rewrote the data access layer of the application as Enterprise Java Beans
	- [X] Used the EJB support for implementing transactions
	- [X] Created a page where the user can claim a resource for a specific exam
		- [X] Created a stateless bean for checking the availability of a resource
		- [X] Created a session bean responsible for the assignment of the resources
		- [X] Created a singleton bean that keeps an in-memory map of the current assignments
	- Notes: 
		- Also created a page where the user can add new resources and view which resources have been claimed by which exam and in which quantity
		- The assignments resource-exams are made using a ManyToMany table with an additional column
- [X] Created test cases that highlight the performance gain of using various forms of JPA optimizations: Second Level Cache, Lazy Loading and Entity Graphs
	- [X] Used an EJB interceptor in order to show the running time of the tests
	- [X] Created a timer that will trigger the invocation of the tests every 30 seconds
	- Test results:
		- Fetch Eager: 740 ms
		- Fetch Lazy: 18 ms
		- Second Level Cache: 14 ms
		- Entity Graph: 299 ms
		
