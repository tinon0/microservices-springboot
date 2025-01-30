package com.library.biblioteca.service.Impl;

import com.library.biblioteca.enums.EstadoLibro;
import com.library.biblioteca.model.Libro;
import com.library.biblioteca.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class LibroServiceImplTest {
    @Mock
    private LibroRepository libroRepository;
    @InjectMocks
    private LibroServiceImpl libroService;

    @Test
    void registrarLibro() {
        //GIVEN
        Libro libro = new Libro(1L, "isbn", "titulo", "autor", EstadoLibro.DISPONIBLE);
        //WHEN
        when(libroRepository.save(any(Libro.class))).thenAnswer(invocation -> invocation.getArgument(0));
        //THEN

        Libro respuesta = libroService.registrarLibro(libro);

        assertNotNull(respuesta);
        assertEquals(respuesta.getIsbn(), "isbn");
    }
    @Test
    void registrarLibro_Mal() {
        //GIVEN
        Libro libro = new Libro(1L, "isbn", "titulo", "autor", EstadoLibro.RESERVADO);
        //WHEN

        assertThrows(IllegalArgumentException.class, () -> {
            Libro respuesta = libroService.registrarLibro(libro);
        });
    }

    @Test
    void obtenerTodosLosLibros() {
        //GIVEN
        List<Libro> libroList = new ArrayList<>(List.of(
                new Libro(1L, "isbn", "titulo", "autor", EstadoLibro.DISPONIBLE),
                new Libro(1L, "isbn2", "titulo2", "autor2", EstadoLibro.DISPONIBLE)
        ));
        //WHEN
        when(libroRepository.findAll()).thenReturn(libroList);
        //THEN

        List<Libro> respuesta = libroService.obtenerTodosLosLibros();

        assertNotNull(respuesta);
        assertEquals(respuesta.size(), 2);
    }

//    @Test
//    void eliminarLibro() {
//    }

    @Test
    void actualizarLibro() {
        //GIVEN
        Libro libro = new Libro(1L, "isbn", "titulo", "autor", EstadoLibro.RESERVADO);
        Libro libroEditado = new Libro(1L, "isbn", "tituloNuevo", "autorNuevo", EstadoLibro.DISPONIBLE);

        //WHEN
        when(libroRepository.findByIsbn(anyString())).thenReturn(libro);
        when(libroRepository.save(any(Libro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //THEN
        Libro respuesta = libroService.actualizarLibro(libroEditado);

        assertNotNull(respuesta);
        assertEquals(respuesta.getAutor(), "autorNuevo");
    }
}