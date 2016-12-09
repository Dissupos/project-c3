package cz.project.c3.repository.user;

import cz.project.c3.domain.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for domain User
 * 
 * @author dis
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findOneByUsername(String username);
    Optional<User> findOneByEmail(String email);
}
