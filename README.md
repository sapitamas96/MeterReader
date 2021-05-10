# Basic Functionality
---

This application is to communicating with smart meters and measuring devices to store and retrieve data. The communication happens through **REST endpoints** and data is stored in a **MySQL database**.
The application is capable to communicate with more than one client from different addresses however **restricts communication with more than one measuring device from one address**. The clients are identified by a **client id** which has to be unique for every device. From one address the first client with sufficient id will be recognized.


# Configuration
---

Before first starting the application create an empty MySQL database to store the data. The application will automatically create the needed tables and populate them with sample data.

**The created database is needed to be configured in the application through the next environment variables:**

| Name    | Example Value | |
| --------|---------------|---|
| DB_URL  | jdbc:mysql://localhost:3306/meter_reading_db | JDBC URL of the database.|
| DB_USERNAME | root | MySQL username|
| DB_PASSWORD | password | MySQL password|


# REST endpoints
---
## Add new reading

Accepts json object in body, and stores the the data in the database. If the request is successful it returns with the same json object else it returns with an error message.
The request needs to be sent to the host address of the application. The following request is just an example.

**HTTP Request example:**

```
	POST https://8.8.8.8:8080/consumptions/add
```

**Parameters**

| Name | Data Type | Explanation |
|------|-----------|-------------|
| clientId | String | Unique id for every measuring device (max lenght 255 char) |
| year | Integer | Year of the reading |
| month | String | Month of the reading |
| consumption | Double | Monthy consumption |

**Example request**

```json
	{
		"clientId" : "5d2TNaCtBH",
		"year" : 2021,
		"month" : "January",
		"consumption" : 14.5
	}
```

## Monthy consumption

Returns monthly consumption specified by json in the body of the HTTP request. If the request is successful it returns a json object with the consumption, else it returns an error message.
The request needs to be sent to the host address of the application. The following request is just an example.

**HTTP Request example:**

```
	POST https://8.8.8.8:8080/consumptions/month
```

**Parameters**

| Name | Data Type | Explanation |
|------|-----------|-------------|
| clientId | String | Unique id for every measuring device (max lenght 255 char) |
| year | Integer | Year of the reading |
| month | String | Month of the reading |
| consumption | Double | Monthy consumption |

**Example request**

```json
	{
		"clientId" : "5d2TNaCtBH",
		"year" : 2021,
		"month" : "January"
	}
```

**Example response**

```json
	{
		"year" : 2021,
		"month" : "January",
		"consumption" : 14.5
	}
```

## Yearly per month consumption

Returns list of monthly consumptions for a given year specified by json in the body of the HTTP request. If the request is successful it returns a json object with the consumptions, else it returns an error message.
The request needs to be sent to the host address of the application. The following request is just an example.

**HTTP Request example:**

```
	POST https://8.8.8.8:8080/consumptions/yearly/month
```

**Parameters**

| Name | Data Type | Explanation |
|------|-----------|-------------|
| clientId | String | Unique id for every measuring device (max lenght 255 char) |
| year | Integer | Year of the reading |
| monthlyConsumption | List | List of monthly consumptions for a specific year |

**Example request**

```json
	{
		"clientId" : "5d2TNaCtBH",
		"year" : 2021
	}
```

**Example response**

```json
	{
		"year" : 2021,
		"monthlyConsumption" : {
			"January" : 14.5,
			"February" : 13,
			"March" : 15.5,
			"April" : 12
		}
	}
```

## Aggregated yearly consumption

Returns the sum of yearly consumption specified by json in the body of the HTTP request. If the request is successful it returns a json object with the whole year consumption, else it returns an error message.
The request needs to be sent to the host address of the application. The following request is just an example.

**HTTP Request example:**

```
	POST https://8.8.8.8:8080/yearly
```

**Parameters**

| Name | Data Type | Explanation |
|------|-----------|-------------|
| clientId | String | Unique id for every measuring device (max lenght 255 char) |
| year | Integer | Year of the reading |
| consumption | Double | Yearly consumption |

**Example request**

```json
	{
		"clientId" : "5d2TNaCtBH",
		"year" : 2021
	}
```

**Example response**

```json
	{
		"year" : 2021,
		"consumption" : 55
	}
```