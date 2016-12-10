package cz.project.c3.web.resource;

import cz.project.c3.domain.offer.Offer;
import cz.project.c3.service.offer.IOfferService;
import cz.project.c3.web.dto.OfferCreateDTO;
import cz.project.c3.web.dto.OfferListDTO;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

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
    public ResponseEntity<Offer> getOffer(@PathVariable long id) {
        Optional<Offer> offerOptional = service.getById(id);
        if (!offerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offerOptional.get(), HttpStatus.OK);

    }

    /**
     * Только фирма может создать предлолжение
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody OfferCreateDTO dto) {
        Offer offer = service.createOffer(dto);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
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
