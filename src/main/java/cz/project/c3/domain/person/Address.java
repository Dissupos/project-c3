package cz.project.c3.domain.person;

import cz.project.c3.domain.base.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "address", uniqueConstraints = {@UniqueConstraint(columnNames = {"country", "city"})})
public class Address extends BaseObject {
    @Column(name = "country", nullable = false, length = 30)
    private String country;
    @Column(name = "city", nullable = false, length = 30)
    private String city;

    public Address() {
        super();
    }

    public Address(String country, String city) {
        super();
        this.country = country;
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

}
