package cz.project.c3.domain.meetup;

import cz.project.c3.domain.base.BaseObject;
import cz.project.c3.domain.offer.Category;
import cz.project.c3.domain.person.Address;
import cz.project.c3.domain.user.PersonalUser;
import cz.project.c3.web.dto.MeetupListDTO;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//title, description, creatorId, category, participants, status, meetupDate, address
@Entity
@Table(name = "meetup")
public class Meetup extends BaseObject {
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    @Column(name = "description", nullable = false, length = 2000)
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private PersonalUser creator;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Column(name = "meetup_date", nullable = false)
    private Date meetupDate;
    @Column(name = "category", nullable = false, length = 100)
    private Category category;
    @Column(name = "canceled", nullable = false)
    private boolean canceled = false;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable
            (
                    name = "meetup_user",
                    joinColumns = {@JoinColumn(name = "offer_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
            )
    private List<PersonalUser> participants;

    public Meetup() {
    }

    public Meetup(
            String title, String description, PersonalUser creator,
            Address address, Date meetupDate, Category category
    ) {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.address = address;
        this.meetupDate = meetupDate;
        this.category = category;
    }

    public static Collection<MeetupListDTO> listToListDTO(Collection<Meetup> meetups) {
        List<MeetupListDTO> meetupsDTO = new ArrayList<>();
        for (Meetup meetup : meetups) {
            meetupsDTO.add(new MeetupListDTO(meetup.getId(), meetup.getTitle(),
                    meetup.getMeetupDate(), meetup.getAddress(), meetup.getStatus(), meetup.getCategory()));
        }
        return meetupsDTO;
    }

    public MeetupStatus getStatus() {
        if (canceled) {
            return MeetupStatus.CANCELED;
        } else {
            if (meetupDate != null && meetupDate.after(new Date())) {
                return MeetupStatus.ONCOMING;
            } else if (meetupDate != null) {
                return MeetupStatus.OVERPAST;
            } else {
                return null;
            }
        }
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
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

    public PersonalUser getCreator() {
        return creator;
    }

    public void setCreator(PersonalUser creator) {
        this.creator = creator;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public List<PersonalUser> getParticipants() {
        return participants;
    }

    public void setParticipants(List<PersonalUser> participants) {
        this.participants = participants;
    }
}
