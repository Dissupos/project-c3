package cz.project.c3.service.other.impl;

import cz.project.c3.domain.other.University;
import cz.project.c3.repository.other.UniversityRepository;
import cz.project.c3.service.other.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniversityService implements IUniversityService {

    private final UniversityRepository repository;

    @Autowired
    public UniversityService(UniversityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<University> getByName(String name) {
        return repository.findOneByName(name);
    }

    @Override
    public University save(University university) {
        return repository.save(university);
    }
}
