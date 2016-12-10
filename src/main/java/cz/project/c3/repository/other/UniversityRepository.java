package cz.project.c3.repository.other;

import cz.project.c3.domain.other.University;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends PagingAndSortingRepository<University, Long> {
    Optional<University> findOneByName(String name);
}
