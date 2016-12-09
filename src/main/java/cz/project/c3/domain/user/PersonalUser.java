package cz.project.c3.domain.user;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class PersonalUser extends User {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    public PersonalUser(String username, String password, AccountType type, String email, Person person) {
        super(username, password, type, email);
        this.person = person;
    }

    public PersonalUser() {
        super();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
