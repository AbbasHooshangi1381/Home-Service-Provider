package Q2.service;

import Q2.entity.Person;

import java.util.List;

public interface PersonService {
    void save(Person person);
    void update(Person person,Integer id);
    Person delete(Integer id);
    List<Person> loadAll();
    Person find(Integer id);
}
