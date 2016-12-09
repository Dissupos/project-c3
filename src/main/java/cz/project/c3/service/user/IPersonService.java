package cz.project.c3.service.user;

import org.springframework.transaction.annotation.Transactional;

import cz.project.c3.domain.user.Person;

public interface IPersonService {
   
    @Transactional
    Person save(Person person);

}
