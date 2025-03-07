package com.egg.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.service.UsuarioServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/") // Acá es donde realizamos el mapeo
    public String index() {
        return "index.html"; // Acá es que retornamos con el método.
    }

    @GetMapping("/registrar") // Acá es donde realizamos el mapeo
    public String registrar() {
        return "registro.html"; // Acá es que retornamos con el método.
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
            @RequestParam String password2, ModelMap model) {

        try {
            usuarioServicio.registrar(nombre, email, password, password2);
            model.put("exito", "usuario registrado");
            return "index.html";
        } catch (MiException e) {
            // TODO Auto-generated catch block
            model.put("error", e.getMessage());
            return "registro.html";
        }

    }

    @GetMapping("/login") // Acá es donde realizamos el mapeo
    public String login() {
        return "login.html"; // Acá es que retornamos con el método.
    }
}
