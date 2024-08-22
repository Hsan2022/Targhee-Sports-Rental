package com.helord.Targhee_Sports_Rental.controller;

import com.helord.Targhee_Sports_Rental.database.dao.UserDAO;
import com.helord.Targhee_Sports_Rental.form.CreateAccountFormBean;
import com.helord.Targhee_Sports_Rental.security.AuthenticatedUserUtilities;
import com.helord.Targhee_Sports_Rental.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/account")
public class LoginController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    // handle requests to this log-in path
    @GetMapping("/loginPageUrl")
    public ModelAndView loginPage(@RequestParam(required = false) String error) {

        // create obj to carry view
        ModelAndView response = new ModelAndView("auth/login");

        // return obj w/view
        return response;
    }

    // handle requests to this create account path
    @GetMapping("/create-account")
    public ModelAndView createAccount() {

        // create obj to carry view/jsp
        ModelAndView response = new ModelAndView("auth/create-account");

        // return obj w/view
        return response;
    }

    // handle requests to this create account 'form submission' path
    @PostMapping("/create-account")
    public ModelAndView createAccountSubmit(@Valid CreateAccountFormBean form, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {

        // create obj to carry view/jsp
        ModelAndView response = new ModelAndView("auth/create-account");

        // check form for errors
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
            }

            // return form with errors for repopulating
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);
        } else {

            // if no errors, a new user is created in the database
            userService.createUser(form);

            // handle user auth manually, logging user with these params
            authenticatedUserUtilities.manualAuthentication(session, form.getEmail(), form.getPassword());


            // Add a welcome message to flash attributes
            redirectAttributes.addFlashAttribute("welcomeMessage", "Welcome, " + form.getFirstname() + "! Your account has been created successfully.");

            // Redirect to the home page
            response.setViewName("redirect:/index"); // Replace with your actual home page URL
        }
        return response;
    }
}