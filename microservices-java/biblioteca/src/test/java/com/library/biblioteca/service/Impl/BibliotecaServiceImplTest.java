package com.library.biblioteca.service.Impl;

import com.library.biblioteca.dto.ClienteDTO;
import com.library.biblioteca.enums.EstadoLibro;
import com.library.biblioteca.model.Libro;
import com.library.biblioteca.model.Registro;
import com.library.biblioteca.repository.LibroRepository;
import com.library.biblioteca.repository.RegistroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BibliotecaServiceImplTest {
    @Mock
    private RegistroRepository registroRepository;
    @Mock
    private LibroRepository libroRepository;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private BibliotecaServiceImpl bibliotecaService;
    @Test
    void alquilarLibros() {
        //GIVEN
        ClienteDTO clienteDTO = new ClienteDTO(1L, "nombre", "domicilio");
        Libro libro = new Libro(1L, "isbn", "titulo", "autor", EstadoLibro.DISPONIBLE);
        //WHEN
        when(libroRepository.findByIsbn(anyString())).thenReturn(libro);
        when(libroRepository.save(any(Libro.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(restTemplate.getForObject(anyString(), eq(ClienteDTO.class))).thenReturn(clienteDTO);
        when(registroRepository.save(any(Registro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //THEN

        Registro respuesta = bibliotecaService.alquilarLibros(List.of("isbn"));

        assertNotNull(respuesta);
        assertEquals(respuesta.getClienteId(), 1L);
        assertEquals(respuesta.getLibrosReservados().size(), 1);
        assertNull(respuesta.getTotal());
    }
    @Test
    void calcularTotal() {
        BigDecimal respuesta = (BigDecimal) ReflectionTestUtils.invokeMethod(bibliotecaService, "calcularCostoAlquiler", LocalDate.now().minusDays(6), LocalDate.now(), 5);
        assertEquals(respuesta, BigDecimal.valueOf(780));
    }

    @Test
    void devolverLibros() {
        //GIVEN
        List<Libro> libroList = new ArrayList<>(List.of(new Libro(1L, "isbn", "titulo", "autor", EstadoLibro.DISPONIBLE)));
        Registro registro = new Registro(1L, 1L, "nombre", LocalDate.now().minusDays(1), null, libroList, null);
        //WHEN
        when(registroRepository.findById(anyLong())).thenReturn(Optional.of(registro));
        when(libroRepository.save(any(Libro.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(registroRepository.save(any(Registro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //THEN

        Registro respuesta = bibliotecaService.devolverLibros(1L);

        assertNotNull(respuesta);
        assertEquals(BigDecimal.valueOf(100), respuesta.getTotal());
        assertNotNull(respuesta.getFechaReserva());
        System.out.println(respuesta.getTotal());
    }

    @Test
    void verTodosLosAlquileres() {
        //GIVEN
        List<Registro> registroList = new ArrayList<>(List.of(
                new Registro(1L, 1L, "nombre", LocalDate.now().minusDays(1), null, null, null),
                new Registro(2L, 2L, "nombre2", LocalDate.now().minusDays(1), null, null, null)
        ));
        //WHEN
        when(registroRepository.findAll()).thenReturn(registroList);

        //THEN
        List<Registro> respuesta = bibliotecaService.verTodosLosAlquileres();

        assertEquals(respuesta.size(), 2);
        assertEquals(respuesta.get(0).getNombreCliente(), "nombre");
        assertEquals(respuesta.get(1).getNombreCliente(), "nombre2");
    }

    @Test
    void informeSemanal() {
        //GIVEN
        List<Registro> registrosSemanales = new ArrayList<>(List.of(
                new Registro(1L, 1L, "nombre", LocalDate.now().minusDays(1), null, null, null),
                new Registro(2L, 2L, "nombre2", LocalDate.now().minusDays(1), null, null, null
        )));
        //WHEN
        when(registroRepository.obtenerRegistrosSemana(LocalDate.now(), LocalDate.now().plusDays(7))).thenReturn(registrosSemanales);
        //THEN
        List<Registro> respuesta = bibliotecaService.informeSemanal(LocalDate.now());

        assertNotNull(respuesta);
        assertEquals(2, respuesta.size());
        assertEquals("nombre2", respuesta.get(1).getNombreCliente());

    }

    @Test
    void informeLibrosMasAlquilados() {
        // GIVEN
        List<Object[]> registrosTop = new ArrayList<>();
        registrosTop.add(new Object[] { "LIBROTOP1", 10L });
        registrosTop.add(new Object[] { "LIBROTOP2", 8L });

        // WHEN
        when(registroRepository.obtenerLibrosMasAlquilados()).thenReturn(registrosTop);

        // THEN
        List<Object[]> respuesta = bibliotecaService.informeLibrosMasAlquilados();

        assertNotNull(respuesta);
        assertEquals(2, respuesta.size());
        assertEquals("LIBROTOP1", respuesta.get(0)[0]);
        assertEquals(10L, respuesta.get(0)[1]);
        assertEquals("LIBROTOP2", respuesta.get(1)[0]);
        assertEquals(8L, respuesta.get(1)[1]);
    }

}