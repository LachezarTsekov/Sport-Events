package com.example.softunifinalproject.web;

import com.example.softunifinalproject.model.binding.EventBindingModel;
import com.example.softunifinalproject.model.binding.UserRegisterBindingModel;
import com.example.softunifinalproject.model.entity.enums.SportCategoryEnum;
import com.example.softunifinalproject.model.view.EventViewModel;
import com.example.softunifinalproject.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class EventsController {

    private final EventService eventService;

    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }


    @ModelAttribute("events")
    public EventViewModel eventViewModel() {
        return new EventViewModel();
    }

    @GetMapping("/events")
    private String events(Model model) {
        model.addAttribute( "events" ,eventService.getAllEvents());
        return "events";
    }

    @GetMapping("/events/create")
    public String createEventPage(Model model) {
        model.addAttribute("sportCategory", Arrays.stream(SportCategoryEnum.values())
                .collect(Collectors.toList()));

        return "create-event";
    }

    @ModelAttribute("eventModel")
    public EventBindingModel eventModel() {
        return new EventBindingModel();
    }

    @PostMapping("/events/create")
    public String createEvent(@Valid EventBindingModel eventBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(error -> {
                        String field = error.getField();
                        String attributeName = "wrong" + field.substring(0, 1).toUpperCase() + field.substring(1);
                        redirectAttributes.addFlashAttribute(attributeName, true);
                    });

            redirectAttributes.addFlashAttribute("eventModel", eventBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.eventBindingModel", bindingResult);
            return "redirect:/events/create";
        }
        if (eventBindingModel.getStartDate().isAfter(eventBindingModel.getEndDate())){
            redirectAttributes.addFlashAttribute("differenceInDates", true);
            return "redirect:/events/create";
        }
        eventService.createEvent(eventBindingModel, principal.getName());

        return "redirect:/events";
    }

    @GetMapping("/events/details/{id}")
    public String detailsPage(@PathVariable Long id, Model model){
        EventViewModel byId = eventService.findById(id);
        model.addAttribute("eventDetails",byId);

        return "event-details";
    }
}
