package cz.project.c3.service.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.project.c3.domain.person.Address;
import cz.project.c3.repository.person.AddressRepository;
import cz.project.c3.service.person.IAddressService;

@Service
public class AddressService implements IAddressService {
    private final AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Address save(Address address) {
        return repository.save(address);
    }

}
