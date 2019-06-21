package ua.flights.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.flights.domain.Flight;

import java.util.List;


@Repository
public class FlightDao {
    @Autowired
    public MongoTemplate mongoTemplate;

    // (Flight Number || (Origin && Destination)) && Date

    public List<Flight> search(String date, String flightNumber, String origin, String destination) {
        Query query = new Query();

        Criteria c1 = new Criteria();
        if (date != null && !date.isEmpty())
            c1 = new Criteria().orOperator(Criteria.where("arrival").regex("^" + date),
                    Criteria.where("departure").regex("^" + date));

        Criteria c2 = new Criteria();
        if (flightNumber != null && !flightNumber.isEmpty())
            c2 = Criteria.where("flightNumber").is(flightNumber);

        Criteria c3 = new Criteria();
        if (origin != null && !origin.isEmpty())
            c3 = Criteria.where("origin").is(origin);

        Criteria c4 = new Criteria();
        if (destination != null && !destination.isEmpty())
            c4 = Criteria.where("destination").is(destination);

        query.addCriteria(new Criteria().andOperator(c1, c2, c3, c4));

        System.out.println("query - " + query.toString());

        List<Flight> result = mongoTemplate.find(query, Flight.class);

        return result;

    }

}