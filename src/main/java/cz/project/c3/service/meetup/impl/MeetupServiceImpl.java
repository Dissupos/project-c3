package cz.project.c3.service.meetup.impl;

import cz.project.c3.domain.meetup.Meetup;
import cz.project.c3.repository.meetup.MeetupRepository;
import cz.project.c3.service.meetup.IMeetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MeetupServiceImpl implements IMeetupService {

    private final MeetupRepository repository;

    @Autowired
    public MeetupServiceImpl(MeetupRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Meetup> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<Meetup> getAll() {
        return (Collection<Meetup>) repository.findAll();
    }

    @Override
    public Collection<Meetup> getAllPagingAndSortng(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }
}
