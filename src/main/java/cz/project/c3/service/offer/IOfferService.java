package cz.project.c3.service.offer;

import cz.project.c3.domain.offer.Offer;

import java.util.Collection;

public interface IOfferService {
    Collection<Offer> getAll();
}
