package ua.flights.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.flights.domain.Flight;

import java.io.IOException;
import java.net.URL;


@Configuration
public class MongoConfig {
    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "embeded_db";
    ObjectMapper mapper = new ObjectMapper();

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        intializeData(mongoTemplate);
        return mongoTemplate;
    }

    private void intializeData(MongoTemplate mongoTemplate) throws IOException {
        // String out = new Scanner(new URL("http://www.google.com").openStream(), "UTF-8").useDelimiter("\\A").next();
        // String json = IOUtils.toString(  new URL("https://raw.githubusercontent.com/lvblazer/simpleFlightSearch/master/flight-docs/flight-sample.json"));
        Flight[] flights = mapper.readValue(new URL("https://raw.githubusercontent.com/lvblazer/simpleFlightSearch/master/flight-docs/flight-sample.json"), Flight[].class);

        for (Flight f : flights) {
            mongoTemplate.insert(f);
        }


    }


}