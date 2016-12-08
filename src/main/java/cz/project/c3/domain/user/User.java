package cz.project.c3.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.project.c3.domain.base.BaseObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

/**
 * User domain
 *
 * @author dic
 * @version 0.0.1
 */
@Table(name = "c3_user")
@Entity
public class User extends BaseObject implements UserDetails {
    // ============= Constants =================================================
    // ============= Attributes ================================================
    /**
     * @since 0.0.1
     */
    @Column(name = "user_name", length = 30, unique = true, nullable = false, updatable = false)
    private String username;

    /**
     * @since 0.0.1
     */
    @JsonIgnore
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    /**
     * @since 0.0.1
     */
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    /**
     * @since 0.0.1
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @Column(name = "enabled")
    private boolean enabled = true;

    @JsonIgnore
    @Transient
    private List<? extends GrantedAuthority> authorities;

    // ============= Constructors ==============================================
    public User() {
        super();
    }

    public User(String username, String password, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // ============= Getter/Setters ============================================

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the {@link #email}}
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the {@link #email}} to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param username the {@link #username}} to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the {@link #password}} to set
     */
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

    // ============= Override, Implements ======================================
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
    // ============= Methods ===================================================

}
