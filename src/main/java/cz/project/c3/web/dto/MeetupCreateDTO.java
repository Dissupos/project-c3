package cz.project.c3.web.dto;

import cz.project.c3.domain.offer.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class MeetupCreateDTO {
    @NotNull
    @Size(min = 1, max = 200)
    private String title;
    @NotNull
    @Size(min = 1, max = 2000)
    private String description;
    @NotNull
    @Size(min = 1, max = 30)
    private String country;
    @NotNull
    @Size(min = 1, max = 30)
    private String city;
    @NotNull
    private Date meetupDate;
    @NotNull
    private Category category;

    public MeetupCreateDTO() {
    }

    public MeetupCreateDTO(String title, String description, String country, String city, Date meetupDate, Category category) {
        this.title = title;
        this.description = description;
        this.country = country;
        this.city = city;
        this.meetupDate = meetupDate;
        this.category = category;
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

    public Date getMeetupDate() {
        return meetupDate;
    }

    public void setMeetupDate(Date meetupDate) {
        this.meetupDate = meetupDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
