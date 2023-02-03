package br.example.rest.services;

import br.example.rest.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
@Component
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){

        logger.info("Finding one person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Carlos");
        person.setLastName("Santos");
        person.setAddress("São Paulo");
        person.setGender("Male");
        return person;
    }

}