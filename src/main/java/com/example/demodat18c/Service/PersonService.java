package com.example.demodat18c.Service;

import com.example.demodat18c.Model.Person;
import com.example.demodat18c.Repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// annoter som service lag
@Service
public class PersonService {

    // dependency injection af repository
    @Autowired
    PersonRepo personRepo;

    // hent alle personer fra repository
    public List<Person> fetchAllPersons(){

        return personRepo.fetchAllPersons();

    }

    public void addPerson(Person person){
        personRepo.addPerson(person);
    }
}
