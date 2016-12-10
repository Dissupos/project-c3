package cz.project.c3.domain.role;

import cz.project.c3.domain.base.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Privilege domain
 *
 * @author dis
 * @version 0.0.1
 */
@Entity
@Table(name = "privilege")
public class Privilege extends BaseObject {
    // ============= Constants =================================================
    // ============= Attributes ================================================
    /**
     * Name of privilege
     *
     * @since 0.0.1
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * List of roles which have this privilege
     *
     * @since 0.0.1
     */
    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;

    // ============= Constructors ==============================================
    public Privilege() {
        super();
    }

    public Privilege(String name) {
        this.name = "ROLE_" + name;
    }
    // ============= Getter/Setters ============================================

    /**
     * @return the {@link #name}}
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
     * @return the {@link #roles}
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the {@link #roles} to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    // ============= Override, Implements ======================================
    // ============= Methods ===================================================
}
