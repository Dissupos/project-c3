package cz.project.c3.service.offer.impl;

import cz.project.c3.domain.offer.Offer;
import cz.project.c3.domain.other.Company;
import cz.project.c3.domain.person.Address;
import cz.project.c3.domain.user.CompanyUser;
import cz.project.c3.domain.user.User;
import cz.project.c3.repository.offer.OfferRepository;
import cz.project.c3.service.offer.IOfferService;
import cz.project.c3.service.person.IAddressService;
import cz.project.c3.service.user.impl.UserService;
import cz.project.c3.web.dto.OfferCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
        Optional<User> userOptional = userService.getCurrentUser();
        if (!userOptional.isPresent()) {
            return null;
        }
        CompanyUser user = (CompanyUser) userOptional.get();
        Company company = user.getCompany();
        Address address = addressService.getOrCreateAddress(dto.getCountry(), dto.getCity());

        return save(new Offer(dto.getTitle(), dto.getDescription(), company, address, dto.getCategory()));
    }

    @Override
    public Offer save(Offer offer) {
        return repository.save(offer);
    }
}
