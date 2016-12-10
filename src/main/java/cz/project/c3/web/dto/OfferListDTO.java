package cz.project.c3.web.dto;

import cz.project.c3.domain.offer.Category;
import cz.project.c3.domain.offer.OfferStatus;
import cz.project.c3.domain.other.Company;
import cz.project.c3.domain.person.Address;

public class OfferListDTO {
    private Long id;
    private String title;
    private Company company;
    private Address address;
    private OfferStatus status;
    private Category category;

    public OfferListDTO() {
    }

    public OfferListDTO(Long id, String title, Company company,
                        Address address, OfferStatus status,
                        Category category) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.address = address;
        this.status = status;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
