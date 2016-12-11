package cz.project.c3.web.dto;

import cz.project.c3.domain.meetup.MeetupStatus;
import cz.project.c3.domain.offer.Category;
import cz.project.c3.domain.person.Address;

import java.util.Date;

public class MeetupListDTO {
    private Long id;
    private String title;
    private Date meetupDate;
    private Address address;
    private MeetupStatus status;
    private Category category;

    public MeetupListDTO() {
    }

    public MeetupListDTO(Long id, String title, Date meetupDate, Address address, MeetupStatus status, Category category) {
        this.id = id;
        this.title = title;
        this.meetupDate = meetupDate;
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

    public Date getMeetupDate() {
        return meetupDate;
    }

    public void setMeetupDate(Date meetupDate) {
        this.meetupDate = meetupDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MeetupStatus getStatus() {
        return status;
    }

    public void setStatus(MeetupStatus status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
