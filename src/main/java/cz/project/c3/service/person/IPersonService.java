package cz.project.c3.service.person;

import org.springframework.transaction.annotation.Transactional;

import cz.project.c3.domain.person.Person;

public interface IPersonService {
   
    @Transactional
    Person save(Person person);

}
