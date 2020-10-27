# REST API demo

This is a simple, portable REST API demo using a family member database.  At startup of the database, "Will" will be the only entry with ID = 1.  Please note the database is meant to be used locally with Docker and run on port 5430 (so as not to conflict with any Postgres DB you already have running on your machine already using 5432).

The application will load in the username, password, and URL for the DB via the app.properties.  You can easily just change this if you want to modify the DB, such as the port number for example (in practice you will not usually include the app.properties in a public repo, I know that!).

## Usage instructions

First, ensure you have Docker installed and running properly as well as support for Java 8.  The application is most easily used (and was used by me) with Postman.  Maven will be needed to package the project.

Using Maven, build the application:
```
mvn clean package
```

Navigate to ./SQL and run the following commands:
```
docker build -t restdemo .
docker run -p 5430:5432 restdemo

```

Launch the API server using Maven or Java.

Finally, you can now send GET, POST, PUT, and DELETE requests to localhost:8080/API/people/ to perform all CRUD operations.  

For example, using Postman, I can send a GET request to localhost:8080/API/people/1 to return my name in the response body in JSON.
Moreover, I can add a family member to the database by sending a POST request to localhost:8080/API/people/ with just a name in the request body in JSON format, like so:
```
{
    "name": "Will"
}
```

