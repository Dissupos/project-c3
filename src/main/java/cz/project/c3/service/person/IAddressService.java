package cz.project.c3.service.person;

import org.springframework.transaction.annotation.Transactional;

import cz.project.c3.domain.person.Address;

public interface IAddressService {
    @Transactional
    Address save(Address address);
}
