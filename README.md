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
### Authentication APIs
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

### User API
#### Update User

Updates user address and phone number information 

**URL:** /mono/v1/user/update

**Method:** PUT

**Auth Required:** Yes

**Request Body**

```json
{
  "email": "johns@gmail.com",
  "address": "Baku, 2361",
  "phoneNumber": "123456789"
}
```

**Response**

HTTP Status: 200 OK

```json
{
    "email": "johns@gmail.com",
    "firstName": "John",
    "lastName": "Smith",
    "address": "Baku, 2361",
    "phoneNumber": "123456789",
    "createdAt": "2023-07-15T00:06:48.657+00:00",
    "updatedAt": "2023-07-15T00:32:11.822+00:00"
}
```

### Product APIs
#### Filter Products

Retrieves a list of products based on specified filters.

**URL:** /mono/v1/product/filter

**Method:** GET

**Auth Required:** Yes

**Parameters:**

- brand (optional): Filter products by brand
- productName (optional): Filter products by name
- categoryName (optional): Filter products by category name
- priceMin (optional): Filter products by minimum price
- priceMax (optional): Filter products by maximum price

Example Request: http://localhost:8080/mono/v1/product/filter?priceMin=50&priceMax=200

**Response**

HTTP Status: 200 OK

```json
[
    {
        "name": "Adidas",
        "description": "Official size football",
        "price": 50.0,
        "quantity": 15,
        "brand": "Football",
        "category": "Sports",
        "createdAt": "2023-07-05T03:00:52.552+00:00",
        "updatedAt": "2023-07-05T03:00:52.552+00:00"
    },
    {
        "name": "KitchenAid",
        "description": "Complete stainless steel cookware set",
        "price": 200.0,
        "quantity": 5,
        "brand": "Cookware Set",
        "category": "Kitchenware",
        "createdAt": "2023-07-05T03:00:52.552+00:00",
        "updatedAt": "2023-07-05T03:00:52.552+00:00"
    }
]
```

#### Search Products

Searches for products based on a keyword.

**URL:** /mono/v1/product/search

**Method:** GET

**Auth Required:** Yes

**Parameters:**

- keyword (required): Search for name, brand, description or category 

Example Request: http://localhost:8080/mono/v1/product/search?keyword=ware

**Response**

HTTP Status: 200 OK

```json
[
    {
        "name": "KitchenAid",
        "description": "Complete stainless steel cookware set",
        "price": 200.0,
        "quantity": 5,
        "brand": "Cookware Set",
        "category": "Kitchenware",
        "createdAt": "2023-07-05T03:00:52.552+00:00",
        "updatedAt": "2023-07-05T03:00:52.552+00:00"
    }
]
```

### Cart APIs
#### Add to Cart

Adds a product to the user's cart.

**URL:** /mono/v1/cart/add

**Method:** POST

**Auth Required:** Yes

**Parameters:**

- productId (required): ID of the product to add to the cart
- quantity (required): Quantity of the product to add

Example Request: http://localhost:8080/mono/v1/cart/add?productId=2&quantity=5

**Response**

HTTP Status: 200 OK

```json
{
    "email": "johns@gmail.com",
    "productName": "Nike",
    "quantity": 5,
    "createdAt": "2023-07-15T02:30:10.674+00:00",
    "updatedAt": "2023-07-15T02:30:10.674+00:00"
}
```

#### View Cart

Retrieves the user's cart.

**URL:** /mono/v1/cart/view

**Method:** GET

**Auth Required:** Yes

**Response**

HTTP Status: 200 OK

```json
[
    {
        "email": "johns@gmail.com",
        "productName": "Nike",
        "quantity": 5,
        "createdAt": "2023-07-15T02:30:10.674+00:00",
        "updatedAt": "2023-07-15T02:30:10.674+00:00"
    }
]
```

#### Remove Product
Removes a product from the user's cart.

**URL:** /mono/v1/cart/remove/product/{productId}

**Method:** DELETE

Path Parameter:

productId (required): ID of the product to remove from the cart.

Example Request: http://localhost:8080/mono/v1/cart/remove/product/2

**Response**

HTTP Status: 200 OK

```json
[
    {
        "email": "johns@gmail.com",
        "productName": "Nike",
        "quantity": 4,
        "createdAt": "2023-07-15T02:30:10.674+00:00",
        "updatedAt": "2023-07-15T03:00:47.145+00:00"
    }
]
```

### Order APIs
#### Place Order

Place an order with the specified list of order requests.

**URL:** /mono/v1/order/place

**Method:** POST

**Auth Required:** Yes

**Request Body**

```json
[
    {
    "productId": 8,
    "quantity": 2
  },
  {
    "productId": 2,
    "quantity": 3
  }
]
```

**Response**

HTTP Status: 200 OK

```json
{
    "email": "johns@gmail.com",
    "orderProduct": [
        {
            "productName": "IKEA",
            "quantity": 2
        },
        {
            "productName": "Nike",
            "quantity": 3
        }
    ],
    "totalAmount": 1675.0,
    "createdAt": "2023-07-15T03:02:57.897+00:00",
    "updatedAt": "2023-07-15T03:02:57.897+00:00"
}
```

#### View Orders

View the list of orders for the authenticated user.

**URL:** /mono/v1/order/view

**Method:** GET

**Auth Required:** Yes

**Response**

HTTP Status: 200 OK

```json
{
    "email": "johns@gmail.com",
    "orderProduct": [
        {
            "productName": "IKEA",
            "quantity": 2
        },
        {
            "productName": "Nike",
            "quantity": 3
        }
    ],
    "totalAmount": 1675.0,
    "createdAt": "2023-07-15T03:02:57.897+00:00",
    "updatedAt": "2023-07-15T03:02:57.897+00:00"
}
```

