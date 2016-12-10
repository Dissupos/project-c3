package cz.project.c3.service.other;

import cz.project.c3.domain.other.Company;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ICompanyService {
    @Transactional(readOnly = true)
    Optional<Company> getByName(String name);

    @Transactional
    Company save(Company company);
}
