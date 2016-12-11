package cz.project.c3.service.meetup;

import cz.project.c3.domain.meetup.Meetup;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface IMeetupService {
    @Transactional(readOnly = true)
    Optional<Meetup> getById(Long id);

    @Transactional(readOnly = true)
    Collection<Meetup> getAll();
}