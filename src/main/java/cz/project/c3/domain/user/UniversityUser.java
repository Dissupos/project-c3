package cz.project.c3.domain.user;

import cz.project.c3.domain.other.University;
import cz.project.c3.domain.person.Person;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class UniversityUser extends PersonalUser {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id")
    private University university;

    public UniversityUser() {
        super();
    }

    public UniversityUser(String username, String password, AccountType type, String email, Person person, University university) {
        super(username, password, type, email, person);
        this.university = university;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
