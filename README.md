## LibraryManagementSystem
It is a whole pipeline from building the application to the deployment. This is the sample application which contains four microservices and configured with Eureka server and Client.
###Technology Used:
 - Spring Boot
 - MySql 
 - Eureka server and Client
 - Docker
 - Jenkins
####Microservices:
1. Eureka-Server 
-This service has only eureka server configured 
2. Student
-This service has the Student related CRUD operations 
3. Library
-This service acts the library database which has all the books related information
4. Order
-This service is used for issuing the book, returning the book and for all the calculation regarding delays and fine.
