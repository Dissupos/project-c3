package cz.project.c3.repository.meetup;

import cz.project.c3.domain.meetup.Meetup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetupRepository extends PagingAndSortingRepository<Meetup, Long> {
}
