#   Westernacher Solutions - Test Task

## Business requirements
We're building a small web application that manages user accounts in a database. The application has the following functionality:
### User Table

| Column name        | Type          |
| ------------------ |:-------------:|
| id                 | Long          |
| firstName          | String        |
| lastName           | String        |
| email              | String        |


## Build
### Requirements
To run this application you need to have following software installed in your environment

* Java 11
* Docker
* Docker Compose

### How to run the application
* First run `./gradlew build` for Linux(MacOS) or `gradlew.bat build`for Windows
* Start application into docker environment `docker-compose up --build`
* Open [Swagger API](http://localhost:8080/swagger-ui.html#/) 

## Authors
[Stefan Angelov Angelov](https://www.stefanangelov.eu/)