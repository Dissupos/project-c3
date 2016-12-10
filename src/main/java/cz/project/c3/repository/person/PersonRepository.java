package cz.project.c3.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.project.c3.domain.person.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
