package net.openwebinars.customize.hibernatebasictype.dao;

import net.openwebinars.customize.hibernatebasictype.model.CuerpoCeleste;

import java.util.List;
import java.util.Optional;

public interface DaoCuerpoCeleste {

    public List<CuerpoCeleste> getAll();

    public Optional<CuerpoCeleste> getById(long id);

    public CuerpoCeleste save(CuerpoCeleste p);

    public void edit(CuerpoCeleste p);

    public void delete(long id);

}
