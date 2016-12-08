package cz.project.c3.resource;

import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * /offers
 */
@RequestMapping("/api/offers")
public class OffersResource {

    /**
     * Только фирма может создать предлолжение
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createOffer() {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void editOffer(@PathVariable long id) {

    }

    /**
     * Различает тип пользователя, студент или преподователь, и поставит в предложении соответствующее поле
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/join", method = RequestMethod.GET)
    public void acceptOffer(@PathVariable long id) {

    }

    /**
     * Аналогия выше
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/leave", method = RequestMethod.GET)
    public void leaveOffer(@PathVariable long id) {

    }

    /**
     * Получить могут все, даже не залогиненные
     *
     * @param page
     * @param sort
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void getAllOffers(Pageable page, Sortable sort) {

    }

    /**
     * Может удалить только которая его создала
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteOffer(@PathVariable long id) {

    }

}
