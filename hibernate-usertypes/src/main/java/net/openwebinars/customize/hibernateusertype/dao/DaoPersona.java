package net.openwebinars.customize.hibernateusertype.dao;

import net.openwebinars.customize.hibernateusertype.model.Color;
import net.openwebinars.customize.hibernateusertype.model.Persona;

import java.util.List;
import java.util.Optional;

public interface DaoPersona {

    public List<Persona> getAll();

    public Optional<Persona> getById(long id);

    public Persona save(Persona p);

    public void edit(Persona p);

    public void delete(long id);

    public List<Persona> getByColor(Color color);


}
