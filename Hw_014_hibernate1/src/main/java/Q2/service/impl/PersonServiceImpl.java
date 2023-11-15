package Q2.service.impl;

import Q2.entity.Person;
import Q2.repository.PersonRepository;
import Q2.repository.impl.PersonRepositoryImpl;
import Q2.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService<Person> {

    PersonRepositoryImpl PersonRepositoryImpl;

    public PersonServiceImpl(PersonRepositoryImpl PersonRepositoryImpl) {
        this.PersonRepositoryImpl = PersonRepositoryImpl;
    }

    @Override
    public void save(Person person) {
        PersonRepositoryImpl.save(person);
    }

    @Override
    public void update(Person person, Integer id) {
        PersonRepositoryImpl.update(person,id);

    }

    @Override
    public Person delete(Integer id) {
        return PersonRepositoryImpl.delete(id);
    }

    @Override
    public List<Person> loadAll() {
        return PersonRepositoryImpl.loadAll();
    }

    @Override
    public Person find(Integer id) {
        return PersonRepositoryImpl.find(id);
    }

    @Override
    public boolean contains(Integer id) {
        return PersonRepositoryImpl.contains(id);
    }
}
