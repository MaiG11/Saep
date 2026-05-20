package com.padoca.estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class PrincipalController {

    @GetMapping("/principal")
    public String exibirPrincipal(HttpSession session){
        if (session.getAttribute("funcionario") == null){
            return "redirect:/login";
    }
        return "principal";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";

 
    }
    }
    

    
