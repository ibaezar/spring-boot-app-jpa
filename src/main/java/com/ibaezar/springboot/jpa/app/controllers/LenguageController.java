package com.ibaezar.springboot.jpa.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LenguageController {
    
    @GetMapping("/lenguage")
    public String lenguage(HttpServletRequest request){
        String lastUrl = request.getHeader("referer");
        return "redirect:".concat(lastUrl);
    }
}
