package cz.project.c3.service.user;

import cz.project.c3.domain.user.University;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IUniversityService {
    @Transactional(readOnly = true)
    Optional<University> getByName(String name);

    @Transactional
    University save(University university);
}
