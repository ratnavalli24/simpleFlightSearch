# simpleFlightSearch
I want to asses your ability to create a web application and web service. It truly is the bare minimum of knowledge necessary to be successful in this position. I don't want you to spend a lot of time on this. You should be able to do this in a few hours if the job is right for you.

### Flight Search

This programming task consists of building a simple web application to search for flights. Fork this repository and create your application. It should take this input from the user:

(Flight Number ||  (Origin && Destination)) && Date

The application will call a service that you create using either Node with Express or Java with Spring MVC. I have provided some sample data for the application in this file 


[Flight Documents](./flight-docs/flight-sample.json)



The file contains an array whose elements represent flights. The data should be defined as a constant in your service. 


You must use Angular 4 or higher. Style however you would like. You have total freedom to do what you want but make sure it can do these two things:

	• Validate that the user has provided the right criteria to make a search 
	• Neatly display the results

Create a pull request once you have it working. I will clone your repository, verify that it works, and evaluate it.

## Solution Code for Review

# Flight search REST API (Backend)

###Run  REST api server 

$ mvn package
$ java -jar target/ua.flights-0.0.1-SNAPSHOT.jar



###Test REST API

Get all flights
http://localhost:8080/flights/all


Simple search using 
http://localhost:8080/flights/search?flightNumber=2005&origin=IAH&date=2018-01-31



#Web server (Front End)

### Build SimpleFlightSearch
cd  ./AngularWebApp
npm install 


Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.



### Run server
Run `ng serve` for a dev server. 


# Simple Flight Search in action
Navigate to `http://localhost:4200/`. 
