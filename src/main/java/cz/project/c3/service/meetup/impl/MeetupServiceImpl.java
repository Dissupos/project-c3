package cz.project.c3.service.meetup.impl;

import cz.project.c3.domain.meetup.Meetup;
import cz.project.c3.domain.person.Address;
import cz.project.c3.domain.user.PersonalUser;
import cz.project.c3.repository.meetup.MeetupRepository;
import cz.project.c3.service.meetup.IMeetupService;
import cz.project.c3.service.person.IAddressService;
import cz.project.c3.service.user.IUserService;
import cz.project.c3.web.dto.MeetupCreateDTO;
import cz.project.c3.web.error.AccessDeniedException;
import cz.project.c3.web.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MeetupServiceImpl implements IMeetupService {

    private final MeetupRepository repository;
    private final IUserService userService;
    private final IAddressService addressService;

    @Autowired
    public MeetupServiceImpl(MeetupRepository repository, IUserService userService, IAddressService addressService) {
        this.repository = repository;
        this.userService = userService;
        this.addressService = addressService;
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

    @Override
    public Meetup createMeetup(MeetupCreateDTO dto) {
        PersonalUser user = (PersonalUser) userService.getCurrentUser();
        Address address = addressService.getOrCreateAddress(dto.getCountry(), dto.getCity());
        return repository.save(new Meetup(dto.getTitle(), dto.getDescription(),
                user, address, dto.getMeetupDate(), dto.getCategory()));
    }

    private Meetup checkForNullAndGet(Long id) {
        Optional<Meetup> meetup = getById(id);
        if (!meetup.isPresent()) {
            throw new NotFoundException("Meetup with id:" + id + ". Not found!");
        }
        return meetup.get();
    }

    private Meetup checkForAccessAndGet(Long id) {
        Meetup meetup = checkForNullAndGet(id);
        PersonalUser user = (PersonalUser) userService.getCurrentUser();
        if (meetup.getCreator().getId() != user.getId()) {
            throw new AccessDeniedException("Its not your meetup!");
        }
        return meetup;
    }

    @Override
    public Meetup updateMeetup(Long id, MeetupCreateDTO dto) {
        Meetup meetup = checkForAccessAndGet(id);
        meetup.setTitle(dto.getTitle());
        meetup.setCategory(dto.getCategory());
        meetup.setDescription(dto.getDescription());
        meetup.setAddress(addressService.getOrCreateAddress(dto.getCountry(), dto.getCity()));
        meetup.setMeetupDate(dto.getMeetupDate());
        return repository.save(meetup);
    }

}
