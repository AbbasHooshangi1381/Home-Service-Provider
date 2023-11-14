package Q2.service.impl;

import Q2.entity.Person;
import Q2.repository.PersonRepository;
import Q2.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void save(Person person) {
    personRepository.save(person);
    }

    @Override
    public void update(Person person, Integer id) {
        personRepository.update(person,id);

    }

    @Override
    public Person delete(Integer id) {
        return personRepository.delete(id);
    }

    @Override
    public List<Person> loadAll() {
        return personRepository.loadAll();
    }

    @Override
    public Person find(Integer id) {
        return personRepository.find(id);
    }
}
