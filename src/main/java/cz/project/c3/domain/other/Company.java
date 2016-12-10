package cz.project.c3.domain.other;

import cz.project.c3.domain.base.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company extends BaseObject {
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public Company() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
