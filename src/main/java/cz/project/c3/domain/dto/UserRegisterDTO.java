package cz.project.c3.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cz.project.c3.domain.user.AccountType;
import cz.project.c3.domain.user.SexType;
import cz.project.c3.service.validation.ValidPassword;

public class UserRegisterDTO extends UserDTO {
    @NotNull
    @Size(min = 3, max = 30)
    private String username;

    @NotNull
    @Size(min = 1, max = 255)
    private String email;

    @NotNull
    private AccountType accountType;

    @ValidPassword
    private String password;

    public UserRegisterDTO() {
        super();
    }

    public UserRegisterDTO(String firstName, String lastName, SexType sex, String country, String city, String username,
            String email, String password, AccountType accountType) {
        super(firstName, lastName, sex, country, city);
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    /**
     * @return the accountType
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
