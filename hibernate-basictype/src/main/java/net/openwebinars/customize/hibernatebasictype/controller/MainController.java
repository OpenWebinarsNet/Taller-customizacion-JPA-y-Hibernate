package net.openwebinars.customize.hibernatebasictype.controller;

import lombok.RequiredArgsConstructor;
import net.openwebinars.customize.hibernatebasictype.dao.DaoCuerpoCeleste;
import net.openwebinars.customize.hibernatebasictype.model.CuerpoCeleste;
import net.openwebinars.customize.hibernatebasictype.utils.ConversorDto;
import net.openwebinars.customize.hibernatebasictype.utils.CuerpoCelesteGetDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cuerpoceleste")
@RequiredArgsConstructor
public class MainController {

    private final DaoCuerpoCeleste daoCuerpoCeleste;
    private final ConversorDto conversorDto;

    @GetMapping("/")
    public ResponseEntity<List<CuerpoCelesteGetDto>> obtenerTodos() {
        return daoCuerpoCeleste.getAll().isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(daoCuerpoCeleste.getAll().stream().map(conversorDto::fromCuerpoCelesteToDto).collect(Collectors.toList()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<CuerpoCelesteGetDto> obtenerPorId(@PathVariable("id") long id) {
        Optional<CuerpoCeleste> result =  daoCuerpoCeleste.getById(id);
        return result.map(c -> ResponseEntity.ok(conversorDto.fromCuerpoCelesteToDto(c))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/")
    public ResponseEntity<CuerpoCeleste> crearCuerpoCeleste(@RequestBody CuerpoCeleste cuerpoCeleste) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                daoCuerpoCeleste.save(cuerpoCeleste));
    }




}
