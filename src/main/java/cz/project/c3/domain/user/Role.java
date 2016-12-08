package cz.project.c3.domain.user;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cz.project.c3.domain.base.BaseObject;

/**
 * Role domain
 * 
 * @author dis
 * @version 0.0.1
 */
@Entity
@Table(name = "role")
public class Role extends BaseObject {

    // ============= Constants =================================================
    // ============= Attributes ================================================
    /**
     * Role name
     * 
     * @version 0.0.1
     */
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    /**
     * List of users which have this role
     * 
     * @since 0.0.1
     */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    /**
     * List of privileges
     * 
     * @since 0.0.1
     */
    @ManyToMany
    @JoinTable(name = "roles_privileges", 
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private List<Privilege> privileges;

    // ============= Constructors ==============================================
    public Role() {
        super();
    }

    public Role(String name) {
        super();
        this.name = name;
    }

    public Role(String name, List<User> users, List<Privilege> privileges) {
        super();
        this.name = name;
        this.users = users;
        this.privileges = privileges;
    }

    // ============= Getter/Setters ============================================
    /**
     * @return the {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the {@link #name} to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * s
     * 
     * @return the {@link #users}
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the {@link #users} to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return the {@link #privileges}
     */
    public List<Privilege> getPrivileges() {
        return privileges;
    }

    /**
     * @param privileges the {@link #privileges} to set
     */
    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
    // ============= Override, Implements ======================================
    // ============= Methods ===================================================

}
