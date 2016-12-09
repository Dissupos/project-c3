package cz.project.c3.service.user.impl;

import cz.project.c3.domain.user.Company;
import cz.project.c3.repository.user.CompanyRepository;
import cz.project.c3.service.user.ICompanyService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {
    private CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Company> getByName(String name) {
        return repository.findOneByName(name);
    }

    @Override
    public Company save(Company company) {
        return repository.save(company);
    }
}
