package cz.project.c3.repository.person;

import cz.project.c3.domain.person.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT t FROM Address t WHERE t.country = ?1 AND t.city = ?2")
    Optional<Address> findByCountryAndCity(String country, String city);
}
