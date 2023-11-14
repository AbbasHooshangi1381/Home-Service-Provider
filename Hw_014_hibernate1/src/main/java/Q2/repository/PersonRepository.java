package Q2.repository;

import Q2.entity.Person;

import java.util.List;

public interface PersonRepository {
    void save(Person person);
    void update(Person person,Integer id);
    Person delete(Integer id);
    List<Person> loadAll();
    Person find(Integer id);

}
