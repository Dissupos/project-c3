package cz.project.c3.repository.user;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cz.project.c3.domain.user.User;

/**
 * Repository for domain User
 * 
 * @author dis
 * @since 0.0.1
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findOneByUsername(String username);
}
