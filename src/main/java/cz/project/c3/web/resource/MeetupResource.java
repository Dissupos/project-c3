package cz.project.c3.web.resource;

import cz.project.c3.domain.meetup.Meetup;
import cz.project.c3.service.meetup.IMeetupService;
import cz.project.c3.web.dto.MeetupListDTO;
import cz.project.c3.web.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
/**
 */
@RequestMapping("/api/meetups")
public class MeetupResource {

    private final IMeetupService meetupService;

    @Autowired
    public MeetupResource(IMeetupService meetupService) {
        this.meetupService = meetupService;
    }

    /**
     * Зарегестрированный
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/join", method = RequestMethod.GET)
    public void inviteMeetup(@PathVariable long id) {

    }

    /**
     * @param id
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createMeetup(@PathVariable long id) {

    }

    /**
     * Тот кто создал и админ
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void editMeetup(@PathVariable long id) {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Meetup> getMeetup(@PathVariable long id) {
        Optional<Meetup> meetupOptional = meetupService.getById(id);
        if (!meetupOptional.isPresent()) {
            throw new NotFoundException("Meetup with id:" + id + " doesn't exists");
        }
        return new ResponseEntity<>(meetupOptional.get(), HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Collection<MeetupListDTO>> getAllMeetups(Pageable pageable) {
        Collection<Meetup> meetups = meetupService.getAllPagingAndSortng(pageable);
        if (meetups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Meetup.listToListDTO(meetups), HttpStatus.OK);

    }

    /**
     * Аналогия выше
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/leave", method = RequestMethod.GET)
    public void leaveMeetup(@PathVariable long id) {

    }


    /**
     * Тот кто создал и админ
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.GET)
    public void cancelMeetup(@PathVariable long id) {

    }
}
