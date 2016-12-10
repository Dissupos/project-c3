package cz.project.c3.web.dto;

import cz.project.c3.domain.offer.Category;
import cz.project.c3.domain.offer.OfferStatus;
import cz.project.c3.domain.other.Company;
import cz.project.c3.domain.person.Address;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OfferCreateDTO extends OfferListDTO {
    @NotNull
    @Size(min = 1, max = 2000)
    private String description;

    public OfferCreateDTO(Long id, String title, Company company, Address address,
                          OfferStatus status, Category category, String description) {
        super(id, title, company, address, status, category);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
