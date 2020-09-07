package net.openwebinars.customize.hibernatecompositeusertype.service;

import lombok.RequiredArgsConstructor;
import net.openwebinars.customize.hibernatecompositeusertype.controller.CreatePersonaDto;
import net.openwebinars.customize.hibernatecompositeusertype.dao.DaoPersona;
import net.openwebinars.customize.hibernatecompositeusertype.model.Direccion;
import net.openwebinars.customize.hibernatecompositeusertype.model.Persona;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final DaoPersona daoPersona;

    public Persona save(CreatePersonaDto createPersonaDto) {
        Persona persona = new Persona();
        persona.setNombre(createPersonaDto.getNombre());
        persona.setApellidos(createPersonaDto.getApellidos());

        Direccion direccion = new Direccion();
        direccion.setLinea1(createPersonaDto.getDireccionLinea1());
        direccion.setLinea2(createPersonaDto.getDireccionLinea2());
        direccion.setCodigoPostal(createPersonaDto.getCodigoPostal());
        direccion.setPoblacion(createPersonaDto.getPoblacion());
        direccion.setProvincia(createPersonaDto.getProvincia());

        persona.setDireccion(direccion);

        return daoPersona.save(persona);
    }

    public Optional<Persona> findById(long id) {
        return daoPersona.getById(id);
    }

    public List<Persona> findAll() {
        return daoPersona.getAll();
    }

    public List<Persona> findByDireccion(Direccion direccion) {
        return daoPersona.getByDireccion(direccion);

    }

    public List<Persona> findByPoblacion(String poblacion) {
        return daoPersona.getByPoblacion(poblacion);

    }

    // Resto de métodos

}
