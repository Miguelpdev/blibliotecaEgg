package com.egg.biblioteca.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.model.Autor;
import com.egg.biblioteca.model.Editorial;
import com.egg.biblioteca.model.Libro;
import com.egg.biblioteca.repository.AutorRepositorio;
import com.egg.biblioteca.repository.EditorialRepositorio;
import com.egg.biblioteca.repository.LibroRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, UUID idAutor, UUID idEditorial)
            throws MiException {
        Libro libro = new Libro();
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        libro.setAlta(new Date());
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList<>();

        libros = libroRepositorio.findAll();
        return libros;
    }

}
