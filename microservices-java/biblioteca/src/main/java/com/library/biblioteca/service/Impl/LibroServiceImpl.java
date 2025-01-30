package com.library.biblioteca.service.Impl;

import com.library.biblioteca.enums.EstadoLibro;
import com.library.biblioteca.model.Libro;
import com.library.biblioteca.repository.LibroRepository;
import com.library.biblioteca.service.LibroService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro registrarLibro(Libro libro) {
        //TODO
        /**
         * Completar el metodo de registro
         * el estado inicial del libro debe ser DISPONIBLE
         */
        if (libro.getEstado().equals(EstadoLibro.RESERVADO)) {
            throw new IllegalArgumentException("Libro no disponible");
        }
        Libro libroGuardado = libroRepository.save(libro);
        return libroGuardado;

    }

    @Override
    public List<Libro> obtenerTodosLosLibros() {
        //DO
        /**
         * Completar el metodo 
         */
        return libroRepository.findAll();
        
    }

    @Override
    public void eliminarLibro(Long id) {
        //DO
        /**
         * Completar el metodo
         */
        libroRepository.deleteById(id);
    }

    @Override
    public Libro actualizarLibro(Libro libro) {
        //DO
        /**
         * Completar el metodo
         */
        Libro libroAModificar = libroRepository.findByIsbn(libro.getIsbn());
        libroAModificar.setTitulo(libro.getTitulo());
        libroAModificar.setAutor(libro.getAutor());
        libroAModificar.setEstado(libro.getEstado());

        Libro libroActualizado = libroRepository.save(libroAModificar);
        return libroActualizado;
    }
}
