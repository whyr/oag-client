# OAG Client API

## Summary

API to get flight information from OAG.

## Configuration

In order to use this API, please sign up to https://developers.oag.com/signup

Once you have an OAG developer account, subscribe to a Flight Info 1.0 API. Two keys will be generated (a primary and a secondary).

Edit the file *src/main/resources/application.properties* and set this property with the primary key given by OAG:

```properties
oag.server.subscription-key=<TO_BE_SET>
```

## Run the application

### Requirements

The application needs the following requirements:

- Maven

### Start the application

This application can be run using the Spring Boot plugin.

```shell
$ mvn spring-boot:run
```

If an IDE is used (i.e. IntelliJ IDEa), the application can be started running the method *main()* in *com.whyr.oagclient.OAGClientApplication*

### REST API

Once the application is started, it exposes several endpoints documented in the built-in Swagger page:

```
http://localhost:8080/swagger-ui.html
```