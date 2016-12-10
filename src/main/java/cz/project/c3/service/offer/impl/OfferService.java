package cz.project.c3.service.offer.impl;

import cz.project.c3.domain.offer.Offer;
import cz.project.c3.repository.offer.OfferRepository;
import cz.project.c3.service.offer.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OfferService implements IOfferService {
    private final OfferRepository repository;

    @Autowired
    public OfferService(OfferRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Offer> getAll() {
        return repository.findAll();
    }
}
