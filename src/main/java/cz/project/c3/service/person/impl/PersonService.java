package cz.project.c3.service.person.impl;

import org.springframework.stereotype.Service;

import cz.project.c3.domain.person.Person;
import cz.project.c3.repository.person.PersonRepository;
import cz.project.c3.service.person.IPersonService;

@Service
public class PersonService implements IPersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

}
