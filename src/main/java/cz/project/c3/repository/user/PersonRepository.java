package cz.project.c3.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.project.c3.domain.user.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
