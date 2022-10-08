package com.proyecto.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyecto.CategoriaRepository;
import com.proyecto.proyecto.RolRepository;

@CrossOrigin
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(value = "")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/nosotros")
    public String nosotros() {
        return "nosotros";
    }
}
