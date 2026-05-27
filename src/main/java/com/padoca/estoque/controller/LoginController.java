package com.padoca.estoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.padoca.estoque.model.Funcionario;
import com.padoca.estoque.repository.FuncionarioRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class LoginController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/login")
    public String exibirLogin(){
        return "login";   
    }

    @PostMapping("/login")
    public String realizaProcessamentoLogin(
        @RequestParam String login, 
        @RequestParam String senha, 
        HttpSession sessao, 
        Model model){

    {

        Funcionario fun = funcionarioRepository.findByLogin(login);
        if (fun != null && fun.getSenha().equals(senha)){
            sessao.setAttribute("funcionario", fun);
            return "redirect:/principal";
        }

        model.addAttribute("erro", "Login ou senha incorretos!");
        return "login";
     }
    
}}
