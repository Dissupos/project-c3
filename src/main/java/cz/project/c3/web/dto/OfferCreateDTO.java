package cz.project.c3.web.dto;

import cz.project.c3.domain.offer.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OfferCreateDTO {
    @NotNull
    @Size(min = 1, max = 200)
    private String title;
    @NotNull
    @Size(min = 1, max = 30)
    private String country;
    @NotNull
    @Size(min = 1, max = 30)
    private String city;
    @NotNull
    private Category category;
    @NotNull
    @Size(min = 1, max = 2000)
    private String description;

    public OfferCreateDTO() {
    }

    public OfferCreateDTO(String title, String description, String country, String city, Category category) {
        this.title = title;
        this.country = country;
        this.city = city;
        this.category = category;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
