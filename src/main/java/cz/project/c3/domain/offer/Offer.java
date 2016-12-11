package cz.project.c3.domain.offer;


import cz.project.c3.domain.base.BaseObject;
import cz.project.c3.domain.other.Company;
import cz.project.c3.domain.person.Address;
import cz.project.c3.domain.user.ProfessorUser;
import cz.project.c3.domain.user.StudentUser;
import cz.project.c3.web.dto.OfferListDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "offer")
public class Offer extends BaseObject {
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    @Column(name = "description", nullable = false, length = 3000)
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id")
    private ProfessorUser professor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private StudentUser student;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Column(name = "complete_at")
    private Date completeAt;
    @Column(name = "category", nullable = false, length = 100)
    private Category category;

    public Offer() {
    }

    public Offer(String title, String description, Company company, Address address, Category category) {
        this();
        this.title = title;
        this.description = description;
        this.company = company;
        this.address = address;
        this.category = category;
    }

    public static OfferListDTO toListDTO(Offer offer) {
        return new OfferListDTO(offer.getId(), offer.getTitle(), offer.getCompany(),
                offer.getAddress(), offer.getStatus(), offer.getCategory());
    }

    public static Collection<OfferListDTO> listToListDTO(Collection<Offer> offers) {
        Collection<OfferListDTO> dtos = new ArrayList<>();
        for (Offer offer : offers) {
            dtos.add(toListDTO(offer));
        }
        return dtos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ProfessorUser getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorUser professor) {
        this.professor = professor;
    }

    public StudentUser getStudent() {
        return student;
    }

    public void setStudent(StudentUser student) {
        this.student = student;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getCompleteAt() {
        return completeAt;
    }

    public void setCompleteAt(Date completeAt) {
        this.completeAt = completeAt;
    }

    public OfferStatus getStatus() {
        if (completeAt != null) {
            return OfferStatus.COMPLETED;
        } else if (professor != null && student != null) {
            return OfferStatus.STARTED;
        } else if (professor != null || student != null) {
            return OfferStatus.WAITING;
        }
        return OfferStatus.NEW;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
