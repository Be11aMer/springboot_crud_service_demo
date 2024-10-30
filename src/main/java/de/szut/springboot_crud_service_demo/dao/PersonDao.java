package de.szut.springboot_crud_service_demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.szut.springboot_crud_service_demo.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {

    private final HashMap<Integer, Person> personList = new HashMap<>();

    public PersonDao() {
        initHashMap();
    }

    private void initHashMap() {
        personList.put(1, new Person(1, "Max", "Mustermann"));
        personList.put(2, new Person(2, "Erika", "Musterfrau"));
    }

    public Person create(Person person) {
        Person newPerson = new Person(person.getId(), person.getFirstname(), person.getLastname());
        personList.put(person.getId(), newPerson);
        return person;
    }

    public Person read(int id) {
        return personList.get(id);
    }

    public List<Person> readAll() {
        return new ArrayList<>(personList.values());
    }

    public Person update(int id, Person person) {
        personList.put(id, person);
        return person;
    }

    public void delete(int id) {
        personList.remove(id);

    }
}
