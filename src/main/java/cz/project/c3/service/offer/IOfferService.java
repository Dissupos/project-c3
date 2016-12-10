package cz.project.c3.service.offer;

import cz.project.c3.domain.offer.Offer;
import cz.project.c3.web.dto.OfferCreateDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface IOfferService {
    @Transactional(readOnly = true)
    Collection<Offer> getAll();

    @Transactional(readOnly = true)
    Optional<Offer> getById(Long id);

    @Transactional
    @PreAuthorize("hasRole('OFFER_EDITOR')")
    Offer createOffer(OfferCreateDTO dto);

    @Transactional
    Offer save(Offer offer);
}
