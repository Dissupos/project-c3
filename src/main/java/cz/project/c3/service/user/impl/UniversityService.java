package cz.project.c3.service.user.impl;

import cz.project.c3.domain.user.University;
import cz.project.c3.repository.user.UniversityRepository;
import cz.project.c3.service.user.IUniversityService;
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
