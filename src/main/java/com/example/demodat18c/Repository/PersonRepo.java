package com.example.demodat18c.Repository;

import com.example.demodat18c.Model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepo {

    public List<Person> fetchAllPersons(){

        //her skal der hentes personer fra databasen

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Kurt", "Hansen"));
        persons.add(new Person("Donald", "Trump"));
        persons.add(new Person("Hillary", "Clinton"));
        persons.add(new Person("Benie", "Sanders"));

        return persons;
    }

}
