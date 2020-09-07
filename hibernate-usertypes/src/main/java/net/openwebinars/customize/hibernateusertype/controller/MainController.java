package net.openwebinars.customize.hibernateusertype.controller;

import lombok.RequiredArgsConstructor;
import net.openwebinars.customize.hibernateusertype.dao.DaoPersona;
import net.openwebinars.customize.hibernateusertype.model.Color;
import net.openwebinars.customize.hibernateusertype.model.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/persona")
@RequiredArgsConstructor
public class MainController {

    private final DaoPersona daoPersona;

    @GetMapping("/")
    public ResponseEntity<List<Persona>> obtenerTodas() {
        return daoPersona.getAll().isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(daoPersona.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable("id") long id) {
        Optional<Persona> result =  daoPersona.getById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/")
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        // Color aleatorio
        persona.setColorAsignado(Color.getRandomColor());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                daoPersona.save(persona));
    }


    @GetMapping("/color/{color}")
    public ResponseEntity<List<Persona>> obtenerPorColor(@PathVariable("color") String color) {

        try {
            Color c = Color.valueOf(color.toUpperCase());
            List<Persona> result = daoPersona.getByColor(c);
            if (result.isEmpty())
                return ResponseEntity.notFound().build();
            else
                return ResponseEntity.ok(result);

        } catch (IllegalArgumentException ex) {
            // Devolvemos bad request porque es un color no válido
            return ResponseEntity.badRequest().build();
        }
    }


}
