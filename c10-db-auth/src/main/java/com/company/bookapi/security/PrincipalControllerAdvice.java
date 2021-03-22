package com.company.bookapi.security;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class PrincipalControllerAdvice {

    @ModelAttribute("currentUser")
    Principal principal(Principal principal) {
        return principal;
    }
}
