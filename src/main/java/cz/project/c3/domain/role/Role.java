package cz.project.c3.domain.role;

import cz.project.c3.domain.base.BaseObject;
import cz.project.c3.domain.user.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends BaseObject {

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private List<Privilege> privileges;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
