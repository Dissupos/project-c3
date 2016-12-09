package cz.project.c3.domain.user;

import cz.project.c3.domain.base.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "university")
public class University extends BaseObject {
    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    public University() {
    }

    public University(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
