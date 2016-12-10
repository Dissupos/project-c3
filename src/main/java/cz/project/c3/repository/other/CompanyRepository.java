package cz.project.c3.repository.other;

import cz.project.c3.domain.other.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
    Optional<Company> findOneByName(String name);
}
