package cz.project.c3.service.meetup;

import cz.project.c3.domain.meetup.Meetup;
import cz.project.c3.web.dto.MeetupCreateDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface IMeetupService {
    @Transactional(readOnly = true)
    Optional<Meetup> getById(Long id);

    @Transactional(readOnly = true)
    Collection<Meetup> getAll();

    @Transactional(readOnly = true)
    Collection<Meetup> getAllPagingAndSortng(Pageable pageable);

    @Transactional
    @PreAuthorize("hasRole('MEETUP_EDITOR')")
    Meetup createMeetup(MeetupCreateDTO dto);

    @Transactional
    @PreAuthorize("hasRole('MEETUP_EDITOR')")
    Meetup updateMeetup(Long id, MeetupCreateDTO dto);
}
