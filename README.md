# MobileWep Web Service
This project's base was created following Sergey udemy's course about Restful WEB Services.

I have added several features on top of that since them in order to improve my coding skills.

### Tools

This project is developed using the following technologies:
- **Java;**
- **Spring Boot;**
- **Spring Data** for DB access;
- **Spring Security** for secure endpoints.
- **MySQL** as database;
- **JUnit4**, **Mockito** and **MockMVC** for tests;
- **Swagger** for API Documentation;
- **Redis** for cache
- **Docker** for Project container building

# Requirements / Dependencies
- [Java 8](https://www.java.com/pt_BR/download/)
- [Maven](https://maven.apache.org/) to manage the project's build, reporting and documentation from a central piece of information;
- [Spring boot](https://spring.io/projects/spring-boot) for project setup and run;
- [Lombok](https://projectlombok.org/) To help with some Java boilerplates such as: Getter, Setter, Constructor, etc.. Also automate the creation of a Builder;
- [Model Mapper](http://modelmapper.org/) to make object mapping easy, by automatically determining how one object model maps to another, based on conventions;
- [JUnit4](https://junit.org/junit4/) for unit tests;
- [Mockito](https://site.mockito.org/) for general tests;
- [MockMVC](https://spring.io/guides/gs/testing-web/) from Spring for Rest Tests;
- [Swagger](https://swagger.io/) for really useful and simple API documentation;
- [Docker](https://www.docker.com/) To build container and reduce concerns with infrastructure.

# Running the application locally

- I Have provided two ways of running application locally. It's up to you to choose which one to use.

 ### With Docker
 - Start Docker in your local machine after cloning the project.
 - Run the command below on the root of the project:
  ```
$ docker-compose up --build
  ```
  Please make sure that MySql default port 3306 is not being used.

## Swagger
- Swagger is already configured in this project in SwaggerConfig.java.
- The API can be seen at http://localhost:8080/swagger-ui.html.
- You can also try the entire REST API directly from the Swagger interface!

## Postman Documentation

Alternatively to swagger, I have prepared a postman documentation, in which you will be able to check in details each endpoint and possible Requests and responses.

Please access it by link below:

```
https://www.getpostman.com/collections/81c694fa549846e98dca
```

### Contributors

- Murilo M. Santos <murilommms@gmail.com>

---


## Support

* If you have any query or doubt, please, feel free to contact me by e-mail.




