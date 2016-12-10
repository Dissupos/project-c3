package cz.project.c3.repository.role;

import cz.project.c3.domain.role.Privilege;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends PagingAndSortingRepository<Privilege, Long> {
    Optional<Privilege> findOneByName(String name);
}
