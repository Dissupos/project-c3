package cz.project.c3.service.user;

import cz.project.c3.domain.user.Company;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ICompanyService {
    @Transactional(readOnly = true)
    Optional<Company> getByName(String name);

    @Transactional
    Company save(Company company);
}
