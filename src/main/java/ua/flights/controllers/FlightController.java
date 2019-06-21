package ua.flights.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.flights.domain.Flight;
import ua.flights.services.FlightDao;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightDao dao;

    // Sample URL: http://localhost:8080/flights/search?flightNumber=2005&origin=IAH&date=2018-01-31
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")

    public List<Flight> getFilteredFlights(@RequestParam(name = "flightNumber", required = false) String flightNumber,
                                           @RequestParam(name = "origin", required = false) String origin,
                                           @RequestParam(name = "destination", required = false) String destination,
                                           @RequestParam(name = "date", required = false) String date

    ) {

        if (date == null) {
            throw new IllegalArgumentException("The 'date' parameter must not be null or empty");
        }

        if (flightNumber == null && (origin == null || destination == null)) {
            throw new IllegalArgumentException("Provide either flightNumber or both (Origin && Destination) ");
        }

        List<Flight> flightList = dao.search(date, flightNumber, origin, destination);


        return flightList;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")

    public List<Flight> getAll() {


        List<Flight> flightList = dao.search(null, null, null, null);


        return flightList;
    }


}
