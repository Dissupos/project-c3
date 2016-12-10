package cz.project.c3.web.dto;

import cz.project.c3.domain.offer.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OfferCreateDTO {
    @NotNull
    @Size(min = 1, max = 2000)
    private String title;
    private String country;
    private String city;
    private Category category;
    @NotNull
    @Size(min = 1, max = 2000)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
