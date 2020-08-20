# Java + Spring + Hibernate Search Lib

This is a Spring Boot project which uses the Hibernate Search lib. A powerfull lib that offers full-text search support for objects stored by Hibernate ORM. With that lib, you can: search words with text, order results by relevance and find by approximation (fuzzy search). More details, please check the official link: https://hibernate.org/search

In this specific project, the following topics were used:

* Java 8
* Maven
* Spring Framework
* Spring Boot
* Hibernate Search Lib
* Lombok
* REST
* Data manipulation
* Tests

Application developed using Spring Boot and it is prepared to load data into the H2 database through the files schema.sql (DDL) and data.sql (load).

To run the application just use the following command:
```sh
mvn spring-boot:run
```

## Objectives

#### Data modeling
Modeling of two entities, one called product and another called category (product category), with relationship between them.

#### REST services

List of all product categories
```sh
http://localhost:8080/api/category/listAll
```
List of all products filtered by category
```sh
http://localhost:8080/api/product/listByCategory/{categoryId}
```
#### Tests
JUnits created (Unit tests and Integrated tests).


------------



- 100 % percentage of coverage

- Hibernate Search Lib: Additional service for querying the category with the highest occurrence of a given letter in its name. For example, if we have the category Toys and the category Medicines and I call this service by passing the letter "e" as a parameter, the return of the service should be the category Medicines that has the highest occurrence of the letter "e".