package cz.project.c3.service.user;

import org.springframework.transaction.annotation.Transactional;

import cz.project.c3.domain.user.Address;

public interface IAddressService {
    @Transactional
    Address save(Address address);
}
