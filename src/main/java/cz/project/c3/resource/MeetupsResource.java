package cz.project.c3.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * /meetups
 */
public class MeetupsResource {
    /**
     * Зарегестрированный
     *
     * @param id
     */
    @RequestMapping(value = "/meetups/{id}/join", method = RequestMethod.GET)
    public void inviteMeetup(@PathVariable long id) {

    }

    /**
     *
     * @param id
     */
    @RequestMapping(value = "/meetups", method = RequestMethod.POST)
    public void createMeetup(@PathVariable long id) {

    }

    /**
     * Тот кто создал и админ
     *
     * @param id
     */
    @RequestMapping(value = "/meetups/{id}", method = RequestMethod.PUT)
    public void editMeetup(@PathVariable long id) {

    }

    /**
     * Аналогия выше
     *
     * @param id
     */
    @RequestMapping(value = "/offers/{id}/leave", method = RequestMethod.GET)
    public void leaveMeetup(@PathVariable long id) {

    }


    /**
     * Тот кто создал и админ
     *
     * @param id
     */
    @RequestMapping(value = "/offers/{id}/cancel", method = RequestMethod.GET)
    public void cancelMeetup(@PathVariable long id) {

    }
}
