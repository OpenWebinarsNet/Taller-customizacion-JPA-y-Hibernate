package net.openwebinars.customize.hibernatecompositeusertype.dao;

import net.openwebinars.customize.hibernatecompositeusertype.model.Direccion;
import net.openwebinars.customize.hibernatecompositeusertype.model.Persona;

import java.util.List;
import java.util.Optional;

public interface DaoPersona {

    public List<Persona> getAll();

    public Optional<Persona> getById(long id);

    public Persona save(Persona p);

    public void edit(Persona p);

    public void delete(long id);

    public List<Persona> getByDireccion(Direccion direccion);

    public List<Persona> getByPoblacion(String poblacion);


}
