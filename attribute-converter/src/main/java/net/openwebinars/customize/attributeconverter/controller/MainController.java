package net.openwebinars.customize.attributeconverter.controller;

import lombok.RequiredArgsConstructor;
import net.openwebinars.customize.attributeconverter.dao.PersonaRepository;
import net.openwebinars.customize.attributeconverter.dto.CreatePersonaDto;
import net.openwebinars.customize.attributeconverter.model.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
@RequiredArgsConstructor
public class MainController {

    private final PersonaRepository personaRepository;

    @GetMapping("/")
    public ResponseEntity<List<Persona>> obtenerTodas() {
        return personaRepository.findAll().isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(personaRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable("id") long id) {
        Optional<Persona> result =  personaRepository.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/")
    public ResponseEntity<Persona> crearPersona(@RequestBody CreatePersonaDto createPersonaDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                personaRepository.save(createPersonaDto.toPersona()));
    }




}
