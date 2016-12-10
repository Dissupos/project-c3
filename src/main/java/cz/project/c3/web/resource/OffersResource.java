package cz.project.c3.web.resource;

import cz.project.c3.domain.offer.Offer;
import cz.project.c3.service.offer.IOfferService;
import cz.project.c3.web.dto.OfferListDTO;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
/**
 * /offers
 */
@RequestMapping("/api/offers")
public class OffersResource {

    private final IOfferService service;

    @Autowired
    public OffersResource(IOfferService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getOffer(@PathVariable long id) {

    }

    /**
     * Только фирма может создать предлолжение
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createOffer() {
        return null;
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
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Collection<OfferListDTO>> getAllOffers(Pageable page, Sortable sort) {
        Collection<Offer> offers = service.getAll();
        if (offers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Offer.listToListDTO(offers), HttpStatus.OK);
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
