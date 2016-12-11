package cz.project.c3.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.project.c3.domain.base.BaseObject;
import cz.project.c3.domain.role.Role;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "c3_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public class User extends BaseObject implements UserDetails {
    @Column(name = "user_name", length = 30, unique = true, nullable = false, updatable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "user_type", nullable = false, length = 10)
    private AccountType type;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @JsonIgnore
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    @JsonIgnore
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @JsonIgnore
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @JsonIgnore
    @Column(name = "enabled")
    private boolean enabled = true;

    @JsonIgnore
    @Transient
    private List<? extends GrantedAuthority> authorities;

    public User() {
        super();
    }

    public User(String username, String password, AccountType type, String email) {
        super();
        this.username = username;
        this.password = password;
        this.type = type;
        this.email = email;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (type != user.type) return false;
        if (getId() != user.getId()) return false;
        return email.equals(user.email);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
