package cz.project.c3.domain.base;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * All persist object must inherit this abstract object
 * 
 * @author dis
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class BaseObject {
    // ============= Constants =================================================
    // ============= Attributes ================================================
    /**
     * Identificator
     * 
     * @since 0.0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /**
     * Date when object was created
     * 
     * @since 0.0.1
     */
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate = new Date();

    /**
     * Date when object was changed
     * 
     * @since 0.0.1
     */
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate = new Date();

    // ============= Constructors ==============================================
    public BaseObject() {
        super();
    }

    // ============= Getter/Setters ============================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    // ============= Override, Implements ======================================
    // ============= Methods ===================================================

}
