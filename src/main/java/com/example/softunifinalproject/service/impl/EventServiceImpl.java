package com.example.softunifinalproject.service.impl;

import com.example.softunifinalproject.model.binding.EventBindingModel;
import com.example.softunifinalproject.model.entity.EventEntity;
import com.example.softunifinalproject.model.entity.PictureEntity;
import com.example.softunifinalproject.model.entity.UserEntity;
import com.example.softunifinalproject.model.view.EventViewModel;
import com.example.softunifinalproject.repository.EventRepository;
import com.example.softunifinalproject.repository.PictureRepository;
import com.example.softunifinalproject.service.CloudinaryService;
import com.example.softunifinalproject.service.EventService;
import com.example.softunifinalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public EventServiceImpl(PictureRepository pictureRepository, CloudinaryService cloudinaryService, EventRepository eventRepository, ModelMapper modelMapper, UserService userService) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void createEvent(EventBindingModel eventBindingModel, String username) throws IOException {


        EventEntity event = modelMapper.map(eventBindingModel, EventEntity.class);
        event.setAttendant(0);
        if (!eventBindingModel.getPictures().getOriginalFilename().isEmpty()) {
            PictureEntity pictureEntity = createPictureEntity(eventBindingModel.getPictures());
            pictureRepository.save(pictureEntity);
            event.getPictures().add(pictureEntity);
        }
        eventRepository.save(event);
        UserEntity user = userService.findByUsername(username);
        user.getCreatedEvents().add(event);
    }

    private PictureEntity createPictureEntity(MultipartFile file) throws IOException {

        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        return new PictureEntity()
                .setPublicId(uploaded.getPublicId())
                .setUrl(uploaded.getUrl());
    }

    @Transactional
    @Override
    public List<EventViewModel> getAllEvents() {
        return eventRepository.findAll()
                .stream().map(eventEntity -> {
                    EventViewModel model = modelMapper.map(eventEntity, EventViewModel.class);
                    if (eventEntity.getPictures().size() > 0) {
                        model.setPicture(eventEntity.getPictures().get(0).getUrl());
                    }else {
                        model.setPicture("/images/image_not_found.png");
                    }

                    return model;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public EventViewModel findById(Long id) {
        EventEntity byId = eventRepository.getById(id);
        EventViewModel map = new EventViewModel();
        String pictureUrl = byId.getPictures().isEmpty() ? "/images/image_not_found.png" : byId.getPictures().get(0).getUrl();

        map.setPicture(pictureUrl)
                .setName(byId.getName())
                .setDescription(byId.getDescription())
                .setEndDate(byId.getEndDate())
                .setStartDate(byId.getStartDate())
                .setId(byId.getId())
        .setLocation(byId.getLocation());

        return map;
    }
}
