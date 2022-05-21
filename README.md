# Kinopoisk parse top 250 client

**Notice:** _The code was created as a test task, please use it in your applications with caution._

[Kinopoisk service](https://github.com/Wildcall/test_kinopoisk)

### This module contains:

* Rest api to represent movies retrieved by the [Kinopoisk service](https://github.com/Wildcall/test_kinopoisk)
* Simple frontend on Vue.js

### Requirements

* Java 17
* Maven

### Usage

Compile and package project with maven:


```cd path-to-project | mvn clean package spring-boot:repack```

After a successful build, you can run the application:

```
cd ../target
java -jar -DDATABASE_URL=jdbc:postgresql://localhost:5432/kinopoisk_data -DDATABASE_USERNAME=searcher_user -DDATABASE_PASSWORD=searcher_password .\kinopoisk_parser_client-0.0.1-SNAPSHOT.jar
```

## Settings