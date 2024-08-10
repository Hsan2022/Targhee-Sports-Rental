package com.helord.Targhee_Sports_Rental.controller;


import com.helord.Targhee_Sports_Rental.security.AuthenticatedUserUtilities;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@Controller
// exception handler annotation
@ControllerAdvice
public class ErrorController {

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities; // auth methods library

    // catch all bucket for 404 errors
    @ExceptionHandler(NoResourceFoundException.class)
    @RequestMapping(value = {"/error/404", "/404"})
    public ModelAndView error404(HttpServletRequest request, Exception e) {
        // security config for 404 pages
        ModelAndView response = new ModelAndView("error/404");

        log.debug("!!!!!!!!!!!!!!!!!! IN ERROR CONTROLLER : 404 NOT FOUND : " + request.getMethod() + " " + request.getRequestURI());
        log.debug("ERROR FOUND", e);

        // set a 404 status code
        response.setStatus(HttpStatus.NOT_FOUND);

        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView accessDenied(HttpServletRequest request, Exception ex) {
        ModelAndView response = new ModelAndView("error/404");
        response.setStatus(HttpStatus.NOT_FOUND);

        // check for user auth
        if (authenticatedUserUtilities.isAuthenticated()) {
            log.warn("User : " + authenticatedUserUtilities.getCurrentUsername()
                    + " requested url that they do not have permission to " + request.getRequestURL());
        } else {
            log.warn("Unauthenticated user requested url that they do not have permission to " + request.getRequestURL());
        }

        log.warn(ex.getMessage());

        return response;
    }

    // Catch all other exceptions
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(HttpServletRequest request, Exception ex) {
        // Log exception message and stack trace
        log.warn("Error page exception : " + ex.getMessage(), ex);

        // view for general server errors (500 status)
        ModelAndView response = new ModelAndView("error/500");

        // If user is admin, request URL, exception message, and stack traces are added to the model
        if (authenticatedUserUtilities.isUserInRole("ADMIN")) {
            response.addObject("requestUrl", request.getRequestURI());
            response.addObject("message", ex.getMessage());

            // ExceptionUtils breaks down the stack trace into an array of strings
            String stackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));
            response.addObject("stackTrace", stackTrace);

            if (ex.getCause() != null) {
                response.addObject("rootCause", ExceptionUtils.getRootCause(ex));

                String rootTrace = getHTMLStackTrace(ExceptionUtils.getRootCauseStackTrace(ex));
                response.addObject("rootTrace", rootTrace);
            }
        }

        return response;
    }

    // Convert stack trace frames into HTML-friendly format
        // showing only frames related to this specific package
    private String getHTMLStackTrace(String[] stack) {
        StringBuffer result = new StringBuffer();
        for (String frame : stack) {
            // Change this to be your package name
            if (frame.contains("com.helord.Targhee_Sports_Rental")) {
                result.append(" &nbsp; &nbsp; &nbsp;" + frame.trim().substring(3) + "<br>\n");
            } else if (frame.contains("Caused by:")) {
                result.append("Caused By:<br>");
            }
        }
        return result.toString();
    }
}