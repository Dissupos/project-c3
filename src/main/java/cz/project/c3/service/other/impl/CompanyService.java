package cz.project.c3.service.other.impl;

import cz.project.c3.domain.other.Company;
import cz.project.c3.repository.other.CompanyRepository;
import cz.project.c3.service.other.ICompanyService;
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
