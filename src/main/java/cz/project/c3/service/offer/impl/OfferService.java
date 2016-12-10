package cz.project.c3.service.offer.impl;

import cz.project.c3.domain.offer.Offer;
import cz.project.c3.domain.offer.OfferStatus;
import cz.project.c3.domain.other.Company;
import cz.project.c3.domain.person.Address;
import cz.project.c3.domain.user.CompanyUser;
import cz.project.c3.repository.offer.OfferRepository;
import cz.project.c3.service.offer.IOfferService;
import cz.project.c3.service.person.IAddressService;
import cz.project.c3.service.user.impl.UserService;
import cz.project.c3.web.dto.OfferCreateDTO;
import cz.project.c3.web.error.AccessDeniedException;
import cz.project.c3.web.error.ConflictException;
import cz.project.c3.web.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class OfferService implements IOfferService {
    private final OfferRepository repository;
    private final UserService userService;
    private final IAddressService addressService;

    @Autowired
    public OfferService(OfferRepository repository, UserService userService, IAddressService addressService) {
        this.repository = repository;
        this.userService = userService;
        this.addressService = addressService;
    }

    @Override
    public Collection<Offer> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Offer> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Offer createOffer(OfferCreateDTO dto) {
        CompanyUser user = (CompanyUser) userService.getCurrentUser();
        Company company = user.getCompany();
        Address address = addressService.getOrCreateAddress(dto.getCountry(), dto.getCity());

        return save(new Offer(dto.getTitle(), dto.getDescription(), company, address, dto.getCategory()));
    }

    @Override
    public Offer updateOffer(Long id, OfferCreateDTO dto) {
        Offer offer = checkForAccessAndGet(id);
        offer.setTitle(dto.getTitle());
        offer.setDescription(dto.getDescription());
        offer.setCategory(dto.getCategory());
        offer.setAddress(addressService.getOrCreateAddress(dto.getCountry(), dto.getCity()));
        return save(offer);
    }

    private Offer checkForAccessAndGet(Long id) {
        Optional<Offer> offerOptional = getById(id);
        CompanyUser user = (CompanyUser) userService.getCurrentUser();
        if (!offerOptional.isPresent()) {
            throw new NotFoundException("Offer with id:" + id + ". Not found");
        }
        Offer offer = offerOptional.get();
        if (offer.getCompany().getId() != user.getCompany().getId()) {
            throw new AccessDeniedException("Its not your offer");
        }
        return offer;
    }

    @Override
    public void completeOffer(Long id) {
        Offer offer = checkForAccessAndGet(id);
        if (offer.getStatus() != OfferStatus.STARTED) {
            throw new ConflictException("Offer must be in status: Started");
        }
        offer.setCompleteAt(new Date());
        save(offer);
    }

    @Override
    public Offer save(Offer offer) {
        return repository.save(offer);
    }


    @Override
    public void delete(Long id) {
        Offer offer = checkForAccessAndGet(id);
        repository.delete(offer);
    }
}
