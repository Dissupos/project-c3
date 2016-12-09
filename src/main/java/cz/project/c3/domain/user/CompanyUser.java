package cz.project.c3.domain.user;

import javax.persistence.*;

@Entity
@DiscriminatorValue("COMPANY")
public class CompanyUser extends PersonalUser {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public CompanyUser() {
        super();
    }

    public CompanyUser(String username, String password, AccountType type, String email, Person person, Company company) {
        super(username, password, type, email, person);
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
