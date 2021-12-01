package com.example.softunifinalproject.web;

import com.example.softunifinalproject.model.binding.UserRegisterBindingModel;
import com.example.softunifinalproject.model.view.UserViewModel;
import com.example.softunifinalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String userPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterBindingModel userModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userModel.getPassword().equals(userModel.getRepeatPassword())) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors
                    .forEach(error -> {
                       if(error.getField().equals("username")){
                           redirectAttributes.addFlashAttribute("wrongUsername", true);
                       }else if(error.getField().equals("password")){
                           redirectAttributes.addFlashAttribute("wrongPassword", true);
                       }
                    });
            if (!userModel.getPassword().equals(userModel.getRepeatPassword())) {
                redirectAttributes.addFlashAttribute("mismachPassword", true);
            }

            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/register";
        }
        if (!userService.isUsernameFree(userModel.getUsername()) ){

            redirectAttributes.addFlashAttribute("usernameExist", true);
            return "redirect:/users/register";

        }
        userService.registerUser(userModel);

        return "redirect:/";
    }

    @PostMapping("/login-error")
    public RedirectView loginError(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                     String username,
                                   RedirectAttributes attributes) {
        attributes.addFlashAttribute("loginError", true);
        attributes.addFlashAttribute("username", username);


        return new RedirectView("/users/login") ;
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {

        UserViewModel userEntity = modelMapper.map(userService.findByUsername(principal.getName()), UserViewModel.class) ;

        model.addAttribute("user", userEntity);

        return "user-profile";
    }

    @ModelAttribute("userModel")
    public UserRegisterBindingModel userModel() {
        return new UserRegisterBindingModel();
    }

}
