package com.example.restservice.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.entities.Greeting;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}

/*

A key difference between a traditional MVC controller and the RESTful web service controller shown
earlier is the way that the HTTP response body is created. Rather than relying on a view technology
to perform server-side rendering of the greeting data to HTML, this RESTful web service controller
populates and returns a Greeting object. The object data will be written directly to the HTTP
response as JSON.


The Greeting object must be converted to JSON. Thanks to Spring’s HTTP message converter support,
you need not do this conversion manually. Because Jackson 2 is on the classpath, Spring’s
MappingJackson2HttpMessageConverter is automatically chosen to convert the Greeting instance to JSON.




If you use Maven, you can run the application by using 
./mvnw spring-boot:run 

Alternatively, you can build the JAR file with ./mvnw clean package and then run the JAR file, as follows:

java -jar target/gs-rest-service-0.1.0.jar

*/


/*
=================================================================================
Test the Service:
*********************

Now that the service is up, visit http://localhost:8080/greeting, where you should see:
{"id":1,"content":"Hello, World!"}

Provide a name query string parameter by visiting http://localhost:8080/greeting?name=User.
Notice how the value of the content attribute changes from Hello, World! to Hello, User!, as the
following listing shows:
{"id":2,"content":"Hello, User!"}

This change demonstrates that the @RequestParam arrangement in GreetingController is working as
expected. The name parameter has been given a default value of World but can be explicitly overridden
through the query string.

Notice also how the id attribute has changed from 1 to 2. This proves that you are working against
the same GreetingController instance across multiple requests and that its counter field is being
incremented on each call as expected.

*/







