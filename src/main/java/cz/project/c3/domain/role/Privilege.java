package cz.project.c3.domain.role;

import cz.project.c3.domain.base.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "privilege")
public class Privilege extends BaseObject {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;

    public Privilege() {
        super();
    }

    public Privilege(String name) {
        this.name = "ROLE_" + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
