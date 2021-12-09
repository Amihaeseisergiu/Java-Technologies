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
		
### Laboratory 7
- [X] Created a JSF Application for managing submissions of documents
	- [X] An authentication mechanism based on username and password. Additionally, the current logged in user is kept in a session and content is displayed according to his role. Also, the users' passwords are encrypted.
	- [X] Register new users and assign them a specific role. When registering, the application verifies if there is already an user with the same name.
	- [X] The possibility to upload a document. The uploaded document is kept as a blob in the database.
	- [X] The possibility to view documents. The documents are displayed in the table, and the admin can view it's content via PrimeFaces' Media Component or view its authors.
- [X] User Contexts and Dependency Injection for:
	- [X] the management of application's beans (@Inject) and transactions (@Transactional)
	- [X] decoupling the components using dependency injection. Registration Numbers are generated as UUIDs.
	- [X] decoupling orthogonal concerns, such as logging;
	- [X] decoupling bussines concerns, such as verifying the date for operations like registration and submission
	- [X] implementing at least one event-based comunication. Created DataEdit and DataView abstract classes to send Events between each other. Also these classes send WebSocket notifications to the client.
	- [X] data validation, using Bean Validation annotations.
		
### Laboratory 8
- [X] Created RESTFul Web service using JAX-RS for Document CRUD operations. Every change to a document will reflect on the frontend aswell via WebSockets. File upload is also supported via form parameters.
	- [X] Used JSON for representing consumed and produces data
	- [X] Commented the services using OpenAPI
	- [X] Tested at least one service (Only with Postman)
- [X] Created a filter that will act as a cache for the ViewDocumentService. The method that will be cached is marked with an annotation. Used CDI Events to determine when to reset the cache.
- [X] Modified the model such that every documents has a list of other documents that represents its references.
	- [X] The /documents/view endpoint returns asyncronously.
	- [X] Created another service that reads asynchronously from /documents/view all the documents and:
		- [X] Verifies that there are no circuits in the corresponding directed graph
		- [X] establishes a chronological order of the documents based on their references
		
### Laboratory 9
- [X] Added the following security features  to the app:
	- [X] Authentication, using JDBC Realm
	- [X] Controlled acces to Web Resources, using Web Constraints
	- [X] Secured business logic and REST services
- [ ] Exposed a CRUD REST resource from the previous lab as a microservice
	- [ ] Ran the microservice using an Eclipse Microprofile server implementation
	- [ ] Created an additional microservice that invokes the first one
	- [ ] Created Docker containers for the microservices. Also deployed the database as a container.