package cz.project.c3.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.project.c3.domain.person.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
