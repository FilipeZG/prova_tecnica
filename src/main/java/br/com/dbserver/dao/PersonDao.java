package br.com.dbserver.dao;

import br.com.dbserver.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PersonDao {

    private static List<Person> persons = new ArrayList<>();

    static {
        persons.add(new Person(1, "Filipe"));
        persons.add(new Person(2, "Alex"));
        persons.add(new Person(3, "Fábio"));
        persons.add(new Person(4, "Juliana"));
        persons.add(new Person(5, "Maria"));
    }

    public List<Person> listAll() {
        return Collections.unmodifiableList(persons);
    }

    public Person findById(int id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .collect(Collectors.toList()).get(0);
    }

}
