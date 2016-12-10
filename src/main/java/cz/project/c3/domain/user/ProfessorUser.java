package cz.project.c3.domain.user;

import cz.project.c3.domain.other.University;
import cz.project.c3.domain.person.Person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PROFESSOR")
public class ProfessorUser extends UniversityUser {
    public ProfessorUser() {
        super();
    }

    public ProfessorUser(String username, String password, AccountType type, String email, Person person, University university) {
        super(username, password, type, email, person, university);
    }
}
