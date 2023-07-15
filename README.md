# Research Guide Repository

**Author:** Aytan Gurbanova

**Class Name:** Research Guide

**Study Name:** A Comparative Study: Monolithic vs. Microservices Architectures

The purpose of the study is to compare monolithic architecture with microservices by creating a simple E-commerce application. 

## File structure
#### /code
contains application source codes 

#### /data
contains data files used in the project; empty at the moment 

#### /img
stores image files used in the project

#### /papers
contains relevant papers that have been analyzed

#### /presentations
contains project presentations; empty at the moment

#### /reports
contains project reports

#### /reviews
contains peer review files 

## E-commerce application
High level system design of the application is as following:

![Alt text](https://github.com/ADA-GWU/guidedresearchproject-aytan-gurbanova/blob/f5ad5c3fb071d16046edb48d40a6ac7284062430/img/e-comm%20design.png)

DB design:

![Alt text](https://github.com/ADA-GWU/guidedresearchproject-aytan-gurbanova/blob/f5ad5c3fb071d16046edb48d40a6ac7284062430/img/db%20design.png)

## Endpoints
### Authentication API
#### Register User

Registers a new user with the provided details.

**URL:** /mono/v1/auth/register

**Method:** POST

**Auth Required:** No

**Request Body**

```json
{
  "firstName": "John",
  "lastName": "Smith",
  "email": "johns@gmail.com",
  "password": "1234"
}
```

**Response**

HTTP Status: 200 OK

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huc0BnbWFpbC5jb20iLCJpYXQiOjE2ODkzNzk2MDgsImV4cCI6MTY4OTQ2NjAwOH0.aDOz7ejNqoAUgFLw94H3kJCJgY7Yj8hMCmH5P1w7JYI",
    "message": "User successfully registered."
}
```

#### Authenticate
Authenticates a user and generates an access token.

**URL:** /mono/v1/auth/authenticate

**Method:** POST

**Auth Required:** No

**Request Body**

```json
{
  "email": "johns@gmail.com",
  "password": "1234"
}
```

**Response**

HTTP Status: 200 OK

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huc0BnbWFpbC5jb20iLCJpYXQiOjE2ODkzNzk2MDgsImV4cCI6MTY4OTQ2NjAwOH0.aDOz7ejNqoAUgFLw94H3kJCJgY7Yj8hMCmH5P1w7JYI",
    "message": "User successfully authenticated."
}
```
