package com.egg.biblioteca.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.model.Autor;
import com.egg.biblioteca.service.AutorServicio;

@Controller
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar") // localhost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {
        try {
            autorServicio.crearAutor(nombre); // llamo a mi servicio para persistir
        } catch (MiException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_form.html";
        }
        return "index.html";

    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Autor> autores = autorServicio.listarAutores();
        modelo.addAttribute("autores", autores);
        return "autor_list.html";
    }
}
