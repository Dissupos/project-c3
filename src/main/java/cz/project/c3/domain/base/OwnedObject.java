package cz.project.c3.domain.base;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * All objects which have Owner atribute must inherit this class
 * 
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class OwnedObject extends BaseObject {

    // ============= Constants =================================================
    // ============= Attributes ================================================
    /**
     * Username of created user
     * 
     * @since 0.0.1
     */
    @CreatedBy
    @Column(name = "created_by", length = 30)
    private String createdBy;

    /**
     * Username of modified user
     * 
     * @since 0.0.1
     */
    @LastModifiedBy
    @Column(name = "last_modified_by", length = 30)
    private String lastModifiedBy;
    // ============= Constructors ==============================================

    public OwnedObject() {
        super();
    }

    // ============= Getter/Setters ============================================
    /**
     * @return {@link #createdBy}
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the {@link #createdBy} to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the {@link #lastModifiedBy}
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy the {@link #lastModifiedBy} to set
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    // ============= Override, Implements ======================================
    // ============= Methods ===================================================

}
