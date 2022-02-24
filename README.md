# eyeTunes

[![standard-readme compliant](https://img.shields.io/badge/standard--readme-OK-green.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

### Second Noroff Java assignment.

The goal of this assignment was to create a web API for an iTunes knockoff database. The database has data about customers, invoices and tracks etc. A web application was also deployed in Heroku with a song search feature. 

## Table of Contents

- [Usage](#usage)
- [API](#api)
- [Maintainers](#maintainers)
- [License](#license)

## Usage

The deployed web application can be accessed [here](https://eyetunes.herokuapp.com). The home page has a list for random songs, artists and genres with 5 random items each. There is also a search bar which the user can use to search for songs. 

## API

### Customer 

#### GET ```/api/customers/id/:customerId```

Returns a customer by ID. 


#### GET ```/api/customers/name/:name```

Returns a customer by name. 


#### GET ```/api/customers/all```

Returns all customers.


#### GET ```/api/customers/?limit={value}&offset={value}```

Returns limited amount of customers. Can be offset.


#### GET ```/api/customers/:customerId/popular/genre```

Returns customer's most popular genre.


#### POST ```/api/customers```

Creates a new customer.

Header content-type application/json.

Example body:

```
{
    "firstName": "Spongebob",
    "lastName": "Squarepants",
    "country": "Pacific Ocean",
    "postalCode": "Bikini Bottom",
    "phoneNumber": "555-5555",
    "email": "squidwardsbestfriend@crustycrab.com"
}
```


#### PATCH ```/api/customers/update```

Updates existing customer by ID.

Header content-type application/json.

Body must contain customer ID. 

Example body:

```
{
    "firstName": "Spongeboy",
    "id": 60
}
```


### Statistics

#### GET ```/api/statistics/countries/customers/count```

Returns the amount of customers per country.


#### GET ```/api/statistics/customers/top-spenders```

Returns customer data with total spending ordered descending by total spending. 


## Maintainers

[@Azruim (Nico Behnen)](https://github.com/Azruim)

[@bgf122 (Sasu Korhonen)](https://github.com/bgf122)


## License

MIT © 2022 Nico Behnen & Sasu Korhonen
