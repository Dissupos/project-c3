package cz.project.c3.service.user.impl;

import org.springframework.stereotype.Service;

import cz.project.c3.domain.user.Person;
import cz.project.c3.repository.user.PersonRepository;
import cz.project.c3.service.user.IPersonService;

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
