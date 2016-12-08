package cz.project.c3.domain.user;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import cz.project.c3.domain.base.BaseObject;

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
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    /**
     * @since 0.0.1
     */
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    /**
     * @since 0.0.1
     */
    @ManyToMany
    @JoinTable(name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    // ============= Constructors ==============================================
    public User() {
        super();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    // ============= Getter/Setters ============================================
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

    // ============= Override, Implements ======================================
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ADMIN,USER");
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    // ============= Methods ===================================================

}
