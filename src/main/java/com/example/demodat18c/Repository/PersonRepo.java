package com.example.demodat18c.Repository;

import com.example.demodat18c.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

//annoter som repository - alt i databasehåndtering
@Repository
public class PersonRepo {

    // brug JdbcTemplate
    @Autowired
    JdbcTemplate template;

    public List<Person> fetchAllPersons(){

        //her skal der hentes personer fra databasen
        String sql = "SELECT * FROM person";
        // rowmapper mapper rækker i sql-query'en til en liste af Person
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        // udfør sql query
        return template.query(sql, rowMapper);

        //List<Person> persons = new ArrayList<>();
        //persons.add(new Person("Kurt", "Hansen"));
        //persons.add(new Person("Donald", "Trump"));
        //persons.add(new Person("Hillary", "Clinton"));
        //persons.add(new Person("Benie", "Sanders"));
        //return persons;
    }

    public void addPerson(Person person){
        // prepared statement
        String sql = "INSERT INTO person (id, first_name, last_name) VALUES (?, ?, ?)";
        // udfør insert med jdbc template
        template.update(sql, person.getId(), person.getFirst_name(), person.getLast_name());
        //System.out.println("Person Id: " + person.getId());
    }

    public void deletePerson(int id){
        // delete statement
        String sql = "DELETE FROM person WHERE id=?";

        //kald update med delete statement og id
        template.update(sql, id);
    }

    public Person findPersonById(int id){
        //sql query der finder person vha. id
        String sql ="SELECT * FROM person WHERE id=?";
        //instantier rowmapper til at mappe query result til person object
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        //udfør query med JdbcTemplate
        return template.queryForObject(sql, rowMapper, id);
    }

    public void updatePerson(Person person){
        //sql statement der opdaterer rækken id med person objektet
        String sql ="UPDATE person SET first_name=?, last_name=? WHERE id=?";
        //udfør update med JdbcTemplate
        template.update(sql, person.getFirst_name(), person.getLast_name(), person.getId());
    }
}
