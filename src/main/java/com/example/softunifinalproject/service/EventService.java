package com.example.softunifinalproject.service;

import com.example.softunifinalproject.model.binding.EventBindingModel;
import com.example.softunifinalproject.model.view.EventViewModel;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface EventService {
    void createEvent(EventBindingModel eventBindingModel, String username) throws IOException;

    List<EventViewModel> getAllEvents();

    EventViewModel findById(Long id);
}
