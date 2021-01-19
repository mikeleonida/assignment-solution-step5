## Seed code - Boilerplate for step 5 Backend - Activity Stream Assignment

### Assignment Step Description

In this case study Activity Stream Step 5, we will implement Java Persistence API (JPA) for accessing, persisting, and managing data between Java objects/classes and a relational database. 
In this step, we will create this application in two parts one for REST and other for Backend.

- REST will have all the controller classes been implemented
- The backend will have all the configuration classes, model classes, repository classes, service classes and logging aspect been implemented.

### Problem Statement

In this case study Activity Stream Step 5-Backend, We will write configuration classes, model classes, repository classes, service classes and logging aspect.

### Steps to be followed:

    Step 1: Clone the boilerplate in a specific folder on your local machine and import the same in your eclipse STS.
    Step 2: Add relevant dependencies in pom.xml file. 
        Note: Read the comments mentioned in pom.xml file for identifying the relevant dependencies.
    Step 3: Create Environment Variable thru script file (.sh for Linux & .bat for Windows). 
    Step 4: Implement ApplicationContextConfig.java and PersistanceJPAConfig.java
    Step 5: Define the data model classes (User, UserCircle, UserTag, Circle, Message)
    Step 6: See the methods mentioned in the DAO interface.
    Step 7: Implement methods in service classes.(UserService, UserCircleService,CircleService, MessageService)
    Step 8: Write code for logging aspect in LoggingAspect.java class
    Step 9: Create logback.xml file in resources folder and write code for it. 
    Step 10: Test each and every service classes with appropriate test cases.


### Project structure

The folders and files you see in this repositories is how it is expected to be in projects, which are submitted for automated evaluation by Hobbes

    Project
    |
    ├── com.stackroute.activitystream.aspect
    |        └── LoggingAspect.java
    ├── com.stackroute.activitystream.config               
    |        └── ApplicationContextConfig.java      // This class will contain the application-context for the application.
    |        └── PersistenceJPAConfig.java          // This class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer class.
    ├── com.stackroute.activitystream.repository
    |        └── CircleRepository.java                // This interface contains all the behaviors of Circle Repository
    |        └── MessageRepository.java               // This interface contains all the behaviors of Message Repository
    |        └── UserCircleRepository.java            // This interface contains all the behaviors of UserCircle Repository
    |        └── UserRepository.java                  // This interface contains all the behaviors of User Repository
    |        └── UserTagRepository.java               // This interface contains all the behaviors of UserTag Repository
    ├── com.stackroute.activitystream.model
    |        └── Circle.java                     // This class will be acting as the data model for the circle Table in the database.
    |        └── Message.java                    // This class will be acting as the data model for the message Table in the database.
    |        └── User.java                       // This class will be acting as the data model for the user Table in the database.
    |        └── UserCircle.java                 // This class will be acting as the data model for the user_circle Table in the database.
    |        └── UserTag.java                    // This class will be acting as the data model for the user_tag Table in the database.
    ├── com.stackroute.activitystream.service
    |        └── CircleService.java              // This class implements the CircleRepository interface. This class has to be annotated with @Repository annotation.
    |        └── MessageService.java             // This class implements the MessageRepository interface. This class has to be annotated with @Repository annotation.
    |        └── UserCircleService.java          // This class implements the UserCircleRepository interface. This class has to be annotated with @Repository annotation.
    |        └── UserService.java                // This class implements the UserRepository interface. This class has to be annotated with @Repository annotation.
    ├── com.stackroute.activitystream.test      // All the test case classes are made available in this package
    |        └── CircleTest.java
    |        └── MessageTest.java  
    |        └── UserAuthTest.java 
    |        └── UserCircleTest.java
    |        └── UserTest.java      
    ├── .classpath                                // This file is generated automatically while creating the project in eclipse
    ├── .hobbes                                   // Hobbes specific config options, such as type of evaluation schema, type of tech stack etc., Have saved default values for convenience
    ├── .project                                // This is automatically generated by eclipse if this file is removed your eclipse will not recognize this as your eclipse project. 
    ├── pom.xml                                 // This is a default file generated by maven if this file is removed your project will not get recognized in hobbes.
    └── PROBLEM.md                              // This files describes the problem of the assignment/project, you can provide as much as information and clarification you want about the project in this file

> PS: All lint rule files are by default copied during the evaluation process, however, if need to be customizing, you should copy from this repo and modify in your project repo

### Important instructions for Participants
> - We expect you to write the assignment on your own by following the guidelines, learning plan, and the practice exercises
> - The code must not be plagiarized, the mentors will randomly pick the submissions and may ask you to explain the solution
> - The code must be properly indented, code structure maintained as per the boilerplate and properly commented
> - Follow the problem statement shared with you

### Further Instructions on Release

*** Release 0.1.0 ***

- Right-click on the Assignment select Run As -> run on server to run your Assignment.
- Right-click on the Assignment select Run As -> JUnit Test to run your Assignment.