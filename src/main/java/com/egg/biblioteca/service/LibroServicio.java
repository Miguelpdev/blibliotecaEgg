package com.egg.biblioteca.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, UUID idAutor, UUID idEditorial)
            throws MiException {
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
        }

        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setEjemplares(ejemplares);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
        }
    }

    private void validar(Long isbn, String titulo, Integer ejemplares, UUID idAutor, UUID idEditorial)
            throws MiException {

        if (isbn == null) {
            throw new MiException("el isbn no puede ser nulo"); //
        }
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("el titulo no puede ser nulo o estar vacio");
        }
        if (ejemplares == null) {
            throw new MiException("ejemplares no puede ser nulo");
        }
        if (idAutor == null) {
            throw new MiException("el Autor no puede ser nulo o estar vacio");
        }

        if (idEditorial == null) {
            throw new MiException("La Editorial no puede ser nula o estar vacia");
        }
    }

    @Transactional(readOnly = true)
    public Libro getOne(Long isbn) {
        return libroRepositorio.getReferenceById(isbn);
    }

}
