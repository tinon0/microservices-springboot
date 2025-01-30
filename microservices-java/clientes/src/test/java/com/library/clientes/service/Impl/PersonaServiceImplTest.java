package com.library.clientes.service.Impl;

import com.library.clientes.model.Persona;
import com.library.clientes.repository.PersonaRepository;
import com.library.clientes.service.PersonaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonaServiceImplTest {

    @Mock
    private PersonaRepository personaRepository;
    @InjectMocks
    private PersonaServiceImpl personaService;


    @Test
    void crearPersona() {
        //GIVEN
        Persona persona = new Persona(1L, "nombre", "domicilio");
        //WHEN
        when(personaRepository.save(any(Persona.class))).thenReturn(persona);
        //THEN

        Persona respuesta = personaService.crearPersona(persona);

        assertNotNull(respuesta);
        assertEquals(respuesta.getId(), persona.getId());
        assertEquals(respuesta.getNombre(), persona.getNombre());
        assertEquals(respuesta.getDomicilio(), persona.getDomicilio());
        System.out.println(respuesta);
    }

    @Test
    void obtenerPersonaPorId() {
        //GIVEN
        Persona persona = new Persona(1L, "nombre", "domicilio");
        //WHEN
        when(personaRepository.findById(anyLong())).thenReturn(Optional.of(persona));
        //THEN

        Persona respuesta = personaService.obtenerPersonaPorId(1L).get();

        assertNotNull(respuesta);
        assertEquals(respuesta.getId(), persona.getId());
        assertEquals(respuesta.getNombre(), persona.getNombre());
        assertEquals(respuesta.getDomicilio(), persona.getDomicilio());
        System.out.println(respuesta);
    }

    @Test
    void obtenerPersonaAlAzar() {
        //GIVEN
        List<Persona> personaList = new ArrayList<>(List.of(
                new Persona(1L, "nombre", "domicilio"),
                new Persona(2L, "nombre2", "domicilio2"),
                new Persona(3L, "nombre3", "domicilio3")
        ));
        //WHEN
        when(personaRepository.findAll()).thenReturn(personaList);
        //THEN

        Persona respuesta = personaService.obtenerPersonaAlAzar();
        Persona respuesta2 = personaService.obtenerPersonaAlAzar();
        Persona respuesta3 = personaService.obtenerPersonaAlAzar();

        //assertNotEquals(respuesta, respuesta2);
        assertNotEquals(respuesta2, respuesta3);
        assertNotEquals(respuesta, respuesta3);
    }

    @Test
    void obtenerTodasLasPersonas() {
        //GIVEN
        List<Persona> personaList = new ArrayList<>(List.of(
                new Persona(1L, "nombre", "domicilio"),
                new Persona(2L, "nombre2", "domicilio2"),
                new Persona(3L, "nombre3", "domicilio3")
        ));
        //WHEN
        when(personaRepository.findAll()).thenReturn(personaList);
        //THEN

        List<Persona> respuesta = personaService.obtenerTodasLasPersonas();

        assertEquals(respuesta.size(), 3);
        assertEquals(respuesta.get(0).getDomicilio(), "domicilio");
    }

    @Test
    void actualizarPersona() {
        //GIVEN
        Persona persona = new Persona(1L, "nombre", "domicilio");
        Persona personaParaActualizar = new Persona(1L, "Tino", "Cordoba");
        //WHEN
        when(personaRepository.findById(anyLong())).thenReturn(Optional.of(persona));
        when(personaRepository.save(any(Persona.class))).thenAnswer(invocation -> invocation.getArgument(0));
        //THEN

        Persona respuesta = personaService.actualizarPersona(1L, personaParaActualizar);

        assertNotNull(respuesta);
        assertEquals(respuesta.getId(), personaParaActualizar.getId());
        assertEquals(respuesta.getNombre(), personaParaActualizar.getNombre());
        assertEquals(respuesta.getDomicilio(), personaParaActualizar.getDomicilio());
        System.out.println(respuesta);
    }

}