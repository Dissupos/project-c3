package cz.project.c3.domain.person;

import cz.project.c3.domain.base.BaseObject;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person extends BaseObject {
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;
    @Column(name = "sex", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private SexType sex;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    public Person() {
        super();
    }

    public Person(String firstName, String lastName, SexType sex, Address address) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.address = address;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the sex
     */
    public SexType getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(SexType sex) {
        this.sex = sex;
    }

    /**
     * @return the adress
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param adress the adress to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }
}
