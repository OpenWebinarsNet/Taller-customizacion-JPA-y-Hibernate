package net.openwebinars.customize.hibernatecompositeusertype.controller;

import lombok.RequiredArgsConstructor;
import net.openwebinars.customize.hibernatecompositeusertype.dao.DaoPersona;
import net.openwebinars.customize.hibernatecompositeusertype.model.Direccion;
import net.openwebinars.customize.hibernatecompositeusertype.model.Persona;
import net.openwebinars.customize.hibernatecompositeusertype.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
@RequiredArgsConstructor
public class MainController {

    private final PersonaService personaService;

    @GetMapping("/")
    public ResponseEntity<List<Persona>> obtenerTodas() {
        return personaService.findAll().isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(personaService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable("id") long id) {
        Optional<Persona> result =  personaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/direccion")
    public ResponseEntity<List<Persona>> obtenerPorDireccion(@RequestBody Direccion direccion) {
        List<Persona> result =  personaService.findByDireccion(direccion);
        return result.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(result);

    }

    @GetMapping("/poblacion/{nombre}")
    public ResponseEntity<List<Persona>> obtenerPorDireccion(@PathVariable("nombre") String poblacion) {
        List<Persona> result =  personaService.findByPoblacion(poblacion);
        return result.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(result);

    }


    @PostMapping("/")
    public ResponseEntity<Persona> crearPersona(@RequestBody CreatePersonaDto createPersonaDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                personaService.save(createPersonaDto));
    }





}
