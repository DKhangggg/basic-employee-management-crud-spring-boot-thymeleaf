package com.luv2code.springboot.thyleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class AuthenticationController {
    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "authentication/login";
    }
}
